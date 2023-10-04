package edu.laplateforme.models.maze_generators.utilities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.Stack;
import java.util.Map.Entry;


public class Room {
    public final int roomId;
    private static int id = 0;
    private final static Map<String, String> neighborRelation = new HashMap<>() {{
        put("North", "South");
        put("South", "North");
        put("West", "East");
        put("East", "West");
    }};

    public boolean visited = false;
    private int x, y, roomIdX, roomIdY;
    private Map<String, int[]> cardinalWalls, cardinalNeighbors;
    private List<int[]> paths;
    
    public static Room[][] roomRegister;
    private static int xId, yId, xMax, yMax, columns, rows;
    


    public Room(int x, int y, int mazeMaxX, int mazeMaxY, int nColumns, int nRows) {
        this.roomId = id;
        id++;
        this.x = x;
        this.y = y;
        this.paths = new ArrayList<>();
        RoomRegistering(mazeMaxX, mazeMaxY, nColumns, nRows);

        this.SetAvailableCardinals();
    }



    
    
    public void CreatePaths(Room nextRoom) {
        //System.out.println("next roomId: " + nextRoom.GetId() + ", this id: " + this.GetId());
        for (Room neighbor : this.GetNeighbors()) {
//            if (neighbor != null) {
//                System.out.println("next roomId: " + nextRoom.GetId() + ", neighbor id: " + neighbor.GetId());
//            }
            if (neighbor == nextRoom) {
                this.SetPath(nextRoom);
            }
        }
    }



    public void RandomPaths() {
        Random rand = new Random();
        List<String> availableCardinals = new ArrayList<String>();
        List<String> pathsDressed = new ArrayList<String>();

        for (String string : this.cardinalWalls.keySet()) {
            if (this.cardinalWalls.get(string) != null) {
                availableCardinals.add(string);
            } 
        }

        for (int i = 0; i <= rand.nextInt(1, availableCardinals.size()); i++) {
            pathsDressed.add(availableCardinals.get(i));
        }

        //System.out.println("number of available cardinals: " + availableCardinals.size());
        for (String paths : pathsDressed) {
            this.paths.add(this.cardinalWalls.get(paths));
        }
    }



    // public List<Room> GetUnvisitedNeighbors() {
    //     List<Room> unvisitedNeighbors = new ArrayList<>();
    //     for (int[] neighborId : this.cardinalNeighbors.values()) {
    //         if (neighborId != null && roomRegister[neighborId[1]][neighborId[0]] != null && !roomRegister[neighborId[1]][neighborId[0]].visited) {
    //             unvisitedNeighbors.add(roomRegister[neighborId[1]][neighborId[0]]);
    //         }
    //     }
    //     return unvisitedNeighbors;
    // }

    
    
    public List<int[]> GetPaths() {
        return this.paths;
    }
    
    public Map<String, int[]> GetCardinals() {
        return this.cardinalWalls;
    }
    
    public List<Room> GetNeighbors() {
        List<Room> neighbors = new ArrayList<>();
        for (int[] neighborId : this.cardinalNeighbors.values()) {
            if (neighborId != null) {
                neighbors.add(roomRegister[neighborId[1]][neighborId[0]]);
            }
        }
        return neighbors;
    }

    public static int GetMazeWidth() { return xMax; }
    public static int GetMazeHeight() { return yMax; }
    public int GetId() { return this.roomId; }
    public int GetX() { return this.x; }
    public int GetY() { return this.y; }
    public int GetIdX() { return this.roomIdX; }
    public int GetIdY() { return this.roomIdY; }


    // public static void DebugRoomRegister() {
    //     int n = 1;
    //     for (int i = 0; i < roomRegister.length; i++) {
    //         for (int j = 0; j < roomRegister[i].length; j++) {
    //             System.out.println(roomRegister[i][j] + ", room number " + n);
    //             n++;
    //         }
    //     }
    // }

    
    
    private void SetPath(Room nextRoom) {
        String wallCardinal = "";
        for (Entry<String, int[]> cardinalNeighbor : this.cardinalNeighbors.entrySet()) {
            int[] neighborId = cardinalNeighbor.getValue();
            if (neighborId != null && roomRegister[neighborId[1]][neighborId[0]] == nextRoom) {
                wallCardinal = cardinalNeighbor.getKey();
            }
        }

        nextRoom.paths.add(nextRoom.cardinalWalls.get(neighborRelation.get(wallCardinal)));
        this.paths.add(this.cardinalWalls.get(wallCardinal));
    }



    private void SetAvailableCardinals() {
        this.cardinalWalls = new HashMap<String, int[]>() {{
            put("North", null);
            put("West", null);
            put("East", null);
            put("South", null);
        }};
        this.cardinalNeighbors = new HashMap<String, int[]>() {{
            put("North", null);
            put("West", null);
            put("East", null);
            put("South", null);
        }};

        //If the room is at the bottom left 
        if (this.x == 1 && this.y == yMax) {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
        } 
        //If the room is in the upper right
        else if (this.x == xMax && this.y == 1) {
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        } 
        //If the room is the end-goal
        else if (this.x == xMax && this.y == yMax) {
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
        } 
        //If the room is the entrance
        else if (this.x == 1 && this.y == 1) {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        } 
        //If the room is on the right extremity of the maze
        else if (this.x == xMax) {
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        } 
        //If the room is at the bottom of the maze
        else if (this.y == yMax) {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
        } 
        //If the room is on the left extremity of the maze
        else if (this.x == 1) {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        } 
        //If the room is on the upper extremity of the maze
        else if (this.y == 1) {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        } 
        //Default available walls
        else {
            this.cardinalWalls.put("East", new int[]{this.x + 1, this.y});
            this.cardinalWalls.put("West", new int[]{this.x - 1, this.y});
            this.cardinalWalls.put("North", new int[]{this.x, this.y - 1});
            this.cardinalWalls.put("South", new int[]{this.x, this.y + 1});
            this.cardinalNeighbors.put("East", new int[]{this.roomIdX + 1, this.roomIdY});
            this.cardinalNeighbors.put("West", new int[]{this.roomIdX - 1, this.roomIdY});
            this.cardinalNeighbors.put("North", new int[]{this.roomIdX, this.roomIdY - 1});
            this.cardinalNeighbors.put("South", new int[]{this.roomIdX, this.roomIdY + 1});
        }
    }


    private void RoomRegistering(int mazeMaxX, int mazeMaxY, int nColumns, int nRows) {
        if (roomRegister == null) {
            xMax = mazeMaxX - 2; yMax = mazeMaxY - 2;
            rows = nRows; columns = nColumns;
            roomRegister = new Room[rows][columns];

            xId = 0; yId = 0;
            roomRegister[yId][xId] = this;
        }
        else if (xId != roomRegister[yId].length - 1) {
            roomRegister[yId][xId] = this;
            xId++;
        }
        else if (yId != roomRegister.length - 1) {
            roomRegister[yId][xId] = this;
            xId = 0; yId++;
        }
        this.roomIdX = xId; this.roomIdY = yId;
    }
}
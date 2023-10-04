package edu.laplateforme.models.maze_generators;


import java.util.*;

import edu.laplateforme.models.maze_generators.utilities.*;


public class SimplePerfectMazeGenerator implements MazeGenerator {
    private int mazeX, mazeY;
    private Random rand = new Random();
    private List<Room> allRooms;
    private Set<Room> resultRooms, currentSet;
    private Room currentRoom;

    public SimplePerfectMazeGenerator(List<Room> rooms) {
        this.allRooms = rooms;
        this.currentRoom = this.allRooms.get(0);
        this.resultRooms = new HashSet<>();
        this.currentSet = new HashSet<>();
        this.mazeX = Room.GetMazeWidth();
        this.mazeY = Room.GetMazeHeight();
    }

    

    @Override
    public Set<Room> MazeGeneration()  throws Exception {
        boolean firstRow = true;

        for (Room room : this.allRooms) {
            System.out.println(room.GetId());
        }

        while(MazeNotDone()) {
            Map<String, int[]> roomCardinals = this.currentRoom.GetCardinals();
            //System.out.println("Room id: " + this.currentRoom.GetId() + ", room index: " + this.allRooms.indexOf(this.currentRoom));
            //System.out.println(roomCardinals.get("East") != null);
            //System.out.println(firstRow);

            if (firstRow && roomCardinals.get("East") != null) {
                //System.out.println("firstrow");
                this.currentRoom.CreatePaths(this.allRooms.get(Room.roomRegister[this.currentRoom.GetIdY()][this.currentRoom.GetIdX() + 1].GetId()));
            }
            else if (!firstRow && roomCardinals.get("East") != null) {
                //System.out.println("not firstrow");
                SidewindAlgo();
            }

            if (this.currentRoom.GetId() == this.mazeX) {
                firstRow = false;
            }

            this.resultRooms.add(this.currentRoom);
            this.currentRoom = (this.currentRoom.GetId() + 1 != this.allRooms.size()) ? this.allRooms.get(this.currentRoom.GetId() + 1) : null;
        }

        return this.resultRooms;
    }


    @Override
    public boolean MazeNotDone() {
        return this.allRooms.size() != this.resultRooms.size();

    }

    @Override
    public List<Room> GetNeighborsDetails() {
        List<Room> visitedNeighbors = new ArrayList<>();
        for (Room neighbor : currentRoom.GetNeighbors()) {
            if (!neighbor.visited) {
                visitedNeighbors.add(neighbor);
            }
        }
        return visitedNeighbors;
    }


    private void SidewindAlgo() {
        List<Room> corridorSet = new ArrayList<>(this.currentSet);

        if (rand.nextBoolean()) {
            this.currentRoom.CreatePaths(this.allRooms.get(this.currentRoom.GetId()));
            this.currentSet.add(this.currentRoom);
        }
        else if (!corridorSet.isEmpty()) {
            int index = corridorSet.size() == 1 ? 0 : rand.nextInt(corridorSet.size());
            Room randomRoomFromSet = corridorSet.get(index);
            randomRoomFromSet.CreatePaths(this.allRooms.get(Room.roomRegister[randomRoomFromSet.GetIdY() - 1][randomRoomFromSet.GetIdX()].GetId()));
            this.currentSet.clear();
        }
    }
}

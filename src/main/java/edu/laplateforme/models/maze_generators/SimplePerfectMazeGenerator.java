package edu.laplateforme.models.maze_generators;


import java.util.*;

import edu.laplateforme.models.maze_generators.utilities.*;


public class SimplePerfectMazeGenerator implements MazeGenerator {
    private int mazeX, mazeY, cooridorLength, currentCorridorLength;
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
        this.currentCorridorLength = 1;
        this.cooridorLength = rand.nextInt(this.mazeX - 1) + 1;
    }

    

    @Override
    public Set<Room> MazeGeneration() {
        for (int eastId = 1; eastId < this.allRooms.size(); eastId++) {
            Map<String, int[]> roomCardinals = this.currentRoom.GetCardinals();
            List<Room> corridorSet = new ArrayList<>(this.currentSet);

            if (roomCardinals.get("East") != null) {
                if ((eastId - 1) < this.mazeX) {
                    this.currentRoom.CreatePaths(this.allRooms.get(eastId));
                }
                else {
                    SidewindAlgo(eastId);
                }
            }

            this.resultRooms.add(this.currentRoom);
            this.currentRoom = this.allRooms.get(eastId);
        }
//        boolean firstRow = true;
//
//        while(MazeNotDone()) {
//            Map<String, int[]> roomCardinals = this.currentRoom.GetCardinals();
//
//            if (firstRow && roomCardinals.get("East") != null) {
//                this.currentRoom.CreatePaths(Room.roomRegister[this.currentRoom.GetIdY()][this.currentRoom.GetIdX() + 1]));
//            }
//            else if (!firstRow && roomCardinals.get("East") != null) {
//                SidewindAlgo();
//            }
//
//            if (this.currentRoom.GetId() == this.mazeX) {
//                firstRow = false;
//            }
//
//            this.resultRooms.add(this.currentRoom);
//            this.currentRoom = (this.currentRoom.GetId() + 1 != this.allRooms.size()) ? this.allRooms.get(this.currentRoom.GetId() + 1) : null;
//        }

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


    private void SidewindAlgo(int eastId) {
        List<Room> corridorSet = new ArrayList<>(this.currentSet);
        System.out.println("current corridor: " + this.currentCorridorLength + ", random corridor length: " + this.cooridorLength);

//        if (this.cooridorLength != this.currentCorridorLength) {
//            this.currentRoom.CreatePaths(this.allRooms.get(eastId));
//            this.currentSet.add(this.currentRoom);
//            this.currentCorridorLength++;
//        }
//        else {
//            Room randomRoomFromSet = corridorSet.get(0);
//            randomRoomFromSet.CreatePaths(Room.roomRegister[randomRoomFromSet.GetIdY() - 1][randomRoomFromSet.GetIdX()]);
//            this.currentCorridorLength = 1;
//            this.cooridorLength = rand.nextInt(this.mazeX - corridorSet.size());
//            this.currentSet.clear();
//        }
    }
}

package edu.laplateforme.models.maze_generators;


import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import edu.laplateforme.models.maze_generators.utilities.*;


public class OptimizedMazeGenerator implements MazeGenerator {
    private boolean isImperfect;
    private List<Room> allRooms;
    private Set<Room> resultRooms;
    private Room currentRoom;

    public OptimizedMazeGenerator(List<Room> rooms, String complexity) {
        if (complexity.toLowerCase().equals("imperfect")) {
            this.isImperfect = true;
        }
        else if (complexity.toLowerCase().equals("perfect")) {
            this.isImperfect = false;
        }
        this.allRooms = rooms;
    }

    

    @Override
    public Set<Room> MazeGeneration() {
        while(MazeNotDone()) {
            
        }
        return this.resultRooms;
    }



    @Override
    public boolean MazeNotDone() {
        for (Room room : this.allRooms) {
            if (!room.visited) {
                return true;
            }
        }
        return false;
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
}

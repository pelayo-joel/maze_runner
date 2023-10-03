package edu.laplateforme.models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Set;

import edu.laplateforme.models.maze_generators.utilities.*;


public class SimplePerfectMazeGenerator implements MazeGenerator {
    private List<Room> allRooms;
    private Set<Room> rooms;
    private Room currentRoom;

    public SimplePerfectMazeGenerator(List<Room> rooms) {
        this.allRooms = rooms;
    }

    

    @Override
    public Set<Room> MazeGeneration() {
        while(MazeNotDone()) {
            
        }
        return this.rooms;
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

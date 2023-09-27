package models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.maze_generators.utilities.*;


public class SimplePerfectMazeGenerator implements MazeGenerator {
    private List<Room> maze;

    public SimplePerfectMazeGenerator(List<Room> rooms) {
        this.maze = rooms;
    }

    @Override
    public boolean MazeDone() {
        for (Room room : maze) {
            if (room.visited) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Room> RecursiveBackTracking(List<Room> allRooms) {
        while(!MazeDone()) {
            
        }
        return this.maze;
    }
}

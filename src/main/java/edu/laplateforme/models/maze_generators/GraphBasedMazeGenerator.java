package edu.laplateforme.models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import edu.laplateforme.models.maze_generators.utilities.*;


public class GraphBasedMazeGenerator implements MazeGenerator {
    private boolean backtracking = false, isImperfect;
    private List<Room> allRooms;
    private Set<Room> resultRooms;
    private Room currentRoom;
    private Stack<Room> currentPath = new Stack<>();

    private Random rand = new Random();

    public GraphBasedMazeGenerator(List<Room> rooms, String complexity) {
        if (complexity.equalsIgnoreCase("imperfect")) {
            this.isImperfect = true;
        }
        else if (complexity.equalsIgnoreCase("perfect")) {
            this.isImperfect = false;
        }
        
        this.allRooms = rooms;
        this.resultRooms = new HashSet<Room>();
        this.currentRoom = this.allRooms.get(0);
        this.currentRoom.visited = true;
        this.currentPath.push(this.currentRoom);
    }




    @Override
    public Set<Room> MazeGeneration() {
        boolean deadEnd = false;

        while(!this.currentPath.isEmpty()) {
            this.currentRoom = this.currentPath.pop();
            List<Room> unvisitedNeighbors = GetNeighborsDetails();

            if (!backtracking) {
                this.resultRooms.add(this.currentRoom);
            }

            if (!unvisitedNeighbors.isEmpty()) {
                int index = unvisitedNeighbors.size() == 1 ? 0 : rand.nextInt(unvisitedNeighbors.size());
                Room neighbor = unvisitedNeighbors.get(index);

                this.currentPath.push(this.currentRoom);
                this.currentRoom.CreatePaths(neighbor);

                neighbor.visited = true;
                this.currentPath.push(neighbor);
                backtracking = false;
            } else if (this.isImperfect && backtracking && deadEnd) {
                List<Room> neighbors = this.currentRoom.GetNeighbors();
                int index = neighbors.size() == 1 ? 0 : rand.nextInt(neighbors.size());

                this.currentRoom.CreatePaths(neighbors.get(index));
                deadEnd = false;
            }
            else {
                backtracking = true;
                deadEnd = true;
            }
        }
        return this.resultRooms;
    }


    @Override
    public boolean MazeNotDone() {
        return this.allRooms.size() != this.resultRooms.size();
    }

    @Override
    public List<Room> GetNeighborsDetails() {
        List<Room> unvisitedNeighbors = new ArrayList<>(), neighbors = this.currentRoom.GetNeighbors();;
        for (Room neighbor : neighbors) {
            if (neighbor != null && !neighbor.visited) {
                unvisitedNeighbors.add(neighbor);
            }
        }
        return unvisitedNeighbors;
    }
}

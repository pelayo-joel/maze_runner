package edu.laplateforme.models.maze_generators;


import java.util.*;

import edu.laplateforme.models.maze_generators.utilities.*;


public class OptimizedMazeGenerator implements MazeGenerator {
    private boolean backtracking = false, isImperfect;
    private long executionTime;
    private List<Room> allRooms;
    private Set<Room> resultRooms;
    private Room currentRoom;
    private Stack<Room> currentPath = new Stack<>();

    private Random rand = new Random();

    public OptimizedMazeGenerator(List<Room> rooms, String complexity) {
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
        long start = System.nanoTime();

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

        this.executionTime = System.nanoTime() - start;
        System.out.println("Labyrinthe généré en 0," + (this.executionTime / 100000) + "ms");
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
}

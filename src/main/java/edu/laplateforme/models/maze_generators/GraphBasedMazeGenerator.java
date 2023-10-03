package edu.laplateforme.models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import edu.laplateforme.models.maze_generators.utilities.*;


public class GraphBasedMazeGenerator implements MazeGenerator {
    private boolean isImperfect;
    private List<Room> allRooms;
    private Set<Room> rooms;
    private Room currentRoom;
    private Stack<Room> currentPath = new Stack<>();

    private Random rand = new Random();

    public GraphBasedMazeGenerator(List<Room> rooms, String complexity) {
        if (complexity.toLowerCase().equals("imperfect")) {
            this.isImperfect = true;
        }
        else if (complexity.toLowerCase().equals("perfect")) {
            this.isImperfect = false;
        }
        
        this.allRooms = rooms;
        this.rooms = new HashSet<Room>();
        this.currentRoom = this.allRooms.get(0);
        this.currentRoom.visited = true;
    }




    @Override
    public Set<Room> MazeGeneration() {
        while(MazeNotDone()) {
            List<Room> unvisitedNeighbors = GetNeighborsDetails();
            //System.out.println(unvisitedNeighbors.size());
            System.out.println("result set size: " + this.rooms.size() + ", n unvisited neighbors: " + unvisitedNeighbors.size() + ", current path n room: " + this.currentPath.size());

            if (!unvisitedNeighbors.isEmpty()) {
                int index = rand.nextInt(unvisitedNeighbors.size());
                Room neighbor = unvisitedNeighbors.get(index);

                this.currentPath.push(this.currentRoom);
                this.currentRoom.CreatePaths(neighbor);

                this.rooms.add(this.currentRoom);
                this.currentRoom = neighbor;
                this.currentRoom.visited = true;
                //System.out.println("result set size: " + this.rooms.size() + ", n unvisited neighbors: " + unvisitedNeighbors.size() + ", current path n room: " + this.currentPath.size());
            } 
            else if (!this.currentPath.isEmpty()) {
                this.currentRoom = this.currentPath.pop();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
                // TODO: handle exception
            }
        }
        return this.rooms;
    }


    @Override
    public boolean MazeNotDone() {
        //System.out.println("allRooms size: " + this.allRooms.size() + ", treatedRooms size: " + this.rooms.size());
        return this.allRooms.size() != this.rooms.size();
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

package edu.laplateforme.models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import edu.laplateforme.models.maze_generators.utilities.*;


public class SimpleImperfectMazeGenerator implements MazeGenerator {
    private List<Room> allRooms;
    private Set<Room> rooms;
    private Room currentRoom;

    public SimpleImperfectMazeGenerator(List<Room> rooms) {
        this.allRooms = rooms;
        this.rooms = new HashSet<Room>();
    }

    

    @Override
    public Set<Room> MazeGeneration() {
        Random rand = new Random();

        for (Room room : this.allRooms) {
            this.currentRoom = room;
            this.currentRoom.RandomPaths();
            this.currentRoom.visited = true;

            List<Room> visitedNeighbors = GetNeighborsDetails();

            if (visitedNeighbors.size() != 0 && rand.nextInt(10) > 2) {
                int randomNeighborIndex = rand.nextInt(visitedNeighbors.size());
                room.CreatePaths(visitedNeighbors.get(randomNeighborIndex));
            }

            this.rooms.add(this.currentRoom);
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
        List<Room> neighborsDetails = new ArrayList<>(), neighbors = this.currentRoom.GetNeighbors();
        for (Room neighbor : neighbors) {
            if (neighbor != null && neighbor.visited) {
                neighborsDetails.add(neighbor);
            }
        }
        return neighborsDetails;
    }
 }
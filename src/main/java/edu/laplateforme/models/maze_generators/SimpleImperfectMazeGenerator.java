package edu.laplateforme.models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import edu.laplateforme.models.maze_generators.utilities.*;


public class SimpleImperfectMazeGenerator implements MazeGenerator {
    private List<Room> allRooms;
    private Set<Room> resultRooms;
    private Room currentRoom;

    public SimpleImperfectMazeGenerator(List<Room> rooms) {
        this.allRooms = rooms;
        this.resultRooms = new HashSet<Room>();
    }

    

    @Override
    public Set<Room> MazeGeneration()  throws Exception {
        Random rand = new Random();

        for (Room room : this.allRooms) {
            this.currentRoom = room;
            this.currentRoom.RandomPaths();
            this.currentRoom.visited = true;

            List<Room> visitedNeighbors = GetNeighborsDetails();

            if (!visitedNeighbors.isEmpty() && rand.nextInt(10) > 2) {
                int randomNeighborIndex = visitedNeighbors.size() == 1 ? 0 : rand.nextInt(visitedNeighbors.size());
                room.CreatePaths(visitedNeighbors.get(randomNeighborIndex));
            }

            this.resultRooms.add(this.currentRoom);
        }
        return this.resultRooms;
    }



    @Override
    public boolean MazeNotDone() {
        return this.allRooms.size() == this.resultRooms.size();
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
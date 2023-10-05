package edu.laplateforme.models.maze_generators.utilities;


import java.util.List;
import java.util.Set;


public interface MazeGenerator {

    public List<Room> GetNeighborsDetails();

    public boolean MazeNotDone();
    
    public Set<Room> MazeGeneration();
}

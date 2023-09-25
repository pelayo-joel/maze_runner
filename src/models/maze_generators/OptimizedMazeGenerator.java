package models.maze_generators;


import java.util.HashSet;
import java.util.Set;

import models.maze_generators.utilities.*;


public class OptimizedMazeGenerator implements MazeGenerator {
    private int nCells; 
    private Set<Pair<Integer>> corridorsCoordinates;

    public OptimizedMazeGenerator(int mazeSize) {
        this.nCells = (mazeSize - 1) * 2;
        this.corridorsCoordinates = new HashSet<>();
    }
}

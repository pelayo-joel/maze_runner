package models.maze_generators;


import java.util.HashSet;
import java.util.Set;

import models.maze_generators.utilities.*;


public class SimplePerfectMazeGenerator implements MazeGenerator {
    private int nCells; 
    private Set<Pair<Integer>> corridorsCoordinates;

    public SimplePerfectMazeGenerator(int mazeSize) {
        this.nCells = (mazeSize - 1) * 2;
        this.corridorsCoordinates = new HashSet<>();

        //Add the entrance and the exit
        this.corridorsCoordinates.add(new Pair<Integer>(1, 2));
        this.corridorsCoordinates.add(new Pair<Integer>(mazeSize - 2, mazeSize - 1));
    }
}

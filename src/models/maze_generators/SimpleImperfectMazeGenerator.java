package models.maze_generators;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import models.maze_generators.utilities.*;


public class SimpleImperfectMazeGenerator implements MazeGenerator {
    private int nCells, mazeSize; 
    private Pair<Integer>[] cells;
    private Pair<Integer> entrance, exit;
    private Set<Pair<Integer>> corridorsCoordinates;
    private UnionFind<Pair<Integer>> corridorsGeneration;

    private Random rand = new Random();

    public SimpleImperfectMazeGenerator(int mazeSize) {
        this.nCells = (mazeSize - 2) * 2;
        this.mazeSize = mazeSize - 1;
        //this.cells = new Pair<Integer>[this.nCells];
        this.corridorsGeneration = new UnionFind<>(this.cells);
        this.corridorsCoordinates = new HashSet<>();

        //Initialize and adds the entrance and the exit
        entrance = new Pair<Integer>(1, 2);
        exit = new Pair<Integer>(mazeSize - 2, mazeSize - 1);
        this.corridorsCoordinates.add(entrance);
        this.corridorsCoordinates.add(exit);
    }



    public Set<Pair<Integer>> SimpleImperfectMaze() {
        this.UnionFindAlgorithm();
        return this.corridorsCoordinates;
    }



    private void UnionFindAlgorithm() {
        while(!corridorsGeneration.areConnected(this.entrance, this.exit)) {
            Pair<Integer> randEntranceNeighbour = new Pair<Integer>(this.rand.nextInt(((this.mazeSize - 1) + 1) + 1), this.rand.nextInt(((this.mazeSize - 1) + 1) + 1));
            Pair<Integer> randExitNeighbour = new Pair<Integer>(this.rand.nextInt(((this.mazeSize - 1) + 1) + 1), this.rand.nextInt(((this.mazeSize - 1) + 1) + 1));
            if(!corridorsGeneration.areConnected(randEntranceNeighbour, randExitNeighbour)) {
                this.corridorsCoordinates.add(randEntranceNeighbour);
                this.corridorsCoordinates.add(randExitNeighbour);
                this.corridorsGeneration.connect(randEntranceNeighbour, randExitNeighbour);
            }
        }
    }
}
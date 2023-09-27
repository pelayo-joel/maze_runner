package models.maze_generators;


import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import models.maze_generators.utilities.*;


public class SimpleImperfectMazeGenerator implements MazeGenerator {
    private List<Room> maze;

    public SimpleImperfectMazeGenerator(List<Room> rooms) {
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
    // private int nCells, mazeWidth, mazeHeight; 
    // private Room[] cells;
    // private Room entrance, exit;
    // private List<Room> visitedCells;
    // private Set<Room> corridorsCoordinates;
    // private UnionFind<Room> corridorsGeneration;

    // private Random rand = new Random();

    // public SimpleImperfectMazeGenerator(int width, int height) {
    //     //Defines the size of the maze interiors and the number of cells
    //     this.mazeWidth = width - 2;
    //     this.mazeHeight = height - 2;
    //     this.nCells = this.mazeWidth * this.mazeHeight;
        
    //     //Defines the different data structure needed for this algortihm 
    //     this.cells = new Room[this.nCells];
    //     this.visitedCells = new Stack<Room>();
    //     this.corridorsGeneration = new UnionFind<>(this.cells);
    //     this.corridorsCoordinates = new HashSet<>();

    //     //Initialize and adds the entrance and the exit
    //     this.entrance = new Room(1, 1);
    //     this.exit = new Room(this.mazeWidth, this.mazeHeight);
    //     this.visitedCells.add(this.entrance); this.visitedCells.add(this.exit);
    //     this.corridorsCoordinates.add(entrance);
    //     this.corridorsCoordinates.add(exit);
    // }



    // public Set<Room> SimpleImperfectMaze() {
    //     this.UnionFindAlgorithm();
    //     return this.corridorsCoordinates;
    // }



    // private void UnionFindAlgorithm() {
    //     while(!corridorsGeneration.areConnected(this.entrance, this.exit)) {
    //         Room randEntranceNeighbour = RandomUnvisitedCell(), randExitNeighbour = RandomUnvisitedCell();
    //         if(!corridorsGeneration.areConnected(randEntranceNeighbour, randExitNeighbour)) {
    //             this.corridorsCoordinates.add(randEntranceNeighbour);
    //             this.corridorsCoordinates.add(randExitNeighbour);
    //             this.corridorsGeneration.connect(randEntranceNeighbour, randExitNeighbour);
    //         }
    //     }
    // }


    // private Room RandomUnvisitedCell() {
    //     int x = this.rand.nextInt(((this.mazeWidth - 1) + 1) + 1), y = this.rand.nextInt(((this.mazeHeight - 1) + 1) + 1);
    //     Room randomCell = new Room(x, y);

    //     while(this.visitedCells.contains(randomCell)) {
    //         x = this.rand.nextInt(((this.mazeWidth - 1) + 1) + 1);
    //         y = this.rand.nextInt(((this.mazeHeight - 1) + 1) + 1);
    //         randomCell.x = x; 
    //         randomCell.y = y;
    //     }
    //     this.visitedCells.add(randomCell);
    //     return randomCell;
    // }
 }
package models;

import java.util.ArrayList;
import java.util.Set;

import models.maze_generators.*;
import models.maze_generators.utilities.Pair;


public class Maze {
    private int mazeSize = 5;
    private char[][] maze;
    private Set<Pair<Integer>> corridors;
    

    public Maze(int size, String algorithm) {
        this.mazeSize = size;
        this.maze = new char[this.mazeSize][this.mazeSize];

        this.Generate();
    }



    public void DisplayMaze() {
        for (char[] row : this.maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }



    private void Generate() {
        //Generates the board for the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = '#';
            }
        }
        //Marks the entrance and the exit
        maze[0][1] = '.'; maze[maze.length - 1][maze[maze.length - 1].length - 2] = '.';

        //Generates the maze (or the corridors that makes maze)
    }
}

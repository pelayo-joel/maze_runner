package models;


import java.util.ArrayList;
import java.util.List;

import models.maze_generators.*;
import models.maze_generators.utilities.Room;


public class Maze {
    private int displayWidth, displayHeight, nRoomColumns, nRoomRows;
    private char[][] mazeDisplay;
    private List<Room> rooms = new ArrayList<>();
    

    public Maze(int width, int height, String mazeComplexity, String algorithm) {
        //Initialize needed variables for the size
        this.displayWidth = width * 3;
        this.displayHeight = height * 3;
        this.nRoomColumns = width;
        this.nRoomRows = height;
        this.mazeDisplay = new char[this.displayHeight][this.displayWidth];

        for (int i = 1; i < this.displayHeight; i+=3) {
            for (int j = 1; j < this.displayWidth; j+=3) {
                this.rooms.add(new Room(j, i, this.displayWidth, this.displayHeight));
            }
        }

        //this.AlgoParser(algorithm);
        this.Generate();
    }



    public void DisplayMaze() {
        for (char[] row : this.mazeDisplay) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }



    private void Generate() {
        //Generates the board for the maze
        for (int i = 0; i < this.mazeDisplay.length; i++) {
            for (int j = 0; j < this.mazeDisplay[i].length; j++) {
                this.mazeDisplay[i][j] = '#';
            }
        }
        //Marks the entrance and the exit
        this.mazeDisplay[0][1] = '.';
        this.mazeDisplay[this.mazeDisplay.length - 1][this.mazeDisplay[this.mazeDisplay.length - 1].length - 2] = '.';

        //Generates the maze (or the corridors that makes the maze)
        for (Room room : this.rooms) {
            List<int[]> roomCardinals = room.GetCardinals();
            this.mazeDisplay[room.GetY()][room.GetX()] = '.';
            for (int[] cardinals : roomCardinals) {
                this.mazeDisplay[cardinals[1]][cardinals[0]] = '.';
            }
        }
    }


    private void AlgoParser(String complexity, String algorithm) {
        if (algorithm.toLowerCase().equals("graph")) {
            GraphBasedMazeGenerator mazeGeneration = new GraphBasedMazeGenerator(this.rooms);
        }
    }
}

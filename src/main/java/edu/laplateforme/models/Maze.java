package edu.laplateforme.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.laplateforme.models.maze_generators.*;
import edu.laplateforme.models.maze_generators.utilities.Room;


public class Maze {
    private int displayWidth, displayHeight, nRoomColumns, nRoomRows;
    private char[][] mazeDisplay;
    private List<Room> rooms = new ArrayList<>();
    private Set<Room> treatedRooms;
    

    public Maze(int width, int height, String mazeComplexity, String algorithm) {
        //Initialize needed variables for the size
        this.displayWidth = width * 3;
        this.displayHeight = height * 3;
        this.nRoomColumns = width;
        this.nRoomRows = height;
        this.mazeDisplay = new char[this.displayHeight][this.displayWidth];

        //Creates every room at their respective coordinates
        int n = 1;
        for (int i = 1; i < this.displayHeight; i+=3) {
            for (int j = 1; j < this.displayWidth; j+=3) {
                //System.out.println(n);
                this.rooms.add(new Room(j, i, this.displayWidth, this.displayHeight, this.nRoomColumns, this.nRoomRows));
                n++;
            }
        }

        this.AlgoParser(mazeComplexity, algorithm);
        this.GenerateMaze();
    }



    public void DisplayMaze() {
        for (char[] row : this.mazeDisplay) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }



    private void GenerateMaze() {
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
        for (Room room : this.treatedRooms) {
            this.mazeDisplay[room.GetY()][room.GetX()] = '.';
            List<int[]> roomPaths = room.GetPaths();
            for (int[] paths : roomPaths) {
                if (paths != null) {
                    this.mazeDisplay[paths[1]][paths[0]] = '.';
                }
            }
        }
    }
    


    private void AlgoParser(String complexity, String algorithm) {
        switch (algorithm) {
            case "simple":
                if (complexity.toLowerCase().equals("perfect")) {
                    SimplePerfectMazeGenerator perfectMazeGeneration = new SimplePerfectMazeGenerator(this.rooms);
                    this.treatedRooms = perfectMazeGeneration.MazeGeneration();
                }
                else if (complexity.toLowerCase().equals("imperfect")) {
                    SimpleImperfectMazeGenerator imperfectMazeGeneration = new SimpleImperfectMazeGenerator(this.rooms);
                    this.treatedRooms = imperfectMazeGeneration.MazeGeneration();
                }
                break;
            case "graph":
                GraphBasedMazeGenerator graphMazeGeneration = new GraphBasedMazeGenerator(this.rooms, complexity);
                this.treatedRooms = graphMazeGeneration.MazeGeneration();
                break;
            case "optimized":
                OptimizedMazeGenerator optimizedMazeGeneration = new OptimizedMazeGenerator(this.rooms, complexity);
                this.treatedRooms = optimizedMazeGeneration.MazeGeneration();
                break;
            default:
                break;
            }
        // try {
        //     switch (algorithm) {
        //             case "simple":
        //                 if (complexity.toLowerCase().equals("perfect")) {
        //                     SimplePerfectMazeGenerator perfectMazeGeneration = new SimplePerfectMazeGenerator(this.rooms);
        //                     this.treatedRooms = perfectMazeGeneration.MazeGeneration();
        //                 }
        //                 else if (complexity.toLowerCase().equals("imperfect")) {
        //                     SimpleImperfectMazeGenerator imperfectMazeGeneration = new SimpleImperfectMazeGenerator(this.rooms);
        //                     this.treatedRooms = imperfectMazeGeneration.MazeGeneration();
        //                 }
        //                 break;
        //             case "graph":
        //                 GraphBasedMazeGenerator graphMazeGeneration = new GraphBasedMazeGenerator(this.rooms, complexity);
        //                 this.treatedRooms = graphMazeGeneration.MazeGeneration();
        //                 break;
        //             case "optimized":
        //                 OptimizedMazeGenerator optimizedMazeGeneration = new OptimizedMazeGenerator(this.rooms, complexity);
        //                 this.treatedRooms = optimizedMazeGeneration.MazeGeneration();
        //                 break;
        //             default:
        //                 break;
        //             }
        // } catch (Exception e) {
        //     System.out.println("Erreur inattendue lors de la génération du labyrinthe. Veuillez réessayer.");
        //     System.exit(2);
        // }
    }
}

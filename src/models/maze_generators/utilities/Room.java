package models.maze_generators.utilities;

import java.util.ArrayList;
import java.util.List;

public class Room {
    // public final int roomId;
    // private static int id = 0;
    public boolean visited = false;
    private int x, y, xMax, yMax;
    private List<int[]> cardinalWalls, paths;
    
    public Room(int x, int y, int mazeMaxX, int mazeMaxY) {
        // id++;
        // this.roomId = id;
        this.x = x;
        this.y = y;
        this.xMax = mazeMaxX - 2;
        this.yMax = mazeMaxY - 2;
        this.cardinalWalls = SetAvailableCardinals();
    }



    
    
    public void CreatePaths(String complexity) {

    }
    
    
    public List<int[]> GetPaths() {
        return this.paths;
    }

    public List<int[]> GetCardinals() {
        return this.cardinalWalls;
    }

    // public int GetId() { return this.roomId; }
    public int GetX() { return this.x; }
    public int GetY() { return this.y; }



    private List<int[]> SetAvailableCardinals() {
        List<int[]> walls = new ArrayList<>();
        //If the room is in the lower left 
        if (this.x == 1 && this.y == this.yMax) {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x, this.y - 1});
        } 
        //If the room is in the upper right
        else if (this.x == this.xMax && this.y == 1) {
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y + 1});
        } 
        else if (this.x == this.xMax && this.y == yMax) {
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y - 1});
        } 
        else if (this.x == 1 && this.y == 1) {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x, this.y + 1});
        } 
        //If the room is on the right extremity of the maze
        else if (this.x == this.xMax) {
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y - 1});
            walls.add(new int[]{this.x, this.y + 1});
        } 
        //If the room is on the lower extremity of the maze
        else if (this.y == this.yMax) {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y - 1});
        } 
        //If the room is on the left extremity of the maze
        else if (this.x == 1) {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x, this.y - 1});
            walls.add(new int[]{this.x, this.y + 1});
        } 
        //If the room is on the upper extremity of the maze
        else if (this.y == 1) {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y + 1});
        } 
        //Default available walls
        else {
            walls.add(new int[]{this.x + 1, this.y});
            walls.add(new int[]{this.x - 1, this.y});
            walls.add(new int[]{this.x, this.y + 1});
            walls.add(new int[]{this.x, this.y - 1});
        }
        return walls;
    }
}
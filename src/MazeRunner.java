import models.Maze;

public class MazeRunner {
    public static void main(String[] args) throws Exception {
        Maze maze = new Maze(Integer.parseInt(args[0]), null);
        maze.DisplayMaze();
    }
}
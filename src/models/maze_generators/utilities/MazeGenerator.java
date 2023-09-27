package models.maze_generators.utilities;


import java.util.List;


public interface MazeGenerator {
    public boolean MazeDone();

    public List<Room> RecursiveBackTracking(List<Room> list);
}

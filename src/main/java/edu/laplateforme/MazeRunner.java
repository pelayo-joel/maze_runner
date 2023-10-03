import edu.laplateforme.models.Maze;

public class MazeRunner {
    private static int width, height;
    private static String complexity, algorithm;
    public static void main(String[] args) throws Exception {
        try {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            complexity = args[2];
            algorithm = args[3];

            if (width < 5 || height < 5) {
                System.out.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5");
                System.exit(1);
            }

            if (!(complexity.toLowerCase().equals("perfect") || complexity.toLowerCase().equals("imperfect")) && !(algorithm.toLowerCase().equals("simple") || algorithm.toLowerCase().equals("graph") || algorithm.toLowerCase().equals("optimized"))) {
                System.out.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
                System.out.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
                System.exit(1);
            }

            Maze maze = new Maze(width, height, complexity, algorithm);
            maze.DisplayMaze();
        }
        catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5");
            System.out.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
        }
    }
}
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));

        final int maxReds = 12;
        final int maxGreens = 13;
        final int maxBlues = 14;

        int sumOfIds = 0;

        while (file.hasNextLine())
        {
            String line = file.nextLine();
            int id = Integer.parseInt(line.substring(5, line.indexOf(":")));
            Scanner rounds = new Scanner(line.substring(line.indexOf(":") + 1)).useDelimiter(";");
            boolean possible = true;
            while (rounds.hasNext())
            {
                Scanner cubes = new Scanner(rounds.next()).useDelimiter(",");
                while (cubes.hasNext())
                {
                    String cube = cubes.next().trim();
                    String[] cubeParts = cube.split(" ");
                    int amount = Integer.parseInt(cubeParts[0]);
                    String color = cubeParts[1];
                    if (color.equals("red") && amount > maxReds
                        || color.equals("green") && amount > maxGreens
                        || color.equals("blue") && amount > maxBlues)
                    {
                        possible = false;
                    }
                }
            }
            if (possible)
            {
                sumOfIds += id;
            }
        }
        System.out.printf("Sum of IDs: %d%n", sumOfIds);
    }
}

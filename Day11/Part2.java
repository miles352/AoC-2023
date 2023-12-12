import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String args[]) throws IOException {
        Scanner file = new Scanner(new File("input.txt"));
        List<char[]> input = new ArrayList<char[]>();
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            // add expansive rows
            if (!line.contains("#"))
            {
                input.add("-".repeat(line.length()).toCharArray());
            }
            else
            {
                input.add(line.toCharArray());
            }
        }
        // add expansive columns
        for (int i = 0; i < input.get(0).length; i++)
        {
            // java moment
            final int x = i;
            Character[] col = input.stream().map(row -> row[x]).map(Character::charValue).toArray(Character[]::new);
            if (!Arrays.toString(col).contains("#"))
            {                
                input = input.stream().map(row -> {
                    String rowString = new String(row);
                    return (rowString.substring(0, x) + "-" + rowString.substring(x + 1)).toCharArray();
                }).collect(Collectors.toList());
            }
        }

        input.forEach(row -> System.out.println(Arrays.toString(row)));
        int rowOffset = 0;
        int colOffset = 0;
        ArrayList<int[]> galaxies = new ArrayList<int[]>();
        for (int i = 0; i < input.size(); i++)
        {
            String line = new String(input.get(i));
            if (line.chars().allMatch(c -> c == line.charAt(0)))
            {
                // if row has a gap
                rowOffset += 100 - 1;
                continue;
            }
            colOffset = 0;
            for (int j = 0; j < input.get(i).length; j++)
            {
                if (input.get(i)[j] == '#')
                {
                    System.out.printf("galaxy at [%d, %d]%n", i + rowOffset, j + colOffset);
                    galaxies.add(new int[]{i + rowOffset, j + colOffset});
                }
                else if (input.get(i)[j] == '-')
                {
                    colOffset += 100 - 1;
                }
            }
        }

        int shortestPathsSum = 0;
        
        for (int i = 0; i < galaxies.size(); i++)
        {
            for (int j = i + 1; j < galaxies.size(); j++)
            {
                shortestPathsSum += getShortestDistance(galaxies.get(i), galaxies.get(j));
            }
        }
        System.out.printf("Sum of Shortest Paths: %d%n", shortestPathsSum);
    }
    
    public static int getShortestDistance(int[] a, int[] b)
    {
        return Math.abs(b[1] - a[1]) + Math.abs(b[0] - a[0]);
    }
}
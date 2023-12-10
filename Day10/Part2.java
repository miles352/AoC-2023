import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        String line1 = file.nextLine();
        int length = line1.length() + 2;
        char[][] grid = new char[length][length];
        int[] startingPos = new int[2];
        grid[0] = ".".repeat(length).toCharArray();
        grid[1] = ('.' + line1 + '.').toCharArray();
        for (int i = 2; i < grid.length - 1; i++)
        {
            String line = file.nextLine();
            int j = line.indexOf('S');
            if (j != -1) startingPos = new int[]{i, j + 1};
            grid[i] = ('.' + line + '.').toCharArray();
        }
        grid[length - 1] = ".".repeat(length).toCharArray();
        int[][] next = getNext(grid, startingPos);
        int[] L = startingPos;
        int[] nextL = next[0];
        int[] R = startingPos;
        int[] nextR = next[1];
        int distance = 1;
        // get each next value until they are equal
        while (!Arrays.equals(nextL, nextR))
        {
            // left side
            int[][] tempNext = getNext(grid, nextL);
            // continue in same direction
            if (Arrays.equals(tempNext[0], L))
            {
                L = nextL;
                nextL = tempNext[1];
            }
            else
            {
                L = nextL;
                nextL = tempNext[0];
            }

            // right side
            tempNext = getNext(grid, nextR);
            if (Arrays.equals(tempNext[0], R))
            {
                R = nextR;
                nextR = tempNext[1];
            }
            else
            {
                R = nextR;
                nextR = tempNext[0];
            }
            distance++;
        }
        System.out.printf("Distance: %d%n", distance);
    }

    public static int[][] getNext(char[][] grid, int[] start)
    {
        ArrayList<int[]> connected = new ArrayList<int[]>();
        int x = start[1];
        int y = start[0];
        char startPipe = grid[y][x];
        char top = grid[y - 1][x];
        char right = grid[y][x + 1];
        char bottom = grid[y + 1][x];
        char left = grid[y][x - 1];
        if (startPipe != '-' && startPipe != '7' && startPipe != 'F' && (top == '|' || top == '7' || top == 'F')) connected.add(new int[]{y - 1, x});
        if (startPipe != '|' && startPipe != 'J' && startPipe != '7' && (right == '-' || right == 'J' || right == '7')) connected.add(new int[]{y, x + 1});
        if (startPipe != '-' && startPipe != 'L' && startPipe != 'J' && (bottom == '|' || bottom == 'L' || bottom == 'J')) connected.add(new int[]{y + 1, x});
        if (startPipe != '|' && startPipe != 'L' && startPipe != 'F' && (left == '-' || left == 'L' || left == 'F')) connected.add(new int[]{y, x - 1});
        return connected.stream().toArray(int[][]::new);
    }
}
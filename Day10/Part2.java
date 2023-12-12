import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1
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
        int[] next = getNext(grid, startingPos)[0];
        int[] curr = startingPos;
        int distance = 1;
        // get each next value until you are back to S
        while (true)
        {
            int[][] tempNext = getNext(grid, next);
            // continue in same direction
            if (Arrays.equals(tempNext[0], curr))
            {
                curr = next;
                next = tempNext[1];
            }
            else
            {
                curr = next;
                next = tempNext[0];
            }
            
            distance++;
            if (grid[next[0]][next[1]] == 'S')
            {
                break;
            } 
        }
        System.out.printf("Distance: %d%n", distance / 2);
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
        if (startPipe != '-' && startPipe != '7' && startPipe != 'F' && (top == '|' || top == '7' || top == 'F' || top == 'S')) connected.add(new int[]{y - 1, x});
        if (startPipe != '|' && startPipe != 'J' && startPipe != '7' && (right == '-' || right == 'J' || right == '7' || right == 'S')) connected.add(new int[]{y, x + 1});
        if (startPipe != '-' && startPipe != 'L' && startPipe != 'J' && (bottom == '|' || bottom == 'L' || bottom == 'J' || bottom == 'S')) connected.add(new int[]{y + 1, x});
        if (startPipe != '|' && startPipe != 'L' && startPipe != 'F' && (left == '-' || left == 'L' || left == 'F' || left == 'S')) connected.add(new int[]{y, x - 1});
        return connected.stream().toArray(int[][]::new);
    }
}
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        int nextValuesSum = 0;
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            List<Integer> numbers = Arrays.stream(line.split(" ")).map(num -> Integer.parseInt(num)).collect(Collectors.toList());
            List<List<Integer>> history = getHistory(numbers);
            for (int i = history.size() - 1; i > 0; i--)
            {
                int result = history.get(i).get(history.get(i).size() - 1);
                int x = history.get(i - 1).get(history.get(i - 1).size() - 1);
                // ? - x = result
                history.get(i - 1).add(result + x);
            }
            nextValuesSum += history.get(0).get(history.get(0).size() - 1);
        }
        System.out.printf("Sum of next value: %d%n", nextValuesSum);
    }

    public static List<List<Integer>> getHistory(List<Integer> start)
    {

        List<List<Integer>> history = new ArrayList<List<Integer>>();
        history.add(start);
        ArrayList<Integer> next = new ArrayList<Integer>();
        for (int i = 0; i < start.size() - 1; i++)
        {
            next.add(start.get(i + 1) - start.get(i));
        }
        // if not all zeros
        if (next.stream().anyMatch(num -> num != 0))
        {
            history.addAll(getHistory(next));
        }
        else
        {
            // add final zero
            next.add(0);
            history.add(next);
        }
        return history;
    }
}
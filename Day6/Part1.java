import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 
{
    public static void main(String args[]) throws IOException 
    {
        Scanner file = new Scanner(new File("input.txt"));
        int error = 1;
        
        ArrayList<Integer> times = getNumbers(file.nextLine().substring(5));
        ArrayList<Integer> distances = getNumbers(file.nextLine().substring(9));
        
        for (int i = 0; i < times.size(); i++)
        {
            int waysPerRace = 0;
            for (int j = 0; j < times.get(i); j++)
            {
                int distance = (times.get(i) - j) * j;
                if (distance > distances.get(i))
                {
                    waysPerRace++;
                }
            }
            error *= waysPerRace;
        }
        System.out.printf("Errors: %d%n", error);
    }
    
    private static ArrayList<Integer> getNumbers(String line)
    {
        Scanner scanner = new Scanner(line);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (scanner.hasNextInt())
        {
            nums.add(scanner.nextInt());
        }
        return nums;
    }
}
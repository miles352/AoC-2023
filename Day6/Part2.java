import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Part2 
{
    public static void main(String args[]) throws IOException 
    {
        Scanner file = new Scanner(new File("input.txt"));
        int error = 1;
        
        long time = getNumbers(file.nextLine().substring(5));
        long recordDistance = getNumbers(file.nextLine().substring(9));
        
        int waysPerRace = 0;
        for (long j = 0; j < time + 1; j++)
        {
            long distance = (time - j) * j;
            if (distance > recordDistance)
            {
                waysPerRace++;
            }
        }
        error *= waysPerRace;
        System.out.printf("Errors: %d%n", error);
    }
    
    private static long getNumbers(String line)
    {
        return Long.parseLong(line.replaceAll("\\s",""));
    }
}
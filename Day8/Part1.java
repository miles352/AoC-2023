import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;

public class Part1
{
    public static void main(String[] ars) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        String instructions = file.nextLine();
        // clear space
        file.nextLine();
        HashMap<String, String[]> keys = new HashMap<String, String[]>();

        while (file.hasNextLine())
        {
            String line = file.nextLine();
            String key = line.substring(0, 3);
            String L = line.substring(7, 10);
            String R = line.substring(12, 15);
            keys.put(key, new String[]{L, R});
        }

        String lastElement = "AAA";
        int steps = 0;
        boolean found = false;
        while (!found)
        {
            
            for (char c : instructions.toCharArray())
            {
                if (lastElement.equals("ZZZ")) 
                {
                    found = true;
                    break;
                }
                if (c == 'L')
                {
                    lastElement = keys.get(lastElement)[0];
                }
                else
                {
                    lastElement = keys.get(lastElement)[1];
                }
                steps++;
            }
            
        }
        System.out.printf("Steps: %d%n", steps);
    }
}
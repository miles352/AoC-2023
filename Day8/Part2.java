import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Part2
{
    public static void main(String[] ars) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        String instructions = file.nextLine();
        // clear space
        file.nextLine();
        ArrayList<String> endsWithA = new ArrayList<String>();
        HashMap<String, String[]> keys = new HashMap<String, String[]>();

        while (file.hasNextLine())
        {
            String line = file.nextLine();
            String key = line.substring(0, 3);
            String L = line.substring(7, 10);
            String R = line.substring(12, 15);
            if (key.charAt(2) == 'A')
            {
                endsWithA.add(key);
            }
            keys.put(key, new String[]{L, R});
        }

        int steps = 0;
        while (true)
        {
            // go through all L/R instructions
            for (int i = 0; i < instructions.length(); i++)
            {
                // for each starting value, map it to the next value
                for (int j = 0; j < endsWithA.size(); j++)
                {
                    if (instructions.charAt(i) == 'L')
                    {
                        endsWithA.set(j, keys.get(endsWithA.get(j))[0]);
                    }
                    else
                    {
                        endsWithA.set(j, keys.get(endsWithA.get(j))[1]);
                    }
                }
                steps++;
                List<String> endsWithZ = endsWithA.stream().filter(element -> element.charAt(2) == 'Z').collect(Collectors.toList());
                if (endsWithZ.size() == endsWithA.size()) {
                    System.out.println(steps);
                    return;
                }              
            }
        }
    }
}
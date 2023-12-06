import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.stream.LongStream;
import java.util.Arrays;
import java.util.Collections;

public class Part1 
{
    public static void main(String args[]) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));

        ArrayList<Long> startingNumbers = numbersFromString(file.nextLine().substring(7));
        file.nextLine(); // get rid of extra line

        ArrayList<String[]> maps = new ArrayList<String[]>();
        ArrayList<String> newMap = new ArrayList<String>();
        // gotta be a better way idk how to use split though
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            if (!line.isEmpty() && Character.isDigit(line.charAt(0)))
            {
                newMap.add(line);
            }
            else if (line.isEmpty())
            {
                maps.add(newMap.toArray(new String[0]));
                newMap.clear();
            }
        } 
        maps.add(newMap.toArray(new String[0]));
        
        for (String[] map : maps)
        {
            for (int i = 0; i < startingNumbers.size(); i++)
            {
                for (String line : map)
                {
                    long mappedNumber = mapNumber(line, startingNumbers.get(i));
                    
                    if (mappedNumber != startingNumbers.get(i))
                    {
                        startingNumbers.set(i, mappedNumber);
                        break;
                    }
                }
            }  
        }
        Collections.sort(startingNumbers);
        System.out.printf("Lowest Location: %d%n", startingNumbers.get(0));
    }
    
    private static long mapNumber(String line, long number)
    {
        Scanner numbers = new Scanner(line);
        long dstStart = numbers.nextLong();
        long srcStart = numbers.nextLong();
        long length = numbers.nextLong();
        numbers.close();
        if (number >= srcStart && number < srcStart + length)
        {
            return dstStart + number - srcStart;
        }
        return number;
    }
    // idk why i made this
    private static ArrayList<Long> numbersFromString(String str)
    {
        Scanner numbers = new Scanner(str);
        ArrayList<Long> nums = new ArrayList<Long>();
        while (numbers.hasNextLong())
        {
            nums.add(numbers.nextLong());
        }
        numbers.close();
        return nums;
    }
}
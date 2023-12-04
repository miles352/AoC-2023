import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Part1
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        
        int sumOfCards = 0;
        
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            int matches = 0;
            int points = 0;
            String data = line.substring(line.indexOf(":")+1);
            String[] numbers = data.split("\\|");
            ArrayList<Integer> winningNumbers = getNumbers(numbers[0]);
            ArrayList<Integer> scratchNumbers = getNumbers(numbers[1]);

            for (int number : scratchNumbers)
            {
                if (winningNumbers.contains(number))
                {
                    if (matches > 0)
                    {
                        points *= 2;
                    }   
                    else
                    {
                        points++;
                    }
                    matches++;
                }
            }  
            sumOfCards += points;
        }
        System.out.printf("Total Sum of points: %d%n", sumOfCards);
    }

    private static ArrayList<Integer> getNumbers(String input)
    {
        String[] numbers = input.split(" ");
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++)
        {
            if (!numbers[i].equals(""))
                ints.add(Integer.parseInt(numbers[i].trim()));
        }
        return ints;
    }
}
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Part2
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        
        ArrayList<String> cards = new ArrayList<String>();
        while (file.hasNextLine())
        {
            cards.add(file.nextLine());
        }

        int amountOfCards = searchCards(cards, 0, cards.size());
        
        System.out.printf("Total cards: %d%n", amountOfCards);
    }
    
    private static int searchCards(ArrayList<String> cards, int start, int amount)
    {
        int amountOfCards = 0;
        for (int i = 0; i < amount; i++)
        {
            amountOfCards += searchCards(cards, start + i + 1, getWinningNumbers(cards.get(start+i)));
            amountOfCards++;
        }
        return amountOfCards;
    }
    
    private static int getWinningNumbers(String line)
    {
        int matches = 0;
        String data = line.substring(line.indexOf(":")+1);
        String[] numbers = data.split("\\|");
        ArrayList<Integer> winningNumbers = getNumbers(numbers[0]);
        ArrayList<Integer> scratchNumbers = getNumbers(numbers[1]);

        for (int number : scratchNumbers)
        {
            if (winningNumbers.contains(number))
            {
                matches++;
            }
        }
        return matches;
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
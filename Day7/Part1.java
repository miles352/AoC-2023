import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

public class Part1 
{
    public static void main(String args[]) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        
        ArrayList<String> hands = new ArrayList<String>();
        ArrayList<Integer> bids = new ArrayList<Integer>();
        
        while (file.hasNextLine())
        {
            hands.add(file.next());
            bids.add(file.nextInt());
        }

        ArrayList<String> sortedHands = new ArrayList<>(hands);
        sortedHands.sort(
            (hand1, hand2) -> {
                Double hand1Value = getHandValue(hand1);
                Double hand2Value = getHandValue(hand2);
                return hand1Value.compareTo(hand2Value);
            }
        );
        int totalWinnings = 0;
        for (int i = 0; i < hands.size(); i++)
        {
            int bidIndex = hands.indexOf(sortedHands.get(i));
            totalWinnings += (i + 1) * bids.get(bidIndex);
        }
        System.out.printf("Total winnings: %d%n", totalWinnings);
    }
    
    private static double getHandValue(String hand)
    {
        int type = getHandType(hand);
        double value = 0;
        char[] charRanking = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
        for (int i = 0; i < hand.length(); i++)
        {
            for (int j = 0; j < charRanking.length; j++)
            {
                if (hand.charAt(i) == charRanking[j])
                {
                    int rankPower = charRanking.length - j;
                    // idk a better way to make this one more significant
                    double positionPower = Math.pow(hand.length() - i, 12);
                    value += rankPower * positionPower;
                }
            }
        }
        // this took forever but this seems to be enough to differentiate between the numbers
        return value * Math.pow(100, type);
    }
    
    private static int getHandType(String hand)
    {
        int maxSimiliarChars = 0;
        int twoPairs = 0;
        for (char c1 : hand.toCharArray())
        {
            int similiarChars = 0;
            for (char c2 : hand.toCharArray())
            {
                if (c1 == c2)
                {
                    similiarChars++;
                }
            }
            if (similiarChars == 2)
            {
                twoPairs++;
            }
            else if (similiarChars == 3)
            {   
                char remainingChar = ' ';
                for (char c2 : hand.toCharArray())
                {
                    if (c1 != c2 && remainingChar == ' ')
                    {
                        remainingChar = c2;
                    }
                    else if (c1 != c2 && c2 == remainingChar)
                    {
                        // full house
                        return 5;
                    } 
                    else if (c1 != c2 && remainingChar != ' ')
                    {
                        // 3 of a kind
                        return 4;
                    }
                }
            }
            if (similiarChars > maxSimiliarChars)
            {
                maxSimiliarChars = similiarChars;
            }
        }
        
        
        if (maxSimiliarChars == 5)
        {
            // five of a kind
            return 7;
        } 
        else if (maxSimiliarChars == 4)
        {
            // four of a kind
            return 6;
        }
        else if (twoPairs / 2 == 2)
        {
            // two pairs of 2
            return 3;
        }
        else if (twoPairs / 2 == 1)
        {
            // one pair of 2
            return 2;
        }
        else if (maxSimiliarChars == 1)
        {
            // high cards
            return 1;
        }
        else
        {
            // unreachable
            return 69420;
        }
    }
}
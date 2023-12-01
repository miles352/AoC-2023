import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class main
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        int totalSums = 0;
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            ArrayList<Integer> nums = new ArrayList<Integer>();

            for (int i = 0; i < line.length(); i++)
            {
                if (Character.isDigit(line.charAt(i)))
                {
                    nums.add(Character.getNumericValue(line.charAt(i)));
                }
                else if (line.startsWith("one", i))
                {
                    nums.add(1);
                }
                else if (line.startsWith("two", i))
                {
                    nums.add(2);
                }
                else if (line.startsWith("three", i))
                {
                    nums.add(3);
                }
                else if (line.startsWith("four", i))
                {
                    nums.add(4);
                }
                else if (line.startsWith("five", i))
                {
                    nums.add(5);
                }
                else if (line.startsWith("six", i))
                {
                    nums.add(6);
                }
                else if (line.startsWith("seven", i))
                {
                    nums.add(7);
                }
                else if (line.startsWith("eight", i))
                {
                    nums.add(8);
                }
                else if (line.startsWith("nine", i))
                {
                    nums.add(9);
                }

            }
            
            int firstNum = nums.get(0);
            int lastNum = nums.get(nums.size() - 1);
            int sum = (firstNum*10) + lastNum;
            totalSums += sum;
        }
        System.out.printf("Sum of all values: %d", totalSums);
    }
}
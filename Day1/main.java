import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MyClass
{
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));
        int totalSums = 0;
        
        final String[] numTexts = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
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
                for (int j = 0; j < numTexts.length; j++)
                {
                    if (line.startsWith(numTexts[j], i))
                    {
                        nums.add(j+1);
                    }
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
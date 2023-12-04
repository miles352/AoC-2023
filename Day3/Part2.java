import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;

public class Part2 {
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));

        ArrayList<String> schematic = new ArrayList<String>();

        int gearRatioSum = 0;

        // create schematic
        // surround it with .'s to make things easier
        while (file.hasNextLine())
        {
            schematic.add("." + file.nextLine() + ".");
        }
        schematic.add(0, ".".repeat(schematic.get(0).length()));
        schematic.add(".".repeat(schematic.get(0).length()));

        for (int i = 0; i < schematic.size(); i++)
        {
            String line = schematic.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                if (line.charAt(j) == '*')
                { 
                    ArrayList<Integer> numbersFound = new ArrayList<Integer>();
                    int lastLookedLine = -1;
                    for (int a = -1; a < 2; a++)
                    {
                        for (int b = -1; b < 2; b++)
                        {
                            if (Character.isDigit(schematic.get(i + a).charAt(j + b)))
                            {
                                int number = getWholeNumber(schematic, i + a, j + b);

                                if (numbersFound.size() > 0 && lastLookedLine == a && Character.isDigit(schematic.get(i + a).charAt(j + b - 1)) && getWholeNumber(schematic, i + a, j + b - 1) == numbersFound.get(numbersFound.size() - 1))
                                {

                                }
                                else
                                {
                                    numbersFound.add(number);
                                }
                                lastLookedLine = a;     
                            }
                        }
                    }
                    if (numbersFound.size() == 2)
                    {
                        gearRatioSum += numbersFound.get(0) * numbersFound.get(1);
                    }
                }
            }
        }
        System.out.printf("Sum of gear ratios: %d%n", gearRatioSum);
    }

    private static int getWholeNumber(ArrayList<String> schematic, int line, int linePos)
    {
        String number = Character.toString(schematic.get(line).charAt(linePos));

        String lineOfNumber = schematic.get(line);
        int q = 1;
        while (Character.isDigit(schematic.get(line).charAt(linePos + q)))
        {
            number += lineOfNumber.charAt(linePos + q);
            q++;
        }
        q = 1;
        while (Character.isDigit(lineOfNumber.charAt(linePos - q)))
        {
            number = lineOfNumber.charAt(linePos - q) + number;
            q++;
        }

        return Integer.parseInt(number);
    }
}

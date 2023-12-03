import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Part1 {
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));

        ArrayList<String> schematic = new ArrayList<String>();

        int sumOfParts = 0;

        // create schematic
        while (file.hasNextLine())
        {
            schematic.add(file.nextLine());
        }

        for (int i = 0; i < schematic.size(); i++)
        {
            String line = schematic.get(i);
            for (int j = 0; j < line.length(); j++)
            {
                if (Character.isDigit(line.charAt(j)))
                {
                    // kinda pointless as numbers length doesnt exceed 3 
                    int numLength = 1;    
                    while (j + numLength < line.length() && Character.isDigit(line.charAt(j + numLength)))
                    {
                        numLength++;
                        if (i == 58)
                        {
                            System.out.printf("i: %d, j: %d, numLength: %d, Partial: %s%n", i, j, numLength, line.substring(j, j + numLength));
                        }
                        
                    }
                    int number = Integer.parseInt(line.substring(j, j + numLength));
                    
                    for (int k = 0; k < numLength; k++)
                    {
                        if (j != 0 && k == 0 && line.charAt(j + k - 1) != '.' // left side
                            || (j + k != line.length() - 1 && k == numLength - 1 && line.charAt(j + k + 1) != '.') // right side
                            || (i != 0 && schematic.get(i - 1).charAt(j + k) != '.') // top side
                            || (i != schematic.size() - 1 && schematic.get(i + 1).charAt(j + k) != '.') // bottom side
                            || (i != 0 && j != 0 && schematic.get(i - 1).charAt(j + k - 1) != '.') // top left
                            || (i != 0 && j + k != line.length() - 1 && schematic.get(i - 1).charAt(j + k + 1) != '.') // top right
                            || (i != schematic.size() - 1 && j != 0 && schematic.get(i + 1).charAt(j + k - 1) != '.') // bottom left
                            || (i != schematic.size() - 1 && j + k != line.length() - 1 && schematic.get(i + 1).charAt(j + k + 1) != '.')) // bottom right
                            {
                                sumOfParts += number;
                                break;
                            }
                    }

                    // kinda hacky but it works
                    j += numLength;
                }
            }
        }
        System.out.printf("Sum of parts: %d%n", sumOfParts);
    }
}

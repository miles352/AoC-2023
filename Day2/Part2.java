import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Part2 {
    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("input.txt"));

        

        int sumOfPower = 0;
        // each line
        while (file.hasNextLine())
        {
            int greatestRed = -1;
            int greatestGreen = -1;
            int greatestBlue = -1;
            String line = file.nextLine();
            Scanner rounds = new Scanner(line.substring(line.indexOf(":") + 1)).useDelimiter(";");
            // each semicolon seperated round
            // theres no need to seperate by semicolon for part 2 but whatever it works
            while (rounds.hasNext())
            {
                Scanner cubes = new Scanner(rounds.next()).useDelimiter(",");
                while (cubes.hasNext())
                {
                    String cube = cubes.next().trim();
                    String[] cubeParts = cube.split(" ");
                    int amount = Integer.parseInt(cubeParts[0]);
                    String color = cubeParts[1];
                    
                    if (color.equals("red") && amount > greatestRed)
                    {
                        greatestRed = amount;
                    } 
                    else if (color.equals("green") && amount > greatestGreen)
                    {
                        greatestGreen = amount;
                    } 
                    else if (color.equals("blue") && amount > greatestBlue)
                    {
                        greatestBlue = amount;
                    }
                } 
            }
            int power = greatestRed * greatestGreen * greatestBlue;
            System.out.println(power);
            sumOfPower += power;
        }
        System.out.printf("Sum of power of sets: %d%n", sumOfPower);
        
    }
}

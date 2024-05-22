/**
 * Krystyn Bondad
 * This class simply reads in a given problem and creates a knapsack and populates the Item array in the knapsack
 * It also calls the sort method to sort the Item array in descending order from greatest unit value to least 
 **/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProblemReader{

    // method that takes in a string representing a file and initializs a knapsack problem
    public static Knapsack readProblem(String x)
    {
        //the error knapsack that returns if somehow the code executes with a bad file
        Knapsack error = new Knapsack();
        try{

            //scans in the first line, sets the number of items in the knapsack and the weight
            Scanner scan = new Scanner(new File(x));
            int numItems = scan.nextInt();
            scan.nextLine(); //consume newline
            int knapsackWeight = scan.nextInt();
            scan.nextLine(); //consume newline

            //initialize the knapsack
            Knapsack knapsack = new Knapsack(numItems, knapsackWeight);

            // loops through the information on each line, setting each item's label, value, weight and inserting it into the knapsack
            for (int i = 0; i < numItems; i++){
                String line = scan.nextLine();
                StringTokenizer myTokenizer = new StringTokenizer(line, " ");
                String label = myTokenizer.nextToken();
                int value = Integer.parseInt(myTokenizer.nextToken());
                int itemWeight = Integer.parseInt(myTokenizer.nextToken());
                Item newItem = new Item(label, value, itemWeight);

                knapsack.addItem(newItem);
            }

            //sorts the knapsack based on unit values of contained items 
            knapsack.sortItems();

            scan.close(); //close scanner
            return knapsack;
        }
        //catch if the file given does not exist, warns Dr. Smith that the error is serious
        catch (FileNotFoundException e)
        {
            System.out.println("THAT IS NOT A REAL FILE. DONT MESS WITH ME. program TERMINATED. run it again when you're ready to input a REAL file (i'm serious Dr. Smith)");
            System.exit(0);
        }
        return error; 
    }







}

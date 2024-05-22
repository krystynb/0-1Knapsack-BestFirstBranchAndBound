/**
 * Krystyn Bondad
 * This is the knapsack driver. It takes in a file and creates a knapsack. It then calls the knapsack solver and prints the results. 
 * It also contains a printItem function that takes an Item from the knapsack via label or index and prints the information about it. 
 * */import java.util.*;
public class KnapsackDriver {
static Knapsack knapsack;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        try{
            System.out.println("Enter the knapsack problem text file: ");
            String problemFile = scan.nextLine();
            knapsack = ProblemReader.readProblem(problemFile);
            
            KnapsackSolver knapsackSolver = new KnapsackSolver(knapsack);
            System.out.println(knapsackSolver.findOptimalKnapsack()); //broken here
            scan.close();
        }
        catch (Exception e){
            System.out.println("SHAME!!! now everything is BROKEN!! (you are stressing out my driver Dr. Smith.)");
            e.printStackTrace();
    
        }
    }

    private static void printItem(String label){
        System.out.println(knapsack.getItem(label));
    }
    
    private static void printItem(int index){
        System.out.println(knapsack.getItem(index));
    }
    
}

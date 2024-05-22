/**
 * Krystyn Bondad
 * This class creates a knapsack solver object that creates a priority queue that sorts knapsack nodes based on their potential value
 * It uses best first algorithm to find the best possible combination of items for the given knapsack
 **/
import java.util.*;
public class KnapsackSolver {

    private Knapsack knapsack;
    private KNode bestNode;
    private Queue<KNode> nodePQ;

    //Knapsack solver object. takes a knapsack and creates a priority queue
    public KnapsackSolver(Knapsack k){
        knapsack = k;
        nodePQ = new PriorityQueue<>(
            new Comparator<KNode>() 
            {
            public int compare(KNode a, KNode b)
            {
                return Double.compare(b.getPotentialValue(), a.getPotentialValue());
            }
        });

        //initializes the best node to the initial node and adds it to the priority queue
        bestNode = new KNode(knapsack);
        nodePQ.add(bestNode);
    }
    
    //finds the node with the best value. continues executing until the priority queue is empty
    public KNode findOptimalKnapsack(){
        do{ 

            //set the priorityNode to the next node in the queue
            KNode priorityNode = nodePQ.remove();


            //expands the node if it is viable
            if (shouldExpand(priorityNode)){

                //creates two nodes, one that includes the next item and one that doesn't
                KNode excludeNext = new KNode(priorityNode, -1, knapsack);
                KNode includeNext = new KNode(priorityNode, 1,  knapsack);
                
                //if the node that includes the next item has a value better than the current best, replaces the best node
                if (replaceBest(includeNext)){ 
                    bestNode.setCurrentWeight(includeNext.getCurrentWeight());
                    bestNode.setCurrentValue(includeNext.getCurrentValue());
                    bestNode.setIncludedItems(copyList(includeNext.getIncludedItems()));
                }

                //adds the two created nodes to the priority queue
                nodePQ.add(includeNext);
                nodePQ.add(excludeNext);
            }

        }while (!nodePQ.isEmpty());
        return bestNode;
    }

    //returns whether a node is viable to be expanded based on multiple conditions
    private Boolean shouldExpand(KNode node){
        Boolean betterPotential = node.getPotentialValue() > bestNode.getCurrentValue(); // checks to see if the potential value is greated than the best node's current value
        Boolean validWeight = node.getCurrentWeight() < knapsack.getWeight(); // checks to see if the current weight doesn't exceed the max weight
        Boolean nextItemExists = node.getNextItemIndex() < knapsack.getTotalItems(); //makes sure there is a next existing item
        Boolean notMaxPotential = node.getPotentialValue() > node.getCurrentValue(); //makes sure the current value is less than it's potential value

        if(betterPotential && validWeight && nextItemExists && notMaxPotential)
            return true;
        
        return false;
    }


    //returns whether a node is better than the current best node based on two conditions 
    private Boolean replaceBest(KNode node){
        Boolean betterValue = node.getCurrentValue() > bestNode.getCurrentValue(); //current value must be better than the best node's current value
        Boolean validWeight = node.getCurrentWeight() <= knapsack.getWeight(); //the weight of the prospective node must also not exceed the max weight of the knapsack 

        if(betterValue && validWeight)
            return true;
        
        return false;
    }

    //copies an ArrayList
    private ArrayList<Item> copyList(ArrayList<Item> list){
        ArrayList <Item> copiedList = new ArrayList<Item>();
        for (Item i: list){
            copiedList.add(i);
        }
        return copiedList;
    }

}





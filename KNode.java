/**
 * Krystyn Bondad
 * This class creates KNode objects that a potential knapsack combination of items as well as the current value and weight of the included items
 * It also stores the index of the next item uses a recursive function to calculate and store the potential value of the knapsack given the included items
 * It has a toString override which prints an Knapsack Node's included items, current weight, and current value
 * */
import java.util.*;
public class KNode{

private Knapsack k;
private int currentWeight, nextItem; 
private double currentValue, potentialValue;
private ArrayList<Item> includedItems; 

    //initializes all the values of the knapsack to 0, to be used for the initial node
    public KNode(Knapsack k){
        this.k = k;
        currentValue = 0; 
        currentWeight = 0;
        nextItem = 0;
        includedItems = new ArrayList<Item>();
        potentialValue = 0;
        potentialValue = findPotential(k, nextItem, currentWeight); 
    }

    //creates a node and calculates the values dependent on whether it is to exclude or the next value of the node it was derived from 
    public KNode(KNode priorityNode, int nodeType, Knapsack k){
        this.k = k;
        includedItems = copyList(priorityNode.getIncludedItems()); //copy the included items list since java passes objects by reference 
        nextItem = priorityNode.getNextItemIndex() + 1;

        if (nodeType == -1)
            setExcludedNode(priorityNode);
        else 
            setIncludedNode(priorityNode);

        //set potential value to the current value then increment 
        potentialValue = currentValue;
        potentialValue = findPotential(k, nextItem, currentWeight); 
    }

    //sets value and weight in the case that the next item is not to be included
    private void setExcludedNode(KNode priorityNode){
        currentValue = priorityNode.getCurrentValue();
        currentWeight = priorityNode.getCurrentWeight();
    }

    //sets value, weight, and adds the next item in the case the next item is to be included
    private void setIncludedNode(KNode priorityNode){
        Item nextItem = priorityNode.getNextItem();
        currentValue = priorityNode.getCurrentValue() + nextItem.getValue();
        currentWeight = priorityNode.getCurrentWeight() + nextItem.getWeight();
        includedItems.add(nextItem);
    }

    //recursive function that calculates a node's potential value
    private double findPotential(Knapsack k, int nItem, int startWeight){

        //if the next item's index exceeds the max index (i.e. the next item does not exist)
        if (nItem >= k.getTotalItems()){

            //if the current weight of the node is > the max weight of the knapsack, set potential value to 0 as it's not a viable node 
            if (startWeight > k.getWeight())
                potentialValue = 0;

            else 
                potentialValue = currentValue;
        }

        else{
            Item nextItem = k.getItem(nItem);
            int nextWeight = nextItem.getWeight() + startWeight;


            //if the weight of adding the next item is equal to the max weight, the potential value is current value + the next item's value
            if (nextWeight == k.getWeight()){
                potentialValue = potentialValue + nextItem.getValue();
            }

            //if the weight of adding the next item exceeds the max weight, potential is calculated by using a fraction of the next item
            else if (nextWeight > k.getWeight()){
                potentialValue = potentialValue + (k.getWeight() - startWeight)*nextItem.getUnitValue();
            }

            //if the weight of adding the next item is less than the max weight, potential value is incremented by the full value of the next item and the function is called again
            else if (nItem + 1 < k.getTotalItems()){
                potentialValue = potentialValue + nextItem.getValue();
                return findPotential(k, nItem+1, nextWeight);
            }
        }
        return potentialValue;
    }
    

    //toString method that prints the contents of a Node in more comprehensible way 
    public String toString(){
        String nodeInfo = "Items included in knapsack: ";

        if(includedItems.size() != 0){
            for (int i = 0; i + 1 < includedItems.size(); i++){
                nodeInfo += includedItems.get(i).getLabel() + ", ";
            }
            nodeInfo += includedItems.get(includedItems.size() -1).getLabel();
        }
        nodeInfo += "\nTotal weight = " + currentWeight + "\nTotal value = " + (int)currentValue;
        return nodeInfo;
        
    }


    //getters and setters


    public double getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    public int getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public ArrayList<Item> getIncludedItems() {
        return includedItems;
    }
    public void setIncludedItems(ArrayList<Item> includedItems) {
        this.includedItems = includedItems;
    }

    private ArrayList<Item> copyList(ArrayList<Item> list){
        ArrayList <Item> copiedList = new ArrayList<Item>();

        for (Item i: list){
            copiedList.add(i);
        }
        
        return copiedList;
    }

    public Item getNextItem() {
        return k.getItem(nextItem);
    }

    public int getNextItemIndex(){
        return nextItem;
    }

    public int getNextItemValue(){
        return k.getItem(nextItem).getValue();
    }

    public void setNextItem(int nextItem) {
        this.nextItem = nextItem;
    }
    public double getPotentialValue() {
        return potentialValue;
    }
    public void setPotentialValue(double potential) {
        this.potentialValue = potential;
    }

    

}
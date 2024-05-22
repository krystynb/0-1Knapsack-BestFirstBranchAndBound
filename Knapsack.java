/**
 * Krystyn Bondad
 * This class creates a Knapsack object that stores the total number of items and weight of a knapsack
 * It also has an Item array and map for sorting and retrieving Items. 
 * */import java.util.*;
public class Knapsack{
private int totalItems,weight;
private List<Item> itemArray;
private HashMap<String, Item> itemMap;

    //Knapsack object. Takes the total items in the knapsack and the max weight and inititializes the itemArray and itemMap
    public Knapsack(int totalItems, int weight) {
        this.totalItems = totalItems;
        this.weight = weight;
        itemArray = new ArrayList<Item>(totalItems);
        itemMap = new HashMap<String,Item>(totalItems);
    }

    //the error knapsack 
    public Knapsack() {
        this.totalItems = -1;
        this.weight = -1;
    }

    //sorts the Item array based on the unit values (ratios) of the items
    public void sortItems(){{
        Collections.sort(itemArray, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return (Double.compare(i1.getUnitValue(), i2.getUnitValue())* -1);
            }
        });
        }
    }

    //toString method that prints the Knapsack in an easily comprehendable way 
    @Override
    public String toString() {
        String string =  "Knapsack (weight:  " + 
                weight + ") \n\nitemArray: \n";
        for (int i = 0; i < totalItems; i++)
        {
            string += itemArray.get(i) + "\n";
        }

        return string;
    } 

    //getters and setters
    
    public int getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Item getItem(int index) {
        return itemArray.get(index);
    }

    public Item getItem(String label) {
        return itemMap.get(label);
    }

    public void setItem(int index, Item item){
        itemArray.set(index, item);
    }

    public void addItem(Item item){
        itemArray.add(item);
        itemMap.put(item.getLabel(), item);
    }
    
}
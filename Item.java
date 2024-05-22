/**
 * Krystyn Bondad
 * This class creates Item objects that store an Item's label, value, weight, and unit value (or ratio)
 * It has a toString override which prints an Object's label, value, weight, and unit value
 * It also has a compareTo method which compares the Items by their unit value
 * */
public class Item implements Comparable<Item> {
private String label;
private int value, weight;
private double unitValue;

    //Item objext. Stores the label, value, and weight of an item. Calculates the unit value and stores it.
    public Item(String label, int value, int weight) {
        this.label = label;
        this.value = value;
        this.weight = weight;
        unitValue = (double)value/weight;
    }

    //toString method that prints Items in a more comprehendable format. Changes doubles that have a .0 on the end into integers
    @Override 
    public String toString() {
        return label + " " + value + " " + weight + " " + Math.round(unitValue*10)/10.0 ;
    }

    //compareTo method. compares Items based on their unit values
    public int compareTo(Item other){
        double value1 = unitValue;
        double value2 = other.unitValue;
        
        return Double.compare(value1, value2);
    }

    //getters and setters 
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

}

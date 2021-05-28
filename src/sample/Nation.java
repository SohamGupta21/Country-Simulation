package sample;

import java.util.ArrayList;
//this class is for the capitals of a nation, not the extra nation parts
public class Nation {
    private final ArrayList<People> members = new ArrayList<>();
    private final String color;
    private int x;
    private int y;

    //these are the things that will be added to
    private int foodScore;
    private int resourceScore;
    private int population;
    private int money;

    //creates new nation object
    public Nation(String c) {
        this.color = c;
    }
    //returns color of nation
    public String getColor() {
        return color;
    }
    //returns population size
    public int getPopulation() {
        return this.population;
    }
    //adds a nation part to the nation
    public void AddMember(People p) {
        members.add(p);
        population++;
    }
    //adds to the food score
    public void addToFoodScore(int a) {
        foodScore += a;
    }
    //returns food score
    public int getFoodScore() {
        return foodScore;
    }
    //sets food score
    public void setFoodScore(int input){
        foodScore = input;
    }
    //adds to resource score
    public void addToResourceScore(int a) {
        resourceScore += a;
    }
    //returns resource score
    public int getResourceScore() {
        return resourceScore;
    }
    //sets resource score
    public void setResourceScore(int input){
        resourceScore = input;
    }
    //adds to money score
    public void addToMoney(int a) {
        money += a;
    }
    //returns money amount
    public int getMoney() {
        return money;
    }
    //sets the money amount
    public void setMoney(int input){
        money = input;
    }
    // returns x coordinate
    public int getX() {
        return x;
    }
    //sets x value
    public void setX(int x) {
        this.x = x;
    }
    //returns y coord
    public int getY() {
        return y;
    }
    //sets y
    public void setY(int y) {
        this.y = y;
    }
    //incremetns population score
    public void incrementPopulationScore() {
        population++;
    }
    //calculates and returns total score, which is used in battle
    public int getTotalScore() {
        //this uses food, resource and population to figure out the total score of the nation at the given time
        //population has a 40% weight, food and resource have a 30% weight with them
        int score = (int) ((double) (population) * 0.4) + (int) ((double) (resourceScore) * 0.4) + (int) ((double) (foodScore) * 0.4) + (int) ((double) (money) * 0.4);
        return score;
    }
}

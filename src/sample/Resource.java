package sample;

import java.util.ArrayList;

public class Resource {
    private final String color;
    private final String path;
    private final String nameType;
    private final int power;
    private Nation country;
    private long startTime;
    private int x;
    private int y;

    private final int objectType;

    //type of resources: food(image of veggie, blue, 1), money ($,green,2), metal (metal image, black,3)
    //constructor
    public Resource(int p, int X, int Y, int type) {
        this.power = p;
        this.x = X;
        this.y = Y;
        startTime = System.nanoTime();

        if(type == -1){
            double rand = Math.random();
            if(rand < 0.33){
                objectType = 1;
            } else if (rand < 0.66){
                objectType = 2;
            } else {
                objectType = 3;
            }
        } else {
            objectType = type;
        }

        if(objectType == 1){
            //food, image of veggie, blue
            this.nameType = "food";
            this.color = "blue";
            this.path = "resources/veggies.png";
        } else if (objectType == 2){
            //money, $, green
            this.nameType = "money";
            this.color = "green";
            this.path = "resources/dollarSign.png";
        } else {
            //metal, metal image, black
            this.nameType = "metal";
            this.color = "black";
            this.path = "resources/metal.png";
        }
    }
    //returns what type of resource it is
    public int getObjectType() {
        return objectType;
    }
    //next couple functions return or set values
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return this.color;
    }
    //resets start time
    public void resetStartTime() {
        startTime = System.nanoTime();
    }

    public long getStartTime() {
        return this.startTime;
    }
    //path refers to image path
    public String getPath(){
        return path;
    }
    //moves the resource in the grid, uses the biased movement
    public int[] changeLoc(int[][] gameGrid, ArrayList<Nation> nationArrayList) {
        //orders the nation arrayList by proximity
        ArrayList<Double> prox = new ArrayList<>();
        for (Nation n : nationArrayList) {
            prox.add(calcProximity(n));
        }
        //reorders the nation based on proximity
        for (int t = 0; t < nationArrayList.size(); t++) {
            double min = prox.get(t);
            int min_index = t;
            for (int i = t; i < prox.size(); i++) {
                if (prox.get(i) < min) {
                    min = prox.get(i);
                    min_index = i;
                }
            }
            nationArrayList.add(0, nationArrayList.remove(min_index));
            prox.add(0, prox.remove(min_index));
        }
        //gets all the total scores of all nations
        double totalScoresAdded = 0.0;
        for (int n = 0; n < nationArrayList.size(); n++) {
            totalScoresAdded += nationArrayList.get(n).getTotalScore() + (nationArrayList.size() - n);
        }
        //calcualtes the percentage of each total score that each nation makes up
        ArrayList<Double> percentages = new ArrayList<>();
        for (int nat = 0; nat < nationArrayList.size(); nat++) {
            percentages.add((double) (nationArrayList.get(nat).getTotalScore() + (nationArrayList.size() - nat)) / totalScoresAdded);
        }
        //loops through to see if the resource will get absorbed
        boolean check = false;
        int[] absorbed = {-1, -1};
        int count = 0;
        while (!check) {
            int tempx = x;
            int tempy = y;
            double rand = Math.random();
            Nation inBoundsOf = null;
            double currentPerc = 0.0;
            //using randomness, sees if it will be moved to a nation, higher scores give advantage to nations in this stage
            for (int i = 0; i < nationArrayList.size(); i++) {
                if (rand >= currentPerc && rand <= currentPerc + percentages.get(i)) {
                    inBoundsOf = nationArrayList.get(i);
                    break;
                } else {
                    currentPerc += percentages.get(i);
                }
            }
            //actually moves the resource towards the nation
            if (inBoundsOf != null && inBoundsOf.getX() < tempx) {
                tempx--;
            } else if (inBoundsOf != null && inBoundsOf.getX() > tempx) {
                tempx++;
            }
            if (inBoundsOf != null && inBoundsOf.getY() < tempy) {
                tempy--;
            } else if (inBoundsOf != null && inBoundsOf.getY() > tempy) {
                tempy++;
            }

            //ensures that the ant is still on the proper limits of the screen
            if (tempx >= gameGrid.length) {
                tempx = gameGrid.length - 1;
            } else if (tempx < 0) {
                tempx = 0;
            }
            if (tempy >= gameGrid[0].length) {
                tempy = gameGrid[0].length - 1;
            } else if (tempy < 0) {
                tempy = 0;
            }
            //moves to the square if it is empty
            if (gameGrid[tempx][tempy] == 0) {
                check = true;
                gameGrid[tempx][tempy] = 2;
                gameGrid[x][y] = 0;

                x = tempx;
                y = tempy;
            }
            //absorbs into nation if the square is not empty
            if (gameGrid[tempx][tempy] == 1) {
                gameGrid[x][y] = 0;
                check = true;
                absorbed[0] = tempx;
                absorbed[1] = tempy;
            }
            //makes sure that the program does not crash if it cannot move
            if (count > 10) {
                check = true;
            }
            count++;

        }
        return absorbed;
    }
    //uses distance formula to see how far a nation is from a square
    public double calcProximity(Nation n) {
        //distance formula
        return Math.sqrt(Math.pow(((double) n.getX() - (double) x), 2) + Math.pow(((double) n.getY() - (double) y), 2));
    }
    //checks if a resource is one square away from a nation and if it should be eaten
    public int[] checkEaten(int[][] secondGrid) {
        int[][] locations_to_check = {{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}, {x - 1, y}, {x + 1, y}};
        int[] answer = {-1, -1};
        for (int[] arr : locations_to_check) {
            if (secondGrid[arr[0]][arr[1]] == 1) {
                answer[0] = arr[0];
                answer[1] = arr[1];
                break;
            }
        }
        return answer;
    }
}

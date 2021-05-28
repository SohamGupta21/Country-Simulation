package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Stage3 {
    private final int x = 40;
    private final int y = 60;
    private final Button[][] thirdbtn = new Button[x][y];
    private final int[][] battleGrid = new int[x][y];
    private final String[][] colorArray = new String[x][y];
    public long attackTime = System.nanoTime();
    //THIRD GRID PANE
    Land[][] landGrid = new Land[x][y];
    private final ArrayList<NationPart> thirdNationParts = new ArrayList<>();
    private final ArrayList<Nation> nationsToDie = new ArrayList<Nation>();
    private final ArrayList<Nation> nationsToReplace = new ArrayList<Nation>();
    private final ArrayList<int[]> locationsToReplace = new ArrayList<int[]>();
    private ArrayList<int[]> buttonsPressed = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> nationPopulations = new ArrayList<>();
    private ArrayList<XYChart.Series> nationSeries = new ArrayList<>();
    private boolean stopStage3 = false;
    private long quakeTimer = System.nanoTime();
    private long tornadoTimer = System.nanoTime();

    private final double one_sec = 1000000000.0;
    //constructor
    public Stage3(ArrayList<Nation> nations, LineChart lineChart){
        for(Nation n : nations){
            nationPopulations.add(new ArrayList<Integer>());
            nationSeries.add(new XYChart.Series());
        }

        for(XYChart.Series s : nationSeries){
            lineChart.getData().add(s);
        }
    }
    //next couple of functions either set or return variables
    public boolean getStopStage3() {
        return stopStage3;
    }

    public ArrayList<int[]> getButtonsPressed(){
        return buttonsPressed;
    }

    public void setStopStage3(boolean bool) {
        stopStage3 = bool;
    }
    //gets random valuews in certain range
    public int getRandom(int upper, int lower) {
        int rnum = (int) (Math.random() * (upper - lower + 1)) + lower;
        return rnum;
    }
    //orders nations by score
    public void reorderNationsForBattle(ArrayList<Nation> nations) {
        //loops once for each nation and if it finds the minimum score it moves it to the front
        for (int t = 0; t < nations.size(); t++) {
            double min = nations.get(t).getTotalScore();
            int min_index = t;
            //so it doesnt look at the values that were set as minimums in previous values
            for (int i = t; i < nations.size(); i++) {
                if (nations.get(i).getTotalScore() < min) {
                    min = nations.get(i).getTotalScore();
                    min_index = i;
                }
            }
            nations.add(0, nations.remove(min_index));
        }
    }
    //updates the array of colors. this array mimics the main grid pane with colors instead of ints
    public void updateColorArray(ArrayList<Nation> nations) {
        for (int xval = 0; xval < battleGrid.length; xval++) {
            for (int yval = 0; yval < battleGrid[0].length; yval++) {
                colorArray[xval][yval] = null;
                for (Nation n : nations) {
                    colorArray[n.getX()][n.getY()] = n.getColor();
                }
                for (NationPart np : thirdNationParts) {
                    colorArray[np.getX()][np.getY()] = np.getColor();
                }
            }
        }
    }
    //next few functions return things
    public Button[][] getThirdbtn() {
        return thirdbtn;
    }

    public int[][] getBattleGrid() {
        return battleGrid;
    }

    public Land[][] getLandGrid() {
        return landGrid;
    }

    public ArrayList<NationPart> getThirdNationParts() {
        return thirdNationParts;
    }
    //spawns nations on the the battle grid
    public void spawnNationsOnBattle(ArrayList<Nation> nations) {
        //find a random place for every nation and puts their square there
        for (int n = 0; n < nations.size(); n++) {
            while (true) {
                int xLoc = getRandom(battleGrid.length - 1, 0);
                int yLoc = getRandom(battleGrid[0].length - 1, 0);
                if (battleGrid[xLoc][yLoc] == 0) {
                    battleGrid[xLoc][yLoc] = 2;
                    nations.get(n).setX(xLoc);
                    nations.get(n).setY(yLoc);
                    break;
                }

            }
        }

    }
    //resets the attack timer
    public void resetAttackTime() {
        attackTime = System.nanoTime();
    }
    //updates the color of the third stage
    public void updateScreenThird(ArrayList<Nation> nations) throws FileNotFoundException {
        //loops through all the buttons
        for (int i = 0; i < thirdbtn.length; i++) {
            for (int j = 0; j < thirdbtn[0].length; j++) {
                //empty spots are green
                if (battleGrid[i][j] == 0) {
                    thirdbtn[i][j].setStyle("-fx-background-color:#32a854");
                } else if (battleGrid[i][j] == 1) {
                    //ocean spots
                    thirdbtn[i][j].setStyle("-fx-background-color:blue");
                } else if (battleGrid[i][j] == 2) {
                    boolean colorPlaced = false;
                    for (Nation n : nations) {
                        //for every nation it changes the color at that spot and adds a star since it is a capital
                        if (n.getX() == i && n.getY() == j) {
                            Image icon = new Image(new FileInputStream("C:/Users/gupta/Java Projects/Country Simulation/src/resources/goldStar.png"));
                            ImageView imageView = new ImageView(icon);
                            imageView.setFitWidth(10);
                            imageView.setFitHeight(10);
                            //imageView.setX(imageView.getX()-7.5);
                            thirdbtn[i][j].setGraphic(imageView);
                            thirdbtn[i][j].setPadding(Insets.EMPTY);
                            thirdbtn[i][j].setStyle("-fx-background-color:" + n.getColor());
                            colorPlaced = true;
                            break;
                        }
                    }
                    //if the color was not place for the nation, prevents unnecessary looping
                    if (!colorPlaced) {
                        //loop through the nation parts array for the third stage and adds their colors
                        for (NationPart n : thirdNationParts) {
                            if (n.getX() == i && n.getY() == j) {
                                thirdbtn[i][j].setStyle("-fx-background-color:" + n.getColor());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    //expqands the nations on the grid
    public void expandNations(ArrayList<Nation> nations, ListView listView) {
        //for every nation
        for (int n = 0; n < nations.size(); n++) {
            //as long as that nation is not surrounded
            NationPart newPart = addNationPart(nations, colorArray, nations.get(n), listView);
            if (newPart != null) {
                //adds the nation part
                thirdNationParts.add(newPart);
                battleGrid[newPart.getX()][newPart.getY()] = 2;
            }
        }
    }
    //adds a nation part for a nation
    public NationPart addNationPart(ArrayList<Nation> nations, String[][] colorArray, Nation nationToAddTo, ListView listView) {
        //better way to expand would just be to add a new nation square where the resource came from
        //this will add a nations part in the correct location around the nation and then it will return the nation that it added
        int loopingDistance = 1;
        //loops until a spot to add it is found
        while (true) {
            if (loopingDistance > 60) {
                break;
            }
            int invalidPoints = 0;
            int count = 0;
            int[][] four_corners = new int[][]{{nationToAddTo.getX() - loopingDistance, nationToAddTo.getY() + loopingDistance}, {nationToAddTo.getX() + loopingDistance, nationToAddTo.getY() + loopingDistance}, {nationToAddTo.getX() + loopingDistance, nationToAddTo.getY() - loopingDistance}, {nationToAddTo.getX() - loopingDistance, nationToAddTo.getY() - loopingDistance}};
            //loops through the top line to see if there is an open spot there;
            int[] top_left = four_corners[0];
            int[] top_right = four_corners[1];
            int[] bottom_right = four_corners[2];
            int[] bottom_left = four_corners[3];
            //compiles all of the points that need to be checked into one array list
            ArrayList<int[]> completePoints = new ArrayList<>();
            for (int i = top_left[0]; i < top_right[0]; i++) {
                completePoints.add(new int[]{i, top_left[1]});
            }
            for (int i = top_right[1]; i > bottom_right[1]; i--) {
                completePoints.add(new int[]{top_right[0], i});
            }
            for (int i = bottom_right[0]; i > bottom_left[0]; i--) {
                completePoints.add(new int[]{i, bottom_right[1]});
            }
            for (int i = bottom_left[1]; i < top_left[1]; i++) {
                completePoints.add(new int[]{top_left[0], i});
            }
            int randomInd = getRandom(completePoints.size() - 1, 0);
            int[] point = completePoints.get(randomInd);
            //if the point is in the grid
            if (checkInGrid(point)) {
                //if the spot is empty then add the nation part
                if (battleGrid[point[0]][point[1]] == 0) {
                    NationPart answer = new NationPart(nationToAddTo, point[0], point[1]);
                    thirdNationParts.add(answer);
                    battleGrid[point[0]][point[1]] = 1;
                    return answer;
                } else if (battleGrid[point[0]][point[1]] == 2) {
                    //if the spot is occupied it runs a battle over the spot
                    NationPart answer = runBattle(colorArray, point[0], point[1], nations, nationToAddTo, listView);
                    if (answer != null) {
                        return answer;
                    }
                } else {
                    //if the spot is not owned by the same nation then it adds to the invldi points(which helps determine if the nation is surrounded)
                    if (!checkSameNation(nationToAddTo, point[0], point[1], nations)) {
                        invalidPoints++;
                    }
                }
            } else {
                //helps determine if the nation is surrounded
                invalidPoints++;
            }

            loopingDistance++;
            count++;
            //prevents crashes
            if (count > 50) {
                break;
            }
            //checks if the natin is surrounded
            if (8 * loopingDistance == invalidPoints) {
                break;
            }
        }
        return null;
    }
    //checks if the point is in the grid
    private boolean checkInGrid(int[] point) {
        return point[0] < battleGrid.length && point[0] >= 0 && point[1] < battleGrid[0].length && point[1] >= 0;
    }
    //checks if a point is owned by the same nation that is passed in
    private boolean checkSameNation(Nation nation, int p1, int p2, ArrayList<Nation> nations) {
        boolean sameNation = false;
        if (battleGrid[p1][p2] == 2) {
            for (Nation n : nations) {
                if (n.getX() == p1 && n.getY() == p2) {
                    if (n.getColor().equals(nation.getColor())) {
                        sameNation = true;
                    }
                }
            }
            for (NationPart n : thirdNationParts) {
                if (n.getX() == p1 && n.getY() == p2) {
                    if (n.getColor().equals(nation.getColor())) {
                        sameNation = true;
                    }
                }
            }
        }
        return sameNation;
    }
    //runs a battle between two nations
    public NationPart runBattle(String[][] colorArray, int x1, int y1, ArrayList<Nation> nations, Nation nation, ListView listView) {
        //if the spots dont belong to the same nation
        if (!nation.getColor().equals(colorArray[x1][y1])) {
            //if the location is a nation part
            boolean done = false;
            for (NationPart np : thirdNationParts) {
                //if the nation part occupies the point that we are looking at
                if (np.getX() == x1 && np.getY() == y1) {
                    double rand = Math.random();
                    //uses randomness and the nation's score to see who wins the battle
                    if (rand < (double) (nation.getTotalScore()) / (double) (nation.getTotalScore() + np.getLeader().getTotalScore())) {
                        //the nation that was passed in wins
                        listView.getItems().add(0,"The " + nation.getColor() + " nation has taken over a " + np.getColor() + " nation city.");
                        thirdNationParts.remove(np);
                        NationPart ans = new NationPart(nation, x1, y1);
                        thirdNationParts.add(ans);
                        return ans;
                    }
                }
            }
            //that location must be a nation rather than a nationpart
            if (!done) {
                //loops through nations
                for (Nation n : nations) {
                    //identifies nation
                    if (n.getX() == x1 && n.getY() == y1) {
                        double rand = Math.random();
                        //uses randomness to see who wins
                        if (rand < (double) (nation.getTotalScore()) / (double) (nation.getTotalScore() + n.getTotalScore())) {
                            listView.getItems().add(0,"The " + nation.getColor() + " nation has taken over the " + n.getColor() + " nation!!!!!");
                            //the nation that was passed in wins and takes over the entire other nation
                            for (NationPart p : thirdNationParts) {
                                if (p.getLeader() == n) {
                                    p.setLeader(nation);
                                }
                            }
                            thirdbtn[n.getX()][n.getY()].setGraphic(null);
                            nations.remove(n);
                            NationPart ans = new NationPart(nation, x1, y1);
                            thirdNationParts.add(ans);
                            return ans;
                        }
                    }
                }
            }
        }
        return null;
    }
    //runs an earthquake
    public void runQuake(int xval, int yval) {
        quakeTimer = System.nanoTime();
        //runs a timer so that the quake is animated
        new AnimationTimer() {
            int count = 0;
            @Override
            public void handle(long now) {
                //every hundredth of a second
                if(now-quakeTimer> one_sec/100){
                    //if the spot is not the ocean
                    if(battleGrid[xval + count][yval] != 1){
                        //if the earthquake occurs in the nation parts array
                        for(int np = 0; np<thirdNationParts.size(); np++){
                            //remove the parts that the nation affects
                            if(thirdNationParts.get(np).getX() == xval+count && thirdNationParts.get(np).getY() == yval){
                                battleGrid[xval + count][yval] = 0;
                                thirdNationParts.remove(np);
                            }
                        }
                    }
                    quakeTimer = System.nanoTime();
                    count++;
                }
                //stops timer
                if(count > 6){
                    this.stop();
                }
            }
        }.start();
    }
    //runs tornado
    public void runTornado(int xval, int yval) {
        tornadoTimer = System.nanoTime();
        new AnimationTimer() {
            int count = 0;
            //runs a timer so that the quake is animated
            @Override
            public void handle(long now) {
                int[][] pointsToRemove = new int[][] {{xval, yval},{xval-1, yval},{xval-1, yval-1},{xval-1,yval+1}};
                if(now-tornadoTimer> one_sec/100){
                    //loops through the points that will be destroyed
                    for(int[] point : pointsToRemove){
                        //as long as points are valid, destroys nations at that point
                        if(checkInGrid(new int[] {point[0],point[1]+count})){
                            if(battleGrid[point[0]][point[1]+count] != 1){
                                //if the earthquake occurs in the nation parts array
                                for(int np = 0; np<thirdNationParts.size(); np++){
                                    if(thirdNationParts.get(np).getX() == point[0]&& thirdNationParts.get(np).getY() == point[1]+count){
                                        battleGrid[point[0]][point[1]+count] = 0;
                                        thirdNationParts.remove(np);
                                    }
                                }
                            }
                        }
                    }

                    tornadoTimer = System.nanoTime();
                    count++;
                }
                //stops timer
                if(count > 6){
                    this.stop();
                }
            }
        }.start();
    }
    //runs a food shortage
    public void runFoodShortage(int xval, int yval, ArrayList<Nation> nations, ListView listView){
        //if the place clicked is a nation head
        if(battleGrid[xval][yval] == 2){
            //if it is a nation, decrease the nation's food score
            for(Nation n : nations){
                if(n.getX() == xval && n.getY() == yval){
                    n.setFoodScore(0);
                    listView.getItems().add(0, "The " + n.getColor() + " nation has gone through a food shortage, tough times.");
                }
            }
            //if it is a nation part decrease the part's leader's food score
            for(NationPart np : thirdNationParts){
                if(np.getX() == xval && np.getY() == yval){
                    np.getLeader().setFoodScore(0);
                    listView.getItems().add(0, "The " + np.getLeader().getColor() + " nation has gone through a food shortage, tough times.");
                }
            }
        }
    }
    //runs economic depresswion
    public void runDepression(int xval, int yval, ArrayList<Nation> nations, ListView listView){
        if(battleGrid[xval][yval] == 2){
            //if it is a nation, decrease the nation's money score
            for(Nation n : nations){
                if(n.getX() == xval && n.getY() == yval){
                    n.setMoney(0);
                    listView.getItems().add(0, "The " + n.getColor() + " nation has gone through a depression, tough times.");
                }
            }
            //if it is a nation part then decrease the money score of the part's leader
            for(NationPart np : thirdNationParts){
                if(np.getX() == xval && np.getY() == yval){
                    np.getLeader().setMoney(0);
                    listView.getItems().add(0, "The " + np.getLeader().getColor() + " nation has gone through a depression, tough times.");
                }
            }
        }
    }
    //counts population of each nation and updates accordingly
    public void updatePopulationNumbers(ArrayList<Nation> nations){
        //loops through nations to see its parts
        for(int n = 0; n<nations.size(); n++){
            int count = 0;
            //loops through parts to see who it belongs to
            for(int np = 0; np<thirdNationParts.size();np++){
                if(thirdNationParts.get(np).getLeader() == nations.get(n)){
                    count ++;
                }
            }
            nationPopulations.get(n).add(count);
        }
    }
    //updates the line chart
    public void updateLineChart(LineChart lineChart, ArrayList<Nation> nations){
        //basic setting stuff
        lineChart.setTitle("People Population");
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(true);
        //clear the series and set the name of the series
        for(int i = 0; i< nations.size(); i++){
            nationSeries.get(i).setName(nations.get(i).getColor());
            nationSeries.get(i).getData().clear();
        }
        //adds the data to the chart
        for(int s = 0 ; s < nationSeries.size(); s++){
            for (int i = 0; i < nationPopulations.get(s).size(); i++) {
                nationSeries.get(s).getData().add(new XYChart.Data(i,nationPopulations.get(s).get(i)));
            }
        }
    }

}

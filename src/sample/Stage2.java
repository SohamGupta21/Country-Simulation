package sample;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Stage2 {
    private final ArrayList<Resource> resources = new ArrayList<>();
    private final int x = 10;
    private final int y = 15;
    private final ArrayList<NationPart> secondNationParts = new ArrayList<>();
    private final Button[][] screen2btn = new Button[x][y];
    private final int[][] secondGrid = new int[x][y];
    private boolean stopStage2 = false;
    private long chartTimer = System.nanoTime();

    //sets or returns variables
    public long getChartTimer() {
        return chartTimer;
    }

    //resets the chart timer
    public void resetChartTimer() {
        chartTimer = System.nanoTime();
    }

    public int getRandom(int upper, int lower) {
        int rnum = (int) (Math.random() * (upper - lower + 1)) + lower;
        return rnum;
    }

    public Button[][] getScreen2btn() {
        return screen2btn;
    }

    public ArrayList<NationPart> getNationParts() {
        return secondNationParts;
    }

    public int[][] getSecondGrid() {
        return secondGrid;
    }

    public boolean getStopStage2() {
        return stopStage2;
    }

    public void setStopStage2(boolean b) {
        stopStage2 = b;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }
    //adds a resource to the screen
    public void addResource(int type, int resToAdd) {
        //finds a random point on the screen and adds to it if it is not taken already
        while (resToAdd > 0) {
            int xval = getRandom(secondGrid.length - 1, 0);
            int yval = getRandom(secondGrid[0].length - 1, 0);
            if (secondGrid[xval][yval] == 0) {
                resources.add(new Resource(5, xval, yval, type));
                secondGrid[xval][yval] = 2;
                resToAdd--;
            }
        }


    }
    //adds a resourdce at a certain point
    public void addResource(int type, int resToAdd, int x, int y) {
        //as long as the point is valid, it adds the point
        if (secondGrid[x][y] == 0) {
            resources.add(new Resource(5, x, y, type));
            secondGrid[x][y] = 2;
            resToAdd--;
        }
    }
    //updates the screen colors
    public void updateScreenSecond(ArrayList<Nation> nations) {
        //loops through the buttons
        for (int i = 0; i < screen2btn.length; i++) {
            for (int j = 0; j < screen2btn[0].length; j++) {
                screen2btn[i][j].setGraphic(null);
                //represents empty space
                if (secondGrid[i][j] == 0) {
                    screen2btn[i][j].setStyle("-fx-background-color:#db8b7d");
                } else if (secondGrid[i][j] == 1) {
                    //this means that the block is a nation cube
                    for (Nation n : nations) {
                        if (n.getX() == i && n.getY() == j) {
                            screen2btn[i][j].setStyle("-fx-background-color:" + n.getColor());
                        }
                    }
                    //this is a nation part cube (essentially a nation cube that is not the capital)
                    for (NationPart np : secondNationParts) {
                        if (np.getX() == i && np.getY() == j) {
                            System.out.println("this chousl be changing the color");
                            screen2btn[i][j].setStyle("-fx-background-color:" + np.getColor());
                        }
                    }
                } else if (secondGrid[i][j] == 2) {
                    //this are resources
                    //loops through the resources and changes their color and adds images where needed
                    for (Resource r : resources) {
                        if (r.getX() == i && r.getY() == j) {
                            screen2btn[i][j].setStyle("-fx-background-color:" + r.getColor());

                            Image pic = new Image(r.getPath());
                            ImageView imageView = new ImageView(pic);
                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);
                            //imageView.setX(imageView.getX()-7.5);
                            screen2btn[i][j].setGraphic(imageView);
                            screen2btn[i][j].setPadding(Insets.EMPTY);

                        }
                    }
                }
            }
        }
    }
    //adds nation part to a nation
    public NationPart addNationPart(ArrayList<Nation> nations, Nation nationToAddTo) {
        int loopingDistance = 1;
        //looks where to add
        while (true) {
            if (loopingDistance > 10) {
                break;
            }
            int invalidPoints = 0;
            //this finds the four corners of a circle around an area and expands their, therefore it expands in a spiral motion around the nation hub
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

            //for every point that needs to be checked
            for (int[] point : completePoints) {
                //if thee point is in the grid
                if (checkInGrid(point)) {
                    //add the nation part if the place is empty
                    if (secondGrid[point[0]][point[1]] == 0) {
                        NationPart answer = new NationPart(nationToAddTo, point[0], point[1]);
                        secondNationParts.add(answer);
                        secondGrid[point[0]][point[1]] = 1;
                        return answer;
                    } else {
                        //adds to the number of invalid points, this allows the looping to stop if the nation is completely surrounded with no place to expand
                        if (!checkSameNation(nationToAddTo, point[0], point[1], nations, secondNationParts)) {
                            invalidPoints++;
                        }
                    }
                } else {
                    //if the point is not in grid, it adds to the invalid points which checks to see if a nation is surrounded
                    invalidPoints++;
                }
            }
            loopingDistance++;
            count++;
            //prevents crashing
            if (count > 50) {
                break;
            }
            //if the circle is surrounded
            if (8 * loopingDistance == invalidPoints) {
                break;
            }
        }
        return null;
    }
    //update the barchart
    public void updateBarChart(BarChart bc, ArrayList<Nation> nations) {
        ArrayList<String> categories = new ArrayList<>();

        //this creates all the categories for the bar chart
        for (Nation n : nations) {
            categories.add(n.getColor());
        }

        //thie will be used in the second gird and the purpose of this is it will show every nation and each of their power attributes
        bc.setTitle("Country Summary");
        bc.getXAxis().setLabel("Country Color");
        //creates the three series for the three types of scores and adds the data
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Food Score");
        for (int i = 0; i < nations.size(); i++) {
            series1.getData().add(new XYChart.Data(categories.get(i), nations.get(i).getFoodScore()));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Resource Score");
        for (int i = 0; i < nations.size(); i++) {
            series2.getData().add(new XYChart.Data(categories.get(i), nations.get(i).getResourceScore()));
        }

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Money");
        for (int i = 0; i < nations.size(); i++) {
            series3.getData().add(new XYChart.Data(categories.get(i), nations.get(i).getMoney()));
        }
        //adds the data to the chart
        bc.getData().clear();
        bc.setCategoryGap(20);
        bc.setAnimated(false);
        bc.getData().addAll(series1, series2, series3);

    }
    //checks if a point is in the grid
    private boolean checkInGrid(int[] point) {
        return point[0] < secondGrid.length && point[0] >= 0 && point[1] < secondGrid[0].length && point[1] >= 0;
    }
    //checks if a point is owned by the same nation that is passed in
    private boolean checkSameNation(Nation nation, int p1, int p2, ArrayList<Nation> nations, ArrayList<NationPart> nationParts) {
        boolean sameNation = false;
        //if the nation is actually colored like a nation
        if (secondGrid[p1][p2] == 2) {
            //if a nation then checks if the nation has the same coords as what was passed in
            for (Nation n : nations) {
                if (n.getX() == p1 && n.getY() == p2) {
                    if (n.getColor().equals(nation.getColor())) {
                        sameNation = true;
                    }
                }
            }
            //if its a nation part it checks it the coords match with the paramters
            for (NationPart n : nationParts) {
                if (n.getX() == p1 && n.getY() == p2) {
                    if (n.getColor().equals(nation.getColor())) {
                        sameNation = true;
                    }
                }
            }
        }
        return sameNation;
    }
}

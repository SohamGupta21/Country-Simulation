package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Stage1 {
    private final String[] possible_first_names = {"Bob", "James", "Delores", "Soham", "John", "Jamal", "Happy", "Gracie", "Sean", "Dan", "Tom", "Russel", "Kobe", "Kareem Abdul", "Lew", "Oscar", "Chris", "Spencer"};
    private final String[] possible_last_names = {"White", "Jordan", "Gupta", "Cousins", "Rich", "Wall", "Fox", "Westbrook", "Malone", "Brady", "Wilson", "Bryant", "Jabbar", "Alcindor", "Robertson", "Anderson", "James"};
    private final String[] possible_nation_colors = {"turquoise", "yellow", "white", "gray", "red"};
    private final ArrayList<Integer> population = new ArrayList<>();
    private final int x = 20;
    private final int y = 30;
    private final Button[][] btn = new Button[x][y];
    private final int[][] gameGrid = new int[x][y];
    private final ArrayList<People> people = new ArrayList<>();
    private final XYChart.Series series = new XYChart.Series();
    private long dataTimer = System.nanoTime();
    private boolean addedOnce = false;
    //sets and returns variables
    public int getX(){ return x; }

    public int getY(){ return y; }

    public long getDataTimer() {
        return dataTimer;
    }
    //resets data timer
    public void resetDataTimer() {
        dataTimer = System.nanoTime();
    }

    public Button[][] getBtn() {
        return btn;
    }

    public int[][] getGameGrid() {
        return gameGrid;
    }

    public ArrayList<Integer> getPopulation() {
        return population;
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    public String[] getPossible_nation_colors() {
        return possible_nation_colors;
    }

    public void addPeople(int x, int y, ListView stage1ListView) {
        int first_name_index = (int) (Math.random() * (possible_first_names.length));
        String first = possible_first_names[first_name_index];
        int last_name_index = (int) (Math.random() * (possible_last_names.length));
        String last = possible_last_names[last_name_index];
//        if(gameGrid[7][8]==0){
        people.add(new People(x, y, first + " " + last));
        gameGrid[people.get(people.size() - 1).getX()][people.get(people.size() - 1).getY()] = 1;
//            System.out.println("test");
//        }
        stage1ListView.getItems().add(0, people.get(people.size() - 1).getName() + " is born!");
        updateScreenfirst();
    }
    //loks at the nnumber on the grid pane and updates visuals of screen
    public void updateScreenfirst() {
        //for every button
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn[0].length; j++) {
                //if it is empty, show the ground
                if (gameGrid[i][j] == 0) {
                    btn[i][j].setStyle("-fx-background-color:#db8b7d");
                    //if there is a person
                } else if (gameGrid[i][j] == 1) {
                    for (People ant : people) {
                        //loops through the people to see which one occupies this spot
                        if (ant.getX() == i && ant.getY() == j) {
                            //if it has  a nation, se tthe square to its color
                            if (ant.getNationColor() != null) {
                                btn[ant.getX()][ant.getY()].setStyle("-fx-background-color:" + ant.getNationColor());
                            } else {
                                //sets color based on gender
                                if (ant.getType() == 0) {
                                    //male
                                    if (ant.getAge() < 3) {
                                        btn[ant.getX()][ant.getY()].setStyle("-fx-background-color:#80a3e1");
                                    } else {
                                        btn[ant.getX()][ant.getY()].setStyle("-fx-background-color:#0660b0");
                                    }
                                } else {
                                    //female
                                    if (ant.getAge() < 3) {
                                        btn[ant.getX()][ant.getY()].setStyle("-fx-background-color:#ec81bf");
                                    } else {
                                        btn[ant.getX()][ant.getY()].setStyle("-fx-background-color:#ab0061");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //reproduces a person
    public ArrayList<People> sexual_reproduction(People a) {
        ArrayList<People> possible_baby = new ArrayList<>();
        a.addAge();
        //SEXUAL REPRODUCTION
        if (a.getType() == 1) {
            //this will allow us to check for if there is a man near the woman
            ArrayList<People> neighbors = locate_neighbors_people(a);
            boolean nearMale = false;
            People possible_mate = null;
            //if there is a single man nearby
            for (int i = 0; i < neighbors.size(); i++) {
                if (neighbors.get(i).getType() == 0 && !neighbors.get(i).hasSpouse()) {
                    nearMale = true;
                    possible_mate = neighbors.get(i);
                    break;
                }
            }
            //if there is a male otmate with
            if (nearMale) {
                //implements randomness to mate based on age
                if ((a.getAge() > 2 && a.getAge() < 5 && Math.random() > 0.85) || (a.getAge() > 4 && a.getAge() < 8 && Math.random() > 0.7) || (a.getAge() > 7 && a.getAge() < 9 && Math.random() > 0.8) || (a.getAge() > 8 && Math.random() > 0.8)) {
                    //sets the spouse to the person
                    possible_mate.setSpouse(a);
                    a.setSpouse(possible_mate);
                    //this code is for creating the new ant
                    int first_name_index = (int) (Math.random() * (possible_first_names.length));
                    String first = possible_first_names[first_name_index];
                    int last_name_index = (int) (Math.random() * (possible_last_names.length));
                    String last = possible_last_names[last_name_index];
                    int[] reproduction_answer = a.reproduce(locate_neighbors(a), gameGrid);
                    People baby = new People(reproduction_answer[0], reproduction_answer[1], first + " " + last);
                    possible_baby.add(baby);
                }
            }
        }
        a.resetReproduceTime();
        return possible_baby;
    }
    //finds the arraylist of people around a specific person
    public ArrayList<People> locate_neighbors_people(People a) {
        ArrayList<People> result = new ArrayList<>();
        //loops through the people to see fi they are near the passed in person
        for (People b : people) {
            if (a.getX() != b.getX() && a.getY() != b.getY()) {
                int x_diff = Math.abs(a.getX() - b.getX());
                int y_diff = Math.abs(a.getY() - b.getY());
                //the larger number is how far the two numbers are
                if (Math.max(x_diff, y_diff) == 1) {//|| b.getX() < gameGrid[0].length || b.getY() < gameGrid.length) {
                    result.add(b);
                }
            }
        }
        return result;
    }
    //locates the coordinates of a person's neighbors
    public ArrayList<int[]> locate_neighbors(People a) {
        ArrayList<int[]> result = new ArrayList<>();
        for (People b : people) {
            if (a.getX() != b.getX() && a.getY() != b.getY()) {
                int x_diff = Math.abs(a.getX() - b.getX());
                int y_diff = Math.abs(a.getY() - b.getY());
                //the larger number is how far the two numbers are
                if (Math.max(x_diff, y_diff) == 1 && b.getX() < gameGrid.length && b.getY() < gameGrid[0].length) {
                    result.add(new int[]{b.getX(), b.getY()});
                }
            }
        }
        return result;
    }
    //updates the population chart
    public void updatePopulationChart(LineChart populationChart) {
        //populationChart.setAnimated(false);

        populationChart.setTitle("People Population");
        populationChart.setCreateSymbols(false);
        //populationChart.getData().clear();
        series.setName("Population");
        series.getData().clear();
        //adds the data to the series
        for (int i = 0; i < population.size(); i++) {
            series.getData().add(new XYChart.Data(i, population.get(i)));
        }
        //adds the series only the first time
        if (!addedOnce) {
            populationChart.getData().add(series);
            addedOnce = true;
        }

    }
    //updates the pie chart
    public void updatePieChart(PieChart populationDistribution,ArrayList<Nation> nations){
        populationDistribution.setTitle("Distribution of Populations");

        int total = people.size();

        int[] popNumbers = new int[nations.size() + 1];
        //adds the numbers of population for each nation by looping through the button array and checking their colors
        for(int n = 0; n<nations.size(); n++){
            for(int i =0; i < btn.length ; i ++){
                for(int j = 0; j < btn.length;j ++){
                    if(btn[i][j].getStyle().contains(nations.get(n).getColor())){
                        popNumbers[n] ++;
                    }
                }
            }
        }

        int totalPOP = 0;
        //adds to the totalPop
        for(int num : popNumbers){
            totalPOP += num;
        }
        //adds the number of squares tha don't belong to any nation in the last part of the chart
        popNumbers[popNumbers.length-1] = total-totalPOP;
        //adds the data to the pie chart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i = 0; i< popNumbers.length -1 ; i++){
            pieChartData.add(new PieChart.Data(nations.get(i).getColor(), popNumbers[i]));
        }
        pieChartData.add(new PieChart.Data("Remaining", popNumbers[popNumbers.length-1]));
        populationDistribution.setData(pieChartData);
        populationDistribution.setLabelLineLength(5);
        populationDistribution.setLabelsVisible(true);
    }
    //increases the population by a certain amount
    public void addToPop(int add) {
        population.add(add);
    }
}

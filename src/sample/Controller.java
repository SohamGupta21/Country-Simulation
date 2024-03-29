package sample;


import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Controller {

    private final double one_sec = 1000000000.0;
    private final ArrayList<Nation> nations = new ArrayList<>();
    int x = 40;
    int y = 60;
    //GridPane gPane = new GridPane();
    Image k = new Image("resources/Koala.jpg");
    @FXML
    ListView stage1ListView, stage2ListView, stage3ListView;
    @FXML
    LineChart populationChart, lineChartStage3;
    @FXML
    ListView userControlEvents;
    //this nation part thing would allow us to simnply change the leader of a nation in order to change their allegiance
    //this is the coordinates of all of the blue points that i decided on
    //{{0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{0,13},{0,14},{0,15},{0,16},{0,17},{0,18},{0,19},{0,20},{0,21},{0,22},{0,23},{0,24},{0,25},{0,26},{0,27},{0,28},{0,29},{0,30},{0,31},{0,32},{0,33},{0,34},{0,35},{0,36},{0,37},{0,38},{0,39},{0,40},{0,41},{0,42},{0,43},{0,44},{0,45},{0,46},{0,47},{0,48},{0,49},{0,50},{0,51},{0,52},{0,53},{1,0},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{1,10},{1,11},{1,12},{1,13},{1,14},{1,15},{1,16},{1,17},{1,18},{1,19},{1,20},{1,21},{1,22},{1,23},{1,24},{1,25},{1,26},{1,27},{1,28},{1,29},{1,30},{1,31},{1,32},{1,33},{1,34},{1,35},{1,36},{1,37},{1,38},{1,39},{1,40},{1,41},{1,42},{1,43},{1,44},{1,45},{1,46},{1,50},{1,51},{1,52},{1,53},{2,0},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{2,10},{2,11},{2,12},{2,13},{2,14},{2,15},{2,16},{2,17},{2,18},{2,19},{2,20},{2,21},{2,22},{2,23},{2,24},{2,25},{2,26},{2,27},{2,28},{2,29},{2,30},{2,31},{2,32},{2,33},{2,34},{2,35},{2,36},{2,37},{2,38},{2,39},{2,40},{2,41},{2,51},{2,59},{3,0},{3,1},{3,8},{3,9},{3,10},{3,11},{3,12},{3,13},{3,14},{3,15},{3,16},{3,19},{3,20},{3,21},{3,22},{3,23},{3,24},{3,25},{3,26},{3,27},{3,28},{3,29},{3,30},{3,31},{3,32},{3,33},{3,34},{3,35},{3,36},{3,37},{3,38},{3,58},{3,59},{4,0},{4,1},{4,2},{4,9},{4,10},{4,11},{4,12},{4,13},{4,18},{4,19},{4,20},{4,21},{4,22},{4,23},{4,24},{4,25},{4,26},{4,27},{4,28},{4,29},{4,30},{4,31},{4,32},{4,33},{4,34},{4,35},{4,36},{4,37},{4,57},{4,58},{4,59},{5,0},{5,1},{5,2},{5,3},{5,18},{5,19},{5,20},{5,21},{5,22},{5,23},{5,24},{5,25},{5,26},{5,27},{5,28},{5,29},{5,30},{5,31},{5,32},{5,33},{5,34},{5,35},{5,36},{5,57},{5,58},{5,59},{6,0},{6,1},{6,2},{6,3},{6,18},{6,19},{6,20},{6,21},{6,22},{6,23},{6,24},{6,25},{6,26},{6,27},{6,28},{6,29},{6,30},{6,57},{6,58},{6,59},{7,0},{7,1},{7,2},{7,3},{7,17},{7,18},{7,19},{7,20},{7,21},{7,22},{7,23},{7,24},{7,25},{7,26},{7,27},{7,28},{7,58},{7,59},{8,0},{8,1},{8,2},{8,17},{8,18},{8,19},{8,20},{8,21},{8,22},{8,23},{8,24},{8,25},{8,26},{8,27},{8,59},{9,0},{9,1},{9,2},{9,16},{9,17},{9,18},{9,19},{9,20},{9,21},{9,22},{9,23},{9,24},{9,25},{9,26},{10,0},{10,1},{10,2},{10,15},{10,16},{10,17},{10,18},{10,19},{10,20},{10,21},{10,22},{10,23},{10,24},{10,25},{10,26},{10,27},{10,28},{10,36},{11,0},{11,1},{11,2},{11,14},{11,15},{11,16},{11,17},{11,18},{11,19},{11,20},{11,21},{11,22},{11,23},{11,24},{11,25},{11,26},{11,27},{11,28},{11,36},{11,42},{12,0},{12,1},{12,2},{12,14},{12,15},{12,16},{12,17},{12,18},{12,19},{12,20},{12,21},{12,22},{12,23},{12,24},{12,25},{12,26},{12,27},{12,34},{12,35},{12,36},{12,42},{13,0},{13,1},{13,2},{13,3},{13,13},{13,14},{13,15},{13,16},{13,17},{13,18},{13,19},{13,20},{13,21},{13,22},{13,23},{13,24},{13,25},{13,26},{13,29},{13,30},{13,31},{13,34},{13,35},{13,36},{14,0},{14,1},{14,2},{14,3},{14,8},{14,9},{14,10},{14,11},{14,13},{14,14},{14,15},{14,16},{14,17},{14,18},{14,19},{14,20},{14,21},{14,22},{14,23},{14,24},{14,25},{14,26},{14,27},{14,28},{14,29},{14,30},{14,31},{14,32},{14,33},{14,34},{14,35},{15,0},{15,1},{15,2},{15,3},{15,7},{15,8},{15,9},{15,10},{15,11},{15,12},{15,13},{15,14},{15,15},{15,16},{15,17},{15,18},{15,19},{15,20},{15,21},{15,22},{15,23},{15,24},{15,25},{15,26},{15,27},{15,32},{15,33},{15,34},{16,0},{16,1},{16,2},{16,3},{16,4},{16,7},{16,8},{16,9},{16,10},{16,11},{16,12},{16,13},{16,14},{16,15},{16,16},{16,17},{16,18},{16,19},{16,20},{16,21},{16,22},{16,23},{16,24},{16,25},{16,26},{16,33},{16,34},{17,0},{17,1},{17,2},{17,3},{17,4},{17,7},{17,8},{17,9},{17,10},{17,11},{17,12},{17,13},{17,14},{17,15},{17,16},{17,17},{17,18},{17,19},{17,20},{17,21},{17,22},{17,23},{17,24},{17,25},{17,59},{18,0},{18,1},{18,2},{18,3},{18,4},{18,5},{18,7},{18,8},{18,9},{18,10},{18,11},{18,12},{18,13},{18,14},{18,15},{18,16},{18,17},{18,18},{18,19},{18,20},{18,21},{18,22},{18,23},{18,24},{18,58},{18,59},{19,0},{19,1},{19,2},{19,3},{19,4},{19,5},{19,8},{19,9},{19,10},{19,11},{19,12},{19,13},{19,14},{19,15},{19,16},{19,17},{19,18},{19,19},{19,20},{19,21},{19,22},{19,23},{19,24},{19,41},{19,42},{19,50},{19,51},{19,52},{19,57},{19,58},{19,59},{20,0},{20,1},{20,2},{20,3},{20,4},{20,5},{20,9},{20,10},{20,11},{20,12},{20,13},{20,14},{20,15},{20,16},{20,17},{20,18},{20,19},{20,20},{20,21},{20,22},{20,23},{20,24},{20,41},{20,42},{20,43},{20,49},{20,50},{20,51},{20,52},{20,53},{20,57},{20,58},{20,59},{21,0},{21,1},{21,2},{21,3},{21,4},{21,5},{21,6},{21,7},{21,10},{21,11},{21,12},{21,13},{21,14},{21,15},{21,16},{21,17},{21,18},{21,19},{21,20},{21,21},{21,22},{21,23},{21,24},{21,25},{21,36},{21,37},{21,38},{21,40},{21,41},{21,42},{21,43},{21,44},{21,49},{21,50},{21,51},{21,52},{21,53},{21,54},{21,56},{21,57},{21,58},{21,59},{22,0},{22,1},{22,2},{22,3},{22,4},{22,5},{22,6},{22,14},{22,15},{22,16},{22,17},{22,18},{22,19},{22,20},{22,21},{22,22},{22,23},{22,24},{22,25},{22,26},{22,36},{22,37},{22,38},{22,39},{22,40},{22,41},{22,42},{22,43},{22,44},{22,45},{22,49},{22,50},{22,51},{22,52},{22,53},{22,54},{22,57},{22,58},{22,59},{23,0},{23,1},{23,2},{23,3},{23,4},{23,5},{23,6},{23,17},{23,18},{23,19},{23,20},{23,21},{23,22},{23,23},{23,24},{23,25},{23,26},{23,27},{23,28},{23,36},{23,37},{23,38},{23,39},{23,40},{23,41},{23,42},{23,43},{23,44},{23,45},{23,48},{23,49},{23,50},{23,51},{23,52},{23,53},{23,54},{23,55},{23,56},{23,57},{23,58},{23,59},{24,0},{24,1},{24,2},{24,3},{24,4},{24,5},{24,6},{24,18},{24,19},{24,20},{24,21},{24,22},{24,23},{24,24},{24,25},{24,26},{24,27},{24,28},{24,29},{24,36},{24,37},{24,38},{24,39},{24,40},{24,41},{24,42},{24,43},{24,44},{24,45},{24,46},{24,48},{24,49},{24,50},{24,51},{24,52},{24,53},{24,54},{24,55},{24,56},{24,57},{24,58},{24,59},{25,0},{25,1},{25,2},{25,3},{25,4},{25,5},{25,6},{25,18},{25,19},{25,20},{25,21},{25,22},{25,23},{25,24},{25,25},{25,26},{25,27},{25,28},{25,29},{25,30},{25,36},{25,37},{25,38},{25,39},{25,40},{25,41},{25,42},{25,43},{25,44},{25,45},{25,46},{25,47},{25,48},{25,49},{25,50},{25,51},{25,52},{25,53},{25,54},{25,55},{25,56},{25,57},{25,58},{25,59},{26,0},{26,1},{26,2},{26,3},{26,4},{26,5},{26,6},{26,18},{26,19},{26,20},{26,21},{26,22},{26,23},{26,24},{26,25},{26,26},{26,27},{26,28},{26,29},{26,30},{26,36},{26,37},{26,38},{26,39},{26,40},{26,41},{26,42},{26,43},{26,44},{26,45},{26,46},{26,47},{26,48},{26,49},{26,50},{26,51},{26,52},{26,53},{26,54},{26,55},{26,56},{26,57},{26,58},{26,59},{27,0},{27,1},{27,2},{27,3},{27,4},{27,5},{27,6},{27,7},{27,18},{27,19},{27,20},{27,21},{27,22},{27,23},{27,24},{27,25},{27,26},{27,27},{27,28},{27,29},{27,30},{27,36},{27,37},{27,38},{27,39},{27,40},{27,41},{27,42},{27,43},{27,44},{27,45},{27,46},{27,47},{27,48},{27,49},{27,50},{27,51},{27,52},{27,53},{27,54},{27,55},{27,56},{27,57},{27,58},{27,59},{28,0},{28,1},{28,2},{28,3},{28,4},{28,5},{28,6},{28,7},{28,8},{28,17},{28,18},{28,19},{28,20},{28,21},{28,22},{28,23},{28,24},{28,25},{28,26},{28,27},{28,28},{28,29},{28,30},{28,35},{28,36},{28,37},{28,38},{28,39},{28,40},{28,41},{28,42},{28,43},{28,44},{28,45},{28,46},{28,47},{28,48},{28,49},{28,50},{28,51},{28,52},{28,53},{28,54},{28,55},{28,56},{28,57},{28,58},{28,59},{29,0},{29,1},{29,2},{29,3},{29,4},{29,5},{29,6},{29,7},{29,8},{29,15},{29,16},{29,17},{29,18},{29,19},{29,20},{29,21},{29,22},{29,23},{29,24},{29,25},{29,26},{29,27},{29,28},{29,29},{29,30},{29,31},{29,35},{29,36},{29,37},{29,38},{29,39},{29,40},{29,41},{29,42},{29,43},{29,44},{29,45},{29,46},{29,47},{29,48},{29,49},{29,50},{29,51},{29,52},{29,53},{29,56},{30,0},{30,1},{30,2},{30,3},{30,4},{30,5},{30,6},{30,7},{30,8},{30,9},{30,14},{30,15},{30,16},{30,17},{30,18},{30,19},{30,20},{30,21},{30,22},{30,23},{30,24},{30,25},{30,26},{30,27},{30,28},{30,29},{30,30},{30,31},{30,32},{30,34},{30,35},{30,36},{30,37},{30,38},{30,39},{30,40},{30,41},{30,42},{30,43},{30,44},{30,45},{30,46},{30,47},{30,48},{30,49},{30,50},{30,51},{30,52},{31,0},{31,1},{31,2},{31,3},{31,4},{31,5},{31,6},{31,7},{31,8},{31,9},{31,14},{31,15},{31,16},{31,17},{31,18},{31,19},{31,20},{31,21},{31,22},{31,23},{31,24},{31,25},{31,26},{31,27},{31,28},{31,29},{31,30},{31,31},{31,32},{31,33},{31,34},{31,35},{31,36},{31,37},{31,38},{31,39},{31,40},{31,41},{31,42},{31,43},{31,44},{31,45},{31,46},{31,47},{31,48},{31,49},{31,50},{31,51},{32,0},{32,1},{32,2},{32,3},{32,4},{32,5},{32,6},{32,7},{32,8},{32,9},{32,14},{32,15},{32,16},{32,17},{32,18},{32,19},{32,20},{32,21},{32,22},{32,23},{32,24},{32,25},{32,26},{32,27},{32,28},{32,29},{32,30},{32,31},{32,32},{32,33},{32,34},{32,35},{32,36},{32,37},{32,38},{32,39},{32,40},{32,41},{32,42},{32,43},{32,44},{32,45},{32,46},{32,47},{32,48},{32,49},{32,50},{33,0},{33,1},{33,2},{33,3},{33,4},{33,5},{33,6},{33,7},{33,8},{33,9},{33,14},{33,15},{33,16},{33,17},{33,18},{33,19},{33,20},{33,21},{33,22},{33,23},{33,24},{33,25},{33,26},{33,27},{33,28},{33,29},{33,30},{33,31},{33,32},{33,33},{33,34},{33,35},{33,36},{33,37},{33,38},{33,39},{33,40},{33,41},{33,42},{33,43},{33,44},{33,45},{33,46},{33,47},{33,48},{33,49},{33,50},{34,0},{34,1},{34,2},{34,3},{34,4},{34,5},{34,6},{34,7},{34,8},{34,9},{34,14},{34,15},{34,16},{34,17},{34,18},{34,19},{34,20},{34,21},{34,22},{34,23},{34,24},{34,25},{34,26},{34,27},{34,28},{34,29},{34,30},{34,31},{34,32},{34,33},{34,34},{34,35},{34,36},{34,37},{34,38},{34,39},{34,40},{34,41},{34,42},{34,43},{34,44},{34,45},{34,46},{34,47},{34,48},{34,49},{34,50},{34,51},{35,0},{35,1},{35,2},{35,3},{35,4},{35,5},{35,6},{35,7},{35,8},{35,9},{35,13},{35,14},{35,15},{35,16},{35,17},{35,18},{35,19},{35,20},{35,21},{35,22},{35,23},{35,24},{35,25},{35,26},{35,27},{35,28},{35,29},{35,30},{35,31},{35,32},{35,33},{35,34},{35,35},{35,36},{35,37},{35,38},{35,39},{35,40},{35,41},{35,42},{35,43},{35,44},{35,45},{35,46},{35,47},{35,48},{35,49},{35,50},{35,51},{35,52},{36,0},{36,1},{36,2},{36,3},{36,4},{36,5},{36,6},{36,7},{36,8},{36,9},{36,12},{36,13},{36,14},{36,15},{36,16},{36,17},{36,18},{36,19},{36,20},{36,21},{36,22},{36,23},{36,24},{36,25},{36,26},{36,27},{36,28},{36,29},{36,30},{36,31},{36,32},{36,33},{36,34},{36,35},{36,36},{36,37},{36,38},{36,39},{36,40},{36,41},{36,42},{36,43},{36,44},{36,45},{36,46},{36,47},{36,48},{36,49},{36,50},{36,51},{36,52},{36,53},{36,54},{36,55},{36,59},{37,0},{37,1},{37,2},{37,3},{37,4},{37,5},{37,6},{37,7},{37,8},{37,9},{37,11},{37,12},{37,13},{37,14},{37,15},{37,16},{37,17},{37,18},{37,19},{37,20},{37,21},{37,22},{37,23},{37,24},{37,25},{37,26},{37,27},{37,28},{37,29},{37,30},{37,31},{37,32},{37,33},{37,34},{37,35},{37,36},{37,37},{37,38},{37,39},{37,40},{37,41},{37,42},{37,43},{37,44},{37,45},{37,46},{37,47},{37,48},{37,49},{37,50},{37,51},{37,52},{37,53},{37,54},{37,55},{37,56},{37,59},{38,0},{38,1},{38,2},{38,3},{38,4},{38,5},{38,6},{38,7},{38,8},{38,11},{38,12},{38,13},{38,14},{38,15},{38,16},{38,17},{38,18},{38,19},{38,20},{38,21},{38,22},{38,23},{38,24},{38,25},{38,26},{38,27},{38,28},{38,29},{38,30},{38,31},{38,32},{38,33},{38,34},{38,35},{38,36},{38,37},{38,38},{38,39},{38,40},{38,41},{38,42},{38,43},{38,44},{38,45},{38,46},{38,47},{38,48},{38,49},{38,50},{38,51},{38,52},{38,53},{38,54},{38,55},{38,56},{38,57},{38,58},{38,59},{39,0},{39,1},{39,2},{39,3},{39,4},{39,5},{39,6},{39,7},{39,8},{39,9},{39,10},{39,11},{39,12},{39,13},{39,14},{39,15},{39,16},{39,17},{39,18},{39,19},{39,20},{39,21},{39,22},{39,23},{39,24},{39,25},{39,26},{39,27},{39,28},{39,29},{39,30},{39,31},{39,32},{39,33},{39,34},{39,35},{39,36},{39,37},{39,38},{39,39},{39,40},{39,41},{39,42},{39,43},{39,44},{39,45},{39,46},{39,47},{39,48},{39,49},{39,50},{39,51},{39,52},{39,53},{39,54},{39,55},{39,56},{39,57},{39,58},{39,59}}
    //FIRST GRID PANE
    private Stage1 stage1;
    //SECOND GRID PANE
    private Stage2 stage2;
    private Stage3 stage3;
    //the purpose of this integer is to see how many nations are already created, so that we do not generate 50 different nations
    private int nationIteration = 0;
    private boolean nationAnimationGoing = true;
    @FXML
    private AnchorPane aPane;
    @FXML
    private GridPane gPane, growthPane, battlePane;
    @FXML
    private TabPane tabPane, mainBar1, mainBar2, mainBar3;
    @FXML
    private PieChart stage1PieChart;
    @FXML
    private BarChart stage2Bar;
    @FXML
    ImageView foodView, resourceView, moneyView;

    private int[] normalPoint = new int[2];
    //gets a random number in a certain range of values
    public int getRandom(int upper, int lower) {
        int rnum = (int) (Math.random() * (upper - lower + 1)) + lower;
        return rnum;
    }
    //sets images to certain squares, runs on start
    @FXML
    private void initialize(){
        Image food = new Image("resources/veggies.png");

        //these represent the images in the key for stage 2
        foodView.setImage(food);
        foodView.setX(5.0);
        foodView.setY(25.0);
        foodView.setFitWidth(105);
        foodView.setFitHeight(105);

        Image metal = new Image("resources/metal.png");
        resourceView.setImage(metal);
        resourceView.setX(5.0);
        resourceView.setY(10.0);
        resourceView.setFitWidth(105);
        resourceView.setFitHeight(105);

        Image money = new Image("resources/dollarSign.png");
        moneyView.setImage(money);
        moneyView.setX(2.5);
        moneyView.setY(5.0);
        moneyView.setFitWidth(105);
        moneyView.setFitHeight(105);

    }
    //start function for the first stage
    @FXML
    private void handleStart(ActionEvent event) {
        stage1 = new Stage1();
        nationAnimationGoing = true;
        //creates the buttons
        for (int i = 0; i < stage1.getBtn().length; i++) {
            for (int j = 0; j < stage1.getBtn()[0].length; j++) {

                //Initializing 2D buttons with values i,j
                stage1.getBtn()[i][j] = new Button();
                stage1.getBtn()[i][j].setStyle("-fx-background-color:#d3d3d3");
                stage1.getBtn()[i][j].setMinSize(0, 0);
                stage1.getBtn()[i][j].setPrefWidth(15);
                stage1.getBtn()[i][j].setPrefHeight(15);
//                btn[i][j].setPrefSize(25, 5);
                //Paramters:  object, columns, rows
                gPane.add(stage1.getBtn()[i][j], j, i);
                stage1.getGameGrid()[i][j] = 0;


            }
        }

        gPane.setGridLinesVisible(true);

        gPane.setVisible(true);
        //handle which adds person wherever clicked
        EventHandler z = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                System.out.println("hello, ant clicked");
                int row = GridPane.getRowIndex(((Button) t.getSource()));
                //gets the column index of which image you clicked on
                int col = GridPane.getColumnIndex(((Button) t.getSource()));
                System.out.println("row: " + row + ", col: " + col);
                stage1.addPeople(row, col, stage1ListView);
            }

        };

        //assigns to handle functions to buttons
        for (int i = 0; i < stage1.getBtn().length; i++) {
            for (int j = 0; j < stage1.getBtn()[0].length; j++) {
                stage1.getBtn()[i][j].setOnMouseClicked(z);

            }
        }

        //adds the initial people to start the game
        int peopleToAdd = 50;
        while (peopleToAdd > 0) {
            int xval = getRandom(stage1.getX() - 1, 0);
            int yval = getRandom(stage1.getY() - 1, 0);
            if (stage1.getGameGrid()[xval][yval] == 0) {
                stage1.addPeople(xval, yval, stage1ListView);
                peopleToAdd--;
            }
        }

        startFirst();
    }
    //stops the stage 1 part of the game
    @FXML
    public void stopNation() {
        nationAnimationGoing = false;
    }
    //runs the timer for the first stage
    public void startFirst() {
        stage1.addToPop(0);
        stage1.updatePopulationChart(populationChart);
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (stage1.getPeople().size() > 0) {
                    ArrayList<People> temp_people_babies = new ArrayList<>();
                    ArrayList<People> people_to_die = new ArrayList<>();
                    //for every person in the game
                    for (People a : stage1.getPeople()) {
                        //change location
                        if (now - a.getStartTime() > one_sec) {
                            //moves the person, the person moves with the spouse if they happen to have one
                            if (a.hasSpouse()) {
                                a.changeLocWithSpouse(stage1.getGameGrid());
                            } else {
                                a.changeLoc(stage1.getGameGrid());
                            }
                            //the nation joins the nation alliance
                            String new_nation_color = a.checkCollideNation(stage1.locate_neighbors_people(a));
                            if (new_nation_color != null) {
                                stage1ListView.getItems().add(0, a.getName() + " has joined the " + new_nation_color + " nation!");
                            }
                            //if collision happens with another couple, a new nation is made
                            if (nationIteration < stage1.getPossible_nation_colors().length) {
                                People p = a.checkCoupleCollideNation(stage1.locate_neighbors_people(a), nationIteration < stage1.getPossible_nation_colors().length);
                                if (p != null) {
                                    //now this will add the allegiance to the ants
                                    stage1ListView.getItems().add(0, "The " + stage1.getPossible_nation_colors()[nationIteration] + " nation is formed!");
                                    nations.add(new Nation(stage1.getPossible_nation_colors()[nationIteration]));

                                    nations.get(nationIteration).AddMember(a);
                                    nations.get(nationIteration).AddMember(a.getSpouse());
                                    nations.get(nationIteration).AddMember(p);
                                    nations.get(nationIteration).AddMember(p.getSpouse());

                                    a.setNationColor(stage1.getPossible_nation_colors()[nationIteration]);
                                    a.setMyNation(nations.get(nationIteration));
                                    a.getSpouse().setNationColor(stage1.getPossible_nation_colors()[nationIteration]);
                                    a.getSpouse().setMyNation(nations.get(nationIteration));
                                    p.setNationColor(stage1.getPossible_nation_colors()[nationIteration]);
                                    p.setMyNation(nations.get(nationIteration));
                                    p.getSpouse().setNationColor(stage1.getPossible_nation_colors()[nationIteration]);
                                    p.getSpouse().setMyNation(nations.get(nationIteration));

                                    nationIteration++;
                                }
                            }

                            a.resetStartTime();
                        }
                        //reproduce if needed/possible
                        if (now - a.getReproduceTime() > one_sec) {

                            ArrayList<People> baby = stage1.sexual_reproduction(a);

                            if (baby.size() > 0) {

                                baby.get(0).setNationColor(a.getNationColor());
                                temp_people_babies.add(baby.get(0));

                            }
                        }
                    }
                    //actually creates the people babies
                    for (People a : temp_people_babies) {
                        stage1.getPeople().add(a);
                        stage1.getGameGrid()[a.getX()][a.getY()] = 1;
                        stage1.updateScreenfirst();
                    }
                    //this removes the dead people from the list
                    for (People c : people_to_die) {
                        stage1.getPeople().remove(c);
                    }
                }
                //updates the charts on stage 1
                if (now - stage1.getDataTimer() > 3 * one_sec) {
                    stage1.getPopulation().add(stage1.getPeople().size());
                    stage1.updatePopulationChart(populationChart);
                    stage1.updatePieChart(stage1PieChart, nations);
                    stage1.resetDataTimer();
                }
                stage1.updateScreenfirst();
                if (!nationAnimationGoing) this.stop();
            }
        }.start();
    }

    //
    ///
    ////
    /////
    ////// BELOW IS CODE FOR THE SECOND SCREEN AND THE SECOND GRID PANE
    /////
    ////
    ///
    //
    @FXML
    public void handleStartGrowth() {
        stage2 = new Stage2();
        stage2.setStopStage2(false);
        //create all of the buttons in the second gridpane
        for (int i = 0; i < stage2.getScreen2btn().length; i++) {
            for (int j = 0; j < stage2.getScreen2btn()[0].length; j++) {

                //Initializing 2D buttons with values i,j
                stage2.getScreen2btn()[i][j] = new Button();
                stage2.getScreen2btn()[i][j].setId(i + "," + j);
                stage2.getScreen2btn()[i][j].setStyle("-fx-background-color:#d3d3d3");
                stage2.getScreen2btn()[i][j].setMinSize(0, 0);
                stage2.getScreen2btn()[i][j].setPrefWidth(50);
                stage2.getScreen2btn()[i][j].setPrefHeight(50);
                //Parameters:  object, columns, rows
                growthPane.add(stage2.getScreen2btn()[i][j], j, i);
                stage2.getSecondGrid()[i][j] = 0;


            }
        }

        growthPane.setGridLinesVisible(true);

        growthPane.setVisible(true);

        //assign the hub of each nation randomly in the grid and then the nations will grow from there
        ArrayList<int[]> pointsOfNations = new ArrayList<>();
        for (Nation n : nations) {
            while (true) {
                int xLoc = (int) (Math.random() * (stage2.getSecondGrid().length));
                int yLoc = (int) (Math.random() * (stage2.getSecondGrid()[0].length));
                if (stage2.getSecondGrid()[xLoc][yLoc] == 0) {
                    n.setX(xLoc);
                    n.setY(yLoc);
                    stage2.getSecondGrid()[xLoc][yLoc] = 1;
                    pointsOfNations.add(new int[]{xLoc, yLoc});
                    break;
                }
            }
        }
        int[] normal_spawn = new int[]{0,0};
        normalPoint = normal_spawn;
        //creates a new resource if clicked in an area
        EventHandler z = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                int row = GridPane.getRowIndex(((Button) t.getSource()));
                //gets the column index of which image you clicked on
                int col = GridPane.getColumnIndex(((Button) t.getSource()));
                stage2.addResource(-1, 1, row, col);
                stage2.updateScreenSecond(nations);
            }

        };
        //assigns the handle function
        for (int i = 0; i < stage2.getScreen2btn().length; i++) {
            for (int j = 0; j < stage2.getScreen2btn()[0].length; j++) {
                stage2.getScreen2btn()[i][j].setOnMouseClicked(z);
            }
        }
        stage2.addResource(-1, 5);
        stage2ListView.getItems().add("The resource sector of the simulation has begun.");
        startGrowth();
    }
    //adds resources randomly when called
    @FXML
    public void handleAddResource() {
        stage2.addResource(-1, 1);
    }
    //the time function for stage 2, which is about growth
    public void startGrowth() {

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ArrayList<Resource> resources_to_remove = new ArrayList<>();
                //loops through every resource
                for (Resource r : stage2.getResources()) {
                    //CURRENT ERROR: the resource disappears when touching a non hub square but doesn't add to the nation
                    //happens every one second
                    if (now - r.getStartTime() > one_sec) {
                        //move the location of the resource
                        int[] loc = r.changeLoc(stage2.getSecondGrid(), nations);
                        //if the value is -1 then there is not any valid place to move that the code could find, otherwise it returns a coordinate
                        if (loc[0] != -1) {
                            //looping through the nations in order to see if there is a nation that the resource should be integrated with
                            for (Nation n : nations) {
                                //if the nations share coordinates
                                if (n.getX() == loc[0] && n.getY() == loc[1]) {

                                    //loops through the nations to see which index of the nationParts array it should be adding to
                                    NationPart trial = stage2.addNationPart(nations, n);
                                    //NationPart trial = null;
                                    if (trial != null) {
                                        //adds to the nation's score
                                        if (r.getObjectType() == 1) {
                                            n.addToFoodScore(5);
                                            stage2ListView.getItems().add("The " + n.getColor() + " nation has gathered a food object,");
                                        } else if (r.getObjectType() == 2) {
                                            n.addToMoney(5);
                                            stage2ListView.getItems().add("The " + n.getColor() + " nation has gathered a money object,");
                                        } else {
                                            n.addToResourceScore(5);
                                            stage2ListView.getItems().add("The " + n.getColor() + " nation has gathered a metal object,");
                                        }
                                    }
                                    break;
                                }
                            }
                            //adds the resource to the nation's score
                            for (NationPart part : stage2.getNationParts()) {
                                if (part.getX() == loc[0] && part.getY() == loc[1]) {

                                    NationPart newPart = stage2.addNationPart(nations, part.getLeader());
                                    //NationPart newPart = null;
                                    if (newPart != null) {
                                        //adds to the nation's score`
                                        if (r.getObjectType() == 1) {
                                            part.getLeader().addToFoodScore(5);
                                            stage2ListView.getItems().add("The " + part.getLeader().getColor() + " nation has gathered a food object,");
                                        } else if (r.getObjectType() == 2) {
                                            part.getLeader().addToMoney(5);
                                            stage2ListView.getItems().add("The " + part.getLeader().getColor() + " nation has gathered a money object,");
                                        } else {
                                            part.getLeader().addToResourceScore(5);
                                            stage2ListView.getItems().add("The " + part.getLeader().getColor() + " nation has gathered a metal object,");
                                        }
                                    }
                                    //loops through the nations to see which index of the nationParts array it should be adding to
                                    break;
                                }
                            }
                            resources_to_remove.add(r);
                        }
                        r.resetStartTime();
                    }
                }
                //updates the bar chart
                if( now - stage2.getChartTimer() > one_sec * 5){
                    System.out.println("Bar chart things");
                    stage2.updateBarChart(stage2Bar, nations);
                    stage2.resetChartTimer();
                }
                //removes resources if needed
                for (Resource res : resources_to_remove) {
                    stage2.getResources().remove(res);
                }
                //the goal of this function is to have nations in this screen, the nations grow and shrink but do not move in the process
                //the resources will spawn from some location and then they will gravitate towards the nations that they "want" to go to
                stage2.updateScreenSecond(nations);
                if (stage2.getStopStage2()) this.stop();
            }
        }.start();
    }
    //stops stage 2
    public void stopStage2() {
        stage2.setStopStage2(true);
    }


    //
    ///
    ////
    /////
    ////// BELOW IS CODE FOR THE THIRD SCREEN AND THE THIRD GRID PANE
    /////
    ////
    ///
    //


    //key
    @FXML
    private void handleStartBattle() {
        //creates a stage 3 object
        stage3 = new Stage3(nations, lineChartStage3);
        int[][] blueSquares = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11}, {0, 12}, {0, 13}, {0, 14}, {0, 15}, {0, 16}, {0, 17}, {0, 18}, {0, 19}, {0, 20}, {0, 21}, {0, 22}, {0, 23}, {0, 24}, {0, 25}, {0, 26}, {0, 27}, {0, 28}, {0, 29}, {0, 30}, {0, 31}, {0, 32}, {0, 33}, {0, 34}, {0, 35}, {0, 36}, {0, 37}, {0, 38}, {0, 39}, {0, 40}, {0, 41}, {0, 42}, {0, 43}, {0, 44}, {0, 45}, {0, 46}, {0, 47}, {0, 48}, {0, 49}, {0, 50}, {0, 51}, {0, 52}, {0, 53}, {1, 0}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, {1, 13}, {1, 14}, {1, 15}, {1, 16}, {1, 17}, {1, 18}, {1, 19}, {1, 20}, {1, 21}, {1, 22}, {1, 23}, {1, 24}, {1, 25}, {1, 26}, {1, 27}, {1, 28}, {1, 29}, {1, 30}, {1, 31}, {1, 32}, {1, 33}, {1, 34}, {1, 35}, {1, 36}, {1, 37}, {1, 38}, {1, 39}, {1, 40}, {1, 41}, {1, 42}, {1, 43}, {1, 44}, {1, 45}, {1, 46}, {1, 50}, {1, 51}, {1, 52}, {1, 53}, {2, 0}, {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9}, {2, 10}, {2, 11}, {2, 12}, {2, 13}, {2, 14}, {2, 15}, {2, 16}, {2, 17}, {2, 18}, {2, 19}, {2, 20}, {2, 21}, {2, 22}, {2, 23}, {2, 24}, {2, 25}, {2, 26}, {2, 27}, {2, 28}, {2, 29}, {2, 30}, {2, 31}, {2, 32}, {2, 33}, {2, 34}, {2, 35}, {2, 36}, {2, 37}, {2, 38}, {2, 39}, {2, 40}, {2, 41}, {2, 51}, {2, 59}, {3, 0}, {3, 1}, {3, 8}, {3, 9}, {3, 10}, {3, 11}, {3, 12}, {3, 13}, {3, 14}, {3, 15}, {3, 16}, {3, 19}, {3, 20}, {3, 21}, {3, 22}, {3, 23}, {3, 24}, {3, 25}, {3, 26}, {3, 27}, {3, 28}, {3, 29}, {3, 30}, {3, 31}, {3, 32}, {3, 33}, {3, 34}, {3, 35}, {3, 36}, {3, 37}, {3, 38}, {3, 58}, {3, 59}, {4, 0}, {4, 1}, {4, 2}, {4, 9}, {4, 10}, {4, 11}, {4, 12}, {4, 13}, {4, 18}, {4, 19}, {4, 20}, {4, 21}, {4, 22}, {4, 23}, {4, 24}, {4, 25}, {4, 26}, {4, 27}, {4, 28}, {4, 29}, {4, 30}, {4, 31}, {4, 32}, {4, 33}, {4, 34}, {4, 35}, {4, 36}, {4, 37}, {4, 57}, {4, 58}, {4, 59}, {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 18}, {5, 19}, {5, 20}, {5, 21}, {5, 22}, {5, 23}, {5, 24}, {5, 25}, {5, 26}, {5, 27}, {5, 28}, {5, 29}, {5, 30}, {5, 31}, {5, 32}, {5, 33}, {5, 34}, {5, 35}, {5, 36}, {5, 57}, {5, 58}, {5, 59}, {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 18}, {6, 19}, {6, 20}, {6, 21}, {6, 22}, {6, 23}, {6, 24}, {6, 25}, {6, 26}, {6, 27}, {6, 28}, {6, 29}, {6, 30}, {6, 57}, {6, 58}, {6, 59}, {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 17}, {7, 18}, {7, 19}, {7, 20}, {7, 21}, {7, 22}, {7, 23}, {7, 24}, {7, 25}, {7, 26}, {7, 27}, {7, 28}, {7, 58}, {7, 59}, {8, 0}, {8, 1}, {8, 2}, {8, 17}, {8, 18}, {8, 19}, {8, 20}, {8, 21}, {8, 22}, {8, 23}, {8, 24}, {8, 25}, {8, 26}, {8, 27}, {8, 59}, {9, 0}, {9, 1}, {9, 2}, {9, 16}, {9, 17}, {9, 18}, {9, 19}, {9, 20}, {9, 21}, {9, 22}, {9, 23}, {9, 24}, {9, 25}, {9, 26}, {10, 0}, {10, 1}, {10, 2}, {10, 15}, {10, 16}, {10, 17}, {10, 18}, {10, 19}, {10, 20}, {10, 21}, {10, 22}, {10, 23}, {10, 24}, {10, 25}, {10, 26}, {10, 27}, {10, 28}, {10, 36}, {11, 0}, {11, 1}, {11, 2}, {11, 14}, {11, 15}, {11, 16}, {11, 17}, {11, 18}, {11, 19}, {11, 20}, {11, 21}, {11, 22}, {11, 23}, {11, 24}, {11, 25}, {11, 26}, {11, 27}, {11, 28}, {11, 36}, {11, 42}, {12, 0}, {12, 1}, {12, 2}, {12, 14}, {12, 15}, {12, 16}, {12, 17}, {12, 18}, {12, 19}, {12, 20}, {12, 21}, {12, 22}, {12, 23}, {12, 24}, {12, 25}, {12, 26}, {12, 27}, {12, 34}, {12, 35}, {12, 36}, {12, 42}, {13, 0}, {13, 1}, {13, 2}, {13, 3}, {13, 13}, {13, 14}, {13, 15}, {13, 16}, {13, 17}, {13, 18}, {13, 19}, {13, 20}, {13, 21}, {13, 22}, {13, 23}, {13, 24}, {13, 25}, {13, 26}, {13, 29}, {13, 30}, {13, 31}, {13, 34}, {13, 35}, {13, 36}, {14, 0}, {14, 1}, {14, 2}, {14, 3}, {14, 8}, {14, 9}, {14, 10}, {14, 11}, {14, 13}, {14, 14}, {14, 15}, {14, 16}, {14, 17}, {14, 18}, {14, 19}, {14, 20}, {14, 21}, {14, 22}, {14, 23}, {14, 24}, {14, 25}, {14, 26}, {14, 27}, {14, 28}, {14, 29}, {14, 30}, {14, 31}, {14, 32}, {14, 33}, {14, 34}, {14, 35}, {15, 0}, {15, 1}, {15, 2}, {15, 3}, {15, 7}, {15, 8}, {15, 9}, {15, 10}, {15, 11}, {15, 12}, {15, 13}, {15, 14}, {15, 15}, {15, 16}, {15, 17}, {15, 18}, {15, 19}, {15, 20}, {15, 21}, {15, 22}, {15, 23}, {15, 24}, {15, 25}, {15, 26}, {15, 27}, {15, 32}, {15, 33}, {15, 34}, {16, 0}, {16, 1}, {16, 2}, {16, 3}, {16, 4}, {16, 7}, {16, 8}, {16, 9}, {16, 10}, {16, 11}, {16, 12}, {16, 13}, {16, 14}, {16, 15}, {16, 16}, {16, 17}, {16, 18}, {16, 19}, {16, 20}, {16, 21}, {16, 22}, {16, 23}, {16, 24}, {16, 25}, {16, 26}, {16, 33}, {16, 34}, {17, 0}, {17, 1}, {17, 2}, {17, 3}, {17, 4}, {17, 7}, {17, 8}, {17, 9}, {17, 10}, {17, 11}, {17, 12}, {17, 13}, {17, 14}, {17, 15}, {17, 16}, {17, 17}, {17, 18}, {17, 19}, {17, 20}, {17, 21}, {17, 22}, {17, 23}, {17, 24}, {17, 25}, {17, 59}, {18, 0}, {18, 1}, {18, 2}, {18, 3}, {18, 4}, {18, 5}, {18, 7}, {18, 8}, {18, 9}, {18, 10}, {18, 11}, {18, 12}, {18, 13}, {18, 14}, {18, 15}, {18, 16}, {18, 17}, {18, 18}, {18, 19}, {18, 20}, {18, 21}, {18, 22}, {18, 23}, {18, 24}, {18, 58}, {18, 59}, {19, 0}, {19, 1}, {19, 2}, {19, 3}, {19, 4}, {19, 5}, {19, 8}, {19, 9}, {19, 10}, {19, 11}, {19, 12}, {19, 13}, {19, 14}, {19, 15}, {19, 16}, {19, 17}, {19, 18}, {19, 19}, {19, 20}, {19, 21}, {19, 22}, {19, 23}, {19, 24}, {19, 41}, {19, 42}, {19, 50}, {19, 51}, {19, 52}, {19, 57}, {19, 58}, {19, 59}, {20, 0}, {20, 1}, {20, 2}, {20, 3}, {20, 4}, {20, 5}, {20, 9}, {20, 10}, {20, 11}, {20, 12}, {20, 13}, {20, 14}, {20, 15}, {20, 16}, {20, 17}, {20, 18}, {20, 19}, {20, 20}, {20, 21}, {20, 22}, {20, 23}, {20, 24}, {20, 41}, {20, 42}, {20, 43}, {20, 49}, {20, 50}, {20, 51}, {20, 52}, {20, 53}, {20, 57}, {20, 58}, {20, 59}, {21, 0}, {21, 1}, {21, 2}, {21, 3}, {21, 4}, {21, 5}, {21, 6}, {21, 7}, {21, 10}, {21, 11}, {21, 12}, {21, 13}, {21, 14}, {21, 15}, {21, 16}, {21, 17}, {21, 18}, {21, 19}, {21, 20}, {21, 21}, {21, 22}, {21, 23}, {21, 24}, {21, 25}, {21, 36}, {21, 37}, {21, 38}, {21, 40}, {21, 41}, {21, 42}, {21, 43}, {21, 44}, {21, 49}, {21, 50}, {21, 51}, {21, 52}, {21, 53}, {21, 54}, {21, 56}, {21, 57}, {21, 58}, {21, 59}, {22, 0}, {22, 1}, {22, 2}, {22, 3}, {22, 4}, {22, 5}, {22, 6}, {22, 14}, {22, 15}, {22, 16}, {22, 17}, {22, 18}, {22, 19}, {22, 20}, {22, 21}, {22, 22}, {22, 23}, {22, 24}, {22, 25}, {22, 26}, {22, 36}, {22, 37}, {22, 38}, {22, 39}, {22, 40}, {22, 41}, {22, 42}, {22, 43}, {22, 44}, {22, 45}, {22, 49}, {22, 50}, {22, 51}, {22, 52}, {22, 53}, {22, 54}, {22, 57}, {22, 58}, {22, 59}, {23, 0}, {23, 1}, {23, 2}, {23, 3}, {23, 4}, {23, 5}, {23, 6}, {23, 17}, {23, 18}, {23, 19}, {23, 20}, {23, 21}, {23, 22}, {23, 23}, {23, 24}, {23, 25}, {23, 26}, {23, 27}, {23, 28}, {23, 36}, {23, 37}, {23, 38}, {23, 39}, {23, 40}, {23, 41}, {23, 42}, {23, 43}, {23, 44}, {23, 45}, {23, 48}, {23, 49}, {23, 50}, {23, 51}, {23, 52}, {23, 53}, {23, 54}, {23, 55}, {23, 56}, {23, 57}, {23, 58}, {23, 59}, {24, 0}, {24, 1}, {24, 2}, {24, 3}, {24, 4}, {24, 5}, {24, 6}, {24, 18}, {24, 19}, {24, 20}, {24, 21}, {24, 22}, {24, 23}, {24, 24}, {24, 25}, {24, 26}, {24, 27}, {24, 28}, {24, 29}, {24, 36}, {24, 37}, {24, 38}, {24, 39}, {24, 40}, {24, 41}, {24, 42}, {24, 43}, {24, 44}, {24, 45}, {24, 46}, {24, 48}, {24, 49}, {24, 50}, {24, 51}, {24, 52}, {24, 53}, {24, 54}, {24, 55}, {24, 56}, {24, 57}, {24, 58}, {24, 59}, {25, 0}, {25, 1}, {25, 2}, {25, 3}, {25, 4}, {25, 5}, {25, 6}, {25, 18}, {25, 19}, {25, 20}, {25, 21}, {25, 22}, {25, 23}, {25, 24}, {25, 25}, {25, 26}, {25, 27}, {25, 28}, {25, 29}, {25, 30}, {25, 36}, {25, 37}, {25, 38}, {25, 39}, {25, 40}, {25, 41}, {25, 42}, {25, 43}, {25, 44}, {25, 45}, {25, 46}, {25, 47}, {25, 48}, {25, 49}, {25, 50}, {25, 51}, {25, 52}, {25, 53}, {25, 54}, {25, 55}, {25, 56}, {25, 57}, {25, 58}, {25, 59}, {26, 0}, {26, 1}, {26, 2}, {26, 3}, {26, 4}, {26, 5}, {26, 6}, {26, 18}, {26, 19}, {26, 20}, {26, 21}, {26, 22}, {26, 23}, {26, 24}, {26, 25}, {26, 26}, {26, 27}, {26, 28}, {26, 29}, {26, 30}, {26, 36}, {26, 37}, {26, 38}, {26, 39}, {26, 40}, {26, 41}, {26, 42}, {26, 43}, {26, 44}, {26, 45}, {26, 46}, {26, 47}, {26, 48}, {26, 49}, {26, 50}, {26, 51}, {26, 52}, {26, 53}, {26, 54}, {26, 55}, {26, 56}, {26, 57}, {26, 58}, {26, 59}, {27, 0}, {27, 1}, {27, 2}, {27, 3}, {27, 4}, {27, 5}, {27, 6}, {27, 7}, {27, 18}, {27, 19}, {27, 20}, {27, 21}, {27, 22}, {27, 23}, {27, 24}, {27, 25}, {27, 26}, {27, 27}, {27, 28}, {27, 29}, {27, 30}, {27, 36}, {27, 37}, {27, 38}, {27, 39}, {27, 40}, {27, 41}, {27, 42}, {27, 43}, {27, 44}, {27, 45}, {27, 46}, {27, 47}, {27, 48}, {27, 49}, {27, 50}, {27, 51}, {27, 52}, {27, 53}, {27, 54}, {27, 55}, {27, 56}, {27, 57}, {27, 58}, {27, 59}, {28, 0}, {28, 1}, {28, 2}, {28, 3}, {28, 4}, {28, 5}, {28, 6}, {28, 7}, {28, 8}, {28, 17}, {28, 18}, {28, 19}, {28, 20}, {28, 21}, {28, 22}, {28, 23}, {28, 24}, {28, 25}, {28, 26}, {28, 27}, {28, 28}, {28, 29}, {28, 30}, {28, 35}, {28, 36}, {28, 37}, {28, 38}, {28, 39}, {28, 40}, {28, 41}, {28, 42}, {28, 43}, {28, 44}, {28, 45}, {28, 46}, {28, 47}, {28, 48}, {28, 49}, {28, 50}, {28, 51}, {28, 52}, {28, 53}, {28, 54}, {28, 55}, {28, 56}, {28, 57}, {28, 58}, {28, 59}, {29, 0}, {29, 1}, {29, 2}, {29, 3}, {29, 4}, {29, 5}, {29, 6}, {29, 7}, {29, 8}, {29, 15}, {29, 16}, {29, 17}, {29, 18}, {29, 19}, {29, 20}, {29, 21}, {29, 22}, {29, 23}, {29, 24}, {29, 25}, {29, 26}, {29, 27}, {29, 28}, {29, 29}, {29, 30}, {29, 31}, {29, 35}, {29, 36}, {29, 37}, {29, 38}, {29, 39}, {29, 40}, {29, 41}, {29, 42}, {29, 43}, {29, 44}, {29, 45}, {29, 46}, {29, 47}, {29, 48}, {29, 49}, {29, 50}, {29, 51}, {29, 52}, {29, 53}, {29, 56}, {30, 0}, {30, 1}, {30, 2}, {30, 3}, {30, 4}, {30, 5}, {30, 6}, {30, 7}, {30, 8}, {30, 9}, {30, 14}, {30, 15}, {30, 16}, {30, 17}, {30, 18}, {30, 19}, {30, 20}, {30, 21}, {30, 22}, {30, 23}, {30, 24}, {30, 25}, {30, 26}, {30, 27}, {30, 28}, {30, 29}, {30, 30}, {30, 31}, {30, 32}, {30, 34}, {30, 35}, {30, 36}, {30, 37}, {30, 38}, {30, 39}, {30, 40}, {30, 41}, {30, 42}, {30, 43}, {30, 44}, {30, 45}, {30, 46}, {30, 47}, {30, 48}, {30, 49}, {30, 50}, {30, 51}, {30, 52}, {31, 0}, {31, 1}, {31, 2}, {31, 3}, {31, 4}, {31, 5}, {31, 6}, {31, 7}, {31, 8}, {31, 9}, {31, 14}, {31, 15}, {31, 16}, {31, 17}, {31, 18}, {31, 19}, {31, 20}, {31, 21}, {31, 22}, {31, 23}, {31, 24}, {31, 25}, {31, 26}, {31, 27}, {31, 28}, {31, 29}, {31, 30}, {31, 31}, {31, 32}, {31, 33}, {31, 34}, {31, 35}, {31, 36}, {31, 37}, {31, 38}, {31, 39}, {31, 40}, {31, 41}, {31, 42}, {31, 43}, {31, 44}, {31, 45}, {31, 46}, {31, 47}, {31, 48}, {31, 49}, {31, 50}, {31, 51}, {32, 0}, {32, 1}, {32, 2}, {32, 3}, {32, 4}, {32, 5}, {32, 6}, {32, 7}, {32, 8}, {32, 9}, {32, 14}, {32, 15}, {32, 16}, {32, 17}, {32, 18}, {32, 19}, {32, 20}, {32, 21}, {32, 22}, {32, 23}, {32, 24}, {32, 25}, {32, 26}, {32, 27}, {32, 28}, {32, 29}, {32, 30}, {32, 31}, {32, 32}, {32, 33}, {32, 34}, {32, 35}, {32, 36}, {32, 37}, {32, 38}, {32, 39}, {32, 40}, {32, 41}, {32, 42}, {32, 43}, {32, 44}, {32, 45}, {32, 46}, {32, 47}, {32, 48}, {32, 49}, {32, 50}, {33, 0}, {33, 1}, {33, 2}, {33, 3}, {33, 4}, {33, 5}, {33, 6}, {33, 7}, {33, 8}, {33, 9}, {33, 14}, {33, 15}, {33, 16}, {33, 17}, {33, 18}, {33, 19}, {33, 20}, {33, 21}, {33, 22}, {33, 23}, {33, 24}, {33, 25}, {33, 26}, {33, 27}, {33, 28}, {33, 29}, {33, 30}, {33, 31}, {33, 32}, {33, 33}, {33, 34}, {33, 35}, {33, 36}, {33, 37}, {33, 38}, {33, 39}, {33, 40}, {33, 41}, {33, 42}, {33, 43}, {33, 44}, {33, 45}, {33, 46}, {33, 47}, {33, 48}, {33, 49}, {33, 50}, {34, 0}, {34, 1}, {34, 2}, {34, 3}, {34, 4}, {34, 5}, {34, 6}, {34, 7}, {34, 8}, {34, 9}, {34, 14}, {34, 15}, {34, 16}, {34, 17}, {34, 18}, {34, 19}, {34, 20}, {34, 21}, {34, 22}, {34, 23}, {34, 24}, {34, 25}, {34, 26}, {34, 27}, {34, 28}, {34, 29}, {34, 30}, {34, 31}, {34, 32}, {34, 33}, {34, 34}, {34, 35}, {34, 36}, {34, 37}, {34, 38}, {34, 39}, {34, 40}, {34, 41}, {34, 42}, {34, 43}, {34, 44}, {34, 45}, {34, 46}, {34, 47}, {34, 48}, {34, 49}, {34, 50}, {34, 51}, {35, 0}, {35, 1}, {35, 2}, {35, 3}, {35, 4}, {35, 5}, {35, 6}, {35, 7}, {35, 8}, {35, 9}, {35, 13}, {35, 14}, {35, 15}, {35, 16}, {35, 17}, {35, 18}, {35, 19}, {35, 20}, {35, 21}, {35, 22}, {35, 23}, {35, 24}, {35, 25}, {35, 26}, {35, 27}, {35, 28}, {35, 29}, {35, 30}, {35, 31}, {35, 32}, {35, 33}, {35, 34}, {35, 35}, {35, 36}, {35, 37}, {35, 38}, {35, 39}, {35, 40}, {35, 41}, {35, 42}, {35, 43}, {35, 44}, {35, 45}, {35, 46}, {35, 47}, {35, 48}, {35, 49}, {35, 50}, {35, 51}, {35, 52}, {36, 0}, {36, 1}, {36, 2}, {36, 3}, {36, 4}, {36, 5}, {36, 6}, {36, 7}, {36, 8}, {36, 9}, {36, 12}, {36, 13}, {36, 14}, {36, 15}, {36, 16}, {36, 17}, {36, 18}, {36, 19}, {36, 20}, {36, 21}, {36, 22}, {36, 23}, {36, 24}, {36, 25}, {36, 26}, {36, 27}, {36, 28}, {36, 29}, {36, 30}, {36, 31}, {36, 32}, {36, 33}, {36, 34}, {36, 35}, {36, 36}, {36, 37}, {36, 38}, {36, 39}, {36, 40}, {36, 41}, {36, 42}, {36, 43}, {36, 44}, {36, 45}, {36, 46}, {36, 47}, {36, 48}, {36, 49}, {36, 50}, {36, 51}, {36, 52}, {36, 53}, {36, 54}, {36, 55}, {36, 59}, {37, 0}, {37, 1}, {37, 2}, {37, 3}, {37, 4}, {37, 5}, {37, 6}, {37, 7}, {37, 8}, {37, 9}, {37, 11}, {37, 12}, {37, 13}, {37, 14}, {37, 15}, {37, 16}, {37, 17}, {37, 18}, {37, 19}, {37, 20}, {37, 21}, {37, 22}, {37, 23}, {37, 24}, {37, 25}, {37, 26}, {37, 27}, {37, 28}, {37, 29}, {37, 30}, {37, 31}, {37, 32}, {37, 33}, {37, 34}, {37, 35}, {37, 36}, {37, 37}, {37, 38}, {37, 39}, {37, 40}, {37, 41}, {37, 42}, {37, 43}, {37, 44}, {37, 45}, {37, 46}, {37, 47}, {37, 48}, {37, 49}, {37, 50}, {37, 51}, {37, 52}, {37, 53}, {37, 54}, {37, 55}, {37, 56}, {37, 59}, {38, 0}, {38, 1}, {38, 2}, {38, 3}, {38, 4}, {38, 5}, {38, 6}, {38, 7}, {38, 8}, {38, 11}, {38, 12}, {38, 13}, {38, 14}, {38, 15}, {38, 16}, {38, 17}, {38, 18}, {38, 19}, {38, 20}, {38, 21}, {38, 22}, {38, 23}, {38, 24}, {38, 25}, {38, 26}, {38, 27}, {38, 28}, {38, 29}, {38, 30}, {38, 31}, {38, 32}, {38, 33}, {38, 34}, {38, 35}, {38, 36}, {38, 37}, {38, 38}, {38, 39}, {38, 40}, {38, 41}, {38, 42}, {38, 43}, {38, 44}, {38, 45}, {38, 46}, {38, 47}, {38, 48}, {38, 49}, {38, 50}, {38, 51}, {38, 52}, {38, 53}, {38, 54}, {38, 55}, {38, 56}, {38, 57}, {38, 58}, {38, 59}, {39, 0}, {39, 1}, {39, 2}, {39, 3}, {39, 4}, {39, 5}, {39, 6}, {39, 7}, {39, 8}, {39, 9}, {39, 10}, {39, 11}, {39, 12}, {39, 13}, {39, 14}, {39, 15}, {39, 16}, {39, 17}, {39, 18}, {39, 19}, {39, 20}, {39, 21}, {39, 22}, {39, 23}, {39, 24}, {39, 25}, {39, 26}, {39, 27}, {39, 28}, {39, 29}, {39, 30}, {39, 31}, {39, 32}, {39, 33}, {39, 34}, {39, 35}, {39, 36}, {39, 37}, {39, 38}, {39, 39}, {39, 40}, {39, 41}, {39, 42}, {39, 43}, {39, 44}, {39, 45}, {39, 46}, {39, 47}, {39, 48}, {39, 49}, {39, 50}, {39, 51}, {39, 52}, {39, 53}, {39, 54}, {39, 55}, {39, 56}, {39, 57}, {39, 58}, {39, 59}};

        stage3.getButtonsPressed().add(new int[]{0, 0});

        userControlEvents.getItems().add("Earthquake");
        userControlEvents.getItems().add("Tornado");
        userControlEvents.getItems().add("Food Shortage");
        userControlEvents.getItems().add("Economic Depression");
        //create all of the buttons in the third gridpane
        for (int i = 0; i < stage3.getThirdbtn().length; i++) {
            for (int j = 0; j < stage3.getThirdbtn()[0].length; j++) {

                boolean isBlue = false;
                for (int[] coord : blueSquares) {

                    if (coord[0] == i && coord[1] == j) {
                        //this is supposed to be a blue square
                        isBlue = true;
                    }
                }
                //Initializing 2D buttons with values i,j
                stage3.getThirdbtn()[i][j] = new Button();
                stage3.getThirdbtn()[i][j].setStyle("-fx-background-color:#32a854");
                stage3.getThirdbtn()[i][j].setMinSize(0, 0);
                stage3.getThirdbtn()[i][j].setPrefWidth(10);
                stage3.getThirdbtn()[i][j].setPrefHeight(10);
//                btn[i][j].setPrefSize(25, 5);
                //Parameters:  object, columns, rows
                battlePane.add(stage3.getThirdbtn()[i][j], j, i);
                if (isBlue) {
                    stage3.getBattleGrid()[i][j] = 1;
                    stage3.getLandGrid()[i][j] = new Land(1, false);
                } else {
                    stage3.getBattleGrid()[i][j] = 0;
                    stage3.getLandGrid()[i][j] = new Land(0, true);
                }
            }
        }

        battlePane.setGridLinesVisible(true);

        battlePane.setVisible(true);
        //clicking an area on this screen allows for the use of powerups
        EventHandler z = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                int row = GridPane.getRowIndex(((Button) t.getSource()));
                int col = GridPane.getColumnIndex(((Button) t.getSource()));
                stage3.getButtonsPressed().add(new int[]{row, col});
            }

        };
        //assigns the handle function
        for (int i = 0; i < stage3.getThirdbtn().length; i++) {
            for (int j = 0; j < stage3.getThirdbtn()[0].length; j++) {
                stage3.getThirdbtn()[i][j].setOnMouseClicked(z);

            }
        }
        stage3.reorderNationsForBattle(nations);
        stage3.spawnNationsOnBattle(nations);
        startBattle();
    }
    //timer function for stage 3
    public void startBattle() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //updates the screen, filenot found is for the image on the capitals
                try {
                    stage3.updateScreenThird(nations);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //runs the things that need to be updated whenever
                if (now - stage3.attackTime > one_sec / 100) {
                    stage3.updateColorArray(nations);
                    stage3.expandNations(nations, stage3ListView);
                    stage3.updatePopulationNumbers(nations);
                    stage3.updateLineChart(lineChartStage3, nations);
                    stage3.resetAttackTime();
                }
                if (stage3.getStopStage3()) this.stop();

            }
        }.start();
    }
    //applies outside features to the screen
    @FXML
    public void handleUserControlEvents() {
        //finds which outside feature to be applied on the screen
        if (userControlEvents.getSelectionModel().getSelectedItem().equals("Earthquake")) {
            stage3.runQuake(stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[0], stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[1]);
        } else if (userControlEvents.getSelectionModel().getSelectedItem().equals("Tornado")) {
            stage3.runTornado(stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[0], stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[1]);
        } else if(userControlEvents.getSelectionModel().getSelectedItem().equals("Food Shortage")){
            stage3.runFoodShortage(stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[0], stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[1], nations, stage3ListView);
        } else if(userControlEvents.getSelectionModel().getSelectedItem().equals("Economic Depression")){
            stage3.runDepression(stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[0], stage3.getButtonsPressed().get(stage3.getButtonsPressed().size() - 1)[1], nations, stage3ListView);
        }
    }
    //stops the 3rd stage
    @FXML
    public void stopStage3() {
        stage3.setStopStage3(true);
    }

}

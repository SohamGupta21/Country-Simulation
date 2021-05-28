package sample;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
//in the first stage, these are the cubes
public class People {
    String loc;
    private int x;
    private int y;
    private People spouse;
    private int age;
    private final int type;
    private final String name;
    private String nationColor;
    private Nation myNation;
    private final ArrayList<int[]> locations = new ArrayList<>();
    private final XYChart.Series series = new XYChart.Series();
    private long startTime;
    private long reproduceTime;
    //constructor
    public People(int x, int y, String n) {
        this.x = x;
        this.y = y;
        this.age = 0;
        if (Math.random() > 0.5) {
            this.type = 0;
        } else {
            this.type = 1;
        }
        startTime = System.nanoTime();
        reproduceTime = System.nanoTime();
        this.name = n;
        locations.add(new int[]{this.x, this.y});
    }
    //picks a place to add another person square and reproduces
    public int[] reproduce(ArrayList<int[]> neighbors, int[][] gameGrid) {
        //{x,y+1},{x,y-1},{x+1,y},{x-1,y},
        //the four places where reproduction could happen
        int[][] possible_coords = {{x + 1, y + 1}, {x + 1, y - 1}, {x - 1, y + 1}, {x - 1, y - 1}};
        ArrayList<int[]> coord_choices = new ArrayList<>();
        //if the point can be added a baby at, then it completes the action
        for (int[] arr : possible_coords) {
            if (!neighbors.contains(arr) && arr[0]>=0&& arr[0]< gameGrid.length&& arr[1]>=0&&arr[1]< gameGrid[0].length) {
                coord_choices.add(arr);
            }
        }
        int random = (int) (Math.random() * (coord_choices.size()));
        return coord_choices.get(random);
    }
    //the next couple functions return or set variables
    public String getName() {
        return name;
    }

    public People getSpouse() {
        return spouse;
    }

    public void setSpouse(People mate) {
        spouse = mate;
    }
    //checks if the person has a spouse, used for reproduction
    public boolean hasSpouse() {
        return spouse != null;
    }
    //moves if the person has a spouse
    public void changeLocWithSpouse(int[][] gameGrid) {
        if (type == 0) {
            //runs the female code, actually finds a new place to move
            boolean check = false;
            int count = 0;
            //loops for a new place to move
            while (!check) {
                int tempx = x;
                int tempy = y;
                double x_random = Math.random();
                //redomly checks a location
                if (x_random < .33) {
                    tempx++;
                } else if (x_random < .66) {
                    tempx--;
                }
                double y_random = Math.random();
                if (y_random < .33) {
                    tempy++;
                } else if (y_random < .66) {
                    tempy--;
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
                //actually moves to that location
                if (gameGrid[tempx][tempy] == 0) {
                    check = true;
                    gameGrid[tempx][tempy] = 1;
                    locations.add(new int[]{x, y});
                    x = tempx;
                    y = tempy;
                }
                //makes sure that the program does not crash if it cannot move
                if (count > 10) {
                    check = true;
                }
                count++;
            }
        } else {
            //finds the location of the female previously and moves there
            //used if the species is a male
            int[] movingTo = spouse.getLocations().get(spouse.getLocations().size() - 1);
            gameGrid[movingTo[0]][movingTo[1]] = 1;
            locations.add(new int[]{x, y});
            gameGrid[x][y] = 0;
            x = movingTo[0];
            y = movingTo[1];
        }
    }
    //this moves the person if they don't have a spouse
    public void changeLoc(int[][] gameGrid) {
        boolean check = false;
        int count = 0;
        //randomly selects locations to check
        while (!check) {
            int tempx = x;
            int tempy = y;
            double x_random = Math.random();
            if (x_random < .33) {
                tempx++;
            } else if (x_random < .66) {
                tempx--;
            }
            double y_random = Math.random();
            if (y_random < .33) {
                tempy++;
            } else if (y_random < .66) {
                tempy--;
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
            //facing issue with array index out of bounds exception
            if (tempx < gameGrid.length && tempx >= 0 && tempy < gameGrid[0].length && tempy >= 0 && gameGrid[tempx][tempy] == 0) {
                check = true;
                gameGrid[tempx][tempy] = 1;
                locations.add(new int[]{x, y});
                gameGrid[x][y] = 0;

                x = tempx;
                y = tempy;
            }
            //makes sure that the program does not crash if it cannot move
            if (count > 10) {
                check = true;
            }
            count++;

        }
    }
    //next couple functions set or return stuff
    public String getNationColor() {
        return nationColor;
    }

    public void setNationColor(String nc) {
        this.nationColor = nc;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ArrayList<int[]> getLocations() {
        return locations;
    }
    //resets the start time
    public void resetStartTime() {
        startTime = System.nanoTime();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void resetReproduceTime() {
        reproduceTime = System.nanoTime();
    }

    public long getReproduceTime() {
        return this.reproduceTime;
    }
    //increases the age
    public void addAge() {
        this.age++;
    }

    public int getAge() {
        return this.age;
    }

    public int getType() {
        return this.type;
    }

    public Nation getMyNation() {
        return myNation;
    }

    public void setMyNation(Nation n) {
        myNation = n;
    }
    //check if a people object and the spouse collided with a nation
    public People checkCoupleCollideNation(ArrayList<People> nearbyPeople, boolean nationIterValid) {
        if (this.getSpouse() != null) {

            for (People p : nearbyPeople) {

                if (p.getSpouse() != null && p.getNationColor() == null && this.getNationColor() == null && nationIterValid) {

                    return p;
                }
            }
        }
        return null;
    }
    //check if an individual collides with a nation
    public String checkCollideNation(ArrayList<People> nearbyPeople) {
        //why is this so commonly switching the folks, that is because this code is running faster than the movement
        String answer = null;
        if (this.getSpouse() == null) {
            for (People p : nearbyPeople) {
                if (p.getNationColor() != null && Math.random() < 0.05) {
                    //this has to add a new member to the formed nation
                    this.setNationColor(p.getNationColor());
                    answer = p.getNationColor();
                    this.setMyNation(p.getMyNation());
                    p.getMyNation().AddMember(this);
                }
            }
        }
        return answer;
        //check if a couple collides into a natio
    }
}

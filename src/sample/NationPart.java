package sample;
//this represents all the nation parts except for the capital
public class NationPart {
    private Nation leader;
    private String color;
    private int x;
    private int y;
    //constructor
    public NationPart(Nation l, int x, int y){
        this.leader = l;
        this.color = l.getColor();
        this.x = x;
        this.y = y;
    }
    //next couple functions set or return certain elements
    public Nation getLeader(){
        return leader;
    }

    public String getColor(){
        return color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setLeader(Nation leader){
        this.leader = leader;
        this.color = leader.getColor();
    }

    public void setXandY(int x, int y){
        this.x = x;
        this.y = y;
    }

}

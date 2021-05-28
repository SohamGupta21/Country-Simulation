package sample;
//this can actually be used for land or sea not  just land
public class Land {
    private int type;
    private boolean passable;
    //gets the type of land and whether nations can pass it or not
    public Land(int t, boolean p){
        type = t;
        passable = p;
    }
    //returns type of land
    public int getType(){
        return type;
    }
    //returns if passable
    public boolean getPassable(){
        return passable;
    }
}

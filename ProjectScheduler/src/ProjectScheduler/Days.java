package ProjectScheduler;

import java.util.ArrayList;

public class Days {

    ArrayList<Boolean> list;

    //this retrieves X from the main controller to match the size of the grid
    public Days(){
        list = new ArrayList<>();
        for(int i=0;i<Controller.gridSize;i++){
            list.add(false);
        }
    }

    //setTrue / setFalse are called when you click on the grid. it's used for changing the list of time slots
    public void setTrue(int x){
        list.set(x, true);
    }

    public void setFalse(int x){
        list.set(x, false);
    }

    public ArrayList<Boolean> getList(){
        return list;
    }


}

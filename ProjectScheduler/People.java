package ProjectScheduler;

import java.util.ArrayList;

public class People {

    private String name;
    private ArrayList<Days> days;

    //constructor to create all 5 days of the week
    public People(String n){
        name = n;
        days = new ArrayList<>();
        for(int i=0;i<5;i++) {
            Days day = new Days();
            days.add(day);
        }
    }

    public ArrayList<Days> getDays(){
        return days;
    }

    //toString is what will be displayed in the listview
    public String toString(){
        return name;
    }
}

package ProjectScheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    public ListView listName; //public to access from FileHandler

    @FXML
    private Label whatDayText;

    //grid rectangles all getting named
    @FXML
    private Rectangle grid00, grid01, grid02, grid03,
            grid10, grid11, grid12, grid13,
            grid20, grid21, grid22, grid23,
            grid30, grid31, grid32, grid33,
            grid40, grid41, grid42, grid43;

    @FXML
    private GridPane rootGrid;

    //creating array list for listview. add to list, set list2 as observable array list
    static ArrayList<People> list = new ArrayList<>();
    ObservableList list2;
    //arraylist for the grid
    ArrayList<Rectangle> gridList = new ArrayList<>();

    //group is file name (can change to add a new group to handle multiple groups later, though file system may need to change some at that point too)
    static FileHandler fileH = new FileHandler("group", 5);

    //dayButtonIndex is used for tracking which day of the week you clicked on. days of the week are retrieved from People.java
    private int dayButtonIndex;

    //this is used for the size of the grid, which is used in Days.java and FileHandler.java (for creating an arraylist for the size of the grid)
    static int gridSize;

    public void initialize(){

        //this make's it look pretty by adding lines to list view
        //this is not needed but good reference for how to add stuff to the arraylist and display them
        //as an oberservable arraylist
        rootGrid.setVisible(false);

        for (int i=0; i< list.size(); i++)
            System.out.println(list.get(i));

        //adding all of the rectangles to the gridlist
        Collections.addAll(gridList, grid00, grid01, grid02, grid03,
                grid10, grid11, grid12, grid13,
                grid20, grid21, grid22, grid23,
                grid30, grid31, grid32, grid33,
                grid40, grid41, grid42, grid43);
        gridSize = gridList.size();
        list = fileH.LoadFile(gridSize); //loads in file data

        list2 = FXCollections.observableArrayList(list);
        listName.setItems(list2);
    }

    //method is called when you click 'Add' - creates person
    public void addButtonAction(){
        People people = new People(textField.getText());
        list.add(people);
        list2 = FXCollections.observableArrayList(list);
        listName.setItems(list2);
        textField.clear();
        System.out.println(list.size());
    }

    //method is called when you press ENTER after or while typing in the textfield
    public void addButtonActionKB(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER) {
            People people = new People(textField.getText());
            list.add(people);
            textField.clear();
            list2 = FXCollections.observableArrayList(list);
            listName.setItems(list2);
        }
    }

    //remove button
    public void removeButtonAction(){
        list.remove(listName.getSelectionModel().getSelectedIndex());
        list2 = FXCollections.observableArrayList(list);
        listName.setItems(list2);
    }

    //days of the week for displaying the grid. they all call the gridDisplay and set rootGrid to visible
    //(when the app first starts the grid is invisible for appearance)
    public void mondayAction(){
        whatDayText.setText("Monday");
        dayButtonIndex = 0;
        gridDisplay();
        rootGrid.setVisible(true);
    }

    public void tuesdayAction(){
        whatDayText.setText("Tuesday");
        dayButtonIndex = 1;
        gridDisplay();
        rootGrid.setVisible(true);
    }

    public void wednesdayAction(){
        whatDayText.setText("Wednesday");
        dayButtonIndex = 2;
        gridDisplay();
        rootGrid.setVisible(true);
    }

    public void thursdayAction(){
        whatDayText.setText("Thursday");
        dayButtonIndex = 3;
        gridDisplay();
        rootGrid.setVisible(true);
    }

    public void fridayAction(){
        whatDayText.setText("Friday");
        dayButtonIndex = 4;
        gridDisplay();
        rootGrid.setVisible(true);
    }

    //this method is called when you click somewhere in the grid
    //i've left the system out messages in place as the code used there was hard to find and would like to keep them
    //there as a reference for the future in case they might be needed again
    //the current set up is what i found to be the most efficient
    public void gridAction(MouseEvent e){
//        System.out.println(e.getSource());
//        System.out.println(gridList.indexOf(e.getSource()));
//        if(gridList.contains(((Node)e.getSource()).getId())){
        int x;
        People person = list.get(listName.getSelectionModel().getSelectedIndex());
        x = gridList.indexOf(e.getSource());
        if(person.getDays().get(dayButtonIndex).getList().get(x)) {
            person.getDays().get(dayButtonIndex).setFalse(x);
            gridDisplay();
        }else{
            person.getDays().get(dayButtonIndex).setTrue(x);
            gridDisplay();
        }

    }

    //this is for displaying the grid when you click on someone in the listview or a day of the week above the grid
    public void gridDisplay(){
        People person = list.get(listName.getSelectionModel().getSelectedIndex());
        for(int i=0;i<person.getDays().get(dayButtonIndex).getList().size();i++){
            if(person.getDays().get(dayButtonIndex).getList().get(i))
                gridList.get(i).setFill(Color.GREEN);
            else
                gridList.get(i).setFill(Color.RED);
        }
    }

}
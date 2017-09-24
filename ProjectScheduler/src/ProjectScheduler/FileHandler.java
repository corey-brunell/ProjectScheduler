package ProjectScheduler;

/**
 * This class will load the schedule info (upon startup) and save it when the program is done.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileHandler {

    private String fileName;
    private int weekLength;

    public FileHandler(String x, int weekLength){
        fileName = x + ".txt";
        this.weekLength = weekLength;
    }

    //loads file into program
    public ArrayList<People> LoadFile(int gridSize){

        ArrayList<People> list = new ArrayList<People>(); //adds file data to list, which is returned
        String fileIn= fileName;
        try {
            File inputFile = new File(fileIn);
            Scanner input = new Scanner(inputFile);
            int peopleCount = 0;
            while (input.hasNextLine()){ //loops through people
                String personName = input.nextLine();
                //adds person name to list
                list.add(new People(personName));
                for (int currDay = 0; currDay < weekLength; currDay++) //loops through days; adds data for one full day per looping
                {
                    String scheduleDay = input.nextLine();
                //    StringTokenizer timeSegment = new StringTokenizer(scheduleDay," "); //breaks up time segments within 1 line
                //    System.out.println("$" + timeSegment +"$");
                    for (int currTimeSeg=0; currTimeSeg < gridSize; currTimeSeg++) //loops adding each 1/2 hour segment to schedule
                    {
                        char test = scheduleDay.charAt(currTimeSeg); //changed to use chars instead of tokens
                        if (test =='1') {
                            list.get(peopleCount).getDays().get(currDay).setTrue(currTimeSeg);
                        }
                    //    timeSegment.nextToken(); //goes to next time Segment
                    }
                }
                peopleCount ++;
            }
            input.close(); //closes scanner
            System.out.println("File successfully loaded.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating file now.");
            //if file does not exist already, creates it (first startup only)
            String groupSchedule = fileName;
            try {
                PrintWriter outputFile = new PrintWriter(new FileWriter(fileName, true)); //true means will not overwrite if file already exists
                outputFile.close();
                System.out.println("File successfully created.");
            } catch (IOException e2) {
                e2.getCause().printStackTrace();
                System.out.println("Error: Failed to create group file.");
            }
        }
        return list; //returns arraylist of people to Controller method
    }
    //saves schedule info into file.
    public void saveFile(int gridSize, ArrayList<People> list){
        System.out.println("Saving ...");
        try {
            PrintWriter input = new PrintWriter(new FileWriter(fileName));
            for (int i=0; i < list.size(); i++) {     // loops for each person in people arraylist
                input.println(list.get(i).toString()); //prints name of person
                //prints each daily schedule for this person
                for (int day = 0; day < weekLength; day ++){
                    //prints T/F for each time segment in the day
                    for (int tSeg = 0; tSeg < gridSize; tSeg++)
                    {
                        if (list.get(i).getDays().get(day).getList().get(tSeg)) //if time segment is TRUE
                            input.print(1); // I got rid of the space after each number to use chars instead of tokens
                        else if(!list.get(i).getDays().get(day).getList().get(tSeg))
                            input.print(0);
                    }
                    input.println(); //goes to next day's schedule (new line)
                }
            }
            System.out.println("Saved File.");
            input.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error Saving: Saving Failed.");
        }
    }
}

package ohtu;

import java.util.ArrayList;

/**
 *
 * @author mxsampsa
 */
public class CourseInformation {
    private String name;
    private String term;
    private String week1;
    private String week2;
    private String week3;
    private String week4;
    private String week5;
    private String week6;
    private ArrayList<String> list;
    
    public String getName() {
        return name;
    }
    
    public String getTerm() {
        return term;
    }
    
    public void initWeeksList() {
        list = new ArrayList();
        list.add(week1);
        list.add(week2);
        list.add(week3);
        list.add(week4);
        list.add(week5);
        list.add(week6);
    }
    
    public String getFromList(int listIndex) {
        return list.get((listIndex-1));
    }
    
    @Override
    public String toString() {
        return name;
    }
}

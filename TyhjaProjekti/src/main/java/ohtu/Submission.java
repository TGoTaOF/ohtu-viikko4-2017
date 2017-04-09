package ohtu;

import java.util.ArrayList;

public class Submission {
    //private String id;
    private String student_number;
    //private String last_name;
    //private String first_name;
    private String week;
    private String points;
    //private String identifier;
    private String hours;
    //private String comments;
    //private String email;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String a7;
    private String a8;
    private String a9;
    private String a10;
    private String a11;
    private String a12;
    private String a13;
    private String a14;
    private String a15;
    private String a16;
    private String a17;
    private String a18;
    private String a19;
    private String a20;
    private String a21;
    //private String created_at;
    //private String updated_at;
    //private String course_id;
    //private String student_id;
    //private String challenging;
    private String pointsString;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        return student_number;
    }
    
    public String getWeek() {
        return week;
    }
    
    public String getHours() {
        return hours;
    }
    
    //Prototype for getting done questions, EXtremely grude.
    public void checkPoints() {
        ArrayList<String> list = new ArrayList();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);
        list.add(a9);
        list.add(a10);
        list.add(a11);
        list.add(a12);
        list.add(a13);
        list.add(a14);
        list.add(a15);
        list.add(a16);
        list.add(a17);
        list.add(a18);
        list.add(a19);
        list.add(a20);
        list.add(a21);
        String ret = "";
        int total = 0;
        for (int i = 0; i < 21; i++) {
            if (list.get(i) != null) {
                ret = ret + " " + (i+1);
                total++;
            }
        }
        if (ret.equals("")) {
            points = "0";
        } else {
            points = Integer.toString(total);
        }
        pointsString = ret;
    }
    
    public String getPoints() {
        return points;
    }
    
    public String pointsToString() {
        return pointsString;
    }
}
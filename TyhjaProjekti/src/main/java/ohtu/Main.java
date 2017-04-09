package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url1 = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String bodyText1 = getJsonData(url1);
        String bodyText2 = getJsonData(url2);
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText1, Submission[].class);
        CourseInformation course = mapper.fromJson(bodyText2, CourseInformation.class);
        
        
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm() + "\n");
        course.initWeeksList();
        
        System.out.println("opiskelijanumero: " + subs[0].getStudent_number() + "\n");
        int hoursTotal = 0;
        int pointsTotal = 0;
        for (Submission submission : subs) {
            submission.checkPoints();
            System.out.print(" viikko " + submission.getWeek() 
                    + ": tehtyjä tehtäviä yhteensä: " + submission.getPoints() + " (maksimi " + course.getFromList(Integer.parseInt(submission.getWeek())) + "), aikaa kului " 
                    + submission.getHours() + " tuntia, tehdyt tehtävät:");
            System.out.println(submission.pointsToString());
            hoursTotal= hoursTotal + Integer.parseInt(submission.getHours());
            pointsTotal= pointsTotal + Integer.parseInt(submission.getPoints());
        }
        System.out.println("\nyhteensä: " + pointsTotal + " tehtävää " + hoursTotal + " tuntia");
    }
    
    public static String getJsonData(String url) throws IOException {
        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
        
        return bodyText;
    }
}
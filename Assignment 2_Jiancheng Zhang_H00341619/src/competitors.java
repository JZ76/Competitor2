import java.io.IOException;
import java.util.ArrayList;

public class competitors {
    public static void main(String[] args) throws IOException {
        //CompetitorList need a ArrayList as instance variable
        ArrayList<Competitor> competitorsList = new ArrayList<>();
        CompetitorList check = new CompetitorList(competitorsList);
        //call fileIO constructor
        FileIO newFile = new FileIO();
        //read files
        newFile.inputGunFighter("GunFighter_list.txt", check);
        newFile.inputKnight("Knight_list.txt", check);
        newFile.inputRacer("Racer_list.txt", check);
        //generate a report
        String report = check.allCompetitors(competitorsList)
                      + "\n" + "STATISTICAL:"
                      + check.overallScore1st();
        //create a GUI
        CompetitorGUI newGUI = new CompetitorGUI(check, newFile, report);
    }
}

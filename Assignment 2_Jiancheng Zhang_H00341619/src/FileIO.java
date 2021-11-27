import java.io.*;
import java.util.Scanner;

public class FileIO {
    //those input method below actually share some same code,
    // the difference is their own instance variables
    //I tried to kill duplicate code, but that will not increase the readability

    //read Gunfighter list from file
    public void inputGunFighter(String fileName, CompetitorList cList) throws FileNotFoundException {
        //call file and scanner constructor
        File    file    = new File(fileName);
        Scanner scanner = new Scanner(file);
        String in;
        //assuming no wrong information from input files
        //read until there is no line
        while (scanner.hasNextLine()) {

            in = scanner.nextLine();
            if (!in.isBlank()){
                String[] section = in.split(",");
                String id      = section[0].replace(" ", "");
                String name    = section[1].trim();
                String level   = section[2].replace(" ", "");
                String country = section[3].trim();
                String age     = section[4].replace(" ", "");
                //There are 5 scores in each competitor
                int n = 0;
                Integer[] scores = new Integer[5];
                for (int s = 5; s < 10; s++) {
                    scores[n] = Integer.parseInt(section[s].trim());
                    n++;
                }
                //own fields start from No.11
                String gun     = section[10].trim();
                double KD      = Double.parseDouble(section[11].trim());
                //pass variables into Competitor constructor
                Competitor c = new gunFighter(id, new Name(name), level, country, age, scores, gun, KD);
                //add each competitor to list
                cList.addCompetitors(c);
            }
        }
    }

    //read Knight list from file
    public void inputKnight(String fileName, CompetitorList cList) throws FileNotFoundException {
        File    file    = new File(fileName);
        Scanner scanner = new Scanner(file);
        String in;
        while (scanner.hasNextLine()) {

            in = scanner.nextLine();
            if (!in.isBlank()){
                String[] section = in.split(",");
                String id      = section[0].replace(" ", "");
                String name    = section[1].trim();
                String level   = section[2].replace(" ", "");
                String country = section[3].trim();
                String age     = section[4].replace(" ", "");

                int n = 0;
                Integer[] scores = new Integer[5];
                for (int s = 5; s < 10; s++) {
                    scores[n] = Integer.parseInt(section[s].trim());
                    n++;
                }

                String horse   = section[10].trim();
                String armor   = section[11].trim();
                String weapon  = section[12].trim();

                Competitor c = new knight(id, new Name(name), level, country, age, scores, horse, armor, weapon);
                cList.addCompetitors(c);
            }
        }
    }

    //read Racer list from file
    public void inputRacer(String fileName, CompetitorList cList) throws FileNotFoundException {
        File    file    = new File(fileName);
        Scanner scanner = new Scanner(file);
        String in;
        while (scanner.hasNextLine()) {

            in = scanner.nextLine();
            if (!in.isBlank()){
                String[] section = in.split(",");
                String id      = section[0].replace(" ", "");
                String name    = section[1].trim();
                String level   = section[2].replace(" ", "");
                String country = section[3].trim();
                String age     = section[4].replace(" ", "");

                int n = 0;
                Integer[] scores = new Integer[5];
                for (int s = 5; s < 10; s++) {
                    scores[n] = Integer.parseInt(section[s].trim());
                    n++;
                }

                String car     = section[10].trim();
                String type    = section[11].trim();

                Competitor c = new racer(id, new Name(name), level, country, age, scores, car, type);
                cList.addCompetitors(c);
            }
        }
    }

    //output the report
    public void output(String fileName, String someText) throws IOException {
        FileWriter  out    = new FileWriter(fileName);
        PrintWriter output = new PrintWriter(out);
        output.print(someText);
        output.close();
        //ergonomics friendly
        System.out.println("The report has successfully written to " + fileName);
    }
}

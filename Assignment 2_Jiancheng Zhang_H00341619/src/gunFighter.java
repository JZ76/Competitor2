import java.util.Arrays;
import java.util.Collections;

public class gunFighter extends Competitor{

    private String gun;
    private double KD;

    //a gunfighter need a gun and a radio kill per dead to judge their performance
    public gunFighter(String id, Name n, String level, String country, String age, Integer[] scores, String gun, double KD){
        super(id, n, level, country, age, scores);
        this.gun = gun;
        this.KD  = KD;
    }

    //the overall score of highest level gunfighter is the max score,
    //and the lowest is sum 4 scores in DESC order and divided by 4 to get a average
    public double getOverallScore() {
        //sort scores array in DESC
        Arrays.sort(scores, Collections.reverseOrder());
        //match each level
        switch (level) {
            case "Legendary":
                return (scores[0]) * 1.0 / 1;
            case "Rare":
                return (scores[0] + scores[1]) * 1.0 / 2;
            case "Uncommon":
                return (scores[0] + scores[1] + scores[2]) * 1.0 / 3;
            case "Common":
                return (scores[0] + scores[1] + scores[2] + scores[3]) * 1.0 / 4;
        }
        return 0;
    }

    //this is method only own by gunfighter that know the detail of revolvers
    public String getDetailOfGun(String g){
        switch (g) {
            case "Colt M1873":
                return "Colt M1873 is a single-action revolver with a revolving cylinder holding six metallic cartridges.";
            case "Colt M1892":
                return "Colt M1892 is the first general issue double-action revolver with a swing out cylinder";
            case "Colt M1851":
                return "Colt M1851 is a cap and ball revolver that was designed by Samuel Colt between 1847 and 1850.";
            case "Smith&Wesson Model 3":
                return "The Smith & Wesson Model 3 is a single-action, cartridge-firing, top-break revolver";
            case "Smith&Wesson Model 500":
                return "The Smith & Wesson Model 500 is a five-shot, double/single action large-caliber revolver";
        }
        return null;
    }

    public String getGun() {
        return gun;
    }

    public double getKD() {
        return KD;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public void setKD(double KD) {
        this.KD = KD;
    }
}

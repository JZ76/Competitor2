public class knight extends Competitor{

    private String horse;
    private String armor;
    private String weapon;

    //a knight ride a horse and wear a piece of armor and hold a weapon
    public knight(String id, Name n, String level, String country, String age, Integer[] scores, String horse, String armor, String weapon){
        super(id, n, level, country, age, scores);
        this.horse  = horse;
        this.armor  = armor;
        this.weapon = weapon;
    }

    //this overall score is calculate a average of all scores without max and min score.
    public double getOverallScore() {
        int max = scores[0];
        int maxIndex = 0;
        int min = scores[0];
        int minIndex = 0;

        //get the max and min score and position in array
        for(int index = 0; index < scores.length; index ++){
            if (scores[index] >= max) {
                max = scores[index];
                maxIndex = index;
            }
            //if all scores in array are same, we have to make sure both max and min position cannot be same
            if ((scores[index] <= min) && (index != maxIndex)) {
                min = scores[index];
                minIndex = index;
            }
        }

        //to sum without max and min score, and we already know the position
        double sum = 0;
        for(int index = 0; index < scores.length; index++){
            if (!(index == maxIndex || index == minIndex)){
                sum += scores[index];
            }
        }
        return sum / (scores.length-2);
    }

    //this is method only own by knight that know the detail of horses
    public String getDetailOfHorse(String h){
        switch (h) {
            case "Arabian":
                return "Arabian horses have very good temperaments, are alert and eager to please. They are fast, quick to learn, and have a willingness for training, though they will not tolerate abuse.";
            case "Missouri Fox Trotter":
                return "The Missouri Fox Trotter is a gaited breed especially suited to working with livestock.";
            case "Turkoman":
                return "An Oriental horse breed, it is a bold horse known for its slender body and high endurance.";
            case "Ardennes":
                return "A cold-blooded breed, Ardennes have a fantastic temperament and are prized for their ability to not become agitated.";
        }
        return null;
    }

    public String getHorse() {
        return horse;
    }

    public String getArmor() {
        return armor;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}

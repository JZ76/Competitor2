import java.util.Arrays;

abstract public class Competitor {
    //these are common fields share with three kinds of competitors
    protected String    id;
    protected Name      name;
    protected String    level;
    protected String    country;
    protected String    age;
    protected Integer[] scores;

    public Competitor(String id, Name n, String level, String country, String age, Integer[] scores){
        this.id      =id;
        this.name    =n;
        this.level   =level;
        this.country =country;
        this.age     =age;
        this.scores  =scores;
    }

    //each kind of competitor has different way to calculate overall score
    public abstract double getOverallScore();

    //but the format of short details are same
    public String getShortDetails(){
        return "CN " + getId()
                     + "\t ("
                     + name.getInitials()
                     + ") \thas overall score "
                     + String.format("%.1f",getOverallScore())
                     + ".";
    }

    public String getId() {
        return id;
    }

    public Name   getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getCountry() {
        return country;
    }

    public String getAge() {
        return age;
    }

    public String getScoreArray() {
        String scoreArray = Arrays.toString(scores);
        return scoreArray.substring(1,scoreArray.length()-1);
    }

    public Integer[] getScores() { return scores; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setScores(Integer[] scores) {
        this.scores = scores;
    }
}

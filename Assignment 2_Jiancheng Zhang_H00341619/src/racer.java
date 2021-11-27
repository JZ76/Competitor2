public class racer extends Competitor {

    private String car;
    private String type;

    //a racer has preference on car and type of car
    public racer(String id, Name n, String level, String country, String age, Integer[] scores, String car, String type){
        super(id, n, level, country, age, scores);
        this.car  = car;
        this.type = type;
    }

    //the overall score is added different weights to each score in array and get average
    public double getOverallScore() {
        double sum = scores[0] * 0.8
                   + scores[1] * 0.8
                   + scores[2] * 1.2
                   + scores[3] * 1.1
                   + scores[4] * 1.1;
        return sum / 5;
    }

    //this is method only own by racer that know the detail of cars
    public String getDetailOfCar(String c){
        switch (c) {
            case "Pfister Comet":
                return "This was made for only one thing: to make every other sports car look like it's the asthmatic kid in gym.";
            case "Benefactor Dubsta":
                return "Is it an SUV? Is it a muscle car? Is it serious military hardware for the oligarch market? It's all of the above with six wheels. " +
                       "No dictator or business leader should be seen without it.";
            case "Vapid Dominator":
                return "Step one: take the best-looking muscle car the 60's ever saw, and introduce it to the greatest American supercar of the modern era." +
                       "Step two: leave them alone in a quiet garage with a few dozen shots of high octane gas, plenty of axel grease and nothing else to do." +
                       "Step three: the Dominator is born, and it's hungry.";
            case "Enus Paragon":
                return "It took generations of privately educated stiff upper lips, all prepared to dig deep into bottomless wells of lazy entitlement - but credit where it's due." +
                       "This is kind of self-assurance that can't be earned. It can only be bought.";
            case "Truffade Adder":
                return "The Adder's monstrous 8-liter engine burns fuel faster than a blazing oil refinery, but its top speed can hold its own against a guided missile, " +
                       "making it the perfect all-round car for life in a busy urban metropolis.";
        }
        return null;
    }

    public String getCar() {
        return car;
    }

    public String getType() {
        return type;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setType(String type) {
        this.type = type;
    }
}

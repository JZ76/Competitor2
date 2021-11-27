import java.util.Comparator;

//to meet the requirement in mark sheet that using Comparator interface
public class AgeComparator implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        String n1 = o1.getAge();
        String n2 = o2.getAge();
        //in ASC order
        return n1.compareTo(n2);
    }
}

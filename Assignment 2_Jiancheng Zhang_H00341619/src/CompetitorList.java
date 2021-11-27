import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

    /**
     * This class is the to store competitors in an ArrayList
     * and is used to give information of competitors in various ways.
     * @author Jiancheng Zhang
     */
public class CompetitorList extends ArrayList<Competitor>{

    private ArrayList<Competitor> competitorsList;

    /**
     * Creates a CompetitorList object with an ArrayList.
     */
    public CompetitorList(ArrayList<Competitor> competitorsList) {
        this.competitorsList = competitorsList;
    }

    /**
     * Add a competitor into the list and check whether there is a same primary key.
     *
     * @param c is a competitor that going to be added.
     *          if the id isn't exist in list then add,
     *          or get the name of the competitor who has a same id and print it.
     */
    public void addCompetitors(Competitor c) {
        String id    = c.getId();
        String cList = isIDExist(id);
        if (cList == null) {
            this.competitorsList.add(c);
        }else {
            String error = "";
            //find which competitor in list has a same competitor number as input
            for (Competitor x : competitorsList) {
                if (x.getId().equals(id)) {
                    error += x.getId() + " " + x.getName().getFullName();
                }
            }
            System.out.println("Duplicate primary key(Competitor number): The " + id + " is belong to the competitor below: " + error);
        }
    }

    /**
     * check if there is a id in the competitor list.
     *
     * @param id is the id need to search.
     * @return null if the id that looking for doesn't in list.
     * otherwise the short details of the competitor with that id if there it is.
     */
    public String isIDExist(String id) {
        for (Competitor c : competitorsList) {
            if (c.getId().equals(id)) {  //check every competitor in list have matched id
                return c.getShortDetails();
            }
        }
        return null;
    }

    /**
     * To create a formative long string of information of every competitors.
     * @param list is used for store competitors, there will be different list
     * @return a table of information of all competitors in the list
     */
    public String allCompetitors(ArrayList<Competitor> list) {
        String a = "Competitors table:\n";
        StringBuilder table = new StringBuilder();
        for (Competitor c : list) {
            table.append(c.getShortDetails()).append("\n");
        }
        return a + table;
    }

        /**
         * To generate a table for show in GUI
         * @param list the original list will be sorted in many ways
         * @return a table of competitor information in that list
         */
    public String listInGUI(ArrayList<Competitor> list) {
        StringBuilder table = new StringBuilder();

        for (Competitor c : list) {
            //this is all kinds of competitor have
            table.append(c.getId())
                 .append("\t").append(c.getName().getLCF())
                 .append("\t").append(c.getAge())
                 .append("\t").append(String.format("%.1f", c.getOverallScore()))
                 .append("\t").append(c.getLevel())
                 .append("\t");
            if (c instanceof gunFighter) {
                //this is gunfighter competitor has
                table.append(((gunFighter) c).getGun())
                     .append("\t").append(((gunFighter) c).getKD())
                     .append("\n");
            } else if (c instanceof knight) {
                //this is knight competitor has
                table.append(((knight) c).getHorse())
                     .append("\t").append(((knight) c).getWeapon())
                     .append("\t").append(((knight) c).getArmor())
                     .append("\n");
            } else if (c instanceof racer) {
                //this is racer competitor has
                table.append(((racer) c).getCar())
                     .append("\t").append(((racer) c).getType())
                     .append("\n");
            }
        }
        return table.toString();
    }

    /**
     * to find out who got the highest overall score and
     * maybe there are more than one competitor got the same score
     *
     * @return the competitors who have the highest overall score
     * @throws IndexOutOfBoundsException which means the input data has problem,
     *                                   wrong file or no data meet requirements
     */
    public String overallScore1st() {
        try {
            double smax = competitorsList.get(0).getOverallScore();
            //if there is no more than one competitor has same highest overall score, start from this
            String a = "\n" + "The person who has the highest overall score is ";
            //if there is more than one competitor has same highest overall score, start from this
            String b = "\n" + "The competitors below got same highest overall score: ";
            String name = competitorsList.get(0).getName().getFullName();

            for (Competitor c : competitorsList) {  //get the max overall score and that competitor's name
                if (smax < c.getOverallScore()) {
                    smax = c.getOverallScore();
                    name = c.getName().getFullName();
                }
            }
            //we need to know how many competitor have same score
            int i = 0;
            for (Competitor c : competitorsList) {
                if (smax == c.getOverallScore()) {
                    b += c.getName().getFullName() + ", ";
                    i++;
                }
            }

            if (i == 1) {//if the highest overall score is gotten by one competitor
                return a + name + " with a score of " + String.format("%.1f", smax) + " ." + "\n";
            } else {
                return b + "and the overall score is " + String.format("%.1f", smax) + " ." + "\n";
            }
        } catch (IndexOutOfBoundsException index) {
            System.out.println("There is no competitor in list! " + index.getMessage());
            System.exit(1);
        }
        return null;
    }

        /**
         * changing one competitor's score and show the overall score after change
         * @param id should know whose score need to change
         * @param s an array of scores
         * @return if the id is in the competitor list, return the overall score.
         * if not return a error message.
         *
         */
    public String alterScores(String id, Integer[] s) {
        for (Competitor c : competitorsList) {
            if (c.getId().equals(id)) {
                c.setScores(s);
                return "The " + c.getId() + " competitor overall score has change to " + String.format("%.1f", c.getOverallScore());
            }
        }
        return "There no such competitor in the list! Try again.";
    }

        /**
         * actually as same as isIDExist() method just with different return message.
         * @param id the competitor that user is looking for
         * @return a short detail if find, an error message if not.
         */
    public String searchCompetitor(String id) {
        if (isIDExist(id) == null) {
            return "Sorry, there is no such competitor number. Try again please.";
        } else {
            return isIDExist(id);
        }
    }

        /**
         * sort gunfighter competitors in age ASC order.
         * @return a table of competitors after sorting
         */
    public String sortCompetitorAge(){
        ArrayList<Competitor> listx = this.competitorsList;
        ArrayList<Competitor> listz = new ArrayList<>();
        //using AgeComparator Class
        Collections.sort(listx, new AgeComparator());
        for (Competitor c : listx) {
            if (c instanceof gunFighter) {
                listz.add(c);
            }
        }
        return listInGUI(listz);
    }

        /**
         * we can sort competitor in overall score DESC order and first letter of last name ASC order
         * @param t programme has to know sort in which way
         * @return greater, less, or equal
         */
    public String sortCompetitor(String t) {
        ArrayList<Competitor> listx = this.competitorsList;
        //using the Comparator class
        Collections.sort(listx, new Comparator() {
            //need to override original compare method
            @Override
            public int compare(Object o1, Object o2) {
                if (t.contains("OS")) {
                    //casting Object to Competitor
                    Competitor c1 = (Competitor) o1;
                    Competitor c2 = (Competitor) o2;
                    //get overall score
                    Double s1 = c1.getOverallScore();
                    Double s2 = c2.getOverallScore();
                    //if s1.compareTo(s2) will in ASC order
                    return s2.compareTo(s1);
                } else if (t.contains("Name")) {
                    Competitor c1 = (Competitor) o1;
                    Competitor c2 = (Competitor) o2;
                    return c1.getName().getLastName().compareTo(c2.getName().getLastName());
                }
                return 0;
            }
        });

        ArrayList<Competitor> listz = new ArrayList<>();
        //we have to pick out a certain kind of competitor that user needed
        if (t.contains("g")) {
            for (Competitor c : listx) {
                if (c instanceof gunFighter) {
                    listz.add(c);
                }
            }
            return listInGUI(listz);

        } else if (t.contains("k")) {
            for (Competitor c : listx) {
                if (c instanceof knight) {
                    listz.add(c);
                }
            }
            return listInGUI(listz);

        } else if (t.contains("r")) {
            for (Competitor c : listx) {
                if (c instanceof racer) {
                    listz.add(c);
                }
            }
            return listInGUI(listz);
        }
        return null;
    }

        /**
         * this method cost me a lot of time to think, not only how to achieve it,
         * but also in which way that user will know what this multi choices can do without any instruction.
         * the aim is simple, give a table of selected categories and levels.
         * @param s to identify what categories and levels need to filter
         * @return a table of needed competitors
         */
    public String alterCom(String s) {
        //we need lots of ArrayList first
        ArrayList<Competitor> listg = new ArrayList<>();
        ArrayList<Competitor> listk = new ArrayList<>();
        ArrayList<Competitor> listr = new ArrayList<>();
        ArrayList<Competitor> listx = new ArrayList<>();
        //pick out each kind of competitors first
        for (Competitor c : competitorsList) {
            if (s.contains("g")) {
                if (c instanceof gunFighter) {
                    listg.add(c);
                }
            }
            if (s.contains("k")) {
                if (c instanceof knight) {
                    listk.add(c);
                }
            }
            if (s.contains("r")) {
                if (c instanceof racer) {
                    listr.add(c);
                }
            }
        }
        //we need another blank ArrayList to store matched level in that category
        //this is for gunfighter
        for (Competitor c : listg) {
            if (s.contains("L")) {
                if (c.getLevel().equals("Legendary")) {
                    listx.add(c);
                }
            }
            if (s.contains("A")) {
                if (c.getLevel().equals("Rare")) {
                    listx.add(c);
                }
            }
            if (s.contains("U")) {
                if (c.getLevel().equals("Uncommon")) {
                    listx.add(c);
                }
            }
            if (s.contains("C")) {
                if (c.getLevel().equals("Common")) {
                    listx.add(c);
                }
            }
        }
        //this is for knight
        for (Competitor c : listk) {
            if (s.contains("L")) {
                if (c.getLevel().equals("Legendary")) {
                    listx.add(c);
                }
            }
            if (s.contains("A")) {
                if (c.getLevel().equals("Rare")) {
                    listx.add(c);
                }
            }
            if (s.contains("U")) {
                if (c.getLevel().equals("Uncommon")) {
                    listx.add(c);
                }
            }
            if (s.contains("C")) {
                if (c.getLevel().equals("Common")) {
                    listx.add(c);
                }
            }
        }
        //this is for racer
        for (Competitor c : listr) {
            if (s.contains("L")) {
                if (c.getLevel().equals("Legendary")) {
                    listx.add(c);
                }
            }
            if (s.contains("A")) {
                if (c.getLevel().equals("Rare")) {
                    listx.add(c);
                }
            }
            if (s.contains("U")) {
                if (c.getLevel().equals("Uncommon")) {
                    listx.add(c);
                }
            }
            if (s.contains("C")) {
                if (c.getLevel().equals("Common")) {
                    listx.add(c);
                }
            }
        }
        return listInGUI(listx);
    }
}


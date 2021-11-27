import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * all GUI parts are assembled in this class, include kinds of actions.
 * @author Jiancheng Zhang
 */
public class CompetitorGUI extends JFrame implements ActionListener {

    private CompetitorList list;
    private JTextField result, searchField, searchID, newScores, alterSResult, fileName;
    private JScrollPane scrollList1, scrollList2, scrollList3, scrollAllList;
    private JPanel infoPanel1, infoPanel2, alterSPanel, idPanel, scorePanel;
    private JPanel listPanel, gListPanel, gButtonPanel, kListPanel, kButtonPanel, rListPanel, rButtonPanel;
    private JPanel searchPanel, southPanel, extraPanel;
    private JPanel eastPanel, eastPanel1, catPanel, levPanel;
    private JButton search, gListOS, gListName, gListAge, alterB, close, Ok;
    private Choice rList;
    private JRadioButton kListOS, kListName;
    private JTextArea gunFighterList, knightList, racerList, allList;
    private JCheckBox level1, level2, level3, level4, catGunFighter, catKnight, catRacer;
    private String r;
    private FileIO file;

    /**
     * create a frame with windowClosing action
     * @param list get a competitor list
     * @param file get a instance from FileIO class in order to write report to a specific .txt file
     * @param r the report that need to output
     */
    public CompetitorGUI(CompetitorList list, FileIO file, String r){
        this.list = list;
        this.file = file;
        this.r    = r;
        this.setTitle("CompetitorListGUI");
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(CompetitorGUI.this, "Exit?", "Really?",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                //result can be 0 or -1 or 1 or 2
                //click ok is 0
                if(result == JOptionPane.OK_OPTION){//the programme will exit
                    System.exit(0);
                }
            }
        });
        northPanel();
        centerPanel();
        eastPanel();
        this.setSize(1100,1100);
        setVisible(true);
    }

    /**
     * this panel achieve alter a competitor's score function
     */
    public void northPanel(){
        //first component
        infoPanel1 = new JPanel(new GridLayout());
        infoPanel1.add(new JLabel("Enter the competitor number that you want to alter the scores"));
        searchID = new JTextField(10);
        idPanel = new JPanel(new GridLayout());
        idPanel.add(new JLabel("Enter ID"));
        //second component
        infoPanel2 = new JPanel(new GridLayout());
        infoPanel2.add(new JLabel("Enter 5 Integer between 0 to 5 include and separate by comma"));
        newScores = new JTextField(10);
        scorePanel = new JPanel(new GridLayout());
        scorePanel.add(new JLabel("Enter Scores"));
        //a button
        alterB = new JButton("Alter");
        alterB.addActionListener(this);
        //show result
        alterSResult = new JTextField(25);
        alterSResult.setEditable(false);
        //create a panel and use GridBagLayout to put those components above into grid
        alterSPanel = new JPanel();
        GridBagLayout gridBagLayout=new GridBagLayout();
        alterSPanel.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        //in horizon, each component will be auto filled to fit grid
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //start from (0,0) and width is 4 unit, height is 1 unit
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=4;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(infoPanel1, gbc);
        //start from (0,2) and width is 2 unit, height is 1 unit
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(idPanel, gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(searchID, gbc);

        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=4;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(infoPanel2, gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(scorePanel, gbc);

        gbc.gridx=2;
        gbc.gridy=6;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(newScores, gbc);

        gbc.gridx=4;
        gbc.gridy=6;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(alterB, gbc);

        gbc.gridx=0;
        gbc.gridy=8;
        gbc.gridwidth=4;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(alterSResult, gbc);
        //add components to the panel and put this panel to north frame
        alterSPanel.add(infoPanel1);
        alterSPanel.add(idPanel);
        alterSPanel.add(searchID);
        alterSPanel.add(infoPanel2);
        alterSPanel.add(scorePanel);
        alterSPanel.add(newScores);
        alterSPanel.add(alterB);
        alterSPanel.add(alterSResult);

        this.add(alterSPanel, BorderLayout.NORTH);
    }

    /**
     * the center panel include sort gunfighter, knight, and racer list and search a competitor by competitor number
     */
    public void centerPanel(){
        //use button to listen action and can sort list by overall score, name, and age
        gunFighterList = new JTextArea(10,20);
        gunFighterList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,18));
        gunFighterList.setEditable(false);
        gunFighterList.setLineWrap(false);
        //because the list cannot show all in the panel, so we need a scrollPane
        scrollList1 = new JScrollPane(gunFighterList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //create three buttons
        gListOS   = new JButton("List all gunfighter by overall score");
        gListName = new JButton("List all gunfighter by Name");
        gListAge  = new JButton("List all gunfighter by Age");
        gListOS  .addActionListener(this);
        gListName.addActionListener(this);
        gListAge .addActionListener(this);
        //use GridBagLayout to arrange 3 buttons
        gButtonPanel = new JPanel();
        GridBagLayout gridBagLayout=new GridBagLayout();
        gButtonPanel.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(gListName, gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(gListOS, gbc);

        gbc.gridx=4;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gridBagLayout.setConstraints(gListAge, gbc);
        //add to panel
        gButtonPanel.add(gListName);
        gButtonPanel.add(gListOS);
        gButtonPanel.add(gListAge);
        //add above panel and scrollPanel
        gListPanel = new JPanel(new GridLayout(2,1));
        gListPanel.add(gButtonPanel);
        gListPanel.add(scrollList1);

        //use JRadioButton to listen action, and can sort knight list by overall score and name
        knightList = new JTextArea(10,20);
        knightList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,18));
        knightList.setEditable(false);
        knightList.setLineWrap(false);
        scrollList2 = new JScrollPane(knightList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //create 2 JRadioButtons
        kListOS   = new JRadioButton("List all knight by overall score");
        kListName = new JRadioButton("List all knight by Name");
        kListOS  .addActionListener(this);
        kListName.addActionListener(this);
        //we have to group 2 buttons so that there is only one button will be selected every time
        ButtonGroup group=new ButtonGroup();
        group.add(kListOS);
        group.add(kListName);
        //use GridLayout to pack 2 buttons
        kButtonPanel = new JPanel();
        kButtonPanel.setLayout(new GridLayout(1,2));
        kButtonPanel.add(kListName);
        kButtonPanel.add(kListOS);
        //use GridLayout to pack above panel and scroll panel
        kListPanel = new JPanel();
        kListPanel.setLayout(new GridLayout(2,1));
        kListPanel.add(kButtonPanel);
        kListPanel.add(scrollList2);

        //use Choice to listen action, racer list can be sorted by overall score and name
        racerList = new JTextArea(10,20);
        racerList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,18));
        racerList.setEditable(false);
        racerList.setLineWrap(false);
        scrollList3 = new JScrollPane(racerList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rList = new Choice();
        rList.add("List all racer by overall score");
        rList.add("List all racer by Name");
        rList.addItemListener(new ItemListener() {
            //we have to add ItemListener here, because we will use itemStateChanged method to deal with multiple choice
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem() == "List all racer by overall score"){
                    racerList.setText(list.sortCompetitor( "rOS"));
                }else if (e.getItem() == "List all racer by Name"){
                    racerList.setText(list.sortCompetitor( "rName"));
                }
            }
        });

        //use GridLayout to pack Choice and list
        rButtonPanel = new JPanel();
        rButtonPanel.setLayout(new GridLayout(1,1));
        rButtonPanel.add(rList);
        rListPanel = new JPanel();
        rListPanel.setLayout(new GridLayout(2,1));
        rListPanel.add(rButtonPanel);
        rListPanel.add(scrollList3);

        //create a new panel to pack gunfighter, knight, and racer components
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(4,1));
        listPanel.add(gListPanel);
        listPanel.add(kListPanel);
        listPanel.add(rListPanel);

        //create a textField and a button to get an ID and listen action
        searchField = new JTextField(6);
        search = new JButton("Search");
        search.addActionListener(this);

        searchPanel = new JPanel(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter ID"));
        searchPanel.add(searchField);
        searchPanel.add(search);

        result= new JTextField(3);
        result.setEditable(false);
        //create a new panel to pack above search components
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(6,1));
        southPanel.add(searchPanel);
        southPanel.add(result);
        //add above panel to list panel
        listPanel.add(southPanel);

        this.add(listPanel,BorderLayout.CENTER);
    }

    /**
     *this panel include multiple choice to filter whole competitor list
     */
    public void eastPanel(){
        //create a new panel to pack a button,a text area, and three JCheckBox about category of competitor
        catPanel = new JPanel(new GridLayout(1,5));
        //we use itemStateChanged again
        catGunFighter = new JCheckBox("Gunfighter");
        catKnight     = new JCheckBox("Knight");
        catRacer      = new JCheckBox("Racer");
        catGunFighter.addItemListener(this::itemStateChanged);
        catKnight    .addItemListener(this::itemStateChanged);
        catRacer     .addItemListener(this::itemStateChanged);
        close = new JButton("Close");
        close.addActionListener(this);

        catPanel.add(new JLabel("Select category"));
        catPanel.add(catGunFighter);
        catPanel.add(catKnight);
        catPanel.add(catRacer);
        catPanel.add(close);
        //create a new panel with the same GridLayout with above, and pack four CheckBox about four kinds of level
        levPanel = new JPanel(new GridLayout(1,5));
        //we will filter competitor level
        level1 = new JCheckBox("Common");
        level2 = new JCheckBox("Uncommon");
        level3 = new JCheckBox("Rare");
        level4 = new JCheckBox("Legendary");
        level1.addItemListener(this::itemStateChanged);
        level2.addItemListener(this::itemStateChanged);
        level3.addItemListener(this::itemStateChanged);
        level4.addItemListener(this::itemStateChanged);

        levPanel.add(new JLabel("Select level"));
        levPanel.add(level1);
        levPanel.add(level2);
        levPanel.add(level3);
        levPanel.add(level4);

        //create a text area to show the result after filter
        allList = new JTextArea(10,20);
        allList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,18));
        allList.setEditable(false);
        allList.setLineWrap(false);
        scrollAllList = new JScrollPane(allList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        eastPanel1 = new JPanel(new GridLayout(3,1));
        eastPanel  = new JPanel(new GridLayout(3,1));
        eastPanel1.add(catPanel);
        eastPanel1.add(levPanel);
        eastPanel .add(eastPanel1);
        eastPanel .add(scrollAllList);
        this.add(eastPanel,BorderLayout.EAST);
    }

    /**
     * this class is about achieve action from kinds of button
     * @param e which button trigger the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==alterB){
            //will return a overall score immediately
            alterScores();
        }else if (e.getSource()==gListOS){
            //pass a string with which kind of competitor and how to sort
            //g means gunfighter   k means knight    r means racer
            //OS means by overall score    Name means by last name
            gunFighterList.setText(list.sortCompetitor( "gOS"));
        }else if (e.getSource()==gListName){
            gunFighterList.setText(list.sortCompetitor( "gName"));
        }else if (e.getSource()==gListAge){
            gunFighterList.setText(list.sortCompetitorAge());
        }else if (e.getSource()==kListOS){
            knightList.setText(list.sortCompetitor( "kOS"));
        }else if (e.getSource()==kListName){
            knightList.setText(list.sortCompetitor( "kName"));
        }else if (e.getSource()==search){
            result.setText(list.searchCompetitor(searchField.getText()));
        }else if (e.getSource()==close){
            //a new frame will show and ask you to enter a file name
            newFrame();
        }else if (e.getSource()==Ok){
            try {
                //if click yes in the new frame, a report will be written to the file you asked for
                fileName(file, fileName.getText(), r);
                System.exit(0);
            } catch (FileNotFoundException f) {
                //prevent you from over writing the input files
                System.out.println("Access denied.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * this is to achieve filter action
     * @param e which box is selected
     */
    public void itemStateChanged(ItemEvent e) {
        //create an order with empty
        String s = "";
        //if a box is selected, add a certain letter to order
        if (catGunFighter.isSelected()){
            //gunfighter is selected
            s += "g";
        }
        if (catKnight.isSelected()){
            //knight is selected
            s += "k";
        }
        if (catRacer.isSelected()){
            //racer is selected
            s += "r";
        }
        if (level1.isSelected()){
            //level Common is selected
            s += "C";
        }
        if (level2.isSelected()){
            //level Uncommon is selected
            s += "U";
        }
        if (level3.isSelected()){
            //level Rare is selected
            s += "A";
        }
        if (level4.isSelected()){
            //level Legendary is selected
            s += "L";
        }
        //filter list and return a list
        allList.setText(list.alterCom(s));
    }

    /**
     * this will set new scores to a certain competitor and handle kinds of wrong input
     */
    public void alterScores(){
        String compid = searchID.getText().replace(" ", "");
        Integer[] scores = new Integer[5];
        String[] s = newScores.getText().split(",");
        //we use if if this error will occur frequently and use try catch if this kind of error is not frequently but severe
        if (Arrays.toString(s).equals("[]")&&s.length==1){
            //if there is nothing enter
            alterSResult.setText("No scores enter!");
        }else {
            //create 3 error status and an error message
            int t1 = 0; // something is not int
            int t2 = 0; // there are not 5 elements in array
            int t3 = 0; // the enter number is not belong to [0,5]
            String z = "";
            //if there are more than 5 elements after split by comma
            if (s.length > 5){
                t2 = 2;
            }
            for (int x = 0; x < 5; x++) {
                try {
                    scores[x] = Integer.parseInt(s[x].replace(" ", ""));
                }catch (NumberFormatException e){
                    //if this element not an int
                    t1 = 1;
                }catch (ArrayIndexOutOfBoundsException e){
                    //if there is less than 5 elements
                    t2 = 2;
                }
            }
            //output error message
            if (t1 == 1){
                z += "Some character not belong to number or not an Integer. \n";
                alterSResult.setText(z);
            }
            if (t2 == 2){
                z += "Enter 5 number please. \n";
                alterSResult.setText(z);
            }
            //check is every score less than 5 and greater than 0
            for (int x = 0; x < 5; x++) {
                try {
                    if (scores[x] < 0 || scores[x] > 5) {
                        t3 = 3;
                        break;
                    }
                }catch (NullPointerException e){
                    t1 = 1;
                }
            }
            //output error message
            if (t3 == 3){
                z += "Each score need to belong [0,5]. \n";
                alterSResult.setText(z);
            }
            //if everything is fine
            if (t1 == 0 && t2 == 0 && t3 == 0) {
                alterSResult.setText(list.alterScores(compid, scores));
            }
        }
    }

    /**
     * this frame can get which file user want to write report in
     */
    public void newFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Generate a report and exit");
        frame.setSize(500,80);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout(50,50));
        frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
        //start with .txt which is ergonomics friendly
        fileName = new JTextField(".txt",6);
        fileName.setFont(new Font(Font.MONOSPACED, Font.PLAIN,18));
        Ok = new JButton("Yes");
        Ok.addActionListener(this);
        //create a panel pack a button, a text area, and a label
        extraPanel = new JPanel(new GridLayout(1,3));
        extraPanel.add(new JLabel("Enter a output file name"));
        extraPanel.add(fileName);
        extraPanel.add(Ok);
        frame.add(extraPanel, BorderLayout.CENTER);
    }

    /**
     * use output method in FileIO class
     * @param file instance of FileIO
     * @param fileName the file name that user enter
     * @param report a report
     * @throws IOException any IOException
     */
    public void fileName(FileIO file, String fileName, String report) throws IOException {
        file.output(fileName, report);
    }
}

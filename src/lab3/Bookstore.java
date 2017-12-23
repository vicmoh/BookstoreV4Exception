 //import  package
package lab3;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//swing gui lib
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bookstore {
    //declare object
    private static Scanner scan = new Scanner(System.in);
    private static Book newBook = new Book();
    public static HashMap<Integer, Book> bookList = new HashMap();
    //decalre var
    private static int menu = 0;
    private static String title = "", author = "", isbn = "", input = "", subject = "", workbookISBN = "";
    private static int year = 0, numProblems;
    private static double price;
    private static String errorString;
    private static boolean wrongFormat;
    private static boolean correctIsbnFormat;
    private static boolean existingISBN;
    private static int hashCount = 0;
    //constant var
    private static final int BOOK = 1;
    private static final int TEXTBOOK = 2;
    private static final int WORKBOOK = 3;
    //gui variables
    private static JFrame mainFrame;
    private static JPanel controlPanel;
    private static String textFieldString = "";
    private static String message = "";
    private static JTextArea messageArea = new JTextArea(message);
    private static JButton listButton = new JButton("List Books");
    private static JButton authorButton = new JButton("Unique Author");    
    private static JButton averageButton = new JButton("Average");
    private static JButton pairButton = new JButton("Textbook-Workbook");
    private static JLabel titleLabel = new JLabel("Title");
    private static JLabel authorLabel = new JLabel("Author");
    private static JLabel yearLabel = new JLabel("Year Published");
    private static JLabel isbnLabel = new JLabel("ISBN");
    private static JLabel priceLabel = new JLabel("Price");
    private static JLabel subjectLabel = new JLabel("Subject(Textbook only)");
    private static JLabel workbookIsbnLabel = new JLabel("WorkbookISBN(Textbook only)");
    private static JLabel numProblemLabel = new JLabel("NumProblems(Workbook only)");
    private static JTextField titleField = new JTextField("");
    private static JTextField authorField = new JTextField("");
    private static JTextField yearField = new JTextField("");
    private static JTextField isbnField = new JTextField("");
    private static JTextField priceField = new JTextField("");
    private static JTextField subjectField = new JTextField("");
    private static JTextField workbookIsbnField = new JTextField("");
    private static JTextField numProblemField = new JTextField("");
    public static final String FONT_TYPE = "Serif";
    public static final int FONT_SIZE = 30;
    //create ui
    private static JButton book = new JButton("Book");
    private static JButton workbook = new JButton("Workbook");
    private static JButton textbook = new JButton("Textbook");
    
    public static void main(String[] args) {
        //set the default font
        setUIFont (new javax.swing.plaf.FontUIResource("Serif",Font.BOLD,35));
        //run the gui
        prepareGUI();
    }//end main

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        //set the default frame of the whole font
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource){
              UIManager.put (key, f);
            }//end if
        }//end while
    }//end func
    
    public static void prepareGUI(){
        //setting the font type and size due to my hish resolution
        listButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE)); 
        authorButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        averageButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        pairButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        titleLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        authorLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        yearLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        isbnLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        priceLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        subjectLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        workbookIsbnLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        numProblemLabel.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        titleField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        authorField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        yearField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        isbnField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        priceField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        subjectField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        workbookIsbnField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        numProblemField.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        book.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        textbook.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        workbook.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        messageArea.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
        
        //set the main frame
        mainFrame = new JFrame("Menu");
        mainFrame.setSize(2000,1200);
        //frame
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }//ennd window closing        
        });     
        
        JPanel content = new JPanel(new GridLayout(2,2));
        
        //create the menu bar for the command
        final JMenuBar menuBar = new JMenuBar();
        JMenu command = new JMenu("FILE");
        //create the child menu
        final JMenuItem saveMenu = new JMenuItem("Save As...");
        final JMenuItem loadMenu = new JMenuItem("Load File...");
        final JMenuItem quitMenu = new JMenuItem("Quit");
        
        menuBar.add(command);
        command.add(saveMenu);
        command.add(loadMenu);
        command.add(quitMenu);
        
        //set action the menu items to the
        saveMenu.setActionCommand("Save");
        loadMenu.setActionCommand("Load");
        quitMenu.setActionCommand("Quit");
        
        //set the action
        saveMenu.addActionListener(new saveMenuListener());
        loadMenu.addActionListener(new loadMenuListener());
        quitMenu.addActionListener(new quitMenuListener());
        
        //for the other button
        listButton.addActionListener(new listListener());
        authorButton.addActionListener(new authorsListener());
        averageButton.addActionListener(new averageListener());
        pairButton.addActionListener(new pairListener());
        
        //for adding
        book.addActionListener(new addBookListener());
        textbook.addActionListener(new addTextbookListener());
        workbook.addActionListener(new addWorkbookListener());
        

        //add objects to the frame
        content.add(menu());
        content.add(bookOptions());
        content.add(messagePanel());
        content.add(addBookInfoOption());
        //set font for all
     
        //show frame
        mainFrame.add(menuBar, BorderLayout.NORTH);
        mainFrame.add(content);
        mainFrame.setVisible(true);
    }//end func
    
    public static JPanel menu(){
        JPanel content = new JPanel(new GridLayout(2,2));
        content.add(listButton);
        content.add(authorButton);
        content.add(averageButton);
        content.add(pairButton);
        return content; 
    }//end func 
    
    public static JPanel messagePanel(){
        //create the panel
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(1,1));
        //create the text
        JScrollPane scrollMessage = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //set the charateristic
        messageArea.setEditable(false);
        messageArea.setBackground(Color.white);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        //add the text in the panels
        content.add(scrollMessage);
        return content;
    }//end method 
    
    public static JPanel bookOptions(){
        //set the frame
        JPanel bookFrame = new JPanel();
        bookFrame.setLayout(new GridLayout(3, 1));
        
        //add ui
        bookFrame.add(book);
        bookFrame.add(textbook);
        bookFrame.add(workbook);
        
        //show the gui
        bookFrame.setVisible(true);
        return bookFrame;
    }//end func
    
    public static JPanel addBookInfoOption(){
        //set the frame
        JPanel frame = new JPanel();
        frame.setLayout(new GridLayout(8, 1));
        
        frame.add(titleLabel);
        frame.add(titleField);
        frame.add(authorLabel);
        frame.add(authorField);
        frame.add(yearLabel);
        frame.add(yearField);
        frame.add(isbnLabel);
        frame.add(isbnField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(subjectLabel);
        frame.add(subjectField);
        frame.add(workbookIsbnLabel);
        frame.add(workbookIsbnField);
        frame.add(numProblemLabel);
        frame.add(numProblemField);
        
        frame.setVisible(true);
        return frame;
    }//end
    
    public JPanel inputPanel(String label, JTextField textField){
        //top content
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(1,2));
        GridBagConstraints constraints = new GridBagConstraints();
        //info of total gain label
        JLabel totalGainLabel = new JLabel(label);
        constraints.gridx = 0;
        constraints.gridy = 0;
        content.add(totalGainLabel, constraints);
        //show the total gain in text area
        //textField = new JTextField("");
        textField.setPreferredSize(new Dimension(800, 60));//set the size
        constraints.insets = new Insets(20, 20, 20, 20);//inverse padding
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        content.add(textField, constraints);
        textFieldString = textField.getText();
        return content;
    }//end method

    public static void infoErrorCheck(int type){
        //set info error checking
        wrongFormat = false;
        correctIsbnFormat = false;
        errorString = "";
        //check if the format is good
        if(title.equals("")){
            errorString = errorString + "Wrong format TITLE\n";
            wrongFormat = true;
        }//end if
        if(author.equals("")){
            errorString = errorString + "Wrong format for AUTHOR\n";
            wrongFormat = true;
        }//end if
        if(year < -2600 || year > 2017){
            errorString = errorString + "Wrong format for YEAR\n";
            wrongFormat = true;
        }//end if
        if(price < 0){
            errorString = errorString + "Wrong format for PRICE\n";
            wrongFormat = true;
        }//end if
        System.out.println("FORMAT: " + correctIsbnFormat);
        System.out.println("LENGTH: " + isbn.length());
        if(isbn.length() == 10 || isbn.length() == 13){
            correctIsbnFormat = true;
        }//end if
        if(correctIsbnFormat != true){
            errorString = errorString + "Wrong format for ISBN\n";
            wrongFormat = true;
        }//end if
        for(Integer x:bookList.keySet()){
            if(bookList.get(x).getISBN().equals(isbn)){
                errorString = errorString + "Found Existing ISBN\n";
                existingISBN = true;
            }//end if
        }//end 
        
        //for other books
        if(type == TEXTBOOK){
            //check if the format is fine
            if(workbookISBN.length() == 10 || workbookISBN.length() == 13){
                correctIsbnFormat = true;
            }//end if
            if(correctIsbnFormat != true){
                errorString = errorString + "Wrong format for WORKBOOK ISBN\n";
                wrongFormat = true;
            }//end if
            for(Integer x:bookList.keySet()){
                if(bookList.get(x).getISBN().equals(workbookISBN)){
                    errorString = errorString + "Found Existing ISBN\n";
                    existingISBN = true;
                }//end if
            }//end for
            if(subject.equals("")){
                errorString = errorString + "Wrong format for SUBJECT\n";
                wrongFormat = true;
            }//end if
        }else if(type == WORKBOOK){
            //check the format
            if(numProblems < 1){
                errorString = errorString + "Wrong format for NUMBER OF PROBLEMS\n";
                wrongFormat = true;
            }//end if
        }//end if
    }//end func
    
    public static void popup(String text){
        //create window
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 1));  
        //create objects 
        JTextArea info = new JTextArea(text);
        JButton button = new JButton("Okay");
        //add to the frame
        frame.add(info);
        frame.add(button);
        frame.setVisible(true);
        //when butto is clicked
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }//end action    
        });
    }//end func
    
    public static int tryCatchInt(String toBeConverted){
        int number = 0;
        try{
            number = Integer.parseInt(toBeConverted);
        }catch(NumberFormatException E){
            System.out.println("WRONG FORMAT");
            return number = -1;
        }//end catch
        return number;
    }//end  func
    
    public static double tryCatchDouble(String toBeConverted){
        double number = 0;
        try{
            number = Double.parseDouble(toBeConverted);
        }catch(NumberFormatException E){
            System.out.println("WRONG FORMAT");
            return number = -1;
        }//end catch
        return number;
    }//end  func
    
    public static String option2(){
        String string = "";
        //print the books in the list
        int bookNumber = 0;
        System.out.println("***************************************");
        for(Integer x:bookList.keySet()){
            bookNumber++;
            string = string + "\nBook Number: " + bookNumber;
            newBook = bookList.get(x);
            string = string + "\n" + newBook.toString();
            System.out.println("***************************************");
        }//end for loop
        return string;
    }//end func
    
    public static String option3(){
        String string = "";
        boolean sameAuthor;
        String string1;
        String string2;
        System.out.println("***************************************");
        string = string +  "List of author available...";
        //check if any author is the same if not print
        int countX = 0;
        int countY = 0;
        for(int x=0; x < bookList.size();x++){
            sameAuthor = false;
            
            for(int y=x+1; y < bookList.size();y++){
                System.out.println(bookList.get(x).getAuthor() + " and " + bookList.get(y).getAuthor());
                string1 = bookList.get(x).getAuthor();
                string2 = bookList.get(y).getAuthor();
                if(string1.equals(string2)){
                    sameAuthor = true;
                    //System.out.println(sameAuthor);
                }//end if
            }//end if

            if(sameAuthor == false){
                string = string + "\n" + bookList.get(x).getAuthor();
                System.out.println(bookList.get(x).getAuthor());
            }//end if
            
        }//end for loop
        System.out.println("***************************************");
        return string;
    }//end func
    
    public static String option4(){
        String string = "";
        //set object of 2 decimal format
        DecimalFormat twoDecimal = new DecimalFormat();
        twoDecimal.setMaximumFractionDigits(2);
        //declare var
        double averagePrice = 0;
        for(Integer x:bookList.keySet()){
            newBook = bookList.get(x);
            averagePrice = averagePrice + newBook.getPrice();
        }//end for loop
        averagePrice = averagePrice/bookList.size();
        System.out.println("***************************************");
        string = string + "\nCalculating...";
        string = string + "\nThe average price of all book is $" + twoDecimal.format(averagePrice);
        string = string + "\nAnd the total number of book is " + bookList.size();
        System.out.println("***************************************");
        return string;
    }//end func
    
    public static String option5(){
        String string = "";
        Workbook workbook;
        Textbook textbook;
        System.out.println("***************************************");
        string = string + ("List of pair books...");
        //check if any author is the same if not print
        for(Integer x:bookList.keySet()){
            for(Integer y:bookList.keySet()){
                if (bookList.get(y) instanceof Textbook) {
                    textbook = (Textbook)bookList.get(y);
                    //System.out.println("Comparing..." + textbook.getTitle() + " and " + bookList.get(x).getTitle());
                    if(textbook.equals(bookList.get(x)) == true){
                        string = string + "\n" + bookList.get(y).getTitle() + " = " + bookList.get(x).getTitle() + "\n";
                    }//end if
               }//end if
            }//end loop
        }//end for loop
        System.out.println("***************************************");
        return string;
    }//end func
    
    public static String option6(){
        //declare var
        String message = "";
        String fileName = "data.txt";
        Textbook textbook;
        Workbook workbook;
        Book book;
        // Write to a file
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            for(Integer x:bookList.keySet()){
                bookList.get(x);
                if(bookList.get(x) instanceof Textbook){
                    textbook = (Textbook)bookList.get(x);
                    writer.write(textbook.data());
                }else if(bookList.get(x) instanceof Workbook){
                    workbook = (Workbook)bookList.get(x);
                    writer.write(workbook.data());
                }else if(bookList.get(x) instanceof Book){
                    book = (Book)bookList.get(x);
                    writer.write(book.data());
                }//end if
                writer.newLine();
            }//end for
            message = message + ("File has been saved.\n");
            writer.close();
        } catch(IOException e){
            message = message + ("Failed to write to "+fileName+".\n");
        }//end try
        return message;
    }//end func
    
    public static String option7() throws Book.titleException, Book.authorException, Book.yearException, Book.priceException, Book.isbnException, Textbook.workbookIsbnException, Textbook.subjectException, Workbook.numProbException{
        //declare var
        String message = "";
        String fileName = "data.txt";
        // Read the file
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            bookList.clear();
            int yearData = 0;
            double priceData = 0;
            int numProbData = 0;
            int count = 0;
            System.out.println("***************************************");
            System.out.println("Loading...");
            while (line != null){
                String[] data = line.split("@");
                
                //for debug
                System.out.println(line);
                /*for(int x=0; x < data.length; x++){
                    System.out.println("Split: " + data[x]);
                }*/
     
                if(data[0].equals("t")){
                   yearData = Integer.parseInt(data[3]);
                   priceData = Double.parseDouble(data[4]);
                   bookList.put(bookList.size(),new Textbook(data[1], data[2], yearData, priceData, data[5], data[6], data[7])); 
                }//en if 
                if(data[0].equals("w")){
                   yearData = Integer.parseInt(data[3]);
                   priceData = Double.parseDouble(data[4]);
                   numProbData = Integer.parseInt(data[6]);
                   bookList.put(bookList.size(), new Workbook(data[1], data[2], yearData, priceData, data[5], numProbData));
                }if(data[0].equals("b")){
                   yearData = Integer.parseInt(data[3]);
                   priceData = Double.parseDouble(data[4]);
                   bookList.put(bookList.size(), new Book(data[1], data[2], yearData, priceData, data[5]));
                }//end if
                line = reader.readLine();
            }//end while
            System.out.println("***************************************");
            message = message + "File has been read.\n";
            reader.close();
        } catch(IOException e){
            message = message + ("Failed to read "+fileName+".\n");
        }//end try
        return message;
    }//end func
    
    static class listListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            message = message + option2();
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class
    
    static class authorsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            message = message + option3();
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class
    
    static class averageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            message = message + option4();
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class
    
    static class pairListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            message = message + option5();
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class
    
    static class addBookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            int type = BOOK;
            //apply to the instance variable            
            try{
                title = titleField.getText();
                author = authorField.getText();
                year = Integer.parseInt(yearField.getText());
                price = Double.parseDouble(priceField.getText());
                isbn = isbnField.getText();
                if(type == TEXTBOOK){
                    workbookISBN = workbookIsbnField.getText();
                    subject = subjectField.getText();
                }else if(type == WORKBOOK){
                    numProblems = Integer.parseInt(numProblemField.getText());
                }//end if
                try {
                    bookList.put(bookList.size(), new Book(title, author, year, price, isbn));
                    message = message + "Book has been added.\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.titleException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.authorException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.yearException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.priceException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.isbnException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                }
            }catch(NumberFormatException E){
                message = message + "Number format exception\n";
                messageArea.setText(message);
                messageArea.setVisible(true);
            }//end catch

        }//end func 
    }//end class
    
    static class addTextbookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            int type = TEXTBOOK;
            //apply to the instance variable            
            try{
                title = titleField.getText();
                author = authorField.getText();
                year = Integer.parseInt(yearField.getText());
                price = Double.parseDouble(priceField.getText());
                isbn = isbnField.getText();
                if(type == TEXTBOOK){
                    workbookISBN = workbookIsbnField.getText();
                    subject = subjectField.getText();
                }else if(type == WORKBOOK){
                    numProblems = Integer.parseInt(numProblemField.getText());
                }//end if
                try {
                    bookList.put(bookList.size(), new Textbook(title, author, year, price, isbn, workbookISBN, subject));
                    message = message + "Textbook has been added.\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.titleException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.authorException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.yearException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.priceException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.isbnException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Textbook.workbookIsbnException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Textbook.subjectException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                }
            }catch(NumberFormatException E){
                message = message + "Number format exception\n";
                messageArea.setText(message);
                messageArea.setVisible(true);
            }//end catch

        }//end func 
    }//end class
    
    static class addWorkbookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            int type = WORKBOOK;
            //apply to the instance variable            
            try{
                title = titleField.getText();
                author = authorField.getText();
                year = Integer.parseInt(yearField.getText());
                price = Double.parseDouble(priceField.getText());
                isbn = isbnField.getText();
                if(type == TEXTBOOK){
                    workbookISBN = workbookIsbnField.getText();
                    subject = subjectField.getText();
                }else if(type == WORKBOOK){
                    numProblems = Integer.parseInt(numProblemField.getText());
                }//end if
                try {
                    bookList.put(bookList.size(), new Workbook(title, author, year, price, isbn, numProblems));
                    message = message + "Workbook has been added.\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.titleException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.authorException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.yearException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.priceException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Book.isbnException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                } catch (Workbook.numProbException ex) {
                    message = message + ex.getMessage() + "\n";
                    messageArea.setText(message);
                    messageArea.setVisible(true);
                }
            }catch(NumberFormatException E){
                message = message + "Number format exception\n";
                messageArea.setText(message);
                messageArea.setVisible(true);
            }//end catch
            
        }//end func 
    }//end class
    
    static class saveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            message = message + option6();
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class
    
    static class loadMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            try {
                message = message + option7();
            } catch (Book.titleException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Book.authorException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Book.yearException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Book.priceException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Book.isbnException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Textbook.workbookIsbnException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Textbook.subjectException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Workbook.numProbException ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            }
            messageArea.setText(message);
            messageArea.setVisible(true);
        }//end func 
    }//end class

    
    
    static class quitMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            System.exit(0);
        }//end func 
    }//end class

}//end class

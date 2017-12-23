package lab3;

import java.text.*;

public class Textbook extends Book{
    
    private String subject;
    private String workbookISBN;
    
    public Textbook(String title, String author, int year, double price, String isbn, String workbookISBN, String subject) 
        throws titleException, authorException, yearException, priceException, isbnException, workbookIsbnException, subjectException{
        super(title, author, year, price, isbn);
        
        if(subject.equals("")){
            throw new subjectException("Cannot leave the subject blank");
        }
        
        //check if the format is fine
        boolean correctIsbnFormat = false;
        if(workbookISBN.length() == 10 || workbookISBN.length() == 13){
            correctIsbnFormat = true;
        }//end if
        if(correctIsbnFormat != true){
            throw new workbookIsbnException("Workbook ISBN must be 10 or 13 length");
        }//end if
        
        this.subject = subject;
        this.workbookISBN = workbookISBN;
    }//end public
    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getSubject(){
        return subject;
    }//end 
    
    public String getWorkbookISBN(){
        return workbookISBN;
    }//end 
    
    @Override
    public boolean equals(Object other){
        //check if the object is the same   
        if (other instanceof Workbook) {
            Workbook workbook = (Workbook) other;   
            if (this.workbookISBN.equals(workbook.getISBN()) == true){
                return true;
            }//end if
        }//end if
        return false;
    }//end 
    
    public String data(){
        return("t" + "@" + super.getTitle() + "@" + super.getAuthor() + "@" + super.getYear() + "@" + super.getPrice() + "@" + super.getISBN() + "@" + this.workbookISBN + "@" + this.subject);
    }//end string
    
    public String toString(){
        //set object of 2 decimal format
       DecimalFormat twoDecimal = new DecimalFormat();
       twoDecimal.setMaximumFractionDigits(2);
       //display book
       System.out.println("Book: " + super.getTitle());
       System.out.println("Author: " + super.getAuthor());
       System.out.println("Year of Publication: " + super.getYear());
       System.out.println("Price: $" + twoDecimal.format(super.getPrice()));
       System.out.println("ISBN: " + super.getISBN());
       System.out.println("workbook ISBN: " + workbookISBN);
       System.out.println("Subject: " + subject);
       System.out.println("Amount of Book: " + super.amountOfBook());
       return("************************************************" +
              "\nTEXTBOOK" +
              "\nBook: " + super.getTitle() +
              "\nAuthor: " + super.getAuthor() +
              "\nYear of Publication: " + super.getYear() +
              "\nPrice: $" + twoDecimal.format(super.getPrice()) +
              "\nISBN: " + super.getISBN() + 
              "\nworkbook ISBN: " + workbookISBN +
              "\nSubject: " + subject +
              "\n***********************************************\n");
    }//end func
    
    public class workbookIsbnException extends Exception{
        public workbookIsbnException(String message){
            super(message);
        }//end func
    }//end class
    
    public class subjectException extends Exception{
        public subjectException(String message){
            super(message);
        }//end func
    }//end class
    
}//end class

/********************************************************************
 * Programmer:	Ayub
 * Class:       CS20S
 *
 * Assignment:  A7
 *
 * Description: Read employee pay roll and calculates to find pay check. 
 ***********************************************************************/
 
 // import java libraries here as needed
 
 import javax.swing.*;
 //import java.text.DecimalFormat;
 import java.io.*;
import java.util.Scanner;

public class A7 {  // begin class
    
    public static void main(String[] args)throws IOException {  // begin main
    
    // ********* declaration of constants **********
        
        final double OT = 1.5;          //constant for extra overtime pay
        final int REGHOURS = 40;        //constant for regular hours
    
    // ********** declaration of variables **********

        String strin = "";				// string data input from keyboard
        String strout;				// processed info string to be output
        String bannerOut;			// string to print banner to message dialogs

        String prompt;				// prompt for use in input dialogs

        String delim = "[ :]+";		// delimiter string for splitting input string
        String[] tokens;            // string array for gathering input
        
        String nl = System.lineSeparator();
        // new line character for file writing
        
        int id = 0;                     // variable for id
        int hours = 0;                  //variable for hours worked
        
        int totalh = 0;                 // variable for total hours
        int othours = 0;                // variable for overtime hours
        
        double wage = 0.0;              // variable for wage
        double regpay = 0.0;            // varaible for regular pay
        double otpay = 0.0;             // variable for overtime pay
        double grosspay = 0.0;          // variable for total pay
    	
    // ***** create objects *******
    
        BufferedReader fin = new BufferedReader(new FileReader("employeePayrollData.txt"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("outFile.txt")));
    	
    // ********** Print output Banner **********

        System.out.println("*******************************************");
        System.out.println("Name: Ayub");
        System.out.println("Class: CS20S");
        System.out.println("Assignment:	A7");
        System.out.println("*******************************************");

        bannerOut = "*******************************************" + nl;
        bannerOut += "Name: Ayub" + nl;
        bannerOut += "Class: CS20S" + nl;
        bannerOut += "Assignment: A7" + nl;
        bannerOut += "*******************************************" + nl + nl;
        
        fout.print(bannerOut);		// prints the banner to the output text file
    	
    // ************************ get input **********************

    // ************************ processing ***************************
    
        strout = String.format("%-10s %-10s %-10s %-10s %-10s %-10s %s", "Id", "Hours", "Wage", "Regular", "Overtime", "Gross pay", nl);
    
        strin = fin.readLine();
        
        while(strin != null){
            //System.out.println(strin);
            
            tokens = strin.split(delim);                                                    
            id = Integer.parseInt(tokens[0]);           // parse for id            
            hours = Integer.parseInt(tokens[1]);        // parse for hours
            wage = Double.parseDouble(tokens[2]);       // parse for wage
            if(hours<=40 || hours==40){
                regpay = wage * hours;                      //calculation for regular pay
            }   //end if
            else{
                othours = hours - REGHOURS;                 // calculations for overtime hours
                regpay = wage * REGHOURS;                   // calculations for regular pay                  
                otpay = wage * othours * OT;                // calculations for overtime pay
            }   //end else     
            grosspay = otpay + regpay;
            strout += String.format("%-10d %-10d $%-10.2f $%-10.2f $%-10.2f $%-10.2f %s", id, hours, wage, regpay, otpay, grosspay, nl); 
            strout += String.format("%s", nl);
            strin = fin.readLine();
        };
        


    // ************************ print output ****************************
    
        System.out.println(strout);
    
    
    // ******** closing message *********
        
        System.out.println("End of processing.");
        fout.println("End of proecessing");
        
    // ***** close streams *****
        
        fin.close();      // close input buffer stream
        fout.close();       // close output stream, note that nothing is printed 
        					// to the output file until the stream is closed		
        
    }  // end main
}  // end class

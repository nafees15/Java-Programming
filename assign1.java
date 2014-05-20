/**
 * @Author -- Nafees Noor
  * @ CSI - 516 - Computer Networking
  * @ Assignment 1   02/3/2014
 * This assignment suppose to construct a simple server
 * which listen on a port for connection then accept and 
 * interact with a client and accept command from the client
 * it process those commands and close the connection upon request
 * It will also use the telnet program to emulate a simple client
 **/
import java.net.*;
import java.io.*; 
import java.net.InetAddress;          // Some of the important libaries for this assignment
import java.lang.*;
import java.util.*;

public class assign1
{
  
  /**
   * This methods below is to decide whether user enters the HELO
   * Commands as their first command to start up communicating with
   * the server. It's a boolean method which return true or false based
   * upon if the user entered HELO command or not
   * 
   * @Param - It's a String array named parts, which hold the first string 
   *           spaced by a white space, that string will help to determine
   *           if HELO was the first command from the users or not
   *@Param -  The second parameter is a boolean variable which decides whether
   *          we already seen HELO commands before or not, if we never seen
   *          the HELO command then we set equal to true and return it
   * 
   *@Return - Boolean variable named check_helo
   *
   */
  public static Boolean Check_For_HELO_Command(String [] parts, boolean check_helo)
  {
    if(parts[0].equals("HELO") && check_helo == false)               // Checking if the first index of the array is HELO and the boolean value is false
    {                                                                // in that case user never given HELO command before so it will force user to input 
      check_helo = true;                                             // HELO command as their first command
    }
    
    else                                                             // If the user does not provide the HELO command as their first command then 
    {                                                                // forcing them to enter HELO before they process any other commands with the server
      check_helo = false;
    } 
    
    return check_helo;                                                // Returning the boolean result back to the method
  }
  
  
  /** The Main Method */
  public static void main(String [] args)
  {
    String user_input, user_output, sec_user_input;  // Used for taking input and output from the socket to communicate with the client
    String [] parts;
    String test_user_input;                                          // All the declared variables
    boolean check_helo = false;                                      // Flag to make count if HELO command already given by users or not
    String test_user_second;
    
    try   // try block to catch exception if it occur when creating the server
    {
      ServerSocket server = new ServerSocket(10000);                // Making the server with the port of 10000
      InetAddress adr = InetAddress.getByName("localhost");         // Making the address to connect to the server      
      
      Socket socket = server.accept();                              // Accept if there's connection available
      
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);   // Getting the input from the client
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
     
      out.println("210 Welcome to the MINE Server");                // Printing out the welcome message once connection with server is established
      
      user_input = in.readLine();                                   // Reading the first command from the user 
      
      user_input = user_input.toUpperCase();                        // Make the command upper case since all the commands are upper case
      
      test_user_input = user_input;                               // saving the input string into temp variable to check for HELO command
      
      parts = test_user_input.split(" ");                     // spliting up the string  to check if the first command is HELO
      
      check_helo = Check_For_HELO_Command(parts, check_helo);       // Calling the Check_For_HELO-COmmand method to see if HELO already been issued by the user or not 
                             
     if(check_helo == true)
     {
       out.println("220 How are you doing, localhost.localdomain?");   // printing out the reply if it is
       
     }
     else
     {
       out.println("503! you must give a HELO command to start communicating with the server, please restart and try again");
       socket.close();
     }
     
      /* while loop is to listen on this socket for more communication until client decides to quit */
      while(((user_input = in.readLine()) != null) && (check_helo == true))     
      {
        user_input = user_input.toUpperCase();                     // making the user input to uppercase
        
        test_user_second = user_input;                              // saving the input into temporary variable so the real input doesn't get destroyed
        
        parts = test_user_second.split(" ");                        
        if(parts[0].equals("HELO") && check_helo == true)
        {
          out.println("220 How are you doing, localhost.localdomain?");          
          if(check_helo != true)
          {
            check_helo = true;
          }
        }
        
       else if(user_input.equals("QUIT"))                          // Exception for the QUIT command to exit
       {
         out.println("100 Don't go away mad, just go away");      
         socket.close();
         
         socket = server.accept();                              // Accept if there's connection available
         
         out = new PrintWriter(socket.getOutputStream(), true);   // Getting the input from the client
         in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
         
         out.println("210 Welcome to the MINE Server");                // Printing out the welcome message once connection with server is established         
         
       }
       
       else if(user_input.equals("MINE"))                         // Cases for MINE command and print out its respond
       {
         out.println("403 No, it's mine");
       }
       
       else if(user_input.equals("YOURS"))                         // Case for YOURS command and print out its respond
       {
         out.println("203 Of course it's mine");
       }
       
       else if(user_input.equals("SHARE"))                        // Case for the SHARE command and print out its respond
       {
         out.println("404 Not on your life");
        }
       else if((!user_input.equals("MINE")) && (!user_input.equals("YOURS")) && (!user_input.equals("SHARE")) ||(check_helo == false) ) // Any other case would be unrecognized commands
       {
         if(check_helo == false)
         {
           out.println("450 You must give HELO command at first before you start communicating with the server");
         }
         else
         {
           out.println("401 Unrecognized input");
         }
       }   
      }
    }     
    
    catch(Exception e)                                          // Handling the exceptions
    {
      System.out.println("It didn't work!");
    } 
  }
  
}
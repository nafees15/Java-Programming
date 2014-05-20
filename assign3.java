/**
 * @Author -- Nafees Noor
  * @ CSI - 516 - Computer Networking
  * @ Assignment 3   04/30/2014
 * This assignment is suppose to construct a simple web server
 * which listen on a port for connection then accept and
 * interact with a client and accept command from the client
 * it process those commands and close the connection upon request
 * It will also use the telnet program to emulate a simple client
 * It also handle commands like GET and multiple files type like txt, pdf, etc.
 **/
import java.net.*;
import java.io.*;           // Some of the important libaries for this assignment
import java.lang.*;
import java.util.*;

public class assign3
{

  static String fileRequested;
  static String content;
  /** The Main Method */
  public static void main(String [] args) throws IOException
  {
    File WEB_ROOT = new File(".");                           // making the current directory as the root diretory
    ServerSocket server_socket;
    server_socket = new ServerSocket(30000);                // Making the server with the port of 30000

    while(true)
    {
      try
      {
        Socket socket = server_socket.accept();              // this continue to accept if client browser trying to connect to the server
        new ClientHandler(socket);
      }
      catch (Exception x) {
        System.out.println(x);
      }
    }
  } // end of the main
}  // end of the class
// A ClientHandler reads an HTTP request and responds
class Client_Handler extends Thread {
  private Socket socket;  // The accepted socket from the Webserver

  // Start the thread in the constructor
  public Client_Handler(Socket s) {
      socket=s;
      start();
  }

  // Read the HTTP request, respond, and close the connection
 public void run() {
    try {

      // Open connections to the socket
      BufferedReader in=new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
      PrintStream out=new PrintStream(new BufferedOutputStream(
        socket.getOutputStream()));

      // Read filename from first input line "GET /filename.html ..."
      // or if not in this format, treat as a file not found.
      String s=in.readLine();
      System.out.println(s);  // Log the request

      // Attempt to serve the file.  Catch FileNotFoundException and
      // return an HTTP error "404 Not Found".  Treat invalid requests
      // the same way.
      String filename="";
      StringTokenizer st=new StringTokenizer(s);
      try {

        // Parse the filename from the GET command
        if (st.hasMoreElements() && st.nextToken().equalsIgnoreCase("GET")
            && st.hasMoreElements())
          filename=st.nextToken();
        else
          throw new FileNotFoundException();  // Bad request

        // Append trailing "/" with "index.html"
        if (filename.endsWith("/"))
          filename+="index.html";

        // Remove leading / from filename
        while (filename.indexOf("/")==0)
          filename=filename.substring(1);

        // Replace "/" with "\" in path for PC-based servers
        filename=filename.replace('/', File.separator.charAt(0));

        // Check for illegal characters to prevent access to superdirectories
        if (filename.indexOf("..")>=0 || filename.indexOf(':')>=0
            || filename.indexOf('|')>=0)
          throw new FileNotFoundException();

        // If a directory is requested and the trailing / is missing,
        // send the client an HTTP request to append it.
        if (new File(filename).isDirectory()) {
          filename=filename.replace('\\', '/');
          out.print("HTTP/1.0 401 Moved\r\n"+
                    "Location: /"+filename+"/\r\n\r\n");
          out.close();
          return;
        }

        // Open the file (may throw FileNotFoundException)
        InputStream f=new FileInputStream(filename);

        // Determine the MIME type and print HTTP header
        String mimeType="text/plain";
        if (filename.endsWith(".html") || filename.endsWith(".htm"))   // checking for html files
          mimeType="text/html";
        else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) // checking for the jpeg images
          mimeType="image/jpeg";
        else if (filename.endsWith(".gif"))   // checking for the gif picture files
          mimeType="image/gif";
        else if (filename.endsWith(".pdf"))  // checking to handle the pdf files
          mimeType="application/pdf";
        else if (filename.endsWith(".png"))  // checking to handle png picture files
          mimeType="image/png";
        else if (filename.endsWith(".css"))  // checking to handle .css files
          mimeType="text/css";
        
        // Send file contents to client, then close the connection
        byte[] a=new byte[4096];
        int n;
        while ((n=f.read(a))>0)
          out.write(a, 0, n);
        out.close();
      }
      catch (FileNotFoundException x) {
        out.println("HTTP/1.0 404 Not Found\r\n"+
          "Content-type: text/html\r\n\r\n"+
                    "<html><head></head><body>"+filename+" not found</body></html>\n");
        out.close();
      }
    }
    catch (IOException x) {
      System.out.println(x);
    }
 }

 /**
  * getContentType returns the proper MIME content type
  * according to the requested file's extension.
  *
  * @param fileRequested File requested by client
   */
 private  String getContentType(String fileRequested)
 {
   if (fileRequested.endsWith(".htm") ||
       fileRequested.endsWith(".html"))
   {
      return "text/html";
   }
   else if (fileRequested.endsWith(".gif"))
   {
     return "image/gif";
   }
    else if (fileRequested.endsWith(".jpg") ||
             fileRequested.endsWith(".jpeg"))
    {
      return "image/jpeg";
    }
    else if (fileRequested.endsWith(".class") ||
             fileRequested.endsWith(".jar"))
    {
      return "applicaton/octet-stream";
    }
    
    else if (fileRequested.endsWith(".pdf"))
    {
      return "application/pdf";
    }
    
    else if (fileRequested.endsWith(".png"))
    {
      return "image/png";
    }
    
    else if (fileRequested.endsWith(".css"))
    {
      return "test/css";
    }
    else
    {
      return "text/plain";
    }
 }

  /**
   * close method closes the given stream.
   *
   * @param stream
   */
 public  void close(Object stream)
  {
   if (stream == null)
     return;

   try
   {
      if (stream instanceof Reader)
      {
        ((Reader)stream).close();
      }

      else if (stream instanceof Writer)
      {
        ((Writer)stream).close();
      }
      else if (stream instanceof InputStream)
      {
        ((InputStream)stream).close();
      }
      else if (stream instanceof OutputStream)
      {
        ((OutputStream)stream).close();
      }
      else if (stream instanceof Socket)
      {
        ((Socket)stream).close();
      }
      else
      {
        System.err.println("Unable to close object: " + stream);
      }
    }
   catch (Exception e)
   {
     System.err.println("Error closing stream: " + e);
    }
 }
}


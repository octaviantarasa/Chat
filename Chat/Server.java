import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Server{

    public static void main(String []args){

        Database d = new Database();
        d.Display();

        ServerSocket server=null;
        try{
            server = new ServerSocket(4321);
        }catch(IOException e){
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }


        Socket clientSocket = null;
        int i=0;

        try{        
            while(true){
                clientSocket =server.accept();
                System.out.println("\nClient nou" + clientSocket);
                new LogIn(clientSocket,d);

            }
        }catch(IOException e){}
    }
}




class Conexiune extends Thread{
    Socket cs  = null;
    OutputStream os = null; InputStream is = null;
    InputStreamReader ir = null; 
    BufferedReader buffread = null;
    OutputStreamWriter ou = null;
    PrintWriter print = null;
    Scanner scan = null;
    int identitate;

    public Conexiune(Socket client, int i)
    throws IOException{
        cs=client; identitate = i;
        InputStreamReader in = new InputStreamReader(cs.getInputStream());
        is=cs.getInputStream(); os = cs.getOutputStream();
        buffread = new BufferedReader(in);
        ou = new OutputStreamWriter(os);
        print = new PrintWriter(cs.getOutputStream(), true);
        start();
        
    }
    public void run(){
        String line = null;
        while(true){
            try{
                line = buffread.readLine();
                if(line.equals("gigel")) {
                    print.println("Username is good");
                }
                else {
                    print.println("Username not good");
                }

            }catch(IOException e){
                System.out.println(e);
            }
            System.out.println(" "+identitate);
            

        }
    }
}



// public interface Observer{

//     //method to update the observer, used by subject 
//     public void update();

//     //attach with subject to observe
//     public void setSubject(Subject sub);
// }


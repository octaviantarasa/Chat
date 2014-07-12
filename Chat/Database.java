import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
public class Database {
    private Connection c = null;
    private Statement stmt = null;
    private List <User> LoggedUsers;
    private List <User> NotLoggedUsers;
    public Database(){
        LoggedUsers = new ArrayList<User>();
        NotLoggedUsers = new ArrayList<User>();
        try{
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:Chat.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();

           ResultSet rs = stmt.executeQuery("select * from USER;");

           while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int logged = rs.getInt("logged");

            if(logged == 1 ) {LoggedUsers.add(new User(name, id, logged));}
            else if(logged == 0) {NotLoggedUsers.add(new User(name, id, logged));}
            
        }
        rs.close();
        stmt.close();
        c.close();
        }catch(Exception e){
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

   
    }
     public Connection getTheConnection(){
        return this.c;
    }

    public Statement getStatement(){
        return this.stmt;
    }   
    public List<User> getLoggedUsers(){
        return LoggedUsers;
    }
    public List<User> getNotLoggedUsers(){
        return NotLoggedUsers;
    }
    public void Display(){
        System.out.println("Online users:");
        for(User item:LoggedUsers){
            System.out.println("Name: "+item.getName() + "----- ID: "+ item.getID());
        }
        System.out.println("Offline users:");
        for(User item:NotLoggedUsers){
            System.out.println("Name: "+item.getName() + "----- ID: "+ item.getID());
        }
    }
}

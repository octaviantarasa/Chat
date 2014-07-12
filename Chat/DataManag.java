import java.sql.*;

public class DataManag { 
	public static void main(String [] args){
		Connection c = null;
        Statement stmt = null;
        try{
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:Chat.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");

           stmt = c.createStatement();
           String sql = "CREATE TABLE USER" + 
           				"(ID INT PRIMARY KEY NOT NULL," +
           				"NAME TEXT NOT NULL)";
			stmt.executeUpdate(sql);
		 	sql = "INSERT INTO USER (ID, NAME)"+
		 			"VALUES (1,'Tavi');";
		 			stmt.executeUpdate(sql);
		 	sql = "INSERT INTO USER (ID, NAME)"+
		 			"VALUES (2,'Gogu');";
		 			stmt.executeUpdate(sql);
		 	sql = "INSERT INTO USER (ID, NAME)"+
		 			"VALUES (3,'Florin');";
		 			stmt.executeUpdate(sql);
		 	sql = "INSERT INTO USER (ID, NAME)"+
		 			"VALUES (4,'Marian');";
		 			stmt.executeUpdate(sql);
		 	sql = "INSERT INTO USER (ID, NAME)"+
		 			"VALUES (5,'Mihai');";
           stmt.executeUpdate(sql);
           stmt.close();
           c.commit();
           c.close();
       }catch(Exception e){
        System.out.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Table created successfully");
	}
}
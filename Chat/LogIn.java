import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;
public class LogIn extends Thread{
	private Socket cs = null;
	private InputStreamReader ir = null;
	private BufferedReader buffread = null;
	private OutputStreamWriter ow = null;
	private PrintWriter print =null;
	private Database d = null;

	public LogIn(Socket client, Database d) throws IOException{
		cs = client; 
		ir = new InputStreamReader(cs.getInputStream());
		buffread = new BufferedReader(ir);
		ow = new OutputStreamWriter(cs.getOutputStream());

		print = new PrintWriter(cs.getOutputStream(),true);
		this.d = d;
		start();
	}

	public void modify(Database d, String name){
		Connection c = d.getTheConnection();
		Statement stmt = d.getStatement();
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Chat.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from USER;");

			while(rs.next()){
				int id = rs.getInt("id");
				String name2 = rs.getString("name");
				int logged = rs.getInt("logged");
				System.out.println(id +  " " + name2+ " " + logged);
			}
			
			String sql = "UPDATE User set logged = 1 where name='"+name+"';";
			stmt.executeUpdate(sql);
			c.commit();
			System.out.println("user updatat :D");
			// ResultSet rs = stmt.executeQuery("select * from USER;");

			// while(rs.next()){
			// 	int id = rs.getInt("id");
			// 	String name = rs.getString("name");
			// 	int logged = rs.getInt("logged");

			// 	if(logged == 1 ) {LoggedUsers.add(new User(name, id, logged));}
			// 	else if(logged == 0) {NotLoggedUsers.add(new User(name, id, logged));}

			// }
			// rs.close();
			stmt.close();
			c.close();
		}catch(Exception e){

			System.out.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


	public void run(){
		String line = null;
		String result = null;
		while(true){
			try{
				line = buffread.readLine();
				result = verifyInput(line);
				if(result.equals("username correct")){
					modify(d,line);
				}
				if(result.equals("username already logged")){
					System.out.println("esti nebun?");
				}

			}catch(IOException e){
				System.out.println(e);
			}
		}
	}

	public String verifyInput(String text){
		for(User item: d.getNotLoggedUsers()){
			if((text.toLowerCase()).equals((item.getName()).toLowerCase() ))
				return "username correct";

		}
		for(User item: d.getLoggedUsers()){
			if((text.toLowerCase()).equals((item.getName()).toLowerCase() ))
				return "username already logged";

		}
		return "error";
	}
}

import java.net.*;
import java.io.*;
import java.util.*;
public class Client{
	public static void main(String[] args){
		Socket socket = null;
		String host = null;
		PrintWriter print = null;
		Scanner scan = new Scanner(System.in);
		String name = null;
		String line = "Username not good";
		InputStreamReader in;
		BufferedReader buffread;
		try{
			socket = new Socket("localhost", 4321);
			System.out.println(socket);
			print = new PrintWriter(socket.getOutputStream(),true);

			in = new InputStreamReader(socket.getInputStream());
			buffread = new BufferedReader(in);

			while(line.equals("Username not good")){
				System.out.println("Insert Username: ");
				name=scan.nextLine();
				print.println(name);
				line = buffread.readLine();
				System.out.println(line);
			}

			while(true){
				
			}
			
		}catch(IOException e){
			System.out.println(e);
		}
	}	
}
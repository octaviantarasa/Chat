import java.net.*;
import java.io.*;
import java.util.*;
public class Chat{

	public static void main(String[] args){
		Socket socket = null;
		String host = null;
		PrintWriter print = null;
		Scanner scan = new Scanner(System.in);
		String text = null;
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
				text=scan.nextLine();
				print.println(text);
				// System.out.println("I'm in the 'while' of Chat 1");
				line = buffread.readLine();
				// System.out.println("I'm in the 'while' of Chat 2");
				System.out.println(line);
			}
			
		}catch(IOException e){
			System.out.println(e);
		}
	}	
}
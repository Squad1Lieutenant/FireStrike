package Game;
import java.net.*;
import java.io.*;

public class Client {
	static Socket socket;
	static DataInputStream in;
	public static void main(String[] args)throws Exception{
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connection Successful");
		in = new DataInputStream(socket.getInputStream());
		System.out.println("Recieving Information");
		String test = in.readUTF();
		System.out.println("Message from server:" + test);
	}

}

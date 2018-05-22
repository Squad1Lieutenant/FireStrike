package Game;

import java.io.*;
import java.net.*;

public class Client
{
	static Socket socket;
	static DataInputStream in;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Connecting");
		socket = new Socket("localhost", 7777);
		System.out.println("Connection Established");
		in = new DataInputStream(socket.getInputStream());
		System.out.println("...");
		String test = in.readUTF();
		System.out.println("Message:" + test);
	}
}

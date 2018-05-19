package Game;
import java.io.*;
import java.net.*;


public class Server {
	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		socket = serverSocket.accept();
		System.out.println("Connection From:" +socket.getInetAddress());
		out = new DataOutputStream(socket.getOutputStream());
		out.writeUTF("This is a Nazi Germany Shooting Simulator");
		System.out.println("Sending Data");
	}

}

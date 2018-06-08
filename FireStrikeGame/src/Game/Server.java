package Game;

import java.io.*;

import java.net.*;
import java.util.Scanner;

public class Server

{

	private static final int MAX_USERS = 10;

	static Scanner sc;

	static ServerSocket serverSocket;

	static Socket socket;

	static DataOutputStream out;

	static DataInputStream in;

	static Users[] user = new Users[MAX_USERS];

	public static void main(String[] args) throws Exception

	{

		System.out.println("Starting Server...");

		serverSocket = new ServerSocket(7777);

		System.out.println("Server Started...");

		while (true)

		{

			socket = serverSocket.accept();

			for (int i = 0; i < 10; i++)

			{

				System.out.println("Connection From:" + socket.getInetAddress());

				out = new DataOutputStream(socket.getOutputStream());

				in = new DataInputStream(socket.getInputStream());

				if (user[i] == null)
				{

					user[i] = new Users(out, in, user, i);

					Thread thread = new Thread(user[i]);

					thread.start();

					break;
				}
			}

		}

	}

}
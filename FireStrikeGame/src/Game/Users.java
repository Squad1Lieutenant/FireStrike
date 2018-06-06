package Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class Users implements Runnable

{

	DataOutputStream out;

	DataInputStream in;

	Users[] user = new Users[10];

	String name;

	public Users(DataOutputStream out, DataInputStream in, Users[] user) {
		this.out = out;
		this.in = in;
		this.user = user;
	}

	public void run()

	{

		try {
			name = in.readUTF();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true)

		{

			int commandW;

			try

			{
				commandW = Integer.parseInt(in.readUTF());

				for (int i = 0; i < 10; i++)

				{

					if (user[i] != null)

					{
						System.out.println("Recieved player Y: " + commandW);
						commandW -= 5;

						System.out.println(commandW);
						user[i].out.writeUTF(Integer.toString(commandW));

					}

				}

			}

			catch (IOException e)

			{

				this.out = null;
				this.in = null;

			}

		}
	}

}

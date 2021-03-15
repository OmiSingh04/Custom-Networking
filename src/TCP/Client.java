package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public Client(String hostname, int port) {
		try {
			socket = new Socket(hostname, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		if (args.length != 2) {
			System.err.println("BRUH");
			System.exit(1);
		}

		try {
			Client client = new Client(args[0], Integer.parseInt(args[1]));
			Scanner stdIn = new Scanner(System.in);
			PrintWriter output = new PrintWriter(client.getSocket().getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
			String in;
			while (true) {
				in = stdIn.nextLine();
				if (in.equals("exit()"))
					break;
				output.println(in);
				System.out.println(reader.readLine());
			}
			reader.close();
			output.close();
			stdIn.close();
			client.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

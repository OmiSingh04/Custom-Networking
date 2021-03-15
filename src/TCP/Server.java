package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket server;

	public ServerSocket getServer() {
		return this.server;
	}

	public Server(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String args[]) {

		if (args.length != 1) {
			System.err.println("breh");
			System.exit(1);
		}

		try {
			Server server = new Server(Integer.parseInt(args[0]));
			Socket socket = server.getServer().accept();
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner stdIn = new Scanner(System.in);
			// first input, then output
			String in;
			while (true) {
				in = reader.readLine();
				System.out.println(in);
				String out = stdIn.nextLine();
				if (out.equals("exit()"))
					break;
				output.println(out);
			}
			output.close();
			reader.close();
			stdIn.close();
			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
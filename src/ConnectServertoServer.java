import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectServertoServer {
	Socket clientSocket;
	String connect;

	public ConnectServertoServer() throws Exception {
		this.clientSocket = new Socket("Mostafa-Nasr", 6769);
		// DataOutputStream outToServer = new
		// DataOutputStream(clientSocket.getOutputStream());
		// outToServer.writeBytes("join(Server1)"+'\n');
		// BufferedReader inFromServer = new BufferedReader(new
		// InputStreamReader(clientSocket.getInputStream()));
		// while (true) {
		// String modifiedSentence = inFromServer.readLine();
		// return (modifiedSentence);
		// }
		connect = connect();
	}

	public String connect() throws IOException {
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes("joins(Server)" + '\n');
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		while (true) {
			String modifiedSentence = inFromServer.readLine();
			return (modifiedSentence);
		}
	}

	public void connecttoserver1() throws Exception {
		// String sentence;
		// String modifiedSentence;

		// BufferedReader inFromUser = new BufferedReader(new
		// InputStreamReader(System.in));

		// Socket clientSocket = new Socket("Mostafa-Nasr", 6769);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		// BufferedReader inFromServer = new BufferedReader(new
		// InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes("Joinserver(S1)");
		// while (true) {

		// sentence = inFromUser.readLine();

		// outToServer.writeBytes(sentence + '\n');

		// modifiedSentence = inFromServer.readLine();

		// System.out.println("FROM SERVER: "+ modifiedSentence);

		// clientSocket.close();
		// if (sentence.equals("bye")) {
		// break;
		// }
		// }

	}

	public String getconnected() throws Exception {
		// Socket clientSocket = new Socket("Mostafa-Nasr", 6769);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes("Get" + '\n');

		while (true) {

			// sentence = inFromUser.readLine();

			// outToServer.writeBytes(sentence + '\n');

			String modifiedSentence = inFromServer.readLine();
			return (modifiedSentence);
			// System.out.println("FROM SERVER: "+ modifiedSentence);
			// System.out.println(".................");
			// x=".............";
			// break;
			// clientSocket.close();
			// if (sentence.equals("bye")) {
			// break;
			// }
			// }
		}
	}

	public String Getmem() throws IOException {
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		// System.out.println("fl Getmem");
		outToServer.writeBytes("GetMemberList()" + '\n');
		while (true) {

			// sentence = inFromUser.readLine();

			// outToServer.writeBytes(sentence + '\n');
			String modifiedSentence = inFromServer.readLine();
			return (modifiedSentence);
			// System.out.println("FROM SERVER: "+ modifiedSentence);
			// System.out.println(".................");
			// x=".............";
			// break;
			// clientSocket.close();
			// if (sentence.equals("bye")) {
			// break;
			// }
			// }
		}
	}

	public String chat(String x, String name, String rec) throws Exception {

		// Socket clientSocket = new Socket("Mostafa-Nasr", 6769);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		outToServer.writeBytes("chat(" + rec + ")" + name + " Sends: " + x + '\n');

		while (true) {

			// sentence = inFromUser.readLine();

			// outToServer.writeBytes(sentence + '\n');
			String modifiedSentence = inFromServer.readLine();
			return (modifiedSentence);
			// System.out.println("FROM SERVER: "+ modifiedSentence);
			// System.out.println(".................");
			// x=".............";
			// break;
			// clientSocket.close();
			// if (sentence.equals("bye")) {
			// break;
			// }
			// }
		}
	}
}

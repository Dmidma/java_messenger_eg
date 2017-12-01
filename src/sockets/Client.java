package sockets;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client implements ActionListener {


	private JFrame window;
	private JTextField ipTextField;
	private JTextField portTextField;
	private Socket echoSocket;

	public static void main(String[] args) {
		new Client();
	}


	public Client() {
		configureClientGUI();
	}

	private void configureClientGUI() {

		// Initializes window
		window = new JFrame("Dmidma Client");
		window.setSize(500, 500);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);


		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);


		// server name
		JLabel serverNameLabel = new JLabel("Server");
		serverNameLabel.setFont(serverNameLabel.getFont().deriveFont(20.0f));
		serverNameLabel.setBounds(new Rectangle(50, 20, 200, 20));
		mainPanel.add(serverNameLabel);


		// IP Label and TextField
		JLabel ipLabel = new JLabel("IP:");
		ipLabel.setFont(ipLabel.getFont().deriveFont(16.0f));
		ipLabel.setBounds(new Rectangle(50, 100, 80, 20));
		mainPanel.add(ipLabel);

		ipTextField = new JTextField();
		ipTextField.setBounds(new Rectangle(100, 100, 100, 30));
		mainPanel.add(ipTextField);



		// Port Label and TextField
		JLabel portLabel = new JLabel("Port:");
		portLabel.setFont(ipLabel.getFont().deriveFont(16.0f));
		portLabel.setBounds(new Rectangle(50, 150, 80, 20));
		mainPanel.add(portLabel);

		portTextField = new JTextField();
		portTextField.setBounds(new Rectangle(100, 150, 100, 30));
		mainPanel.add(portTextField);


		// Connect Button
		JButton connectButton = new JButton("Connect");
		connectButton.setBounds(new Rectangle(90, 200, 90, 40));
		mainPanel.add(connectButton);
		connectButton.addActionListener(this);

		// add main panel to the window
		window.getContentPane().add(mainPanel, BorderLayout.CENTER);


		// That's all Folks
		window.setVisible(true);
	}


	private void connectServer(String hostName, int port) {
		try {
			echoSocket = new Socket(hostName, port);


			System.out.println("Connected to server");

			// gets the socket's output stream and opens a PrintWriter on it
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

			// gets the input stream and opens a BufferedReader on it
			BufferedReader in = new BufferedReader(
					new InputStreamReader(echoSocket.getInputStream()));

			/*
			 * It uses reader and writers so that it can write Unicode characters
			 * over the socket.
			 * 
			 * To send data through the socket to the server: write to the 
			 * PrintWriter.
			 * 
			 * To get the server's response, the client reads from the 
			 * BufferedReader object stdIn.
			 */ 

			BufferedReader stdIn = new BufferedReader(
					new InputStreamReader(System.in));

			// reads a line at a time from the standard input stream
			// and immediately sends it to the server by writing it to
			// the PrintWriter.
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}

		} catch (Exception e) {

		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ("Connect".equals(arg0.getActionCommand())) {
			String hostName = ipTextField.getText();
			int port = Integer.parseInt(portTextField.getText());
			connectServer(hostName, port);
		}

	}
}

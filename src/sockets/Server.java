package sockets;


import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Server implements ActionListener, Runnable {
	
	
	private JFrame window;
	private JTextField portTextField;
	private JLabel ipTextField;
	private ServerSocket serverSocket;
	
	private int port;
	
	
	public static void main(String[] args) {
		new Server();
	}
	
	
	
	public Server() {
		configureServerGUI();
	}
	
	
	
	
	private void configureServerGUI() {
		
		// Initializes window
		window = new JFrame("Dmidma Server");
		window.setSize(270, 300);
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
		
		try {
			ipTextField = new JLabel(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	private void createServer(int port) {
		int portNumber = port;
		try {
			serverSocket = new ServerSocket(portNumber);
			
			System.out.println("Connected");
			
			// You can add a loop over the serverSocket.accept() to make the server
			// accept more than one client
			Socket clientScoket = serverSocket.accept();

			PrintWriter out = new PrintWriter(clientScoket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
			String inputLine, outputLine;
		
			
			// Initiate conversation with client
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye."))
					break;
			}

		} catch(Exception e) {

		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if ("Connect".equals(arg0.getActionCommand())) {
			
			// connect to server
			if (portTextField.getText() == null || portTextField.getText() == "") {
				System.out.println("You must enter a valid port number");
			}
			else {
				port = Integer.parseInt(portTextField.getText());
				
				Thread th = new Thread(this);
				th.start();
				
			}
			
			
		}
		
	}

	@Override
	public void run() {
		createServer(port);
		
	}
}

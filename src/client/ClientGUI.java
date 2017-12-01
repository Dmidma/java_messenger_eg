package client;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import general.definition.Menu;
import general.definition.Window;
import javafx.scene.shape.Box;

public class ClientGUI {

	// the one and the only frame
	private Window window;

	// event handler of the current GUI
	private HandleClientGUI handleGUI;

	// map to store text fields or password fields
	private HashMap<String, Component> fields;



	public ClientGUI() {

		// create window
		window = new Window("Dmidma Client", 350, 400, false);

		// create event handler
		handleGUI = new HandleClientGUI(this);
		// configure menu
		configureMenu();
		// initialize map
		fields = new HashMap<String, Component>();

		window.getContentPane().add(logInConfig(), BorderLayout.CENTER);

		// That's all folks
		window.setVisible(true);
	}


	/**
	 * This method will create the log in panel and returns it.
	 * 
	 * @return the created panel with all of it components.
	 */
	public JPanel logInConfig() {

		// initialize panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);


		// user and password labels
		String[] labels = {"User:", "Pass:"};
		int xBase = 50;
		int yBase = 50;
		for (int i = 0, j = 0; i < labels.length; i++, j += 100) {
			JLabel currentLabel = new JLabel(labels[i]);
			currentLabel.setFont(currentLabel.getFont().deriveFont(20.f));
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 30));
			mainPanel.add(currentLabel);
		}

		// user text fields
		String[] texts = {"User", "Pass"};
		xBase = 100;
		yBase = 50;
		JTextField userField = new JTextField();
		userField.setFont(userField.getFont().deriveFont(16.f));
		fields.put(texts[0], userField);
		userField.setBounds(xBase, yBase, 150, 50);
		mainPanel.add(userField);


		// pass password filed
		yBase += 100;
		JPasswordField passField = new JPasswordField();
		passField.setFont(passField.getFont().deriveFont(16.f));
		fields.put(texts[1], passField);
		passField.setBounds(xBase, yBase, 150, 50);
		mainPanel.add(passField);

		// create button
		JButton logIn = new JButton("Log In");
		logIn.setBounds(new Rectangle(100, 250, 100, 40));
		logIn.addActionListener(handleGUI);
		mainPanel.add(logIn);

		return mainPanel;
	}

	/**
	 * This method will create the sign up panel and returns it.
	 * @return the created panel with all of it components.
	 */
	public JPanel signUpConfig() {


		this.window.getContentPane().removeAll();
		this.window.setSize(new Dimension(330, 500));


		// initialize panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);


		// user and password labels
		String[] labels = {"User:", "Pass:", "Confirm:"};
		int xBase = 50;
		int yBase = 50;
		for (int i = 0, j = 0; i < labels.length; i++, j += 100) {
			JLabel currentLabel = new JLabel(labels[i]);
			currentLabel.setFont(currentLabel.getFont().deriveFont(20.f));
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 30));
			mainPanel.add(currentLabel);
		}

		// user text fields
		String[] texts = {"User", "Pass", "confirm"};
		xBase = 100;
		yBase = 50;
		JTextField userField = new JTextField();
		userField.setFont(userField.getFont().deriveFont(16.f));
		fields.put(texts[0], userField);
		userField.setBounds(xBase, yBase, 150, 50);
		mainPanel.add(userField);


		// pass password filed
		yBase += 100;
		JPasswordField passField = new JPasswordField();
		passField.setFont(passField.getFont().deriveFont(16.f));
		fields.put(texts[1], passField);
		passField.setBounds(xBase, yBase, 150, 50);
		mainPanel.add(passField);

		// confirm password filed
		yBase += 100;
		JPasswordField confirmField = new JPasswordField();
		confirmField.setFont(confirmField.getFont().deriveFont(16.f));
		fields.put(texts[2], confirmField);
		confirmField.setBounds(xBase, yBase, 150, 50);
		mainPanel.add(confirmField);

		// create button
		JButton logIn = new JButton("Sign Up");
		logIn.setBounds(new Rectangle(120, 350, 100, 40));
		logIn.addActionListener(handleGUI);
		mainPanel.add(logIn);

		return mainPanel;
	}



	public void openWhereServer() {

		// create a second frame
		JFrame whereFrame = new JFrame();
		whereFrame.setTitle("Server Location:");
		whereFrame.setSize(350, 300);
		whereFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		whereFrame.setLocationRelativeTo(window);

		whereFrame.getContentPane().add(createWhereServerPanel(), BorderLayout.CENTER);


		// that's all folks
		whereFrame.setVisible(true);

	}
	
	
	/**
	 * This method opens a new frame so the user can enters the server's IP and Port.
	 * TextHere -> JTextArea
	 * SendMessage -> JTextField
	 * SidePanel -> JPanel
	 */
	public void openConfigServer() {

		// create a second frame
		JFrame whereFrame = new JFrame();
		whereFrame.setTitle("Configure Server Location:");
		whereFrame.setSize(350, 400);
		whereFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		whereFrame.setLocationRelativeTo(window);

		whereFrame.getContentPane().add(createConfigureServerPanel(), BorderLayout.CENTER);


		// that's all folks
		whereFrame.setVisible(true);
	}


	/**
	 * This method will initialize the chat interface.
	 */
	public void initLogedChat() {
		
		// remove all items of window
		this.window.getContentPane().removeAll();
		this.window.setResizable(true);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(16.f));
		textArea.setEditable(false);
		fields.put("TextHere", textArea);
		this.window.getContentPane().add(textArea, BorderLayout.CENTER);
		
		
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		JLabel connected = new JLabel("Connected Users:");
		connected.setFont(connected.getFont().deriveFont(15.f));
		sidePanel.add(connected);
		
		fields.put("SidePanel", sidePanel);
		
		
		this.window.getContentPane().add(sidePanel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextField messageField = new JTextField();
		messageField.setFont(messageField.getFont().deriveFont(16.f));
		fields.put("SendMessage", messageField);
		
		JButton sendMessage = new JButton("Send");
		sendMessage.addActionListener(handleGUI);
		
		panel.add(messageField);
		panel.add(sendMessage);
		
		this.window.getContentPane().add(panel, BorderLayout.SOUTH);
		
		this.window.repaint();
		this.window.validate();

	}
	
	/**
	 * Getter for the main Frame. 
	 * @return the current main window.
	 */
	public Window getWindow() {
		return this.window;
	}


	/**
	 * Given the name of the field, the method will returns the field stored in the map.
	 * @param FieldName the name of the wanted field.
	 * @return the corresponding field if it exists
	 */
	public Component getField(String FieldName) {
		return fields.get(FieldName);
	}


	/**
	 * IPLabel
	 * portLabel
	 * @param IP
	 * @param port
	 */
	public void configureIPAndPort(String IP, String port) {

		JLabel ipLabel = new JLabel(IP);
		JLabel portLabel = new JLabel(port);

		fields.put("IPLabel", ipLabel);
		fields.put("portLabel", portLabel);
	}


	/**
	 * This private method will create the menu for the client GUI.
	 * It will also add listeners to all menu's elements.
	 */
	private void configureMenu() {

		Menu menu = new Menu();

		// main menu bar
		String[] menus = {"What?", "Config", "Exit"};
		menu.addMenuElems(menus);

		// Items under What?
		String[] whatItems = {"Log In", "Sign Up"};
		menu.addItemsToMenu(whatItems, "What?");

		// Items under Config
		String[] configItems = {"@", "Where"};
		menu.addItemsToMenu(configItems, "Config");


		// add listeners to all menu elements
		menu.addActionListenerToItems(handleGUI);
		menu.addMenuListenerToMenu(handleGUI);

		// That's all folks
		window.setJMenuBar(menu);
	}


	// TODO: write a general method that take an array of String, x, y so it will create labels in a panel
	// and return the panel.
	/**
	 * This private method will create a panel for the server configuration.
	 * 
	 * @return
	 */
	private JPanel createConfigureServerPanel() {

		// initialize panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		// create title 
		JLabel title = new JLabel("Where is the Server?");
		title.setFont(title.getFont().deriveFont(20.f));
		title.setBounds(new Rectangle(10, 10, 200, 50));
		mainPanel.add(title);


		// create labels
		String[] labels = {"IP:", "Port:"};
		int xBase = 20;
		int yBase = 80;
		for (int i = 0, j = 0; i < labels.length; i++, j += 100) {
			JLabel currentLabel = new JLabel(labels[i]);
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 40));
			mainPanel.add(currentLabel);
		}

		// create text fields 
		String[] fieldNames = {"IP", "Port"};
		xBase = 50;
		for (int i = 0, j = 0; i < fieldNames.length; i++, j += 100) {
			JTextField currentField = new JTextField();
			this.fields.put(fieldNames[i], currentField);
			currentField.setBounds(new Rectangle(xBase, yBase + j, 100, 50));
			currentField.setFont(currentField.getFont().deriveFont(16.f));
			mainPanel.add(currentField);
		}



		// add the button
		JButton button = new JButton("Configure");
		button.addActionListener(handleGUI);
		button.setBounds(new Rectangle(150, 250, 100, 50));
		mainPanel.add(button);


		return mainPanel;
	}


	private JPanel createWhereServerPanel() {

		// initialize panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		// create title 
		JLabel title = new JLabel("The server is:");
		title.setFont(title.getFont().deriveFont(20.f));
		title.setBounds(new Rectangle(10, 10, 200, 50));
		mainPanel.add(title);


		// create labels
		String[] labels = {"IP:", "Port:"};
		int xBase = 20;
		int yBase = 80;
		for (int i = 0, j = 0; i < labels.length; i++, j += 100) {
			JLabel currentLabel = new JLabel(labels[i]);
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 40));
			mainPanel.add(currentLabel);
		}

		// add ip and port label
		String[] labals = {"IPLabel", "portLabel"};
		xBase = 50;
		for (int i = 0, j = 0; i < labals.length; i++, j += 100) {
			JLabel currentLabel = (JLabel)fields.get(labals[i]);
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 50));
			currentLabel.setFont(currentLabel.getFont().deriveFont(16.f));
			mainPanel.add(currentLabel);
		}


		return mainPanel;

	}
}	

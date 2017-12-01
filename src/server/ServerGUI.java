package server;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import general.definition.Menu;
import general.definition.Window;
import javafx.scene.layout.Border;
import javafx.scene.shape.Box;

public class ServerGUI {


	// main frame
	private Window window;

	// event handler
	private HandleServerGUI handler; 

	// map for text fields
	private HashMap<String, JTextField> fields;
	
	// table for connected users
	private JTable connectedTable;
	
	// table data
	private Vector<Vector<String>> data;
	
	
	// port label
	private JLabel portLabel;
	
	public ServerGUI() {


		// System.out.println(Inet4Address.getLocalHost().getHostAddress());

		this.window = new Window("Dmidma Server", 500, 500, true);
		// create event handler for the server GUI
		this.handler = new HandleServerGUI(this);
		// initialize map
		fields = new HashMap<String, JTextField>();
		menuConfig();
		headerConfig();
		centerConfig();


		// That's all Folks!
		window.setVisible(true);
	}
		
	
	
	public void configPort() {
		
		// create the port configuration frame
		Window portWindow = new Window("Config Port", 300, 200, false);
		portWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		// main panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		
		// port label
		JLabel portLabel = new JLabel("Port:");
		portLabel.setFont(portLabel.getFont().deriveFont(20.f));
		portLabel.setBounds(new Rectangle(10, 10, 100, 50));
		mainPanel.add(portLabel);
		
		
		// Port field
		JTextField portField = new JTextField();
		fields.put("Port", portField);
		portField.setBounds(new Rectangle(100, 10, 100, 50));
		portField.setFont(portField.getFont().deriveFont(16.f));
		mainPanel.add(portField);
		
		
		
		
		// change port button
		JButton changeButton = new JButton("Change");
		changeButton.setBounds(new Rectangle(100, 100, 100, 50));
		changeButton.addActionListener(handler);
		mainPanel.add(changeButton);
		
		
		
		portWindow.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		// That's all Folks
		portWindow.setVisible(true);
	}
	
	
	
	

	
	public JTextField getField(String fieldName) {
		return this.fields.get(fieldName);
	}
	
	public JLabel getPortLabel() {
		return this.portLabel;
	}
	
	public Window getWindow() {
		return this.window;
	}
	
	private void configTable() {

		
		data = new Vector<Vector<String>>();
		Vector<String> columns = new Vector<String>();
		columns.add("User");
		columns.add("IP");
		
		
		this.connectedTable = new JTable(new DefaultTableModel(data, columns));		
		
		
	}
	
	
	
	/**
	 * This private method will initialize the scroll pane and add it to 
	 * the center of the main Frame.
	 */
	private void centerConfig() {
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		JLabel here = new JLabel("I am here");
		here.setFont(here.getFont().deriveFont(30.f));
		
		
		
		configTable();
		
		
		scrollPane.getViewport().add(connectedTable);
		
	
		
		// That's all folks
		window.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	
	/**
	 * This method will create the header panel and all its labels.
	 */
	private void headerConfig() {

		// create header panel
		JPanel headPanel = new JPanel();
		headPanel.setLayout(null);
		headPanel.setPreferredSize(new Dimension(500, 150));

		// create title for the panel
		JLabel panelTitle = new JLabel("Dmidma Server:");
		panelTitle.setFont(panelTitle.getFont().deriveFont(20.f));
		panelTitle.setBounds(new Rectangle(10, 10, 200, 50));
		headPanel.add(panelTitle);
		
		
		
		
		// create labels
		String[] labels = {"IP:", "Port:"};
		int xBase = 50;
		int yBase = 50;
		for (int i = 0, j = 0; i < labels.length; i++, j += 50) {
			JLabel currentLabel = new JLabel(labels[i]);
			currentLabel.setFont(currentLabel.getFont().deriveFont(16.f));
			currentLabel.setBounds(new Rectangle(xBase, yBase + j, 100, 50));
			headPanel.add(currentLabel);
		}
		
		// create IP label
		xBase = 150;
		JLabel ipLabel;
		try {
			ipLabel = new JLabel(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			ipLabel = new JLabel("N/A");
		}
		
		ipLabel.setFont(ipLabel.getFont().deriveFont(14.f));
		ipLabel.setBounds(new Rectangle(xBase, yBase, 100, 50));
		headPanel.add(ipLabel);

		
		// create Port label
		yBase = 100;
		portLabel = new JLabel("N/A");
		portLabel.setFont(portLabel.getFont().deriveFont(14.f));
		portLabel.setBounds(new Rectangle(xBase, yBase, 100, 50));
		headPanel.add(portLabel);
				
		
		// That's All Folks!
		window.getContentPane().add(headPanel, BorderLayout.NORTH);

	}

	/**
	 * This private method will create the menu and add it the main Frame.
	 */
	private void menuConfig() {

		// initialize menu
		Menu menu = new Menu();


		// main menus
		String[] menus = {"What?", "Exit"};
		menu.addMenuElems(menus);

		// under What? items
		String[] whatItems = {"Port", "Start", "Stop"};
		menu.addItemsToMenu(whatItems, "What?");


		menu.addActionListenerToItems(handler);
		menu.addMenuListenerToMenu(handler);


		// That's all Folks
		window.setJMenuBar(menu);
	}
	
	
	
	
}

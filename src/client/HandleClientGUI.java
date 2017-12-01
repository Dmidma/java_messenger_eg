package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import messenger.Client;

public class HandleClientGUI implements ActionListener, MouseListener, MenuListener{



	// Reference of the handled GUI
	private ClientGUI clientGUI;


	private Client client;


	public HandleClientGUI(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}



	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuSelected(MenuEvent e) {

		if ("Exit".equals(((JMenuItem)e.getSource()).getText())) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// action from one of the GUI's buttons
		if (arg0.getSource() instanceof JButton) {


			if ("Send".equals(arg0.getActionCommand())) {
				String textToSend = ((JTextField)clientGUI.getField("SendMessage")).getText();
				if (textToSend != null && !"".equals(textToSend)) {
					int sendToID = Integer.parseInt(textToSend.split("->")[0]);

					((JTextField)clientGUI.getField("SendMessage")).setText("");
					((JTextArea)clientGUI.getField("TextHere"))
					.setText(((JTextArea)clientGUI.getField("TextHere")).getText() 
							+ "\n" 
							+ textToSend);

					try {
						client.sendMessageTo(client.getMyID() + "->" + textToSend.split("->")[1], sendToID);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			if ("Log In".equals(arg0.getActionCommand())) {
				if ("dmidma".equals(((JTextField)clientGUI.getField("User")).getText()) && 
						"dmidma".equals(((JPasswordField)clientGUI.getField("Pass")).getText())) {
					System.out.println("Here");
					String ip = ((JTextField)clientGUI.getField("IP")).getText();
					int port = Integer.parseInt(((JTextField)clientGUI.getField("Port")).getText());
					try {
						this.client = new Client(ip, port);
						System.out.println("client up");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					this.clientGUI.initLogedChat();


					Thread th = new Thread(new Runnable() {

						@Override
						public void run() {
							while (true) {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								HashSet<Integer> ids = null;
								try {
									ids = client.getOtherIds();
								} catch (RemoteException ex) {
									ex.printStackTrace();
								}
								((JPanel)clientGUI.getField("SidePanel")).removeAll();
								JLabel titleLabel = new JLabel("Current connected:");
								titleLabel.setFont(titleLabel.getFont().deriveFont(15.f));
								((JPanel)clientGUI.getField("SidePanel")).add(titleLabel);
								for (int i : ids) {
									JLabel currentId = new JLabel(Integer.toString(i));
									currentId.setFont(currentId.getFont().deriveFont(14.f));

									((JPanel)clientGUI.getField("SidePanel")).add(currentId);
								}
								((JPanel)clientGUI.getField("SidePanel")).repaint();
								((JPanel)clientGUI.getField("SidePanel")).validate();
								
								
								String message = client.checkMessages();
								
								if (!message.equals("")) {
									((JTextArea)clientGUI.getField("TextHere"))
									.setText(((JTextArea)clientGUI.getField("TextHere")).getText() + "\n" + message);
								}
								
								
							}
						}
					});
					th.start();

				}
			}

			if ("Sign Up".equals(arg0.getActionCommand())) {
				System.out.println(((JTextField)clientGUI.getField("User")).getText());
				System.out.println(((JPasswordField)clientGUI.getField("Pass")).getPassword());
				System.out.println(((JPasswordField)clientGUI.getField("confirm")).getPassword());
			}

			if ("Configure".equals(arg0.getActionCommand())) {
				String ip = ((JTextField)clientGUI.getField("IP")).getText();
				String port = ((JTextField)clientGUI.getField("Port")).getText();
				clientGUI.configureIPAndPort(ip, port);
			}

		}
		// action from one of the GUI's menu items
		else if (arg0.getSource() instanceof JMenuItem) {

			if ("Log In".equals(arg0.getActionCommand())) {
				clientGUI.getWindow().getContentPane().removeAll();
				clientGUI.getWindow().getContentPane().add(clientGUI.logInConfig(), BorderLayout.CENTER);
				clientGUI.getWindow().repaint();
				clientGUI.getWindow().validate();
			}

			if ("Sign Up".equals(arg0.getActionCommand())) {
				clientGUI.getWindow().getContentPane().add(clientGUI.signUpConfig(), BorderLayout.CENTER);
				clientGUI.getWindow().repaint();
				clientGUI.getWindow().validate();
			}

			if ("@".equals(arg0.getActionCommand())) {
				clientGUI.openConfigServer();
			}

			if ("Where".equals(arg0.getActionCommand())) {
				clientGUI.openWhereServer();
			}
		}

	}

}

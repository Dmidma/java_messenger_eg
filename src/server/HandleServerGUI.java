package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import messenger.Server;

public class HandleServerGUI implements ActionListener, MenuListener, MouseListener {

	
	private ServerGUI serverGUI;
	
	private Server server;
	
	public HandleServerGUI(ServerGUI serverGUI) {
		
		this.serverGUI = serverGUI;
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
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		
		if ("Exit".equals(((JMenuItem)arg0.getSource()).getText())) {
			System.exit(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if ("Port".equals(arg0.getActionCommand())) {
			serverGUI.configPort();
		}
	
		
		if ("Stop".equals(arg0.getActionCommand())) {
			System.out.println("Stop !!!!");
		}
		
		if ("Change".equals(arg0.getActionCommand())) {
			
			String port = serverGUI.getField("Port").getText();
			
			serverGUI.getPortLabel().setText(port);
		}
		
		if ("Start".equals(arg0.getActionCommand())) {
			
			int portNumber = 0;
			try {
				portNumber = Integer.parseInt(serverGUI.getPortLabel().getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(serverGUI.getWindow(), "Invalid Port Number!");
				return;
			}
			
			try {
				this.server = new Server(portNumber);
				JOptionPane.showMessageDialog(serverGUI.getWindow(), "Server Started =D");
			} catch (RemoteException e) {
				
			}
			
		}

	}
	

}

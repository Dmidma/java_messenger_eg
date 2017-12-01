package messenger;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	
	private int myID;
	private Messenger messenger;
	
	private Queue<String> messageQueues = new LinkedList<String>();
	
	public Client(String ip, int port) throws AccessException, RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry(ip, port);
		messenger = (Messenger) registry.lookup("messenger");
		int id = messenger.getID();
		myID = id;
		new ServerTask().start();
	}
	
	public int getMyID() {
		return this.myID;
	}
	
	public HashSet<Integer> getOtherIds() throws RemoteException {
		HashSet<Integer> ids = new HashSet<Integer>();
		
		for (int i : messenger.getUsers()) {
			ids.add(i);
		}
		ids.remove(myID);
		return ids;
	}
	
	public void sendMessageTo(String txt, int id) throws RemoteException {
		messenger.sendMessage(txt, id);
	}
	
	public String checkMessages() {
		String message = "";
		if (!messageQueues.isEmpty()) {
			message = messageQueues.poll();
		}
		
		return message;
	}
	
	
	class ServerTask extends Thread implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					
					
					String message = messenger.readMessage(myID);
					
					if (message != null) {
						messageQueues.add(message);
						messenger.clearBox(myID);
					}
					
					
					/*
					byte[] b = messenger.readFile(myID);
					if (b != null) {
						String name = messenger.readMessage(myID);
						if (name != null) {
							JFileChooser fc = new JFileChooser();
							fc.showSaveDialog(null);
							File file = fc.getSelectedFile();
							if (file != null) {
								messenger.clearBox(myID);
								java.io.FileOutputStream fos = new FileOutputStream(file + name);
								fos.write(b);
								fos.flush();
								fos.close();
							}
						}
					}
					*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	

}

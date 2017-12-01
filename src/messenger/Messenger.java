package messenger;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Messenger extends Remote {

	public int getID() throws RemoteException;

	public void sendMessage(String msg, int id) throws RemoteException;

	public String readMessage(int id) throws RemoteException;

	public Vector<Integer> getUsers() throws RemoteException;

	public void clearBox(int id) throws RemoteException;

	public void sendFile(byte[] f, String name, int id) throws RemoteException;

	public byte[] readFile(int id) throws RemoteException;
	

}

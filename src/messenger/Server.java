package messenger;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class Server extends UnicastRemoteObject implements Messenger {


	private static final long serialVersionUID = 1L;

	private HashMap<Integer, String> map = new HashMap<Integer, String>();

	private HashMap<Integer, byte[]> mapFiles = new HashMap<Integer, byte[]>();
	
	private HashSet<Integer> ids = new HashSet<Integer>();
	
	public Server(int port) throws RemoteException {

		Registry registry = LocateRegistry.createRegistry(port);
		registry.rebind("messenger", this);
	}


	public Server(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	@Override
	public int getID() throws RemoteException {
		int id = new Random().nextInt(1000);
		ids.add(id);
		map.put(id, "Bonjour, votre ID est " + id);
		System.out.println(id);
		return id;
	}
	
	

	@Override
	public void sendMessage(String msg, int id) throws RemoteException {
		map.put(id, msg);
	}

	@Override
	public String readMessage(int id) throws RemoteException {
		return map.get(id);
	}

	@Override
	public Vector<Integer> getUsers() throws RemoteException {
		Set<Integer> set = map.keySet();
		Vector<Integer> v = new Vector<Integer>();
		for (Integer i : set) {
			v.add(i);
		}
		return v;
	}

	@Override
	public void clearBox(int id) throws RemoteException {
		map.put(id, null);

	}

	@Override
	public void sendFile(byte[] f, String name, int id) throws RemoteException {
		mapFiles.put(id, f);
		map.put(id, name);
	}

	@Override
	public byte[] readFile(int id) throws RemoteException {
		return mapFiles.get(id);
	}
	
	

}

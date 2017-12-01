package server;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

public class ServerTableModel extends AbstractTableModel {


	private static final long serialVersionUID = 5L;

	
	
	private ArrayList<String> header;
	private HashMap<String, ArrayList<String>> data;
	
	
	
	public ServerTableModel(ArrayList<String> header, HashMap<String, ArrayList<String>> data) {
		this.header = header;
		this.data = data;
	}
	
	@Override
	public int getColumnCount() {
		return header.size();
	}

	@Override
	public int getRowCount() {
		
		if (data.size() > 0) {
			return data.get(header.get(0)).size();
		}
		
		// the map does not contain any data
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		
		return data.get(header.get(arg1)).get(arg0);
	}
	
}

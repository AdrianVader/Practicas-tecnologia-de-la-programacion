package tp.pr5.gui;

import javax.swing.table.DefaultTableModel;

public class Jtable extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	public boolean isCellEditable (int row, int column){
	       return false;
	   }
}

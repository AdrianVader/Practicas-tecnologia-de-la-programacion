package tp.pr4.gui;

import javax.swing.table.DefaultTableModel;

public class Jtable extends DefaultTableModel{

	public boolean isCellEditable (int row, int column){
	       return false;
	   }
}

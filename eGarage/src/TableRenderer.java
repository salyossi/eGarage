import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	private int row, col;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		// Save row and column information for use in setValue().
		this.row = row;
		this.col = column;

		// Allow superclass to return rendering component.
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	}

	protected void setValue(Object v) {
		// Allow superclass to set the value.
		super.setValue(v);

		if(row == 2 && col == 2) {
			setForeground(Color.yellow);
			setBackground(Color.red);
		}
			
		

	}

}

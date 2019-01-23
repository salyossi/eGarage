import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCellRenderer implements TableCellRenderer {

	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		int status = EgarageDB.getSlotStatus(column, row);

		switch (status) {
		case 1:
			c.setBackground(Color.GREEN);
			break;
		case 2:
			c.setBackground(Color.YELLOW);
			break;
		case 3:
			c.setBackground(Color.BLUE);
			break;
		case 4:
			c.setBackground(Color.RED);
			break;
			
		default:
			c.setBackground(Color.WHITE);
			break;
		}

		c.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		return c;
	}
};

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableSignRenderer implements TableCellRenderer {

	private static final DefaultTableCellRenderer RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// this renderer is created just to make first column aligned to the right
		// and all other columns to be centered
		
		switch (column) {
	
		case 0:
			RENDERER.setHorizontalAlignment( JLabel.RIGHT );
			RENDERER.setFont(new Font("Ariel", Font.BOLD, 14));
			
			break;
		default:
			RENDERER.setHorizontalAlignment( JLabel.CENTER );

		}

		return c;
	}
};

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCellRenderer implements TableCellRenderer {

	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
	private AlarmEventListener myAlarmEventListener;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		int status = EgarageDB.getSlotStatus(column, row);

		switch (status) {
		case 1:
			c.setBackground(Color.GREEN);

			// pass the event to listeners
			if (myAlarmEventListener != null)
				myAlarmEventListener.checkAlarm(row, column);
			break;
		case 2:
			c.setBackground(Color.YELLOW);
			// pass the event to listeners
			if (myAlarmEventListener != null)
				myAlarmEventListener.checkAlarm(row, column);
			break;
		case 3:
			c.setBackground(Color.BLUE);
			// pass the event to listeners
			if (myAlarmEventListener != null)
				myAlarmEventListener.checkAlarm(row, column);
			break;
		case 4:
			c.setBackground(Color.RED);
			int tmpCarIdInLevelAndSlot = EgarageDB.getCarIdInLevelAndSlot(row, column);
			int tmpCarTypeInParkingList = EgarageDB.getCarTypeInParkingList(row, column);
			int tmpCarTypeInUserList = EgarageDB.getCarTypeInUserList(tmpCarIdInLevelAndSlot);
			
			if ((tmpCarTypeInParkingList == 3 && tmpCarTypeInUserList != 3) || (tmpCarTypeInParkingList == 2 && tmpCarTypeInUserList == 1)) {
				// pass the event to listeners
				if (myAlarmEventListener != null)
					myAlarmEventListener.raseAlarm(row, column);
			}

			break;

		default:
			c.setBackground(Color.WHITE);
			break;
		}

		c.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		return c;
	}

	public void setAlarmEventListener(AlarmEventListener listener) {
		this.myAlarmEventListener = listener;
	}

};

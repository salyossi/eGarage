import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.table.*;

public class ParkingUseMapPanel extends TimerTask {

	private JPanel p, pp1;
	private JLabel l1;
	private JTable parkingUseMapTable;
	private JScrollPane sp;
	private JSeparator sep;

	private Timer timer;
	private int alarmColumn = 0;
	private int alarmRow = 0;
	private boolean isAlarmOn;

	public ParkingUseMapPanel(String l1Text) {

		// JTextField textBox = new JTextField();

		setL1(new JLabel(l1Text));
		getL1().setAlignmentX(Component.CENTER_ALIGNMENT);
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));
		setP(new JPanel());
		getP().setLayout(new BoxLayout(getP(), BoxLayout.PAGE_AXIS));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().add(l1);
		setSep(new JSeparator());
		getSep().setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 25));

		// Create a default table model consisting of the headers columns
		// and 4 rows representing the eGarage Levels.
		String[] headers = { "קומה", "חניה 1", "חניה 2", "חניה 3", "חניה 4", "חניה 5", "חניה 6", "חניה 7", "חניה 8",
				"חניה 9", "חניה 10" };
		DefaultTableModel dtm = new DefaultTableModel(headers, 4) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		// Populate all cells in the default table model.

		String[] levels = { "קומה 0", "קומה 1", "קומה 2", "קומה 3" };
		String[] parking1 = { "", "", "", "" };
		String[] parking2 = { "", "", "", "" };
		String[] parking3 = { "", "", "", "" };
		String[] parking4 = { "", "", "", "" };
		String[] parking5 = { "", "", "", "" };
		String[] parking6 = { "", "", "", "" };
		String[] parking7 = { "", "", "", "" };
		String[] parking8 = { "", "", "", "" };
		String[] parking9 = { "", "", "", "" };
		String[] parking10 = { "", "", "", "" };

		int nrows = dtm.getRowCount();
		int ncols = dtm.getColumnCount();

		for (int i = 0; i < nrows; i++) {
			dtm.setValueAt(levels[i], i, 0);
			dtm.setValueAt(parking1[i], i, 1);
			dtm.setValueAt(parking2[i], i, 2);
			dtm.setValueAt(parking3[i], i, 3);
			dtm.setValueAt(parking4[i], i, 4);
			dtm.setValueAt(parking5[i], i, 5);
			dtm.setValueAt(parking6[i], i, 6);
			dtm.setValueAt(parking7[i], i, 7);
			dtm.setValueAt(parking8[i], i, 8);
			dtm.setValueAt(parking9[i], i, 9);
			dtm.setValueAt(parking10[i], i, 10);
		}

		// Create a table using the previously created default table
		// model
		parkingUseMapTable = new JTable(dtm);

		// Create a renderer for displaying cells in certain colors.
		// this represents LEDS in the Garage
		TableColorCellRenderer renderer = new TableColorCellRenderer();
		// renderer.setAlarmEventListener(this);
		getParkingUseMapTable().setDefaultRenderer(Object.class, renderer);

		// set headers to be bold
		Font f = new Font("Arial", Font.BOLD, 14);
		JTableHeader header = getParkingUseMapTable().getTableHeader();
		header.setFont(f);

		getParkingUseMapTable().setCellSelectionEnabled(true);
		getParkingUseMapTable().enableInputMethods(false);
		getParkingUseMapTable().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		ListSelectionModel select = getParkingUseMapTable().getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSP(new JScrollPane(getParkingUseMapTable()));

		setPP1(new JPanel());
		getPP1().setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
		getPP1().setLayout(new GridLayout(1, 0));
		getPP1().add(getSP());

		getP().removeAll();
		getP().add(getL1());
		getP().add(getSep());
		getP().add(getPP1());
		getP().revalidate();
		getP().repaint();

	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public JPanel getPP1() {
		return pp1;
	}

	public void setPP1(JPanel p) {
		this.pp1 = p;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel L) {
		this.l1 = L;
	}

	public JTable getParkingUseMapTable() {
		return parkingUseMapTable;
	}

	public void setParkingUseMapTable(JTable TBL) {
		this.parkingUseMapTable = TBL;
	}

	public JScrollPane getSP() {
		return sp;
	}

	public void setSP(JScrollPane SP) {
		this.sp = SP;
	}

	public JSeparator getSep() {
		return sep;
	}

	public void setSep(JSeparator SEP) {
		this.sep = SEP;
	}

	public void updatePanel() {
		getP().revalidate();
		getP().repaint();
	}

	@Override
	public void run() {
		Toolkit.getDefaultToolkit().beep();
	}

}

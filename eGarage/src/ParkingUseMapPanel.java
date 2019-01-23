import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class ParkingUseMapPanel {

	private JPanel p;
	private JLabel l1;
	private JTable table;
	private JScrollPane sp;
	private JSeparator sep;

	public ParkingUseMapPanel(String l1Text) {

		JTextField textBox = new JTextField();
		// TableModel model = new myTableModel();

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
		DefaultTableModel dtm = new DefaultTableModel(headers, 4);

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
		table = new JTable(dtm);
		
		// Create a renderer for displaying cells in certain colors.
		// this represents LEDS in the Garage
		TableColorCellRenderer renderer = new TableColorCellRenderer();
		table.setDefaultRenderer(Object.class, renderer);

		getTable().setCellSelectionEnabled(true);
		getTable().enableInputMethods(false);
		getTable().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		ListSelectionModel select = getTable().getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSP(new JScrollPane(getTable()));

		getP().removeAll();
		getP().add(getL1());
		getP().add(getSep());
		getP().add(getSP());
		getP().revalidate();
		getP().repaint();

	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel L) {
		this.l1 = L;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable TBL) {
		this.table = TBL;
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

}

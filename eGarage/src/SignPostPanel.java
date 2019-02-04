
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SignPostPanel {

	private JPanel p, pp1;
	private JLabel l1;
	private JTable table;
	private JScrollPane sp;
	private JSeparator sep;
	private int nrows = 4;
	// Populate levels & headers for default table model.
	private String[] levels = { "קומה 0", "קומה 1", "קומה 2", "קומה 3" };
	private String[] headers = { "קומה", "פנוי חניה רגילה", "פנוי חניה מנויים", "פנוי חניה נכים" };
	private String[] free1 = new String[nrows]; // Free parking of general parking for each level
	private String[] free2 = new String[nrows]; // Free parking of VIP parking for each level
	private String[] free3 = new String[nrows]; // Free parking of handicaps parking for each level
	private DefaultTableModel dtm;

	public SignPostPanel(String l1Text) {

		setL1(new JLabel(l1Text));
		getL1().setAlignmentX(Component.CENTER_ALIGNMENT);
		getL1().setFont(new Font("Ariel", Font.PLAIN, 16));
		setP(new JPanel());
		getP().setLayout(new BoxLayout(getP(), BoxLayout.PAGE_AXIS));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().add(l1);
		setSep(new JSeparator());
		getSep().setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 25));

		// Create a default table model consisting of the headers columns
		// and "nrows" representing the eGarage Levels.
		dtm = new DefaultTableModel(headers, nrows) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		for (int i = 0; i < nrows; i++) {
			free1[i] = Integer.toString(EgarageDB.getFreeRegularInLevel(i, 1)); // Column of free regular clients
			free2[i] = Integer.toString(EgarageDB.getFreeRegularInLevel(i, 2)); // Column of free VIP clients
			free3[i] = Integer.toString(EgarageDB.getFreeRegularInLevel(i, 3)); // Column of free handicaps clients
			dtm.setValueAt(levels[i], i, 0);
			dtm.setValueAt(free1[i], i, 1);
			dtm.setValueAt(free2[i], i, 2);
			dtm.setValueAt(free3[i], i, 3);
		}

		// Create a table using the previously created default table
		// model
		table = new JTable(dtm);

		// Create a renderer for displaying cells in certain orientation.
		TableSignRenderer renderer = new TableSignRenderer();
		table.setDefaultRenderer(Object.class, renderer);

		// set headers to be bold
		Font f = new Font("Arial", Font.BOLD, 14);
		JTableHeader header = table.getTableHeader();
		header.setFont(f);

		getTable().setCellSelectionEnabled(true);
		getTable().enableInputMethods(false);
		getTable().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		ListSelectionModel select = getTable().getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSP(new JScrollPane(getTable()));

		setPP1(new JPanel());
		getPP1().setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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

	public void updateSignPost(String Level, String Type) {

		switch (Type) {
		case "1":
			dtm.setValueAt(Integer.toString(EgarageDB.getFreeRegularInLevel(Integer.parseInt(Level), 1)),
					Integer.parseInt(Level), 1);
			break;
		case "2":
			dtm.setValueAt(Integer.toString(EgarageDB.getFreeRegularInLevel(Integer.parseInt(Level), 2)),
					Integer.parseInt(Level), 2);
			break;
		case "3":
			dtm.setValueAt(Integer.toString(EgarageDB.getFreeRegularInLevel(Integer.parseInt(Level), 3)),
					Integer.parseInt(Level), 3);
			break;
		}
	}

	public JSeparator getSep() {
		return sep;
	}

	public void setSep(JSeparator SEP) {
		this.sep = SEP;
	}

}

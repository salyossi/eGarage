
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SignPostPanel {

	private JPanel p, pp1;
	private JLabel l1;
	private JTable table;
	private JScrollPane sp;
	private JSeparator sep;

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
		// and 4 rows representing the eGarage Levels.
		String[] headers = { "קומה", "סה''כ", "חניה רגיל", "חניה מנוי", "חניה נכה" };
		DefaultTableModel dtm = new DefaultTableModel(headers, 4){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		// Populate all cells in the default table model.

		String[] levels = { "קומה 0", "קומה 1", "קומה 2", "קומה 3" };
		String[] sign1 = { "", "", "", "1" };
		String[] sign2 = { "", "", "1", "" };
		String[] sign3 = { "", "1", "", "" };
		String[] sign4 = { "1", "", "", "" };

		int nrows = dtm.getRowCount();
		int ncols = dtm.getColumnCount();

		for (int i = 0; i < nrows; i++) {
			dtm.setValueAt(levels[i], i, 0);
			dtm.setValueAt(sign1[i], i, 1);
			dtm.setValueAt(sign2[i], i, 2);
			dtm.setValueAt(sign3[i], i, 3);
			dtm.setValueAt(sign4[i], i, 4);
		}

		// Create a table using the previously created default table
		// model
		table = new JTable(dtm);
		
		// Create a renderer for displaying cells in certain colors.
		// this represents LEDS in the Garage
		TableSignRenderer renderer = new TableSignRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		
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

	
	public JSeparator getSep() {
		return sep;
	}

	public void setSep(JSeparator SEP) {
		this.sep = SEP;
	}

}

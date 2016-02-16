//Author: ah25327
//Class: CSC 205 (003N) with Professor Tanes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Display extends JPanel implements ActionListener {
	JTextField entry;
	JLabel resultDisplay;
	final static int GAP = 10;
	static String result = "";

	public Display() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		//Creates Entry Panel to the left that will include buttons and text field
		JPanel leftHalf = new JPanel() {
			public Dimension getMaximumSize() {
				Dimension pref = getPreferredSize();
				return new Dimension(Integer.MAX_VALUE, pref.height);
			}
		};
		leftHalf.setLayout(new BoxLayout(leftHalf, BoxLayout.PAGE_AXIS));
		leftHalf.add(createEntryFields());
		leftHalf.add(createButtons());

		add(leftHalf);
		add(createresultDisplay());
	}

	protected JComponent createButtons() {
		//Adding and creates the components to the left side
		JPanel panel = new JPanel(new GridLayout(0, 1));
		JButton button = new JButton("Convert To Single Precision Floating Binary");
		button.addActionListener(this);
		button.setActionCommand("DSB");
		button.setPreferredSize(new Dimension(250, 20));
		panel.add(button);

		button = new JButton("Convert To Double Precision Floating Binary");
		button.addActionListener(this);
		button.setActionCommand("DDB");
		button.setPreferredSize(new Dimension(200, 20));
		panel.add(button);

		button = new JButton("Convert From Binary to Decimal");
		button.addActionListener(this);
		button.setActionCommand("BD");
		button.setPreferredSize(new Dimension(250, 20));
		panel.add(button);

		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, GAP - 5, GAP - 5));
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		//Declares what actions are performed depending on the button click
		try {
			if ("DSB".equals(e.getActionCommand())) {
				result = Converter.decimalTo7554(entry.getText(), true);
			} else if ("DDB".equals(e.getActionCommand())) {
				result = Converter.decimalTo7554(entry.getText(), false);
			} else {
				result = Converter.parseValueToDecimal(entry.getText());
			}

			updateDisplays();
		} catch (NumberFormatException nfe) {
			result = "Incorrectly formatted number";
			updateDisplays();
		}
	}

	protected void updateDisplays() {
		//updates the results display
		resultDisplay.setText(result);

	}

	protected JComponent createresultDisplay() {
		//Creates the results display on the right of the GUI that consists of the text label with the results
		JPanel panel = new JPanel(new BorderLayout());
		resultDisplay = new JLabel();
		resultDisplay.setHorizontalAlignment(JLabel.CENTER);

		updateDisplays();

		// Lay out the panel.
		panel.setBorder(BorderFactory.createEmptyBorder(GAP / 2, // top
				0, // left
				GAP / 2, // bottom
				0)); // right
		panel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.LINE_START);
		panel.add(resultDisplay, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(500, 150));

		return panel;
	}

	private static void createAndShowGUI() {
		// Creates and sets up the window.
		JFrame frame = new JFrame("Binary Converter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame.add(new Display());

		// Displays the window.
		frame.pack();
		frame.setVisible(true);
	}

	protected JComponent createEntryFields() {
		JPanel panel = new JPanel();

		String labelString = "Number: ";

		// Creates the text field and sets it up.
		entry = new JTextField();
		entry.setColumns(42);
		JComponent component = entry;
		JLabel label;

		label = new JLabel(labelString, JLabel.TRAILING);
		label.setLabelFor(entry);
		panel.add(entry);
		panel.add(component);

		JTextField tf = null;

		tf = (JTextField) component;

		tf.addActionListener(this);

		return panel;
	}

	public static void main(String[] args) {
		//Creates and opens the display once the program is run
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

}

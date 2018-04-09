package edu.ncsu.csc216.forest_system.gui;

import edu.ncsu.csc216.forest_system.model.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 * Graphical user interface for an ecosystem simulation.
 * @author Jo Perry
 */
public class SimulatorGUI extends JFrame implements ActionListener {

	private final SimpleSimulator simulator; // Backend model
    private int totalSteps;  // Number of steps simulated so far
    
    // Strings for buttons and labels
    private final static String TOTAL_STEPS = "Total steps: ";
    private final static String NUMBER_OF_STEPS = "Number of steps to run: ";
    private final static String RUN = "Run";
    private final static String QUIT = "Quit";
    private final static String TITLE = "Forest Ecosytem Simulation";
   
    // Visual components
    private JTextArea cell[]; // The display grid
    
    // Components for user input
    private final JButton btnQuit = new JButton(QUIT);    
    private final JButton btnRun = new JButton(RUN); 
    private final JLabel lblSteps = new JLabel(NUMBER_OF_STEPS);  
    private final JLabel lblTotalSteps = new JLabel(TOTAL_STEPS);
    private JTextField txtSteps;
    private JTextField txtTotalSteps;
    
    // Panels for the components
    private final JPanel pnlInstructions = new JPanel();
    private final JPanel pnlDisplay = new JPanel();	
    private final JPanel pnlLegend = new JPanel();

    /**
     * Constructor -- constructs the entire user interface.
     * @param simulator simulation model
     */
    public SimulatorGUI(SimpleSimulator simulator)
    {       
    	super();
    	this.simulator = simulator;
    	// Set up the window
        setSize(550, 600);
        setLocation(50, 50);
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        // Set up the widgets and panels
        setUpButtonsAndTextFields();
        setUpControlPanel();
        setUpDisplayPanel();
        setUpLegend();
        
        // Add the control and result panel to the container.
        Container container = getContentPane(); 
        container.add(pnlInstructions, BorderLayout.NORTH);
        container.add(pnlDisplay, BorderLayout.CENTER);
        container.add(pnlLegend, BorderLayout.SOUTH);
    }
    
    /**
     * Called when a button is clicked.
     * @param event indicates which button is clicked 
     */
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == btnQuit)
            System.exit(0);
        if (event.getSource() == btnRun) {
          try {
            start(Integer.parseInt(txtSteps.getText()));
          }
          catch (NumberFormatException nfe) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            txtSteps.setText(" ");
          }
        }
    }
    
    /**
     * Initiates the system and its graphical user interface.
     * @param args input file representing the initial environment
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {  
    	String filename = null;
    	try {
    		if (args.length == 1) {
    			filename = args[0];
    		}
    		SimpleSimulator simulation = new SimpleSimulator(filename);
            SimulatorGUI gui = new SimulatorGUI(simulation);
            gui.setVisible(true);
    	}
    	catch (IllegalArgumentException e) {
    		// Bail out if the input file is missing or improper
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Input File Error",JOptionPane.PLAIN_MESSAGE);
    		System.exit(1);   		
    	}
    }
    
    //------------- Private methods ----------------------------------    
    
    /**
     * Populates the panel holding the controls.
     */
    private void setUpControlPanel() {
        pnlInstructions.add(lblTotalSteps);
        pnlInstructions.add(txtTotalSteps);
        
        pnlInstructions.add(lblSteps);
        pnlInstructions.add(txtSteps);
        
        pnlInstructions.add(btnRun);
        pnlInstructions.add(btnQuit);   	
    }
    
    /**
     * Establishes a legend for the display symbols.
     */
    private void setUpLegend(){
    	pnlLegend.setLayout(new BoxLayout(pnlLegend, BoxLayout.PAGE_AXIS));
    	String[] toShow = simulator.displayItemNames();
    	pnlLegend.add(new JLabel("  "));
    	for (String s : toShow) {
    		pnlLegend.add(new JLabel("  " + s));
    	}
    	pnlLegend.add(new JLabel("  "));  	
    }
    
    /**
     * Sets up the buttons and text fields that go across the top of the window.
     */
    private void setUpButtonsAndTextFields() {
    	// Set up the buttons
        btnQuit.addActionListener(this);
        btnQuit.setBackground(Color.white);
        btnRun.addActionListener(this);
        btnRun.setBackground(Color.white);
        
        // Now set up the text fields
        totalSteps = 0;
        txtTotalSteps = new JTextField(3);
        txtTotalSteps.setEditable(false);
        txtTotalSteps.setBackground(Color.white);
        txtTotalSteps.setText("" + totalSteps);       
        txtSteps = new JTextField(3);   	
    }
    
    /**
     * Sets up the panel that holds the simulation results.
     */
    private void setUpDisplayPanel(){
    	GridLayout layoutForResults = new GridLayout(20, 20, 5, 5);
        pnlDisplay.setLayout(layoutForResults);
        cell = new JTextArea[400];
        //Initialize each square as blanks (with periods).
        for (int i = 0; i < 400; i++) {
        	cell[i] = new JTextArea(".", 1, 1);
        	pnlDisplay.add(cell[i]);    	
        }
        drawSimulationDisplay();    	
    }
	
	/**
     * Draws the environment on the grid.
     */
	private void drawSimulationDisplay() {
		Item[][] system = simulator.snapshot();
		int rows = system.length;
		int columns = system[0].length;
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < columns; k++) {
				if (system[i][k] == null) {
					cell[i * rows + k].setText(".");
					cell[i * rows + k].setBackground(Color.black);
				}
				else {
					cell[i * rows + k].setText(system[i][k].getSymbol() + "");
					cell[i * rows + k].setBackground(system[i][k].getColor());
				}
			}
		}
	}
    
    /**
     * Starts the simulation running for given number of steps.
     * @param steps Number of steps to run the simulation
     */
	private void start(int steps) {
		for (int k = 0; k < steps; k++) {
			simulator.step();
			totalSteps++;
			txtTotalSteps.setText("" + totalSteps);
			drawSimulationDisplay();
			update(getGraphics());
		}
	}

}
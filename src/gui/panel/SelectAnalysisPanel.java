package gui.panel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import analysis.AbstractFileAnalyzer;
import analysis.CountTokenOccurrenceAnalyzer;


/**
 * UI class for selecting an analysis action
 * @author Patrick
 */
public class SelectAnalysisPanel extends JPanel{

	private static final int LAYOUT_GAP = 20; //px
	
	/**
	 * default serial version ID as required for extending JPanel
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Interactive UI components
	 */
	JComboBox<AbstractFileAnalyzer> dropdownChooseAnalysis;
	JButton buttonAnalyze;
	
	/**
	 * Default constructor
	 * 
	 * set-up UI elements
	 */
	public SelectAnalysisPanel() {
		super();
		prepareLayout();
	}
	
	private void prepareLayout() {
		SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        JLabel labelCaption = new JLabel("Select Analysis");
        dropdownChooseAnalysis = new JComboBox<AbstractFileAnalyzer>();
        dropdownChooseAnalysis.addItem(new CountTokenOccurrenceAnalyzer(null));
        
        buttonAnalyze = new JButton();
        buttonAnalyze.setAction(invokeAnalyzerAction);
        buttonAnalyze.setText("Analyze");
                
        this.add(labelCaption);
        this.add(dropdownChooseAnalysis);
        this.add(buttonAnalyze);
         
        layout.putConstraint(SpringLayout.NORTH, labelCaption, LAYOUT_GAP, SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, labelCaption, LAYOUT_GAP, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dropdownChooseAnalysis, LAYOUT_GAP,SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, dropdownChooseAnalysis, LAYOUT_GAP,SpringLayout.EAST, labelCaption);
        layout.putConstraint(SpringLayout.NORTH, buttonAnalyze, LAYOUT_GAP,SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, buttonAnalyze, LAYOUT_GAP,SpringLayout.EAST, dropdownChooseAnalysis);
	}
	
	private AbstractAction invokeAnalyzerAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ANALYZE");
		}
	};
}

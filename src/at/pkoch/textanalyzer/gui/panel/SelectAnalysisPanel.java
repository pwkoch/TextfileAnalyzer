package at.pkoch.textanalyzer.gui.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

import at.pkoch.textanalyzer.analysis.analyzer.AbstractFileAnalyzer;
import at.pkoch.textanalyzer.analysis.analyzer.CountTokenOccurrenceAnalyzer;

/**
 * SelectAnalysisPanel
 * 
 * UI class for selecting an analysis action.
 * @author Patrick
 */
public class SelectAnalysisPanel extends AbstractUIPanel{

	/*
	 * INTERFACES
	 */
	
	/**
	 * ISelectedFilePanelEventListener
	 * 
	 * Interface for callback events.
	 * @author Patrick
	 */
	public interface ISelectAnalysisPanelEventListener {
		
		/**
		 * onAnalyze
		 * 
		 * Event callback method, invoked when an analysis should be started.
		 * @param analyzer AbstractFileAnalyzer to be invoked for the analysis 
		 */
		public void onAnalyze(AbstractFileAnalyzer analyzer);
		
		/**
		 * onCancel
		 * 
		 * Event callback method, invoked when a running analysis should be cancelled.
		 */
		public void onCancel(AbstractFileAnalyzer analyzer);
		
	}
	
	/*
	 * CONSTANTS
	 */
	
	//default serial version ID as required for extending JPanel
	private static final long serialVersionUID = 1L;
	
	/*
	 * MEMBER VARIABLES
	 */
	
	//registered event listener for the panel
	ISelectAnalysisPanelEventListener eventListener;

	//UI components
	JComboBox<AbstractFileAnalyzer> dropdownChooseAnalysis;
	JButton buttonAnalyze;
	JProgressBar progressBarAnalysis;
	JButton buttonCancelAnalysis;
	
	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 * Default constructor
	 * 
	 * Set event listener and set-up UI elements.
	 */
	public SelectAnalysisPanel(ISelectAnalysisPanelEventListener eventListener) {
		super();
		this.eventListener = eventListener;
		prepareLayout();
	}
	
	/**
	 *  setEnabled
	 * 
	 *  Sets enabled status of contained UI elements.
	 *  Cancel button should be disabled on start
	 */
	@Override
	public void setEnabled(boolean enabledStatus) {
		for (Component c : this.getComponents()) {
			c.setEnabled(enabledStatus);
		}
		buttonCancelAnalysis.setEnabled(false);
	}
	
	/**
	 * onAnalysisStarted
	 * 
	 * A new analysis has been started; disable start button.
	 */
	public void onAnalysisStarted() {
		buttonAnalyze.setEnabled(false);
		dropdownChooseAnalysis.setEnabled(false);
		buttonCancelAnalysis.setEnabled(true);
	}
	
	/**
	 * onAnalysisUpdate
	 * 
	 * The analysis process has progressed to the given percentage.
	 * @param percentage current percentage of analysis process as integer
	 */
	public void onAnalysisUpdate(int percentage) {
		progressBarAnalysis.setValue(percentage);
	}
	
	/**
	 * onAnalysisStopped
	 * 
	 * An analysis has stopped;. reset buttons and progress bar.
	 */
	public void onAnalysisStopped() {
		buttonAnalyze.setEnabled(true);
		dropdownChooseAnalysis.setEnabled(true);
		buttonCancelAnalysis.setEnabled(false);
		progressBarAnalysis.setValue(0);
	}
	
	
	/*
	 * PRIVATE METHODS
	 */	
	
	private void prepareLayout() {
		SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        JLabel labelCaption = new JLabel("Select Analysis");
        dropdownChooseAnalysis = new JComboBox<AbstractFileAnalyzer>();
        dropdownChooseAnalysis.addItem(new CountTokenOccurrenceAnalyzer());
        
        buttonAnalyze = new JButton();
        buttonAnalyze.setAction(startAnalysisAction);
        buttonAnalyze.setText("Analyze");
        
        progressBarAnalysis = new JProgressBar();
       
        buttonCancelAnalysis = new JButton();
        buttonCancelAnalysis.setAction(cancelAnalysisAction);
        buttonCancelAnalysis.setText("Cancel");
                
        this.add(labelCaption);
        this.add(dropdownChooseAnalysis);
        this.add(buttonAnalyze);
        this.add(progressBarAnalysis);
        this.add(buttonCancelAnalysis);
         
        layout.putConstraint(SpringLayout.NORTH, labelCaption, LAYOUT_GAP, SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, labelCaption, LAYOUT_GAP, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dropdownChooseAnalysis, LAYOUT_GAP, SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, dropdownChooseAnalysis, LAYOUT_GAP, SpringLayout.EAST, labelCaption);
        layout.putConstraint(SpringLayout.NORTH, buttonAnalyze, LAYOUT_GAP, SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, buttonAnalyze, LAYOUT_GAP, SpringLayout.EAST, dropdownChooseAnalysis);
        layout.putConstraint(SpringLayout.NORTH, progressBarAnalysis, LAYOUT_GAP, SpringLayout.SOUTH, labelCaption);  
        layout.putConstraint(SpringLayout.WEST, progressBarAnalysis, 0, SpringLayout.WEST, labelCaption);
        layout.putConstraint(SpringLayout.NORTH, buttonCancelAnalysis, LAYOUT_GAP, SpringLayout.SOUTH, labelCaption);  
        layout.putConstraint(SpringLayout.WEST, buttonCancelAnalysis, 0, SpringLayout.WEST, buttonAnalyze);
	}
	
	
	/*
	 * UI EVENT HANDLING ACTIONS
	 */
	
	/**
	 * startAnalysisAction
	 * 
	 * Notifies application to start an analysis.
	 */
	private AbstractAction startAnalysisAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			eventListener.onAnalyze((AbstractFileAnalyzer)dropdownChooseAnalysis.getSelectedItem());
		}
	};
	
	/**
	 * cancelAnalysisAction
	 * 
	 * Notifies application to cancel an analysis.
	 */
	private AbstractAction cancelAnalysisAction = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			eventListener.onCancel((AbstractFileAnalyzer)dropdownChooseAnalysis.getSelectedItem());
		}
	};

}

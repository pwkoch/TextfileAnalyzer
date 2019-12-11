package application;

import analysis.AnalysisResultType;
import analysis.analyzer.AbstractFileAnalyzer;
import analysis.analyzer.AbstractFileAnalyzer.IFileAnalysisListener;

import gui.frame.AnalysisResultFrame;
import gui.frame.TextAnalyzerFrame;
import gui.panel.SelectAnalysisPanel;
import gui.panel.SelectAnalysisPanel.ISelectAnalysisPanelEventListener;
import gui.panel.SelectFilePanel;
import gui.panel.SelectFilePanel.ISelectFilePanelEventListener;


/**
 * Application
 * 
 * Main class of TextAnalyzer Project.
 * Creates new Application object which builds and serves as callback-handler for UI elements.
 * @author Patrick
 */
public class Application implements ISelectFilePanelEventListener, ISelectAnalysisPanelEventListener, IFileAnalysisListener {

	/**
	 * main
	 * 
	 * Main method, invoked at startup of TextAnalyzer project.
	 * Creates new Application object.
	 * @param args unused
	 */
	public static void main(String[] args) {
		new Application();
	}

	/*
	 * MEMBER VARIABLES
	 */
	
	SelectFilePanel selectFilePanel;
	SelectAnalysisPanel selectAnalysisPanel;
	
	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 * Constructor
	 * 
	 * Creates UI, registers event callbacks and shows interface.
	 */
	private Application() {
		TextAnalyzerFrame frame = new TextAnalyzerFrame();
        
		selectFilePanel = new SelectFilePanel(this);
		frame.add(selectFilePanel);
		
		selectAnalysisPanel = new SelectAnalysisPanel(this);
		frame.add(selectAnalysisPanel);
		selectAnalysisPanel.setEnabled(false); //disabled until a valid file is selected

		frame.setVisible(true);
	}
	
	
	/*
	 * CALLBACK METHODS OF ISelectFilePanelEventListener INTERFACE
	 */

	/**
	 * onFileSelected
	 * 
	 * Event handler callback, registered with SelectFilePanel; invoked after an eligible file was selected.
	 * Enables SelectAnalysisPanel.
	 */
	@Override
	public void onFileSelected() {
		selectAnalysisPanel.setEnabled(true);
	}
	
	/*
	 * CALLBACK METHODS OF ISelectAnalysisPanelEventListener INTERFACE
	 */

	/**
	 * onAnalyze
	 * 
	 * Event handler callback, registered with SelectAnalysisPanel; invoked when an analysis should be performed.
	 * Disables other UI components and starts an analysis.
	 */
	@Override
	public void onAnalyze(AbstractFileAnalyzer analyzer) {
		selectFilePanel.setEnabled(false);
		selectAnalysisPanel.onAnalysisStarted();
		analyzer.processFile(selectFilePanel.getFile(), this);
	}

	/**
	 * onCancel
	 * 
	 * Event handler callback, registered with SelectAnalysisPanel; invoked when an analysis has been cancelled.
	 * Requests cancellation of analysis.
	 */
	@Override
	public void onCancel(AbstractFileAnalyzer analyzer) {
		analyzer.cancelAnalysis();
	}
	
	/*
	 * CALLBACK METHODS OF ISelectAnalysisPanelEventListener INTERFACE
	 */

	/**
	 * onFileAnalysisFailed
	 * 
	 * Event handler callback, registered with AbstractFileAnalyzer; invoked when an analysis process has failed.
	 * Enable other UI components and reset progress
	 */
	@Override
	public void onFileAnalysisFailed(String message) {
		selectFilePanel.setEnabled(true);
		selectAnalysisPanel.onAnalysisStopped();
	}

	/**
	 * onProcessUpdate
	 * 
	 * Event handler callback, registered with AbstractFileAnalyzer; invoked to signal process updates.
	 * Updates process bar on SelectAnalysisPanel.
	 */
	@Override
	public void onProcessUpdate(int percentage) {
		selectAnalysisPanel.onAnalysisUpdate(percentage);
	}

	/**
	 * onFileAnalysisComplete
	 * 
	 * Event handler callback, registered with AbstractFileAnalyzer; invoked to when an analysis process has concluded.
	 * Resets UI components and visualizes the analysis results in a new frame.
	 */
	@Override
	public void onFileAnalysisComplete(Object result, AnalysisResultType resultType) {
		selectFilePanel.setEnabled(true);
		selectAnalysisPanel.onAnalysisStopped();
		AnalysisResultFrame.visualizeAnalysisResult(result, resultType);
	}
}

package at.pkoch.textanalyzer.analysis.analyzer;

import java.io.File;

import at.pkoch.textanalyzer.analysis.AnalysisResultType;

/**
 * AbstractFileAnalyzer
 * 
 * Abstract class that models an asynchronous analysis of a provided textfile, providing various event callbacks during the analysis.
 * @author Patrick
 */
public abstract class AbstractFileAnalyzer {

	/*
	 * INTERFACES
	 */
	
	/**
	 * FileAnalysisListener
	 * 
	 * Interface to be implemented by invoking components to listen for process-relevant events.
	 * @author Patrick
	 */
	public interface IFileAnalysisListener {
		
		/**
		 * onFileAnalysisFailed
		 * 
		 * Event callback in case the analysis process has failed.
		 * @param message String that describes the failure cause.
		 */
		void onFileAnalysisFailed(String message);
		
		/**
		 * onProcessUpdate
		 * 
		 * Event callback to notify user of current analysis state.
		 * @param percentage int describing the current state from 0 to 100 %
		 */
		void onProcessUpdate(int percentage);
		
		/**
		 * onFileAnalysisComplete
		 * 
		 * Event callback in case the analysis process has finished.
		 * @param result Object containing the analysis result
		 * @param analysisResultType Enum describing the analysis result
		 */
		void onFileAnalysisComplete(Object result, AnalysisResultType analysisResultType);
	}

	
	/*
	 * MEMBER VARIABLES
	 */
	
	//registered callback listener
	protected IFileAnalysisListener listener;
	
	//file to be analyzed
	protected File file;
	
	//worker thread
	private Thread worker;

	
	/*
	 * INTERFACE METHODS
	 */

	/**
	 * processFile
	 * 
	 * Start the analysis process for the provided file.
	 * @param file File to be analyzed
	 * @param listener callback listener for events during analysis
	 */
	public void processFile(File file, IFileAnalysisListener listener) {
		this.file = file;
		this.listener = listener;
		if (!isEligible()) {
			listener.onFileAnalysisFailed("File not eligible for analysis.");
		} else {
			worker = new Thread(() -> {
				Object analysisResult = analyzeFile();
				if (analysisResult != null) {
					listener.onFileAnalysisComplete(analysisResult, AbstractFileAnalyzer.this.getResultType());
				} else {
					listener.onFileAnalysisFailed("An error occurred during file analysis.");
				}
            });
			worker.start(); 
		}
	}
	
	/**
	 * cancelAnalysis
	 * 
	 * Requests cancellation of a running analysis process.
	 */
	public void cancelAnalysis() {
		worker.interrupt();
	}
	
	/**
	 * toString
	 * 
	 * Default toString method; the provided String is used to select an analysis.
	 */
	public abstract String toString();
	
	/**
	 * getResultType
	 * 
	 * Determines the content of the analysis result.
	 * @return enum determining the result type
	 */
	public abstract AnalysisResultType getResultType();	
		
	
	/*
	 * PROTECTED METHODS TO BE IMPLEMENTED BY ANALYZERS
	 */
	
	/**
	 * isEligible 
	 * 
	 * @return true if provided file is eligible for analysis; false otherwise
	 */
	protected abstract boolean isEligible();
	
	/**
	 * analyzeFile
	 * 
	 * Implements file analysis process.
	 * Finished with callback to "onFileAnalysisComplete"
	 */
	protected abstract Object analyzeFile();	
	
}
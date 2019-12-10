package analysis;

import java.io.File;

public abstract class AbstractFileAnalyzer {

	public static final String MESSAGE_FAILED_NOT_ELIGIBLE = "File not eligible for analysis.";
	
	/**
	 * FileAnalysisListener
	 * 
	 * Interface to be implemented by invoking components to listen for process-relevant events.
	 * @author Patrick
	 */
	public interface FileAnalysisListener {
		void onProcessUpdate(int percentage);
		void onFileAnalysisFailed(String message);
		void onFileAnalysisComplete(Object result);
	}

	/*
	 * Protected attributes and methods
	 */
	
	protected FileAnalysisListener listener;
	protected File file;

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
	protected abstract void analyzeFile();
	
	
	/*
	 * Public interface methods
	 */
	
	/**
	 * Constructor
	 * 
	 * @param listener Interface that provides callback functions for events during file analysis.
	 */
	public AbstractFileAnalyzer(FileAnalysisListener listener) {
		this.listener = listener;
	}
	
	public void processFile(File file) {
		this.file = file;
		if (!isEligible()) {
			listener.onFileAnalysisFailed(MESSAGE_FAILED_NOT_ELIGIBLE);
		} else {
			analyzeFile();
		}
	}
}

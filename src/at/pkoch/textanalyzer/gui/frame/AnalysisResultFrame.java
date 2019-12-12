package at.pkoch.textanalyzer.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import at.pkoch.textanalyzer.analysis.AnalysisResultType;

/**
 * AnalysisResultFrame
 * 
 * UI class that allows visualization of a specific text analysis result.
 * @author Patrick
 */
public class AnalysisResultFrame extends JFrame {
	
	/*
	 * CONSTANTS 
	 */
	
	//default serialVersionUID required for extending JFRAME
	private static final long serialVersionUID = 1L;

	
	/*
	 * MEMBER VARIABLES
	 */
	//swing worker for asynchronous generation of UI elements
	SwingWorker<Void, Void> swingWorker = null;
	
	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 * visualizeAnalysisResult
	 * 
	 * Creates and views custom result visualization frame
	 * @param analysisResult
	 * @param resultType
	 */
	public static void visualizeAnalysisResult(Object analysisResult, AnalysisResultType resultType) {
		new AnalysisResultFrame(analysisResult, resultType).setVisible(true);
	}
	
	/*
	 * PRIVATE METHODS
	 */	
	
	/**
	 * AnalysisResultFrame
	 * 
	 * Determines result type and invokes matching UI setUp method.
	 * Also registers window listener to cancel running tasks when window is closed.
	 * @param analysisResult Object containing the analysis result
	 * @param resultType enum describing the analysis result
	 */
	@SuppressWarnings("unchecked")
	private AnalysisResultFrame(Object analysisResult, AnalysisResultType resultType) {
		super();
		
		switch(resultType) {
		case MAP_KEY_STRING_VALUE_INTEGER:
			visualizeStringIntegerMapResult((Map<String, Integer>)analysisResult);
			break;
		default:
			break;
		}
		
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent windowEvent) {
	        	if (swingWorker != null) {
	        		swingWorker.cancel(true);
	        	}
	        }
	    });
	}
	
	/**
	 * visualizeStringIntegerMapResult
	 * 
	 * Create a scrollable panel and embed a grid that contains the key-value pairs.
	 * Then starts a worker thread to fill in the grid asynchronously.
	 * Entries in the grid are sorted in descending order of the recorded values.
	 * @param analysisResult
	 */
	private void visualizeStringIntegerMapResult(Map<String, Integer> analysisResult) {
		//preparing UI
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(0,2));

		JLabel label = new JLabel("Key");
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		contentPanel.add(label);
		JLabel label2 = new JLabel("Value");
		label2.setVerticalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));	
		contentPanel.add(label2);
		
		JPanel northOnlyPanel = new JPanel();
		northOnlyPanel.setLayout(new BorderLayout());
		northOnlyPanel.add(contentPanel, BorderLayout.NORTH);
		
		
		JScrollPane scrollPane = new JScrollPane(northOnlyPanel);
		scrollPane.setPreferredSize(new Dimension(400, 800));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		this.pack();
		
		
		//prepare async handling of grid entries	
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.setTitle("Preparing UI...");

        swingWorker = new SwingWorker<Void, Void>()  
        { 
  
            @Override
            protected Void doInBackground() throws Exception  
            { 
            	Iterator<Entry<String,Integer>> iter = analysisResult.entrySet().stream()
            			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            			.iterator();

            	while (iter.hasNext()) {
                    // define what thread will do here
            		Entry<String, Integer> e = iter.next();
                	JLabel keyLabel = new JLabel(e.getKey());
        			keyLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        			JLabel valueLabel = new JLabel(e.getValue().toString());
        			valueLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        			contentPanel.add(keyLabel);
        			contentPanel.add(valueLabel);
        			
        			if(isCancelled()) {
        				break;
        			}
                }
            	
        		return null;
            } 
    
            @Override
            protected void done()  
            { 
            	AnalysisResultFrame.this.pack();
            	AnalysisResultFrame.this.setCursor(Cursor.getDefaultCursor());
            	AnalysisResultFrame.this.setTitle("Analysis Results");
            } 
        };
        swingWorker.execute();  
	}
}

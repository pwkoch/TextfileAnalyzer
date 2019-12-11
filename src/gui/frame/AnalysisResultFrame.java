package gui.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import analysis.AnalysisResultType;

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
	}
	
	/**
	 * visualizeStringIntegerMapResult
	 * 
	 * Create a scrollable panel and embed a grid that contains the key-value pairs.
	 * Entries in the grid are sorted in descending order of the recorded values.
	 * @param analysisResult
	 */
	private void visualizeStringIntegerMapResult(Map<String, Integer> analysisResult) {		
		JPanel contentPanel = new JPanel();
		int numEntries = analysisResult.keySet().size();
		contentPanel.setLayout(new GridLayout(numEntries+1,2));

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
		
		analysisResult.entrySet().stream()
		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .forEach(e -> {
        	JLabel keyLabel = new JLabel(e.getKey());
			keyLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			JLabel valueLabel = new JLabel(e.getValue().toString());
			valueLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			contentPanel.add(keyLabel);
			contentPanel.add(valueLabel);
        });
		
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setPreferredSize(new Dimension(200, 800));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		this.pack();
	}

}

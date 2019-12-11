package gui.frame;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * TextAnalyzerFrame
 * 
 * Main UI Frame for Text Analyzer utility application.
 * 
 * @author Patrick
 */
public class TextAnalyzerFrame extends JFrame {
	
	/*
	 * CONSTANTS 
	 */
	
	//default serialVersionUID required for extending JFRAME
	private static final long serialVersionUID = 1L;
		
	
	/**
	 * Default constructor
	 * 
	 * Initialize UI window.
	 */
	public TextAnalyzerFrame() {
		super("Textfile Analyzer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 330);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
	}
}

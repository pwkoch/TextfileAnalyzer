package gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import analysis.AbstractFileAnalyzer.FileAnalysisListener;
import gui.dialog.ProgreessBarDialog;
import gui.panel.SelectAnalysisPanel;
import gui.panel.SelectFilePanel;
import analysis.CountTokenOccurrenceAnalyzer;

/**
 * Main UI Frame for Token Occurrence Analyzer Utility
 * 
 * Navigation primarily via Menu & MenuItems.
 * Frame Class serves as Listener for added menu components and handles event accordingly.
 * 
 * @author Patrick
 */
public class TextAnalyzerFrame extends JFrame implements ActionListener, FileAnalysisListener{

	/**
	 * Constants
	 */
	
	//Strings
	//Note: handle Strings & translations thereof in separate component
	public static final String NAME_WINDOW = "Textfile Analyzer";
	public static final String NAME_MENU_FILE = "File";
	public static final String NAME_MENU_HELP = "?";
	public static final String NAME_MENUITEM_OPEN = "Open File...";
	public static final String NAME_MENUITEM_EXIT = "Exit";

	//serialVersionUID: automatically generated as required for JFrame inheritance 
	private static final long serialVersionUID = 2426342617955222670L;
		
	/**
	 * Class variables
	 */
	//JMenus
	private JMenu menu_file;
	
	//JMenuItems
	private JMenuItem menuItem_open;
	private JMenuItem menuItem_exit;
	private JMenuItem menuItem_help;
	
	/**
	 * Interface Methods
	 */
	
	/**
	 * TextOccurrenceAnalyzerFrame
	 * 
	 * Default class constructor
	 * 
	 * Initialize UI window components.
	 */
	public TextAnalyzerFrame() {
		super(NAME_WINDOW);
	       
		//configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JPanel panel = new SelectFilePanel();
//        panel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(panel);
        
        JPanel panel2 = new SelectAnalysisPanel();
        mainPanel.add(panel2);
//        this.pack();
        this.getContentPane().add(mainPanel);
//        this.pack();
        this.setVisible(true);
//        this.add(panel);
	}

	/**
	 * Internal methods
	 */
	
	/**
	 * actionPerformed
	 * 
	 * Callback method to handle events triggered by menu items of the frame.
	 * 
	 * Invokes matching handleX() method based on which component triggered the event.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == menuItem_open) {
			handleOpen();
		} else if (event.getSource() == menuItem_exit) {
			handleExit();
		} else if (event.getSource() == menuItem_help) {
			handleHelp();
		}
	}
	
	/**
	 * handleOpen
	 * 
	 * Handles event of menuItem_open
	 * 
	 * Show file chooser dialog and handle user input.
	 * Starts file parsing if user selected a file.
	 */
	private void handleOpen() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    (new CountTokenOccurrenceAnalyzer(this)).processFile(selectedFile);
		}
	}
	
	/**
	 * handleExit
	 * 
	 * Handles event of menuItem_exit
	 * 
	 * Exit application with Code 0
	 */
	private void handleExit() {
		System.exit(0);
	}
	
	/**
	 * handleHelp
	 * 
	 * Handles event of menuItem_help
	 * 
	 * Show help dialog
	 */
	private void handleHelp() {
		JProgressBar progressBar = new JProgressBar();
		ProgreessBarDialog dialog = new ProgreessBarDialog(progressBar, this);
		dialog.createProgressUI();
	}

	/*
	 * (non-Javadoc)
	 * @see analysis.AbstractFileAnalyzer.FileAnalysisListener#onFileAnalysisFailed(java.lang.String)
	 */
	@Override
	public void onFileAnalysisFailed(String message) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see analysis.AbstractFileAnalyzer.FileAnalysisListener#onFileAnalysisComplete()
	 */
	@Override
	public void onFileAnalysisComplete(Object result) {
		
		System.out.println("file analysis complete");
		Map<String, Integer> map = (Map<String, Integer>) result;
		
//		map.entrySet().stream()
//		.sorted(reverseOrder(Map.Entry.comparingByValue()))
//        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
		
		
//		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
//        list.sort(Entry.comparingByValue().reversed());
		
//		@SuppressWarnings("unchecked")
//		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(map);

		
		
//		sorted_map.forEach((k,v)->{System.out.println(k + ": " + v);});
//		list.forEach(e->{System.out.println(e.getKey() + ": " + e.getValue());});
		
		System.out.println("output complete");
	}

	@Override
	public void onProcessUpdate(int percentage) {
		
	}
}

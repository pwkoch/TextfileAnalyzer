package gui.panel;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import util.MimeTypeChecker;

/**
 * SelectFilePanel
 * 
 * UI class for selecting a text file.
 * @author Patrick
 */
public class SelectFilePanel extends AbstractUIPanel{

	/*
	 * INTERFACES
	 */
	
	/**
	 * ISelectedFilePanelEventListener
	 * 
	 * Interface for callback events.
	 * @author Patrick
	 */
	public interface ISelectFilePanelEventListener {
		
		/**
		 * onFileSelected
		 * 
		 * Event callback method, invoked when a file was successfully selected.
		 */
		public void onFileSelected();
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
	ISelectFilePanelEventListener eventListener;
	
	//selected file, 
	protected File file = null;
	
	//UI elements
	JButton buttonSelectFile;
	JLabel labelFilename;
	JLabel labelFilepath;
	JLabel labelFilesize;

	
	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 * Default constructor
	 * 
	 * Set event listener and set-up UI elements.
	 */
	public SelectFilePanel(ISelectFilePanelEventListener eventListener) {
		super();
		this.eventListener = eventListener;
		prepareLayout();
	}
	
	/**
	 * getFile
	 * 
	 * Getter for the selected file.
	 * @return File selected file 
	 */
	public File getFile() {
		return this.file;
	}
	

	/*
	 * PRIVATE METHODS
	 */
	
	/**
	 * prepareLayout
	 * 
	 * Creates UI elements and adds them to spring layout.
	 */
	private void prepareLayout() {
		SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        JLabel labelPaneCaption = new JLabel("Select File");
        buttonSelectFile = new JButton();
        buttonSelectFile.setAction(selectFileAction);
        buttonSelectFile.setText("Select");
        
        
        JLabel labelFilenameCaption = new JLabel("Name");
        labelFilename = new JLabel("-");
        JLabel labelFilepathCaption = new JLabel("Path");
        labelFilepath = new JLabel("-");
        JLabel labelFilesizeCaption = new JLabel("Size");
        labelFilesize = new JLabel("-");
        
        this.add(labelPaneCaption);
        this.add(buttonSelectFile);
        this.add(labelFilenameCaption);
        this.add(labelFilename);
        this.add(labelFilepathCaption);
        this.add(labelFilepath);
        this.add(labelFilesizeCaption);
        this.add(labelFilesize);
        
        layout.putConstraint(SpringLayout.NORTH, labelPaneCaption, LAYOUT_GAP, SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, labelPaneCaption, LAYOUT_GAP, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, buttonSelectFile, LAYOUT_GAP,SpringLayout.NORTH, this);  
        layout.putConstraint(SpringLayout.WEST, buttonSelectFile, LAYOUT_GAP,SpringLayout.EAST, labelPaneCaption);
        
        layout.putConstraint(SpringLayout.NORTH, labelFilenameCaption, LAYOUT_GAP,SpringLayout.SOUTH, labelPaneCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilenameCaption, LAYOUT_GAP,SpringLayout.WEST, this);        
        layout.putConstraint(SpringLayout.NORTH, labelFilename, LAYOUT_GAP,SpringLayout.SOUTH, labelPaneCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilename, 0,SpringLayout.WEST, buttonSelectFile);
        
        layout.putConstraint(SpringLayout.NORTH, labelFilepathCaption, LAYOUT_GAP,SpringLayout.SOUTH, labelFilenameCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilepathCaption, LAYOUT_GAP,SpringLayout.WEST, this);        
        layout.putConstraint(SpringLayout.NORTH, labelFilepath, LAYOUT_GAP,SpringLayout.SOUTH, labelFilenameCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilepath, 0,SpringLayout.WEST, buttonSelectFile);

        layout.putConstraint(SpringLayout.NORTH, labelFilesizeCaption, LAYOUT_GAP,SpringLayout.SOUTH, labelFilepathCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilesizeCaption, LAYOUT_GAP,SpringLayout.WEST, this);        
        layout.putConstraint(SpringLayout.NORTH, labelFilesize, LAYOUT_GAP,SpringLayout.SOUTH, labelFilepathCaption);  
        layout.putConstraint(SpringLayout.WEST, labelFilesize, 0,SpringLayout.WEST, buttonSelectFile);
	}
	
	/**
	 * setFile
	 * 
	 * Sets file, updates UI and invokes onFileSelected callback.
	 * @param file File selection
	 */
	private void setFile(File file) {
		this.file = file;
		labelFilename.setText(file.getName());
		labelFilepath.setText(file.getAbsolutePath());
		labelFilesize.setText(file.length() + " bytes");
		eventListener.onFileSelected();
	}
	
	/*
	 * UI EVENT HANDLING ACTIONS
	 */
	
	/**
	 * selectFileAction
	 * 
	 * Shows file chooser dialog, validates selection, and updates UI accordingly.
	 */
	private AbstractAction selectFileAction = new AbstractAction() {
		//default serialVersionUID
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(SelectFilePanel.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				if (MimeTypeChecker.isTextFile(fileChooser.getSelectedFile())) {
					setFile(fileChooser.getSelectedFile());
				} else {
					JOptionPane.showMessageDialog(SelectFilePanel.this,
							"Please select a valid text file.",
						    "Error.",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
}

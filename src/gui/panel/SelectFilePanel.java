package gui.panel;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


/**
 * UI class for selecting a target file
 * @author Patrick
 */
public class SelectFilePanel extends JPanel{

	private static final int LAYOUT_GAP = 20; //px
	
	/**
	 * default serial version ID as required for extending JPanel
	 */
	private static final long serialVersionUID = 1L;

	protected File file;
	
	/**
	 * Interactive UI components
	 */
	JButton buttonSelectFile;
	JLabel labelFilename;
	JLabel labelFilepath;
	JLabel labelFilesize;
	
	/**
	 * Default constructor
	 * 
	 * set-up UI elements
	 */
	public SelectFilePanel() {
		super();
		prepareLayout();
	}
	
	private void prepareLayout() {
		SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        JLabel labelPaneCaption = new JLabel("Select File");
        buttonSelectFile = new JButton();
        buttonSelectFile.setAction(selectFileAction);
        buttonSelectFile.setText("Select");
        
        JLabel labelFilenameCaption = new JLabel("Name");
        labelFilename = new JLabel("PLACEHOLDER");
        JLabel labelFilepathCaption = new JLabel("Path");
        labelFilepath = new JLabel("PLACEHOLDER");
        JLabel labelFilesizeCaption = new JLabel("Size");
        labelFilesize = new JLabel("PLACEHOLDER");
        
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
	
	private AbstractAction selectFileAction = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(SelectFilePanel.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				setFile(fileChooser.getSelectedFile());
			}
		}
	};
	
	private void setFile(File file) {
		this.file = file;
		labelFilename.setText(file.getName());
		labelFilepath.setText(file.getAbsolutePath());
		labelFilesize.setText(file.length() + " bytes");
	}
}

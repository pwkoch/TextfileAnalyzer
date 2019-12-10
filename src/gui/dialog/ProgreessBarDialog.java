package gui.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgreessBarDialog extends JDialog {
    private static final long serialVersionUID = 1L;

	public static final String STRING_WINDOW_CAPTION = "Processing...";
	public static final String STRING_WINDOW_TEXT = "Processing input file.";
    
    private static JProgressBar progressBar;
    private JFrame motherFrame;
    private JLabel label = new JLabel("loading.. ");
    private JButton button;

    public ProgreessBarDialog(JProgressBar progressBar, JFrame frame) {

        this.progressBar = progressBar;
        this.motherFrame = frame;
        this.button = button;
    }

    public void createProgressUI() {
    	System.out.println("createProgressUI");
    	JProgressBar dpb = new JProgressBar(0, 500);
        add(BorderLayout.CENTER, dpb);
        add(BorderLayout.NORTH, new JLabel("Progress..."));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(300, 75);
        setLocationRelativeTo(motherFrame);
        
    }

    public static void doSomething() {
    	//custom title, warning icon
    	JProgressBar progressBar = new JProgressBar();
    }
}


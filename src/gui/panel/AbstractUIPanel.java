package gui.panel;

import java.awt.Component;

import javax.swing.JPanel;

/**
 * AbstractUI Panel
 * 
 * Abstract base class for UI windows to enrich features of base JPanel class
 * @author Patrick
 */
public class AbstractUIPanel extends JPanel {
	
	/*
	 * CONSTANTS
	 */
	
	//Default serialVersionUID to extend from JPanel class
	protected static final long serialVersionUID = 1L;

	//gap between elements of spring layout
	protected static final int LAYOUT_GAP = 20; //px
	
	
	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 *  setEnabled
	 * 
	 *  Sets enabled status of contained UI elements.
	 */
	@Override
	public void setEnabled(boolean enabledStatus) {
		for (Component c : this.getComponents()) {
			c.setEnabled(enabledStatus);
		}
	}

}

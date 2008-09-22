/*
 * Copyright (c) 2003, Vanderbilt University
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice, the following
 * two paragraphs and the author appear in all copies of this software.
 *
 * IN NO EVENT SHALL THE VANDERBILT UNIVERSITY BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE VANDERBILT
 * UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE VANDERBILT UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND THE VANDERBILT UNIVERSITY HAS NO OBLIGATION TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */

package tools.mcenter;

import javax.swing.text.*;
import javax.swing.JOptionPane;

/**
 * 
 * @author nadand
 */
public class MessageCenter extends javax.swing.JFrame {

	static private java.util.Hashtable childWindowList = new java.util.Hashtable();

	static private MessageCenter _instance = null;

	private FrameMenuItem serialConnectorMenuItem;

	private boolean logPanelMin = true;

	private DefaultStyledDocument logDocument = new DefaultStyledDocument();

	/** Creates new form centerFrame */
	private MessageCenter() {
		_instance = this;
		initComponents();
		redirectStreams();
		desktopPane.add(SerialConnector.instance());
		SerialConnector.instance().setVisible(true);
		serialConnectorMenuItem = new MessageCenter.FrameMenuItem(
				SerialConnector.instance());
		windowMenu.add(serialConnectorMenuItem);
		childWindowList
				.put(SerialConnector.instance(), serialConnectorMenuItem);
		new AppLoader();
		// registerChildFrame(new AllMSGDisplay());
		// registerChildFrame(new AppLoader());\
	}

	private void redirectStreams() {
		Style def = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		Style err = logDocument.addStyle("Error", def);
		StyleConstants.setFontFamily(err, "SansSerif");
		StyleConstants.setItalic(err, true);
		StyleConstants.setForeground(err, java.awt.Color.RED);

		System.setErr(new java.io.PrintStream(new DocumentLogger(err)));
		System.setOut(new java.io.PrintStream(new DocumentLogger(def)));
	}

	/************************** Singleton Pattern "constructor" **********************/
	static public MessageCenter instance() {
		if (null == _instance) {
			_instance = new MessageCenter();
		}
		return _instance;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		java.awt.GridBagConstraints gridBagConstraints;

		logPopupMenu = new javax.swing.JPopupMenu();
		clearSelectedMenuItem = new javax.swing.JMenuItem();
		clearLogMenuItem = new javax.swing.JMenuItem();
		jSplitPane1 = new javax.swing.JSplitPane();
		desktopPane = new javax.swing.JDesktopPane();
		logPanel = new javax.swing.JPanel();
		logScrollPane = new javax.swing.JScrollPane();
		logTextArea = new javax.swing.JTextPane();
		centerMenuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		dataBaseMenuItem = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		quitMenuItem = new javax.swing.JMenuItem();
		windowMenu = new javax.swing.JMenu();
		helpMenu = new javax.swing.JMenu();
		helpMenuItem = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JSeparator();
		aboutMenuItem = new javax.swing.JMenuItem();

		clearSelectedMenuItem.setText("Clear Selected");
		clearSelectedMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						clearSelectedMenuItemActionPerformed(evt);
					}
				});

		logPopupMenu.add(clearSelectedMenuItem);

		clearLogMenuItem.setText("Clear All");
		clearLogMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearLogMenuItemActionPerformed(evt);
			}
		});

		logPopupMenu.add(clearLogMenuItem);

		setTitle("messageCenter");
		setName("centerFrame");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jSplitPane1.setDividerLocation(this.getHeight() - 24);
		jSplitPane1.setDividerSize(7);
		jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane1.setResizeWeight(1.0);
		jSplitPane1.setContinuousLayout(true);
		jSplitPane1.setOneTouchExpandable(true);
		jSplitPane1.setTopComponent(desktopPane);

		logPanel.setLayout(new java.awt.GridBagLayout());

		logPanel.setBorder(new javax.swing.border.EtchedBorder(
				javax.swing.border.EtchedBorder.RAISED));
		logPanel.setMinimumSize(new java.awt.Dimension(10, 48));
		logPanel.setPreferredSize(new java.awt.Dimension(10, 48));
		logScrollPane.setMinimumSize(new java.awt.Dimension(26, 48));
		logScrollPane.setPreferredSize(new java.awt.Dimension(26, 48));
		logTextArea.setDocument(logDocument);
		logTextArea.setEditable(false);
		logTextArea.setPreferredSize(new java.awt.Dimension(7, 48));
		logTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				logTextAreaMouseClicked(evt);
			}
		});

		logScrollPane.setViewportView(logTextArea);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		logPanel.add(logScrollPane, gridBagConstraints);

		jSplitPane1.setBottomComponent(logPanel);

		getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

		fileMenu.setText("File");
		dataBaseMenuItem.setText("Preferences...");
		dataBaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dataBaseMenuItemActionPerformed(evt);
			}
		});

		fileMenu.add(dataBaseMenuItem);

		fileMenu.add(jSeparator1);

		quitMenuItem.setText("Quit");
		quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				quitMenuItemActionPerformed(evt);
			}
		});

		fileMenu.add(quitMenuItem);

		centerMenuBar.add(fileMenu);

		windowMenu.setText("Window");
		windowMenu.addMenuListener(new javax.swing.event.MenuListener() {
			public void menuCanceled(javax.swing.event.MenuEvent evt) {
			}

			public void menuDeselected(javax.swing.event.MenuEvent evt) {
			}

			public void menuSelected(javax.swing.event.MenuEvent evt) {
				windowMenuMenuSelected(evt);
			}
		});

		centerMenuBar.add(windowMenu);

		helpMenu.setText("Help");
		helpMenuItem.setText("Help");
		helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpMenuItemActionPerformed(evt);
			}
		});

		helpMenu.add(helpMenuItem);

		helpMenu.add(jSeparator2);

		aboutMenuItem.setText("About");
		aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuItemActionPerformed(evt);
			}
		});

		helpMenu.add(aboutMenuItem);

		centerMenuBar.add(helpMenu);

		setJMenuBar(centerMenuBar);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2,
				800, 600);
	}// GEN-END:initComponents

	private void clearSelectedMenuItemActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:
												// event_clearSelectedMenuItemActionPerformed

		int selectionStart = logTextArea.getSelectionStart();
		int selectionLength = logTextArea.getSelectionEnd() - selectionStart;
		try {
			logDocument.remove(selectionStart, selectionLength);
		} catch (javax.swing.text.BadLocationException be) {
		}
	}// GEN-LAST:event_clearSelectedMenuItemActionPerformed

	private void logTextAreaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-
																			// FIRST
																			// :
																			// event_logTextAreaMouseClicked
		if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3)
			logPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

	}// GEN-LAST:event_logTextAreaMouseClicked

	private void clearLogMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN
																					// -
																					// FIRST
																					// :
																					// event_clearLogMenuItemActionPerformed
		// logTextArea.selectAll();
		try {
			logDocument.remove(0, logDocument.getLength());
		} catch (javax.swing.text.BadLocationException be) {
		}

	}// GEN-LAST:event_clearLogMenuItemActionPerformed

	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN
																				// -
																				// FIRST
																				// :
																				// event_aboutMenuItemActionPerformed
		JOptionPane
				.showMessageDialog(
						this,
						"                The Message Center\nInstitute of Software Integrated Systems\n           Vanderbilt University 2003",
						"About", JOptionPane.INFORMATION_MESSAGE);
	}// GEN-LAST:event_aboutMenuItemActionPerformed

	private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN
																				// -
																				// FIRST
																				// :
																				// event_helpMenuItemActionPerformed
		javax.swing.JOptionPane.showMessageDialog(this,
				"The answers are comming!");

	}// GEN-LAST:event_helpMenuItemActionPerformed

	private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN
																				// -
																				// FIRST
																				// :
																				// event_quitMenuItemActionPerformed
		System.exit(0);
	}// GEN-LAST:event_quitMenuItemActionPerformed

	private void dataBaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN
																					// -
																					// FIRST
																					// :
																					// event_dataBaseMenuItemActionPerformed
		DatabaseConfigDialog configDialog = new DatabaseConfigDialog(this, true);
		configDialog.setLocationRelativeTo(this);
		configDialog.show();
	}// GEN-LAST:event_dataBaseMenuItemActionPerformed

	private void windowMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-
																			// FIRST
																			// :
																			// event_windowMenuMenuSelected
		java.util.Enumeration internalFrames = childWindowList.keys();
		while (internalFrames.hasMoreElements()) {
			javax.swing.JInternalFrame internalFrame = (javax.swing.JInternalFrame) internalFrames
					.nextElement();
			FrameMenuItem menuItem = (FrameMenuItem) childWindowList
					.get(internalFrame);
			menuItem.setText(internalFrame.getTitle());
		}
	}// GEN-LAST:event_windowMenuMenuSelected

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {// GEN-FIRST:
															// event_exitForm
		System.exit(0);
	}// GEN-LAST:event_exitForm

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		new MessageCenter().show();
		// new AllMSGDisplay();

	}

	protected SerialConnector serialConnector;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuBar centerMenuBar;
	private javax.swing.JMenuItem clearLogMenuItem;
	private javax.swing.JMenuItem clearSelectedMenuItem;
	private javax.swing.JMenuItem dataBaseMenuItem;
	private javax.swing.JDesktopPane desktopPane;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JMenuItem helpMenuItem;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JPanel logPanel;
	private javax.swing.JPopupMenu logPopupMenu;
	private javax.swing.JScrollPane logScrollPane;
	private javax.swing.JTextPane logTextArea;
	private javax.swing.JMenuItem quitMenuItem;
	private javax.swing.JMenu windowMenu;

	// End of variables declaration//GEN-END:variables

	/***************************** Add Child Window IF *************************/

	public void registerChildFrame(javax.swing.JInternalFrame newChildFrame) {
		FrameMenuItem menuItem = new FrameMenuItem(newChildFrame);
		childWindowList.put(newChildFrame, menuItem);
		this.windowMenu.add(menuItem);
		desktopPane.add(newChildFrame);
		newChildFrame.setVisible(true);

	}

	public void removeChildFrame(javax.swing.JInternalFrame oldChildFrame) {
		oldChildFrame.setVisible(false);
		this.windowMenu.remove((FrameMenuItem) childWindowList
				.get(oldChildFrame));
		desktopPane.remove(oldChildFrame);

	}

	private class DocumentLogger extends java.io.OutputStream {
		Style s = null;
		javax.swing.JScrollBar vScrollBar = null;

		public DocumentLogger(Style s) {
			this.s = s;
			vScrollBar = logScrollPane.getVerticalScrollBar();
		}

		public void write(int b) throws java.io.IOException {
			try {
				logDocument.insertString(logDocument.getLength(), String
						.valueOf((char) b), s);
				if (((char) b) == '\n') {
					vScrollBar.setValue(logTextArea.getHeight());
				}
			} catch (javax.swing.text.BadLocationException ble) {
				System.out.println(ble.getMessage());
			}
		}

	}

	/********************************* Inner Classes **************************/

	private class FrameMenuItem extends javax.swing.JMenuItem {

		public javax.swing.JInternalFrame childFrame;

		public FrameMenuItem(javax.swing.JInternalFrame newChildFrame) {
			super(newChildFrame.getTitle());
			this.childFrame = newChildFrame;

			this.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try {
						childFrame.setSelected(true);
						childFrame.moveToFront();
						if (childFrame.isIcon())
							childFrame.setIcon(false);
					} catch (java.beans.PropertyVetoException pve) {
					}
				}
			});
		}
	}

}

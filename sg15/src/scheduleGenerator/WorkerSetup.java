package scheduleGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//SWAP 1, TEAM 03

// BAD SMELLS
// Divergent Change
// This class seems to have bulked up with a lot of features that should be delegated to different classes.
// 

//SWAP 2, TEAM 04
//REFACTORING FOR ENHANCEMENT
//Most of the functionality has been moved to WorkerTab for the display. These are JPanels for the tabbedPane display.
//This will make it easier to change how things are displayed without changing this class.

/**
 * 
 * @author schneimd
 */
public class WorkerSetup extends javax.swing.JFrame {

	private ArrayList<Day> days;
	private ArrayList<WorkerTab> workerTabs;

	/**
	 * Allows for editing of already made workers.
	 * 
	 * @param workers
	 */
	public WorkerSetup(ArrayList<Worker> workers) {
		this.setPreferredSize(new Dimension(425, 450));
		this.workerTabs = new ArrayList<WorkerTab>();
		initComponents();
		for (int c = 0; c < workers.size(); c++) {
			this.addWorker(workers.get(c));
		}

	}

	/**
	 * Creates new form WorkerSetup
	 */
	public WorkerSetup() {
		this.setPreferredSize(new Dimension(425, 450));
		this.workerTabs = new ArrayList<WorkerTab>();
		initComponents();
		addWorker(new Worker("",new ArrayList<Day>()));
	}
	
	// SWAP 1, TEAM 03
	
	// BAD SMELLS
	// Long Method
	// Should break it up into logical chunks that do single tasks, place these chunks into new functions.\
	
	// SWAP 2, TEAM 04 - Extract Method
	//REFACTORING FOR ENHANCEMENT FORM BAD SMELL
	// No Feature Mentioned that could be added. It is difficult to mention features when extracting code from a class.
	//I think the refactoring was quite successful. The functions are now much easier to read and contain a good focus.
	//Many of these functions were moved to WorkerTab in response to the divergent change response.
	private void addWorker(Worker w) {
		WorkerTab tab = w.getWorkerTab();
		this.workerTabs.add(tab);
		this.workerTabPanel.addTab(this.workerTabs.size()==0 ? "Worker 1":"Worker " + String.valueOf(this.workerTabs.size()), null, tab, "");
	}
	
	
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		this.workerTabPanel = new javax.swing.JTabbedPane();
		this.addButton = new javax.swing.JButton();
		this.removeButton = new javax.swing.JButton();
		this.nextButton = new javax.swing.JButton();
		this.backButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Worker Setup");

		this.addButton.setText("Add Worker");
		this.addButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		this.removeButton.setText("Remove Worker");
		this.removeButton
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeButtonActionPerformed(evt);
					}
				});

		this.nextButton.setText("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		this.backButton.setText("Back");
		this.backButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		JScrollPane outside = new JScrollPane();
		outside.setViewportView(this.workerTabPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(106, 106, 106)
								.addComponent(this.backButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										65,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(this.nextButton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										65,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.addButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		136,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		82,
																		Short.MAX_VALUE)
																.addComponent(
																		this.removeButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		136,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														outside,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														0, Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(outside,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										330,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(this.addButton)
												.addComponent(this.removeButton))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(this.nextButton)
												.addComponent(this.backButton))
								.addGap(0, 8, Short.MAX_VALUE)));

		pack();
	}

	/**
	 * @param evt
	 */
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ArrayList<Worker> workers = new ArrayList<Worker>();
		boolean allGood = true;
		for (JPanel tab : this.workerTabs) {
			ArrayList<Day> workerDays = new ArrayList<Day>();
			JTextField nameArea = (JTextField) tab.getComponent(2);
			if (nameArea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"You have not entered a name for every worker.");
				allGood = false;
				break;
			}
			JTabbedPane daysPane = (JTabbedPane) tab.getComponents()[0];
			for (int i = 0; i < daysPane.getTabCount(); i++) {

				JPanel day = (JPanel) daysPane.getComponent(i);

				JScrollPane pane = (JScrollPane) day.getComponent(0);

				JViewport view = (JViewport) pane.getComponent(0);

				JPanel p = (JPanel) view.getComponent(0);

				ArrayList<Object> jobNames = new ArrayList<Object>();

				for (Component job : p.getComponents()) {
					if (((JCheckBox) job).isSelected()) {
						jobNames.add(((JCheckBox) job).getText());
					}
				}
				workerDays.add(new Day(daysPane.getTitleAt(i), jobNames));
			}
			workers.add(new Worker(nameArea.getText(), workerDays));
		}
		if (allGood) {
			HTMLGenerator.reset();
			Main.setWorkers(workers);
			Main.setSchedule(new Schedule(Main.getDays(), Main.getWorkers()));
			Main.dumpConfigFile(Main.path);
			Main.cal = new CalendarGUI(Main.getSchedule());
			Main.toggleCalendar();
			Main.toggleWorkerSetup();
		}
	}

	/**
	 * @param evt
	 */
	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Main.toggleConfig();
		Main.toggleWorkerSetup();
	}

	/**
	 * @param evt
	 */
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.addWorker(new Worker("", new ArrayList<Day>()));
	}

	/**
	 * @param evt
	 */
	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.workerTabs.remove(this.workerTabPanel.getSelectedComponent());
		this.workerTabPanel.remove(this.workerTabPanel.getSelectedIndex());
	}

	private javax.swing.JButton addButton;
	private javax.swing.JButton backButton;
	private javax.swing.JButton nextButton;
	private javax.swing.JButton removeButton;
	private javax.swing.JTabbedPane workerTabPanel;
}

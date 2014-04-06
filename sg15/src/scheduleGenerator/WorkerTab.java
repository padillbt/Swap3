package scheduleGenerator;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class WorkerTab extends JPanel {
	JTabbedPane workerDays;
	public WorkerTab() {
		ArrayList<Day> days = Main.getDays();
		JTabbedPane tempWorkerDays = new javax.swing.JTabbedPane();

		// Makes a tab for each day and a check box for each job.
		for (Day day : days) {
			setupDay(day, tempWorkerDays);

		}
		
		setupWorkerTabLayout(tempWorkerDays);
	}
	
	private void setupDay(Day day, JTabbedPane tempWorkerDays) { 
		JCheckBox[] jobs = new JCheckBox[day.getJobs().size()];
		for (int i = 0; i < day.getJobs().size(); i++) {
			jobs[i] = new JCheckBox(day.getJobs().get(i));
		}

		// Put Check Boxes in a scrollPane for dynamics
		JScrollPane tempDayJobPane = new JScrollPane();
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(jobs.length, 1));

		for (JCheckBox job : jobs) {
			tempPanel.add(job);
		}
		tempDayJobPane.setViewportView(tempPanel);

		// Label the Pane
		JLabel jobLabel = new JLabel("Preferred Jobs:");

		// Create a tab Panel for the Worker Tab and add the inputs.

		JPanel dayTab = new JPanel();

		// Set vertical and horizontal layouts.
		javax.swing.GroupLayout sundayTab1Layout = new javax.swing.GroupLayout(
				dayTab);
		dayTab.setLayout(sundayTab1Layout);
		
		setGroupLayoutHorizontalStyleForJob(sundayTab1Layout, tempDayJobPane, jobLabel);

		setGroupLayoutVerticalStyleForJob(sundayTab1Layout,tempDayJobPane,jobLabel );

		tempWorkerDays.addTab(day.getNameOfDay(), dayTab);
	}
	
	private void setGroupLayoutHorizontalStyleForJob(GroupLayout layout, JScrollPane tempDayJobPane, JLabel jobLabel) {
		layout
		.setHorizontalGroup(layout
				.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout
								.createSequentialGroup()
								.addGap(63, 63, 63)
								.addGroup(
										layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														tempDayJobPane,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														198,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jobLabel))
								.addContainerGap(73,
										Short.MAX_VALUE)));
	}
	
	private void setGroupLayoutVerticalStyleForJob(GroupLayout layout, JScrollPane tempDayJobPane, JLabel jobLabel) {
		layout
		.setVerticalGroup(layout
				.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jobLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
										tempDayJobPane,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										179, Short.MAX_VALUE)
								.addContainerGap()));
	}
	
	private void setupWorkerTabLayout(JTabbedPane workerDays) {
		javax.swing.JTextField tempWorkerName = new javax.swing.JTextField();
		JLabel workerNameLabel = new JLabel("Worker's Name:");
		
		javax.swing.JTextField tempWorkerComment = new javax.swing.JTextField();
		JLabel workerCommentLabel = new JLabel("Additional Comments:");
		

		javax.swing.GroupLayout workerTab1Layout = new javax.swing.GroupLayout(
						this);
		this.setLayout(workerTab1Layout);
				
		setGroupLayoutHorizontalForWorker(workerTab1Layout, workerNameLabel, tempWorkerName, workerCommentLabel, tempWorkerComment, workerDays);
		setGroupLayoutVerticalForWorker(workerTab1Layout, workerNameLabel, tempWorkerName, workerCommentLabel, tempWorkerComment, workerDays);
	}
	
	
	
	

	private void setGroupLayoutHorizontalForWorker(GroupLayout layout, JLabel workerNameLabel, JTextField tempWorkerName, JLabel workerCommentLabel, 
													JTextField tempWorkerComment, JTabbedPane tempWorkerDays) {
		layout
		.setHorizontalGroup(layout
				.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														tempWorkerDays)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout
																.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		workerNameLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		tempWorkerName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		150,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(49,
																		49,
																		49))
								)
								.addContainerGap()
								)
				.addGroup(
						layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														tempWorkerDays)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout
																.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		workerCommentLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		tempWorkerComment,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		150,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(49,
																		49,
																		49))
								)
								.addContainerGap()
								));
	}
	
	private void setGroupLayoutVerticalForWorker(GroupLayout layout,JLabel workerNameLabel, JTextField tempWorkerName, JLabel workerCommentLabel, 
			JTextField tempWorkerComment, JTabbedPane tempWorkerDays) {
		layout
		.setVerticalGroup(layout
				.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														workerNameLabel)
												.addComponent(
														tempWorkerName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												)
								
								.addGroup(
										layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														workerCommentLabel)
												.addComponent(
														tempWorkerComment,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)		
												)
											
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
										tempWorkerDays,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										249,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}
}

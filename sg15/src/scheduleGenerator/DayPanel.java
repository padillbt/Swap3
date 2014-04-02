package scheduleGenerator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DayPanel extends JPanel {

	private DefaultListModel<Object>[] models;
	private Config config;
	private JList<Object> dayJobList;
	private JScrollPane dayScrollPane;
	private JTextField dayJobName;
	private JLabel dayLabel;
	private JButton dayAddJob;
	private JButton dayDeleteJob;
	
	public void DayPanelSet(DefaultListModel<Object>[] modelSet, Config configSet, JList<Object> dayJobListSet, JTextField dayNameSet,
			JLabel labelSet, JButton addSet, JButton deleteSet, JScrollPane scrollSet)
	{
		this.dayScrollPane = scrollSet;
		this.models = modelSet;
		this.config = configSet;
		this.dayJobList = dayJobListSet;
		this.dayJobName = dayNameSet;
		this.dayLabel = labelSet;
		this.dayAddJob = addSet;
		this.dayDeleteJob = deleteSet;
	}
	
	public void setUp(final int x)
	{
		this.models[x] = new DefaultListModel<Object>();
		this.dayJobList.setModel(this.models[x]);
		this.dayScrollPane.setViewportView(this.dayJobList);

		this.dayJobName.setColumns(20);

		this.dayLabel.setText("Job Name:");

		this.dayAddJob.setText("Add Job");
		this.dayAddJob
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {
						if (!DayPanel.this.dayJobName.getText().isEmpty()) {
							DayPanel.this.models[x]
									.addElement(DayPanel.this.dayJobName
											.getText());
							DayPanel.this.dayJobList
									.setModel(DayPanel.this.models[x]);
							DayPanel.this.dayJobName.setText("");
						}
					}
				});

		this.dayDeleteJob.setText("Delete Job");
		this.dayDeleteJob
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {
						while (!DayPanel.this.dayJobList
								.isSelectionEmpty()) {
							int n = DayPanel.this.dayJobList
									.getSelectedIndex();
							DayPanel.this.models[x].remove(n);
						}

					}
				});
		
		javax.swing.GroupLayout dayTabLayout = new javax.swing.GroupLayout(
				this);
		this.setLayout(dayTabLayout);
		setTabVerticalGroup(dayTabLayout, dayJobName, dayLabel,
				dayAddJob, dayDeleteJob, dayScrollPane);
		setTabHorizontalGroup(dayTabLayout, dayJobName, dayLabel,
				dayAddJob, dayDeleteJob, dayScrollPane);
	}
	
	
	// SWAP, 1 TEAM: 03
		//
		// QUALITY CHANGES
		//
		// Moved the duplicate code for setting the vertical tab group into this
		// method
		// This overcame the duplicated code Bad code smell
		private void setTabVerticalGroup(GroupLayout dayTabLayout,
				JTextField dayjobName, JLabel dayLabel, JButton dayAddJob,
				JButton dayDeleteJob, JScrollPane dayScrollPane) {
			dayTabLayout
					.setVerticalGroup(dayTabLayout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									dayTabLayout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													dayTabLayout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING,
																	false)
															.addGroup(
																	dayTabLayout
																			.createSequentialGroup()
																			.addGroup(
																					dayTabLayout
																							.createParallelGroup(
																									javax.swing.GroupLayout.Alignment.BASELINE)
																							.addComponent(
																									dayjobName,
																									javax.swing.GroupLayout.PREFERRED_SIZE,
																									javax.swing.GroupLayout.DEFAULT_SIZE,
																									javax.swing.GroupLayout.PREFERRED_SIZE)
																							.addComponent(
																									dayLabel))
																			.addPreferredGap(
																					javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(
																					dayAddJob)
																			.addPreferredGap(
																					javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																					javax.swing.GroupLayout.DEFAULT_SIZE,
																					Short.MAX_VALUE)
																			.addComponent(
																					dayDeleteJob))
															.addComponent(
																	dayScrollPane,
																	javax.swing.GroupLayout.PREFERRED_SIZE,
																	javax.swing.GroupLayout.DEFAULT_SIZE,
																	javax.swing.GroupLayout.PREFERRED_SIZE))
											.addContainerGap(25, Short.MAX_VALUE)));
		}

		// SWAP, 1 TEAM: 03
		//
		// QUALITY CHANGES
		//
		// Moved the duplicate code for setting the horizontal tab group into this
		// method
		// This overcame the duplicated code Bad code smell
		private void setTabHorizontalGroup(GroupLayout dayTabLayout,
				JTextField dayjobName, JLabel dayLabel, JButton dayAddJob,
				JButton dayDeleteJob, JScrollPane dayScrollPane) {
			dayTabLayout
					.setHorizontalGroup(dayTabLayout
							.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									dayTabLayout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(
													dayScrollPane,
													javax.swing.GroupLayout.PREFERRED_SIZE,
													182,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(18, 18, 18)
											.addGroup(
													dayTabLayout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING)
															.addGroup(
																	dayTabLayout
																			.createSequentialGroup()
																			.addComponent(
																					dayLabel)
																			.addGroup(
																					dayTabLayout
																							.createParallelGroup(
																									javax.swing.GroupLayout.Alignment.LEADING)
																							.addGroup(
																									dayTabLayout
																											.createSequentialGroup()
																											.addGap(14,
																													14,
																													14)
																											.addComponent(
																													dayAddJob))
																							.addGroup(
																									dayTabLayout
																											.createSequentialGroup()
																											.addPreferredGap(
																													javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																											.addComponent(
																													dayjobName,
																													javax.swing.GroupLayout.PREFERRED_SIZE,
																													100,
																													javax.swing.GroupLayout.PREFERRED_SIZE))))
															.addComponent(
																	dayDeleteJob))
											.addContainerGap(431, Short.MAX_VALUE)));
		}
}

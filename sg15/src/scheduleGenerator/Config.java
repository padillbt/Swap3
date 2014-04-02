/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleGenerator;

import java.awt.Dimension;
import java.lang.reflect.Field;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * SWAP 1, TEAM: 03
 * BAD SMELL:
 * This is an example of Large Class. This class is responsible 
 * for creating and formating all the pieces on the Config window 
 * as well as creating the handlers and code for the events.
 * To fix this issue you would move parts of this class to its 
 * own classes such as the tabs that are created for each weekday.
 * 
 * BAD SMELL: Shotgun Surgery
 * This class creates 7 different tabs that all do pretty much the same 
 * thing and infact look identical. Therefore if you want to, say change 
 * the look of the tabs, you have to go through and make the change for 
 * every tab.
 *  * To fix this issue you would move parts of this class to its 
 * own classes such as the tabs that are created for each weekday and 
 * then just created a new day tab and then all the code will be in the same spot
 * 
 * SWAP 2, TEAM: 04
 * REFACTORING FOR ENHANCEMENT
 * Created a DayPanel class which allows for the code to be placed inside and easily changed.
 * This handles both smells listed above since it pulls the code for the tabs into its own class.
 */
/**
 * 
 * @author schneimd
 */
public class Config extends javax.swing.JFrame {

	private boolean firstSelection = true;
	private int numSelected = 0;
	@SuppressWarnings("rawtypes")
	private DefaultListModel[] models;
	private Locale locale;

	/**
	 * Used to edit days.
	 * 
	 * @param days
	 */
	@SuppressWarnings("unchecked")
	public Config(ArrayList<Day> days, Locale locale) {
		this.models = new DefaultListModel[7];
		for(int x = 0; x < 7; x ++)
		{
		this.models[x] = new DefaultListModel<String>();
		}
		this.locale = locale;
		initDyn();
		initComponents();

		// SWAP, 1 TEAM: 03
		//
		// QUALITY CHANGES
		//
		// Removed the if statement inside of the loop because it all did the
		// same stuff except for the do click
		// which i moved to a new method called performClickForDay()
		// This overcame the duplicated code Bad code smell
		for (Day day : days) {
			int dayIndex =  day.getIndexOfDay();
			
			performClickForDay(dayIndex);
			ArrayList<String> jobs = day.getJobs();
			
			for (String job : jobs) {
				this.models[dayIndex].addElement(job);
			}
		}
	}

	// SWAP, 1 TEAM: 03
	//
	// QUALITY CHANGES
	//
	// Created this method to allow the loop in the method above to remove some
	// of the duplicate code
	// This overcame the duplicated code Bad code smell
	public void performClickForDay(int day) {
		switch (day) {
		case 0:
			this.sundayCheck.doClick();
			break;
		case 1:
			this.mondayCheck.doClick();
			break;
		case 2:
			this.tuesdayCheck.doClick();
			break;
		case 3:
			this.wednesdayCheck.doClick();
			break;
		case 4:
			this.thursdayCheck.doClick();
			break;
		case 5:
			this.fridayCheck.doClick();
			break;
		case 6:
			this.saturdayCheck.doClick();
			break;
		}
	}

	/**
	 * Creates new form.
	 */
	public Config(Locale locale) {
		this.models = new DefaultListModel[7];
		for(int x = 0; x < 7; x ++)
		{
		this.models[x] = new DefaultListModel<String>();
		}
		this.locale = locale;
		initDyn();

		initComponents();
	}
	
	public Config() {
		this.models = new DefaultListModel[7];
		for(int x = 0; x < 7; x ++)
		{
		this.models[x] = new DefaultListModel<String>();
		}
		this.locale = Main.getLocale();
		initDyn();

		initComponents();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initDyn() {
		this.sundayScrollPane = new javax.swing.JScrollPane();
		this.sundayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.sundayJobList = new javax.swing.JList();
		this.sundayJobName = new javax.swing.JTextField();
		this.sundayLabel = new javax.swing.JLabel();
		this.sundayAddJob = new javax.swing.JButton();
		this.sundayDeleteJob = new javax.swing.JButton();

		this.mondayScrollPane = new javax.swing.JScrollPane();
		this.mondayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.mondayJobList = new javax.swing.JList();
		this.mondayJobName = new javax.swing.JTextField();
		this.mondayLabel = new javax.swing.JLabel();
		this.mondayAddJob = new javax.swing.JButton();
		this.mondayDeleteJob = new javax.swing.JButton();

		this.tuesdayScrollPane = new javax.swing.JScrollPane();
		this.tuesdayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.tuesdayJobList = new javax.swing.JList();
		this.tuesdayJobName = new javax.swing.JTextField();
		this.tuesdayLabel = new javax.swing.JLabel();
		this.tuesdayAddJob = new javax.swing.JButton();
		this.tuesdayDeleteJob = new javax.swing.JButton();

		this.wednesdayScrollPane = new javax.swing.JScrollPane();
		this.wednesdayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.wednesdayJobList = new javax.swing.JList();
		this.wednesdayJobName = new javax.swing.JTextField();
		this.wednesdayLabel = new javax.swing.JLabel();
		this.wednesdayAddJob = new javax.swing.JButton();
		this.wednesdayDeleteJob = new javax.swing.JButton();

		this.thursdayScrollPane = new javax.swing.JScrollPane();
		this.thursdayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.thursdayJobList = new javax.swing.JList();
		this.thursdayJobName = new javax.swing.JTextField();
		this.thursdayLabel = new javax.swing.JLabel();
		this.thursdayAddJob = new javax.swing.JButton();
		this.thursdayDeleteJob = new javax.swing.JButton();

		this.fridayScrollPane = new javax.swing.JScrollPane();
		this.fridayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.fridayJobList = new javax.swing.JList();
		this.fridayJobName = new javax.swing.JTextField();
		this.fridayLabel = new javax.swing.JLabel();
		this.fridayAddJob = new javax.swing.JButton();
		this.fridayDeleteJob = new javax.swing.JButton();

		this.saturdayScrollPane = new javax.swing.JScrollPane();
		this.saturdayScrollPane.setPreferredSize(new Dimension(185, 150));
		this.saturdayJobList = new javax.swing.JList();
		this.saturdayJobName = new javax.swing.JTextField();
		this.saturdayLabel = new javax.swing.JLabel();
		this.saturdayAddJob = new javax.swing.JButton();
		this.saturdayDeleteJob = new javax.swing.JButton();

		this.mondayTab = new DayPanel();
		this.tuesdayTab = new DayPanel();
		this.wednesdayTab = new DayPanel();
		this.thursdayTab = new DayPanel();
		this.fridayTab = new DayPanel();
		this.saturdayTab = new DayPanel();
		this.sundayTab = new DayPanel();
	}

	private void initComponents() {

		this.jPanel1 = new javax.swing.JPanel();
		this.sundayCheck = new javax.swing.JCheckBox();
		this.wednesdayCheck = new javax.swing.JCheckBox();
		this.mondayCheck = new javax.swing.JCheckBox();
		this.tuesdayCheck = new javax.swing.JCheckBox();
		this.jLabel1 = new javax.swing.JLabel();
		this.thursdayCheck = new javax.swing.JCheckBox();
		this.fridayCheck = new javax.swing.JCheckBox();
		this.saturdayCheck = new javax.swing.JCheckBox();
		this.nextButton = new javax.swing.JButton();
		this.dayTabs = new javax.swing.JTabbedPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Configuration");
		setPreferredSize(new java.awt.Dimension(801, 87));
		setResizable(false);

		this.sundayCheck.setText("Sunday");
		this.sundayCheck.setName("sundayCheck"); // NOI18N
		this.sundayCheck.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sundayCheckActionPerformed(evt);
			}
		});

		this.wednesdayCheck.setText("Wednesday");
		this.wednesdayCheck.setName("wednesdayCheck"); // NOI18N
		this.wednesdayCheck
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						wednesdayCheckActionPerformed(evt);
					}
				});

		this.mondayCheck.setText("Monday");
		this.mondayCheck.setName("mondayCheck"); // NOI18N
		this.mondayCheck.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mondayCheckActionPerformed(evt);
			}
		});

		this.tuesdayCheck.setText("Tuesday");
		this.tuesdayCheck.setName("tuesdayCheck"); // NOI18N
		this.tuesdayCheck
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						tuesdayCheckActionPerformed(evt);
					}
				});

		this.jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		this.jLabel1.setText("Days:");

		this.thursdayCheck.setText("Thursday");
		this.thursdayCheck.setName("thursdayCheck"); // NOI18N
		this.thursdayCheck
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						thursdayCheckActionPerformed(evt);
					}
				});

		this.fridayCheck.setText("Friday");
		this.fridayCheck.setName("fridayCheck"); // NOI18N
		this.fridayCheck.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fridayCheckActionPerformed(evt);
			}
		});

		this.saturdayCheck.setText("Saturday");
		this.saturdayCheck.setName("saturdayCheck"); // NOI18N
		this.saturdayCheck
				.addActionListener(new java.awt.event.ActionListener() {
					
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saturdayCheckActionPerformed(evt);
					}
				});

		this.nextButton.setText("Next");
		this.nextButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(this.jLabel1)
										.addGap(18, 18, 18)
										.addComponent(this.sundayCheck)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.mondayCheck,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												71,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(this.tuesdayCheck)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.wednesdayCheck,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(this.thursdayCheck)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.fridayCheck,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.saturdayCheck,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(this.nextButton)
										.addGap(78, 78, 78)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																this.sundayCheck,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				this.fridayCheck,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				this.saturdayCheck,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				33,
																				Short.MAX_VALUE)
																		.addComponent(
																				this.nextButton))
														.addComponent(
																this.wednesdayCheck,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																this.tuesdayCheck,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jLabel1)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addComponent(
																this.thursdayCheck,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																this.mondayCheck,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(this.jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 18, Short.MAX_VALUE))
				.addComponent(this.dayTabs));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(this.jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(this.dayTabs,
								javax.swing.GroupLayout.DEFAULT_SIZE, 196,
								Short.MAX_VALUE)));

		this.dayTabs.getAccessibleContext().setAccessibleName("Days Tab");

		pack();
	}// </editor-fold>

	/*
	 * SWAP 1, TEAM 03
	 * 
	 * BAD SMELL:
	 * Duplicated Code
	 * This method, as well as the following 6, all repeat pretty much 
	 * the same code but use specific fields depending on the type of tab.
	 * To Fix I would move the duplicate code to its own method and have all 
	 * the places that use it call the new method
	 */
	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void sundayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.sundayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			// SWAP, 1 TEAM: 03
			//
			// QUALITY CHANGES
			//
			// Moved the duplicate code into a method for setting the Horizontal
			// and vertical tab groups
			// This overcame the duplicated code Bad code smell
			this.sundayJobList.setModel(this.models[0]);
			sundayTab.DayPanelSet(models, this, sundayJobList, sundayJobName, sundayLabel, sundayAddJob, sundayDeleteJob, sundayScrollPane);
			sundayTab.setUp(0);
			this.dayTabs.addTab("Sunday", this.sundayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.sundayTab);
		}
	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void mondayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.mondayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.mondayJobList.setModel(this.models[1]);
			mondayTab.DayPanelSet(models, this, mondayJobList, mondayJobName, mondayLabel, mondayAddJob, mondayDeleteJob, mondayScrollPane);
			mondayTab.setUp(1);
			this.dayTabs.addTab("Monday", this.mondayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.mondayTab);
		}

	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void tuesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.tuesdayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.tuesdayJobList.setModel(this.models[2]);
			tuesdayTab.DayPanelSet(models, this, tuesdayJobList, tuesdayJobName, tuesdayLabel, tuesdayAddJob, tuesdayDeleteJob, tuesdayScrollPane);
			tuesdayTab.setUp(2);
			this.dayTabs.addTab("Tuesday", this.tuesdayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.tuesdayTab);
		}
	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void wednesdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.wednesdayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.wednesdayJobList.setModel(this.models[3]);
			wednesdayTab.DayPanelSet(models, this, wednesdayJobList, wednesdayJobName, wednesdayLabel, wednesdayAddJob, wednesdayDeleteJob, wednesdayScrollPane);
			wednesdayTab.setUp(3);

			this.dayTabs.addTab("Wednesday", this.wednesdayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.wednesdayTab);
		}

	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void thursdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.thursdayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.thursdayJobList.setModel(this.models[4]);
			thursdayTab.DayPanelSet(models, this, thursdayJobList, thursdayJobName, thursdayLabel, thursdayAddJob, thursdayDeleteJob, thursdayScrollPane);
			thursdayTab.setUp(4);

			this.dayTabs.addTab("Thursday", this.thursdayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.thursdayTab);
		}

	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void fridayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.fridayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			
			this.fridayJobList.setModel(this.models[5]);
			fridayTab.DayPanelSet(models, this, fridayJobList, fridayJobName, fridayLabel, fridayAddJob, fridayDeleteJob, fridayScrollPane);
			fridayTab.setUp(5);
			this.dayTabs.addTab("Friday", this.fridayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.fridayTab);
		}

	}

	/**
	 * @param evt
	 */
	@SuppressWarnings("unchecked")
	private void saturdayCheckActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.saturdayCheck.isSelected()) {
			this.numSelected++;
			if (this.firstSelection) {
				stretch();
			}
			this.saturdayJobList.setModel(this.models[6]);
			saturdayTab.DayPanelSet(models, this, saturdayJobList, saturdayJobName, saturdayLabel, saturdayAddJob, saturdayDeleteJob, saturdayScrollPane);
			saturdayTab.setUp(6);

			this.dayTabs.addTab("Saturday", this.saturdayTab);
		} else {
			this.numSelected--;
			stretch();
			this.dayTabs.remove(this.saturdayTab);
		}
	}

	

	/**
	 * @param evt
	 */
	private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ArrayList<Day> days = new ArrayList<Day>();
		if (this.sundayCheck.isSelected()) {
			ArrayList<Object> sun = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[0].toArray());
			sun.addAll(jobs);
			days.add(new Day("Sunday", sun));
		}
		if (this.mondayCheck.isSelected()) {
			ArrayList<Object> mon = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[1].toArray());
			mon.addAll(jobs);
			days.add(new Day("Monday", mon));
		}
		if (this.tuesdayCheck.isSelected()) {
			ArrayList<Object> tue = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[2].toArray());
			tue.addAll(jobs);
			days.add(new Day("Tuesday", tue));
		}
		if (this.wednesdayCheck.isSelected()) {
			ArrayList<Object> wed = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[3].toArray());
			wed.addAll(jobs);
			days.add(new Day("Wednesday", wed));
		}
		if (this.thursdayCheck.isSelected()) {
			ArrayList<Object> thu = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[4].toArray());
			thu.addAll(jobs);
			days.add(new Day("Thursday", thu));
		}
		if (this.fridayCheck.isSelected()) {
			ArrayList<Object> fri = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[5].toArray());
			fri.addAll(jobs);
			days.add(new Day("Friday", fri));
		}
		if (this.saturdayCheck.isSelected()) {
			ArrayList<Object> sat = new ArrayList<Object>();
			List<Object> jobs = Arrays.asList(this.models[6].toArray());
			sat.addAll(jobs);
			days.add(new Day("Saturday", sat));
		}
		if (days.size() > 0) {
			boolean hasJobs = true;
			int i = 0;
			while (hasJobs && i < days.size()) {
				if (days.get(i).getJobs().size() == 0) {
					hasJobs = false;
				}
				i++;
			}
			if (hasJobs) {
				Main.setDays(days);
				Main.wSet = new WorkerSetup(this.locale);
				Main.toggleWorkerSetup();
				Main.config = this;
				Main.toggleConfig();
			} else {
				JOptionPane.showMessageDialog(this,
						"You must have at least one job each day.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have not added any days.");
		}
	}

	private void stretch() {
		if (this.numSelected > 0) {
			this.setSize(801, 290);
			this.firstSelection = false;
		} else {
			this.setSize(801, 87);
			this.firstSelection = true;
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Config.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				new Config().setVisible(true);
			}
		});
	}
	

	private javax.swing.JScrollPane sundayScrollPane;
	private javax.swing.JButton sundayAddJob;
	private javax.swing.JButton sundayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList sundayJobList;
	private javax.swing.JTextField sundayJobName;
	private javax.swing.JLabel sundayLabel;
	private DayPanel sundayTab;

	private javax.swing.JScrollPane mondayScrollPane;
	private javax.swing.JButton mondayAddJob;
	private javax.swing.JButton mondayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList mondayJobList;
	private javax.swing.JTextField mondayJobName;
	private javax.swing.JLabel mondayLabel;
	private DayPanel mondayTab;

	private javax.swing.JScrollPane tuesdayScrollPane;
	private javax.swing.JButton tuesdayAddJob;
	private javax.swing.JButton tuesdayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList tuesdayJobList;
	private javax.swing.JTextField tuesdayJobName;
	private javax.swing.JLabel tuesdayLabel;
	private DayPanel tuesdayTab;

	private javax.swing.JScrollPane wednesdayScrollPane;
	private javax.swing.JButton wednesdayAddJob;
	private javax.swing.JButton wednesdayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList wednesdayJobList;
	private javax.swing.JTextField wednesdayJobName;
	private javax.swing.JLabel wednesdayLabel;
	private DayPanel wednesdayTab;

	private javax.swing.JScrollPane thursdayScrollPane;
	private javax.swing.JButton thursdayAddJob;
	private javax.swing.JButton thursdayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList thursdayJobList;
	private javax.swing.JTextField thursdayJobName;
	private javax.swing.JLabel thursdayLabel;
	private DayPanel thursdayTab;

	private javax.swing.JScrollPane fridayScrollPane;
	private javax.swing.JButton fridayAddJob;
	private javax.swing.JButton fridayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList fridayJobList;
	private javax.swing.JTextField fridayJobName;
	private javax.swing.JLabel fridayLabel;
	private DayPanel fridayTab;

	private javax.swing.JScrollPane saturdayScrollPane;
	private javax.swing.JButton saturdayAddJob;
	private javax.swing.JButton saturdayDeleteJob;
	@SuppressWarnings("rawtypes")
	private javax.swing.JList saturdayJobList;
	private javax.swing.JTextField saturdayJobName;
	private javax.swing.JLabel saturdayLabel;
	private DayPanel saturdayTab;

	private javax.swing.JTabbedPane dayTabs;
	private javax.swing.JCheckBox fridayCheck;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JCheckBox mondayCheck;
	private javax.swing.JButton nextButton;
	private javax.swing.JCheckBox saturdayCheck;
	private javax.swing.JCheckBox sundayCheck;
	private javax.swing.JCheckBox thursdayCheck;
	private javax.swing.JCheckBox tuesdayCheck;
	private javax.swing.JCheckBox wednesdayCheck;
}

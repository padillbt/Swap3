package scheduleGenerator;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

//SWAP 1, TEAM 03

// BAD SMELLS
// Data Class
// This class is mainly used for data storage and retrieval. There isn't much "functionality". Moving some behavior to the class would solve the issue.

//Swap 2 TEAM 04 - Refactoring for Enhancement
//I disagree with this just being a data class. We can see that it stores the information on a worker working and days that it worked,
//But it also contains methods for accessing and manipulating this data.

//SWAP 3, TEAM 6  
// ENHANCEMENT FROM REFACTORING
// 1. The refactoring allowed new functionality off the new method they created to fix the Data Class smell. 
// 2. The refactoring was successful in enabling us to display how much each worker has worked without having to calculate it on the fly. We were quickly able to add a menu
//		option on CalendarGui under the Generate menu item.
// 3. It adds to the value of the system by letting the user quickly see how many hours a worker has been signed up for and thus making changes if a worker has too many hours.
//

//However, we pull functionality from Schedule to make this less of a data class as possible
//Pulled getting the worker tabs for worker setup here. Makes more sense to prep the panel where the data is.
/**
 * A worker contains days available to work with jobs.
 *
 * @author schneimd.
 *         Created Oct 15, 2012.
 */
public class Worker implements Serializable{

	private String name;
	private ArrayList<Day> days = new ArrayList<Day>();
	private HashMap<String, Integer> timesWorked;
	
	public static Worker EmptyWorker() {
		return new Worker("Empty", new ArrayList<Day>());
	}
	/**
	 * Builds a worker with available days.
	 * @param name 
	 * @param days 
	 *
	 * @param jobs
	 */
	public Worker(String name, ArrayList<Day> days)
	{
		this.name = name;
		this.days = days;
		this.timesWorked = new HashMap<String, Integer>();
		for(Day day: days) {
			for(String job:day.getJobs()) {
				this.timesWorked.put(job, 0);
			}
		}
	}
	
	/**
	 * Gives the name of the worker.
	 *
	 * @return name of worker
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Increments the time a job is worked by one.
	 *
	 * @param jobName
	 */
	public void addWorkedJob(String jobName) {
		Integer i = this.timesWorked.get(jobName);
		if(i == null){
			this.timesWorked.put(jobName, 0);
		}
		this.timesWorked.put(jobName, this.timesWorked.get(jobName).intValue() + 1);
	}
	
	/**
	 * Returns the number of times a job has been worked.
	 *
	 * @param jobName
	 * @return number of times job has been worked.
	 */
	//Function no longer called in code. Left because it used to be used and could be used
	//to implement additional features. Felt no need to remove it right now.
	public int numWorkedForJob(String jobName) {
		return this.timesWorked.get(jobName);
	}
	
	public int getTotalWorkedCount() {
		// SWAP 1, TEAM 03
		//
		//BONUS FEATURE
		//
		//This method now returns the number of days worked for all positions
		
		// SWAP 2, TEAM 04
		// Moved to function call that makes more sense in name
		int count = 0;
		for (String s : timesWorked.keySet()){
			count += this.timesWorked.get(s);
		}
				
		return count;
	}
	
	/**
	 * Returns the workers day based on name.
	 *
	 * @param name
	 * @return day with same name
	 */
	public Day getDayWithName(String name) {
		for(Day d: this.days) {
			if(d.getNameOfDay().equals(name)) {
				return d;
			}
		}
		return null;
	}
	
	/**
	 * Returns the worker's days.
	 *
	 * @return days
	 */
	public ArrayList<Day> getDays() {
		return this.days;
	}
	
	/**
	 * Adds a day to the worker.
	 *
	 * @param d
	 */
	public void addDay(Day d) {
		this.days.add(d);
	}
	
	public WorkerTab getWorkerTab() {
		WorkerTab t = new WorkerTab();
		JTextField nameArea = (JTextField) t.getComponent(2);
		nameArea.setText(this.getName());
		JTabbedPane daysPane = (JTabbedPane) t.getComponents()[0];
		for (int i = 0; i < daysPane.getTabCount(); i++) {
			for (int n = 0; n < this.getDays().size(); n++) {
				if (daysPane.getTitleAt(i).equals(
						this.getDays().get(n).getNameOfDay())) {

					JPanel day = (JPanel) daysPane.getComponent(i);
					JScrollPane pane = (JScrollPane) day.getComponent(0);
					JViewport view = (JViewport) pane.getComponent(0);
					JPanel p = (JPanel) view.getComponent(0);

					for (Component job : p.getComponents()) {
						for (String workerJob : this.getDays().get(n)
								.getJobs()) {
							if (((JCheckBox) job).getText().equals(workerJob)) {
								((JCheckBox) job).setSelected(true);
							}
						}
					}
				}
			}
		}
		return t;
	}
	
}

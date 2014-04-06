package scheduleGenerator;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * SWAP 1, TEAM: 03
 * 
 * BAD CODE SMELL:
 * This is an example of a Lazy class because it doesn't do very much.
 * 
 * I would move the functionality to the code that calls it or I could 
 * add more functionality to this class to lighten the work done in other classes.
 */
 
 /*
 * SWAP 2, TEAM 04
 * REFACTORING FOR ENHANCEMENT FROM BAD SMELL
 * Pulled the calculation of index for day into this class. This means it is no longer needed in the
 * multiple separate classes where it was being performed. This makes this less of a lazy class since
 * it now converts its own day of hte week.
 */

//SWAP 3, TEAM 6  
// ENHANCEMENT FROM REFACTORING
// 1. The refactoring for getting the index of the day of the week enabled internationalization for Day.java. 
// 2. The refactoring was successful in enabling this enhancement because it allowed us to pass a Locale, which would then generate 
// the appropriate index for a localized name of day.
// 3. It adds to the value of the system because it no longer restricts the possible users to English speakers only.
//
// Note: We only added internationalization to this particular class because this particular refactor enabled it. For full internationalization changes must be made to the other
// 		 classes as well. To see the effects of internationalization you must be on the Config or Edit days screen.	
//

/**
 * Day is used to store jobs for a given day.
 *
 * @author schneimd.
 *         Created Oct 15, 2012.
 */
public class Day implements Serializable{
	
	private String dayOfWeek;
	private ArrayList<String> jobs = new ArrayList<String>();
	
	/**
	 * Construct a day with a name and jobs.
	 * 
	 * @param name 
	 *
	 * @param jobs
	 */
	public Day(String name, ArrayList<Object> jobs)
	{
		this.dayOfWeek = name;
		for(Object i:jobs)
		{
			this.jobs.add((String)i);
		}
	}
	
	/**
	 * Add one jobName.
	 *
	 * @param jobName
	 */
	public void addJob(String jobName) {
		this.jobs.add(jobName);
	}
	
	/**
	 * Set jobs to new jobs.
	 *
	 * @param jobNames
	 */
	public void setJobs(ArrayList<String> jobNames) {
		this.jobs = jobNames;
	}
	
	/**
	 * return current jobs.
	 *
	 * @return jobs
	 */
	public ArrayList<String> getJobs() {
		return this.jobs;
	}
	
	/**
	 * Gives the name of this day.
	 *
	 * @return day of week
	 */
	public String getNameOfDay() {
		return this.dayOfWeek;
	}
	
	public int getIndexOfDay() {
		DateFormatSymbols objDaySymbol = new DateFormatSymbols().getInstance(Main.getLocale());
		String[] symbolDayNames = objDaySymbol.getWeekdays();
		List<String> dayList = Arrays.asList(symbolDayNames);
		return dayList.indexOf(this.getNameOfDay()) - 1;
	}
}

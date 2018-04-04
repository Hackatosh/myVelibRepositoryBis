package myVelibCore.utilities;

import myVelibCore.stationPackage.Network;

/**
 * <b>This class allows to have a unique thread for the time management</b>
 * <p>It is a singleton pattern. This class implements runnable interface.
 * @author Simon Lecler
 *
 */
public class RunningTime implements Runnable{
	/**
	 * The unique instance of running time
	 */
	private static RunningTime instance = new RunningTime();
	/**
	 * The thread currently used by the instance to update the time
	 */
	private Thread currentThread;
	/**
	 * A static variable in order to know if the time is running or no
	 */
	private static boolean isTimeRunning = false;
	/**
	 * Incremented as time pass in order to print update for the user
	 */
	private int minutesPassed = 0;
	
	private RunningTime() {
		super();
	}
	/**
	 * 
	 * @return the unique instance of the RunningTime class
	 */
	public static RunningTime getInstance() {
		if (instance==null) {
			instance = new RunningTime();
			}
			return instance;
	}
	/**
	 * Overridden method run from interface runnable
	 * During the simulation, the time is incremented of 1 min at each loop
	 */
	@Override
	public void run() {
		while(isTimeRunning) {
			Time.updateTime();
			minutesPassed++;
			if(minutesPassed==60) {
				minutesPassed = 0; 
				System.out.println("An hour has passed !");}
			try {Thread.sleep(100);} 
			catch (InterruptedException e) {}
		}
	}
	/**
	 * Method used by user interface and other method to run the thread which update time
	 * and update isTimeRunning boolean accordingly
	 */
	public static void runTime() {
		if(RunningTime.isTimeRunning()) {
			System.out.println("Time is already running !");
		}
		else { 
			RunningTime.setTimeRunning(true);
			Thread time = new Thread(RunningTime.getInstance());
			RunningTime.getInstance().setCurrentThread(time);
			time.start();
		}
	}
	/**
	 * Method used by user interface and other method to stop the thread which update time
	 * and update isTimeRunning boolean accordingly
	 */
	public static void stopTime() {
		if(RunningTime.isTimeRunning()) {
			RunningTime.getInstance().getCurrentThread().interrupt();
			RunningTime.setTimeRunning(false);
		}
		else {
			System.out.println("Time is already stopped !");
		}
	}
	
	public Thread getCurrentThread() {
		return currentThread;
	}
	public void setCurrentThread(Thread currentThread) {
		this.currentThread = currentThread;
	}
	public static boolean isTimeRunning() {
		return isTimeRunning;
	}
	public static void setTimeRunning(boolean isTimeRunning) {
		RunningTime.isTimeRunning = isTimeRunning;
	}

}
	
	




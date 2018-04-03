package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelibCore.stationPackage.Network;
import myVelibCore.utilities.RunningTime;
import myVelibCore.utilities.Time;

class RunningTimeTest {

	@Test
	void testGetInstance() {
		RunningTime timeThread = RunningTime.getInstance();
		assertTrue(RunningTime.getInstance()!=null);
	}

	@Test
	void testRun() {
		
		RunningTime.runTime();
		try {Thread.sleep(5000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		RunningTime.stopTime();
		assertTrue(Time.getStaticCurrentTime()>0);
		
	}

}

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UnimplementedSubclassWithInputException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

class NetworkTest {

	
	@Test
	void getAListOfStationTypeTest() throws BadInstantiationException, UnimplementedSubclassWithInputException, FactoryNullException, NetworkNameAlreadyUsedException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork2");
		Station station1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network1,"station1");
		Station station2 = stationFactory.getStation("Standard",new GPSLocation(3,0),network1,"station2");
		ArrayList<Station> plus = new ArrayList<Station>();
		ArrayList<Station> standard = new ArrayList<Station>();
		ArrayList<Station> all = new ArrayList<Station>();
		plus.add(station1);
		standard.add(station2);
		all.add(station1);
		all.add(station2);
		ArrayList<Station> testAll =network1.getAllStations();
		ArrayList<Station> testPlus = network1.getAListOfStationType("Plus");
		ArrayList<Station> testStandard = network1.getAListOfStationType("Standard");
		
		assertTrue(testAll.equals(all));
		assertTrue(testPlus.equals(plus));
		assertTrue(testStandard.equals(standard));
	}

		


}

package tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NoBycicleAvailableException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningRideFailException;
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.exceptions.ReturnBikeFailException;
import myVelibCore.exceptions.SlotStatusFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UserNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;

@RunWith(value = BlockJUnit4ClassRunner.class)
class StationTest {

	@Test
	void testRentABikeWhenBikeAvailable() throws BadInstantiationException, RentBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, NoBycicleAvailableException, UserNameAlreadyUsedException, StationNameAlreadyUsedException{
	
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory networkFactory = FactoryProducer.getFactory("Network");
		Network network1 = networkFactory.getNetwork("network1");

		User user = userFactory.getUser("John",network1);
		
		Station station = stationFactory.getStation("Plus", new GPSLocation(1,0),network1,"station1");
		
		ParkingSlot slot1 = new ParkingSlot(station);
		
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		
		station.addBike(bycicle1);
		
		station.rentABike(user, "Electrical");
		
		assertTrue(station.getStationBikeCounters().isThereAnyBycicle() ==false);
		assertTrue(station.getStationStatitics().getRentOperationsNumber() == 1);		
	}
	
	@Test 
	void testRentABikeWhenNoBikeAvailable() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, NoBycicleAvailableException, UserNameAlreadyUsedException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network2 = NetworkFactory.getNetwork("testNetwork2");
		User user = userFactory.getUser("John",network2);
		Station station = stationFactory.getStation("Plus", new GPSLocation(1,0),network2,"station2");
		ParkingSlot slot1 = new ParkingSlot(station);
		
		try {
			station.rentABike(user, "Electrical");
		} catch (RentBikeFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			assertTrue(station.getStationStatitics().getRentOperationsNumber() == 0);
		}
		
		
		
		
	}
	@Test
	void testReturnABikeWhenThereIsFreeSlot() throws BadInstantiationException, RentBikeFailException, ReturnBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException, NoBycicleAvailableException, AddBikeFailException, UserNameAlreadyUsedException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network3 = NetworkFactory.getNetwork("testNetwork3");
		User user = userFactory.getUser("John",network3);
		Station stationRent = stationFactory.getStation("Plus", new GPSLocation(1,0),network3,"station3");
		Station stationReturn = stationFactory.getStation("Standard", new GPSLocation(1,1),network3,"station4");
		ParkingSlot slot1 = new ParkingSlot(stationRent);
		ParkingSlot slot2 = new ParkingSlot(stationReturn);
		Bycicle bycicle = bycicleFactory.getBycicle("Electrical");
		stationRent.addBike(bycicle);
		stationRent.rentABike(user,"Electrical");
		
		try {
			stationReturn.returnABike(user);
		} catch (StationFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(stationReturn.getStationBikeCounters().isThereAny("Electrical"));
		assertTrue(stationReturn.getStationStatitics().getReturnOperationsNumber()==1);
	}
	

	@Test 
	void testReturnABikeWhenNoFreeSlots()throws BadInstantiationException, RentBikeFailException, FactoryNullException, NetworkNameAlreadyUsedException, NoBycicleAvailableException, AddBikeFailException, StationFullException, UserNameAlreadyUsedException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network4 = NetworkFactory.getNetwork("testNetwork4");
		User user = userFactory.getUser("John",network4);
		Station stationRent = stationFactory.getStation("Plus", new GPSLocation(1,0),network4,"station5");
		Station stationReturn = stationFactory.getStation("Standard", new GPSLocation(1,1),network4,"station6");
		ParkingSlot slot1 = new ParkingSlot(stationRent);
		ParkingSlot slot2 = new ParkingSlot(stationReturn);
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle3 = bycicleFactory.getBycicle("Mechanical");
		stationRent.addBike(bycicle1);
		stationReturn.addBike(bycicle3);
		
		user.setBycicle(bycicle2);
		try {
			stationRent.returnABike(user);
		} catch (ReturnBikeFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			assertTrue(stationReturn.getStationBikeCounters().isThereAny("Electrical")== false);
			assertTrue(stationReturn.getStationStatitics().getReturnOperationsNumber()==0);		
		}
		
	}

	@Test
	void testNotifyObserversDeparture() throws BadInstantiationException, NoStartingStationAvailableException, NoDestinationStationAvailableException, SlotStatusFailException, PlanningRideFailException, FactoryNullException, NetworkNameAlreadyUsedException, AddBikeFailException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network5 = NetworkFactory.getNetwork("testNetwork5");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,0),network5,"station7");
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(20,20),network5,"station8");
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(10,10),network5,"station9");
		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus2);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		User user = null;
		User user1 = null;
		try {user = new User("Edouard",network5);user1 = new User("Simon", network5);}
		catch(Exception e) { System.out.println(e.getMessage());}
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		Bycicle bycicle2 = bycicleFactory.getBycicle("Electrical");
		stationPlus2.addBike(bycicle1);
		stationStandard1.addBike(bycicle2);
		user.setGpsLocation(new GPSLocation(25,25));
		user.planningRide(new GPSLocation(0,0), "Shortest Path", "Electrical");
		try {
			stationPlus2.rentABike(user1, "Electrical");
		} catch (RentBikeFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(user.getCurrentDepartureStation()== stationStandard1);

	}

	@Test
	void testNotifyObserversDestination()throws BadInstantiationException, NoStartingStationAvailableException, NoDestinationStationAvailableException, StationFullException, AddBikeFailException, PlanningRideFailException, SlotStatusFailException, FactoryNullException, NetworkNameAlreadyUsedException, StationNameAlreadyUsedException{
		AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
		AbstractFactory userFactory = FactoryProducer.getFactory("User");
		AbstractFactory bycicleFactory = FactoryProducer.getFactory("Bycicle");
		Bycicle bycicle1 = bycicleFactory.getBycicle("Electrical");
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network6 = NetworkFactory.getNetwork("testNetwork6");
		Station stationPlus1 = stationFactory.getStation("Plus", new GPSLocation(1,1),network6,"station10");
		Station stationPlus2 = stationFactory.getStation("Plus", new GPSLocation(20,20),network6,"station11");
		Station stationStandard1 = stationFactory.getStation("Standard", new GPSLocation(3,3),network6,"station12");
		ParkingSlot slot1 = new ParkingSlot(stationPlus1);
		ParkingSlot slot2 = new ParkingSlot(stationPlus2);
		ParkingSlot slot3 = new ParkingSlot(stationStandard1);
		User user = null; 
		User user1 = null;
		try{user = new User("Edouard",network6); user1 = new User("Simon", network6);}
		catch (Exception e) {System.out.println(e.getMessage());}
		user.setGpsLocation(new GPSLocation(25,25));
		stationPlus2.getStationBikeCounters().addBike(bycicle1, stationPlus2.getSlots());
		user.planningRide(new GPSLocation(0,0), "Shortest Path", "Electrical");
		user1.setBycicle(bycicle1);
		try {
			stationPlus2.returnABike(user1);
		} catch (ReturnBikeFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(user.getCurrentDestinationStation()== stationPlus1);
		
	}

}

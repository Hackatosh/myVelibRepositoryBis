package myVelibCLUI;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.AddBikeFailException;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.NotEnoughSlotsException;
import myVelibCore.exceptions.PlanningRideFailException;
import myVelibCore.exceptions.RentBikeFailException;
import myVelibCore.exceptions.ReturnBikeFailException;
import myVelibCore.exceptions.SlotStatusFailException;
import myVelibCore.exceptions.StationFullException;
import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UnexistingNetworkNameException;
import myVelibCore.exceptions.UnexistingStationIDException;
import myVelibCore.exceptions.UnexistingStationNameException;
import myVelibCore.exceptions.UnexistingUserIDException;
import myVelibCore.exceptions.UnexistingUserNameException;
import myVelibCore.exceptions.UserNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
import myVelibCore.utilities.RunningTime;
import myVelibCore.utilities.Time;

public class ParsingAndCalling {
	
	private static final String TYPE_ERROR_MSG = "An argument is of a wrong type. Please retry or type help for assistance.";
	
	public static void setupWith1Param(String[] args) {
		String velibNetworkName;
		velibNetworkName = args[1];
		try {
			Network.setupNetwork(velibNetworkName, 10, 10, 4, 75);
		}
		catch (NotEnoughSlotsException | NetworkNameAlreadyUsedException e) {
			System.out.println("Can not create the network because : " + e.getMessage()); 
		}
	}
	
	public static void defaultSetUpWith2Param(String[] args) {
		boolean argsParsable = true;
		String velibNetworkName;
		double sideArea=0;
		velibNetworkName = args[1];
		try {
			sideArea = Double.parseDouble(args[2]);
		}
		catch(Exception e) {System.out.println("Error : sideArea must be a double"); argsParsable = false;}
		if(argsParsable) {
			try {
				AbstractFactory networkFactory = FactoryProducer.getFactory("Network");

				
				Network defaultNetwork = networkFactory.getNetwork(velibNetworkName, sideArea);
				
			}
			catch(NetworkNameAlreadyUsedException |BadInstantiationException |FactoryNullException e) {
				System.out.println("Cannot create this network because : "+ e.getMessage());}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
				
		
	}
	public static void setupWith5Param(String[] args) {
		String name; int nstations=0; int nslots=0; float sidearea=0; int nbikes=0;
		boolean argsParsable = true;
		name = args[1];
		try{nstations = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : nstations must be an integer"); argsParsable = false;}
		try{nslots = Integer.parseInt(args[3]);}
		catch(Exception e) {System.out.println("Error : nslots must be an integer"); argsParsable = false;}
		try{sidearea = Float.parseFloat(args[4]);}
		catch(Exception e) {System.out.println("Error : sidearea must be a float"); argsParsable = false;}
		try{nbikes = Integer.parseInt(args[5]);}
		catch(Exception e) {System.out.println("Error : nbikes must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network.setupNetwork(name, nstations, nslots, sidearea, nbikes);
			}
			catch (NotEnoughSlotsException | NetworkNameAlreadyUsedException e) {
				System.out.println("Can not create the network because : " + e.getMessage());
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	
	public static void setupWith6Param(String[] args) {
		String name; int nstations=0; int nslots=0; float sidearea=0; int nbikesMechanical=0; int nbikesElectrical=0;
		boolean argsParsable = true;
		name = args[1];
		try{nstations = Integer.parseInt(args[2]);}
		catch(Exception e) {System.out.println("Error : nstations must be an integer"); argsParsable = false;}
		try{nslots = Integer.parseInt(args[3]);}
		catch(Exception e) {System.out.println("Error : nslots must be an integer"); argsParsable = false;}
		try{sidearea = Float.parseFloat(args[4]);}
		catch(Exception e) {System.out.println("Error : sidearea must be a float"); argsParsable = false;}
		try{nbikesMechanical = Integer.parseInt(args[5]);}
		catch(Exception e) {System.out.println("Error : nbikesMechanical must be an integer"); argsParsable = false;}
		try{nbikesElectrical = Integer.parseInt(args[6]);}
		catch(Exception e) {System.out.println("Error : nbikesElectrical must be an integer"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network.setupNetworkWithDefinedBycicle(name, nstations, nslots, sidearea, nbikesMechanical, nbikesElectrical);
			}
			catch (NotEnoughSlotsException | NetworkNameAlreadyUsedException e) {
				System.out.println("Can not create the network because : " + e.getMessage());
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	public static void addStationWith6Param(String[] args) {
		String networkName; String stationName; String stationType; double latitude = 0; double longitude = 0; int nSlots = 0;
		boolean argsParsable = true;
		try {nSlots = Integer.parseInt(args[6]);}
		catch(Exception e) {System.out.println("Error : the number of slots must be in Integer");argsParsable = false;}
		try {latitude = Double.parseDouble(args[4]);}
		catch(Exception e) {System.out.println("Error : latitude must be a double"); argsParsable = false;}
		try {longitude = Double.parseDouble(args[5]);}
		catch(Exception e) {System.out.println("Error : longitude must be a double"); argsParsable = false;}
		if(argsParsable) {
			try {
				networkName = args[1];
				stationName = args[2];
				stationType = args[3];
				GPSLocation gpsLocation = new GPSLocation(latitude, longitude);
				double maxLat = GPSLocation.getMaxLatitude(Network.searchNetworkByName(networkName).getSideArea());
				double maxLong = GPSLocation.getMaxLongitude(Network.searchNetworkByName(networkName).getSideArea());
				AbstractFactory stationFactory = FactoryProducer.getFactory("Station");
				Station station = stationFactory.getStation(stationType, gpsLocation, Network.searchNetworkByName(networkName), stationName);
				for (int i=1; i<=nSlots; i++) {
					station.addParkingSlot();
				}
			}
			catch(BadInstantiationException |FactoryNullException|UnexistingNetworkNameException |StationNameAlreadyUsedException e) {System.out.println("Error occured because :"+ e.getMessage());}
		}
		else {
			System.out.println(TYPE_ERROR_MSG);
		}
		
	}
	public static void addUserWith3Param(String[] args) {
		String userName; String cardType; String velibNetworkName;
		userName = args[1];
		cardType = args[2];
		velibNetworkName = args[3];
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			AbstractFactory UserFactory = FactoryProducer.getFactory("User");
			User user = UserFactory.getUser(userName,network);
			AbstractFactory cardFactory = FactoryProducer.getFactory("Card");
			Card userCard = cardFactory.getCard(cardType);
			Thread userThread = new Thread(user);
			userThread.start();
		}
		catch (UnexistingNetworkNameException | UserNameAlreadyUsedException | BadInstantiationException  e) {
			System.out.println("Impossible to add user because : " + e.getMessage());
		} 
		catch (FactoryNullException e) {
			System.out.println("IMPOSSIBLE TO PERFORM OPERATION. ERROR NOT SUPPOSED TO HAPPEN" + e.getMessage());
		} 

	}
	
	public static void setUserGPSPositionWith4Param(String[] args) {
		String velibNetworkName; String userName ; double latitude = 0; double longitude = 0;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		userName = args[2];
		try {latitude = Double.parseDouble(args[3]);}
		catch(Exception e) {System.out.println("Error : latitude must be a double"); argsParsable = false;}
		try {longitude = Double.parseDouble(args[4]);}
		catch(Exception e) {System.out.println("Error : longitude must be a double"); argsParsable = false;}
		if(argsParsable) {
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				User user = network.searchUserByName(userName);
				user.setGpsLocation(new GPSLocation(latitude, longitude));
			}
			catch (UnexistingNetworkNameException | UnexistingUserNameException e) {
				System.out.println("Impossible to modify the GPS location because : " + e.getMessage());
			}
			
		}
		else {System.out.println(TYPE_ERROR_MSG);}
	}
	public static void addBicycleWith3Param(String[] args) {
		String networkName; String stationName; String bicycleType;
		networkName = args[1];
		stationName = args[2];
		bicycleType = args[3];
		try {
			AbstractFactory bicycleFactory;
			bicycleFactory = FactoryProducer.getFactory("Bycicle");
			Bycicle bycicle = bicycleFactory.getBycicle(bicycleType);
				Network.searchStationByName(stationName, Network.searchNetworkByName(networkName)).addBike(bycicle);
		} catch (BadInstantiationException | FactoryNullException | UnexistingStationNameException | UnexistingNetworkNameException | AddBikeFailException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void offlineWith2Param(String[] args) {
		String velibNetworkName; String stationName;
		velibNetworkName = args[1];
		stationName = args[2];
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				Station station = network.searchStationByName(stationName,network);
				station.turnOff();
			}
			catch (UnexistingNetworkNameException | UnexistingStationNameException e) {
				System.out.println("Impossible to turn station offline because : " + e.getMessage());
			} 
			catch (SlotStatusFailException e) {
				System.out.println("Station offline but we encountered problems during the operation : " + e.getMessage());
			} 
	}
	
	public static void onlineWith2Param(String[] args) {
		String velibNetworkName; String stationName;
		velibNetworkName = args[1];
		stationName=args[2];
			try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			Station station = network.searchStationByName(stationName,network);
			station.turnOn();
			}
			catch (UnexistingNetworkNameException | UnexistingStationNameException e) {
				System.out.println("Impossible to turn station online because : " + e.getMessage());
			} 
			catch (SlotStatusFailException e) {
				System.out.println("Station online but we encountered problems during the operation : " + e.getMessage());
			} 
			
	}
	
	public static void rentBikeWith2Param(String[] args) {
		String userName; String stationName; String bycicleType;
		userName = args[1];
		stationName = args[2];
		bycicleType = args[3];
			try {
				User user = Network.searchUserByNameAllNetworks(userName);
				Station station = Network.searchStationByNameAllNetworks(stationName);
				station.rentABike(user, bycicleType);
			}
			catch (UnexistingUserNameException | UnexistingStationNameException | RentBikeFailException e) {
				System.out.println("Impossible to perform the renting operation because : "+e.getMessage());
			} 
	}
	
	public static void returnBikeWith3Param(String[] args) {
		String userName; String stationName;
		boolean argsParsable = true;
		userName = args[1];
		stationName = args[2];
		try {
			User user = Network.searchUserByNameAllNetworks(userName);
			Station station = Network.searchStationByNameAllNetworks(stationName);
			station.returnABike(user);
		}
		catch (UnexistingUserNameException | UnexistingStationNameException | ReturnBikeFailException | StationFullException e) {
			System.out.println("Impossible to perform the returning operation because : "+e.getMessage());
		} 
	}
	
	public static void displayStationWith2Param(String[] args) {
		String velibNetworkName =""; String stationName;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		stationName = args[2];
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				Station station = Network.searchStationByName(stationName,network);
				station.display();
			}
			catch (UnexistingStationNameException | UnexistingNetworkNameException e) {
				System.out.println("Impossible to display the station because : " + e.getMessage());
			}
		
	}
	
	public static void displayUserWith2Param(String[] args) {
		String velibNetworkName; String userName;
		velibNetworkName = args[1];
		userName = args[2];
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				User user = network.searchUserByName(userName);
				user.display();
			}
			catch (UnexistingNetworkNameException | UnexistingUserNameException e) {
				System.out.println("Impossible to display the user because : " + e.getMessage());
			}
	}
	
	public static void sortStationWith2Param(String[] args) {
		String velibNetworkName; String policy;
		velibNetworkName = args[1];
		policy = args[2];
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			network.sortStationBy(policy);
		}
		catch (UnexistingNetworkNameException | BadInstantiationException e) {
			System.out.println("Impossible to sort the stations because : " + e.getMessage());
		}
	}
	
	public static void displayWith1Param(String[] args) {
		String velibNetworkName;
		velibNetworkName = args[1];
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			System.out.println(network);
		}
		catch (UnexistingNetworkNameException e) {
			System.out.println("Impossible to display the network because : " + e.getMessage());
		}
	}
	
	public static void listNetworkWith0Param(String[] args) {
		Network.ListAllNetworks();
	}
	
	public static void planningRideWith6Param(String[] args) {
		String velibNetworkName; String userName; String policy ; double destinationLatitude = 0; double destinationLongitude = 0 ; String bicycleType;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		userName = args[2];
		policy = args[3];
		try {destinationLatitude = Double.parseDouble(args[4]);}
		catch(Exception e) {System.out.println("Error : destinationLatitude must be a double"); argsParsable = false;}
		try {destinationLongitude = Double.parseDouble(args[5]);}
		catch(Exception e) {System.out.println("Error : destinationLongitude must be a double"); argsParsable = false;}
		bicycleType = args[6];
		if(argsParsable) {
			try {
				GPSLocation destination = new GPSLocation(destinationLatitude, destinationLongitude);
				Network network = Network.searchNetworkByName(velibNetworkName);
				User user = network.searchUserByName(userName);
				user.planningRide(destination, policy, bicycleType);
			}
			catch (UnexistingNetworkNameException | UnexistingUserNameException | PlanningRideFailException e) {
				System.out.println("Impossible to plan the ride because : " + e.getMessage());
			}
		}
		else {System.out.println(TYPE_ERROR_MSG);}	
	}
	
	public static void addRentOperationWith5Param (String[] args) {
		String velibNetworkName; String stationName; String userName; int timeOfOperation = 0; int numberOfParkingSlot = 0;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		stationName = args[2];
		userName = args[3];
		try {timeOfOperation = Integer.parseInt(args[4]);}
		catch(Exception e) {System.out.println("Error : timeOfOperation must be an integer"); argsParsable = false;}
		try {numberOfParkingSlot = Integer.parseInt(args[5]);}
		catch(Exception e) {System.out.println("Error : numberOfParkingSlot must be an integer"); argsParsable = false;}
		try {
			Network network = Network.searchNetworkByName(velibNetworkName);
			User user = network.searchUserByName(userName);
			Station station = Network.searchStationByName(stationName,network);
			ParkingSlot slot = station.getSlots().get(numberOfParkingSlot); //We can do better...
			station.getStationStatitics().addRentOperation(user, new Time(timeOfOperation), slot);
		}
		catch (UnexistingNetworkNameException | UnexistingUserNameException | UnexistingStationNameException e) {
			System.out.println("Impossible to add the renting operation because : " + e.getMessage());
		}
	}
	
	public static void addReturnOperationWith5Param (String[] args) {
		String velibNetworkName; String stationName; String userName; int timeOfOperation = 0; int numberOfParkingSlot = 0;
		boolean argsParsable = true;
		velibNetworkName = args[1];
		stationName = args[2];
		userName = args[3];
		try {timeOfOperation = Integer.parseInt(args[4]);}
		catch(Exception e) {System.out.println("Error : timeOfOperation must be an integer"); argsParsable = false;}
		try {numberOfParkingSlot = Integer.parseInt(args[5]);}
		catch(Exception e) {System.out.println("Error : numberOfParkingSlot must be an integer"); argsParsable = false;}
		if (argsParsable) {
			try {
				Network network = Network.searchNetworkByName(velibNetworkName);
				User user = network.searchUserByName(userName);
				Station station = Network.searchStationByName(stationName,network);
				ParkingSlot slot = station.getSlots().get(numberOfParkingSlot); //We can do better...
				station.getStationStatitics().addReturnOperation(user, new Time(timeOfOperation), slot);
			}
			catch (UnexistingNetworkNameException | UnexistingUserNameException | UnexistingStationNameException e) {
				System.out.println("Impossible to add the returning operation because : " + e.getMessage());
			}	
		}
	}
	
	public static void runTimeWith0Param(String[] args) {
		RunningTime.runTime();
	}
	
	public static void stopTimeWith0Param(String[] args) {
		RunningTime.stopTime();
	}
	
	public static void updateTimeWith1Param(String[] args) {
		int minutes = 0;
		boolean argsParsable = true;
		try {minutes = Integer.parseInt(args[1]);}
		catch(Exception e) {System.out.println("Error : timeOfOperation must be an integer"); argsParsable = false;}
		if (argsParsable) {
			Time.updateTime(minutes);
		}
	}
}

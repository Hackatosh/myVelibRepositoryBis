package myVelibCore.stationPackage;

import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.userAndCardPackage.Observer;
import myVelibCore.utilities.GPSLocation;

/**
 * The standard station type
 * @author Simon Lecler
 *
 */
public class StationStandard extends Station{
	/**
	 * Credit given to the user card when he returns a bike
	 */
	private final int creditGivenWhenReturning=0;
	/**
	 * Constructor of the standard station
	 * @param gpsLocation
	 * 		The GPS Coordinates of the station
	 * @throws UnimplementedSubclassWithoutInputException 
	 * @throws StationNameAlreadyUsedException 
	 */
	public StationStandard(GPSLocation gpsLocation, Network network, String name) throws UnimplementedSubclassWithoutInputException, StationNameAlreadyUsedException {
		super(gpsLocation,network, name);
	}
	@Override
	public String toString() {
		return "Station Standard" + " " + "|" + " " +" id="+" " + this.getId() + " "+"|" + " "+ "name =" + this.getName() + " " +"|"+" " + "Coordonnées GPS :"+ " " + this.getGpsLocation() +" "+ "|" + " "+ "Number of parking slots" + " " +this.getSlots().size() + this.getStationBikeCounters() +"\r\n";
		
	}
	
	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	

}

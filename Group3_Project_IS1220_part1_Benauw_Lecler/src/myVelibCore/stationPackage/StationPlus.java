package myVelibCore.stationPackage;

import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
import myVelibCore.userAndCardPackage.Observer;
import myVelibCore.utilities.GPSLocation;
/**
 * <b>Special type of station</b>
 * When a user returns a bike in such a station, he earns 5 minutes of time-credit. It is a way of keeping uniformity among bicycles repartition.
 * @author Edouard Benauw
 *
 */
public class StationPlus extends Station{
	@Override
	public String toString() {
		return "Station Plus" + " " + "|" + " " +" id=" + this.getId() + " "+"|" + " "+ "name =" + this.getName() + " " +"|"+" "+ "Coordonnées GPS : " + " " + this.getGpsLocation() +" "+ "|" + " "+ "Number of parking slots :" + " " + this.getSlots().size() + this.getStationBikeCounters() +"\r\n";
		
	}
	/*
	 * Time-credit earned by the user. It is declared final as it doesn't change
	 */
	private final int creditGivenWhenReturning=5;
	/**
	 * Constructor with parameters inherited from Station
	 * @param gpsLocation
	 * @param network
	 * @throws UnimplementedSubclassWithoutInputException
	 * @throws StationNameAlreadyUsedException 
	 */
	public StationPlus(GPSLocation gpsLocation, Network network, String name) throws UnimplementedSubclassWithoutInputException, StationNameAlreadyUsedException {
		super(gpsLocation,network, name);
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

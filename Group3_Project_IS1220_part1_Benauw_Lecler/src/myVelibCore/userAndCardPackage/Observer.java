package myVelibCore.userAndCardPackage;

/**
 * Observer pattern to update the rides of user
 * @author Edouard
 *
 */

public interface Observer {
	public void updateDeparture (boolean isThereAnyBicycle,boolean stationStatus);
	public void updateDestination (boolean isThereFreeSlots,boolean stationStatus);
}

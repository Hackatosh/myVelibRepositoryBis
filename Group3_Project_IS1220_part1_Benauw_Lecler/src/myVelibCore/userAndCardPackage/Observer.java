package myVelibCore.userAndCardPackage;

public interface Observer {
	public void updateDeparture (boolean isThereAnyBicycle,boolean stationStatus);
	public void updateDestination (boolean isThereFreeSlots,boolean stationStatus);
}

package myVelibCore.planningPolicyPackage;

import myVelibCore.exceptions.BadSpeedSelectionException;
import myVelibCore.exceptions.NoDestinationStationAvailableException;
import myVelibCore.exceptions.NoStartingStationAvailableException;
import myVelibCore.exceptions.PlanningPathFailedException;
import myVelibCore.exceptions.RecalculatePathFailedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.utilities.GPSLocation;

/**
 * Strategy Pattern which allow to choose a policy to plan or update a ride for an user
 * @author Edouard
 *
 */

public interface PlanningPolicy {
	public Station[] chooseStations(GPSLocation start,GPSLocation destination, String bycicleType, Network network) throws PlanningPathFailedException;
	public Station	recalculateWhenRiding(GPSLocation start, GPSLocation destination, String bycicleType, Network network) throws  RecalculatePathFailedException;
}

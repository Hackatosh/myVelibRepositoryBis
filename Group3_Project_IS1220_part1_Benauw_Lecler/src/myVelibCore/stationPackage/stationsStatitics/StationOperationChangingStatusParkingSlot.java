package myVelibCore.stationPackage.stationsStatitics;

import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.utilities.Time;

/**
 * Extends StationOperation in order to register the operation which corresponds to a changement of status for a parking slot
 * @author Edouard
 *
 */

public class StationOperationChangingStatusParkingSlot extends StationOperation {
	
	public StationOperationChangingStatusParkingSlot(Time dateOfOperation, ParkingSlot parkingSlotConcerned,boolean isParkingSlotOccupiedAfter,boolean wasParkingSlotOccupiedBefore) {
		super(dateOfOperation,parkingSlotConcerned,isParkingSlotOccupiedAfter,wasParkingSlotOccupiedBefore);
	}
}

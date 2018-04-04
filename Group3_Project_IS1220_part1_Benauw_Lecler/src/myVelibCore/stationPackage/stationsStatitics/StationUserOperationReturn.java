package myVelibCore.stationPackage.stationsStatitics;

import myVelibCore.stationPackage.ParkingSlot;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.Time;
/**
 * Extends StationOperation in order to register the operation which corresponds to a returning operation
 * @author Edouard
 *
 */
public class StationUserOperationReturn extends StationOperation {
	private User userConcerned;
	
	public StationUserOperationReturn(User userConcerned, Time dateOfOperation, ParkingSlot parkingSlotConcerned,boolean isParkingSlotOccupiedAfter,boolean wasParkingSlotOccupiedBefore) {
		super(dateOfOperation,parkingSlotConcerned,isParkingSlotOccupiedAfter,wasParkingSlotOccupiedBefore);
		this.userConcerned=userConcerned;
	}

	public User getUserConcerned() {
		return userConcerned;
	}

	public void setUserConcerned(User userConcerned) {
		this.userConcerned = userConcerned;
	}
	
	
}

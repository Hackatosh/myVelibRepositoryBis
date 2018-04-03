package myVelibCore.exceptions;

public class UnexistingStationNameException extends Exception {
	private String unexistingName;
	
	
	
	public UnexistingStationNameException(String unexistingName) {
		super();
		this.unexistingName = unexistingName;
	}



	@Override
	public String getMessage() {
		return "The following name : " + unexistingName + "could not be find in the station database.\r\n";
	}
}

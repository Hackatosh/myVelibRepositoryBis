package myVelibCore.exceptions;

import myVelibCore.stationPackage.Network;

public class UserNameAlreadyUsedException extends Exception{
	private String nameAlreadyUsed;

	public UserNameAlreadyUsedException(String nameAlreadyUsed) {
		super();
		this.nameAlreadyUsed = nameAlreadyUsed;
	}
	
	@Override
	public String getMessage() {
		return "The following user name : " + this.nameAlreadyUsed + "is already used in this network. Please pick an other one !";
	}
}

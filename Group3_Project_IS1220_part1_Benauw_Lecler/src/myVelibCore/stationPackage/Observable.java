package myVelibCore.stationPackage;

import myVelibCore.userAndCardPackage.Observer;
/**
 * Observable interface used to update the rides of user
 * @author Edouard
 *
 */
public interface Observable {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}

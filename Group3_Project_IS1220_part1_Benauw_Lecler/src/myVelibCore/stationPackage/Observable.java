package myVelibCore.stationPackage;

import myVelibCore.userAndCardPackage.Observer;

public interface Observable {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}

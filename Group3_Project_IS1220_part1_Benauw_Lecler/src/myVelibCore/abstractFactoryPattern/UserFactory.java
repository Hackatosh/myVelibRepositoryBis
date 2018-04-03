package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.UserNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
/**
 * <b> This is the UserFactory It belongs to the factory pattern.</b>
 * <p>It allows to instantiate User objects.
 * @author Simon Lecler
 */
public class UserFactory extends AbstractFactory{
	/**
	 * @param User's name of type String
	 * @return Instance of User 
	 */
	@Override
	public User getUser(String name, Network network) throws UserNameAlreadyUsedException{
		return new User(name,network);
	}
	@Override
	public Bycicle getBycicle(String BycicleType) throws FactoryNullException {
		throw new FactoryNullException("User","Bycicle");
	}
	@Override
	public Station getStation(String stationType, GPSLocation gpsLocation, Network network, String name) throws FactoryNullException {
		throw new FactoryNullException("User","Station");
	}

	@Override
	public Card getCard(String CardType) throws FactoryNullException {
		throw new FactoryNullException("User","Card");
	}
	@Override
	public Card getCard(String CardType, int balance) throws FactoryNullException {
		throw new FactoryNullException("User","Card");
	}
	@Override
	public Network getNetwork(String name, double sideArea) throws FactoryNullException {
		throw new FactoryNullException("User","Network");
	}
	@Override
	public Network getNetwork(String name) throws FactoryNullException {
		throw new FactoryNullException("Station","Network");
	}
}

package myVelibCore.abstractFactoryPattern;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.StationNameAlreadyUsedException;
import myVelibCore.exceptions.UserNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.stationPackage.Station;
import myVelibCore.userAndCardPackage.Card;
import myVelibCore.userAndCardPackage.User;
import myVelibCore.utilities.GPSLocation;
/**
 * <b>FactoryProducer belongs to the factory pattern</b>
 * <p>It contains methods overriden in the Factory classes that instantiate objects
 * <p>Methods used throw FactoryNullException when a wrong type of factory is used (for example a Card Factory to create a Bycicle)
 * <p>BadInstantiationException is thrown when a factory is used with a wrong input (for example an inexisting type of card)
 * <p>Exceptions are also thrown when you try to create a user, a station or a network with an already used name 
 * @author Simon Lecler
 */
public abstract class AbstractFactory {
	public abstract User getUser(String name, Network network)throws FactoryNullException, UserNameAlreadyUsedException;
	public abstract Station getStation(String stationType, GPSLocation gpsLocation, Network network, String name)throws BadInstantiationException,FactoryNullException, StationNameAlreadyUsedException;
	public abstract Card getCard(String CardType) throws BadInstantiationException,FactoryNullException; 
	public abstract Card getCard(String CardType, int balance)throws BadInstantiationException,FactoryNullException;
	public abstract Bycicle getBycicle(String BycicleType) throws BadInstantiationException,FactoryNullException;
	public abstract Network getNetwork(String name, double sideArea) throws FactoryNullException, NetworkNameAlreadyUsedException;
	public abstract Network getNetwork(String name) throws FactoryNullException, NetworkNameAlreadyUsedException;

}

package myVelibCore.userAndCardPackage;

import myVelibCore.byciclePackage.Bycicle;
import myVelibCore.exceptions.CardNoneNoBalanceException;
import myVelibCore.exceptions.UnimplementedSubclassWithoutInputException;
/**
 * Visitor pattern used to implement in a open-close way the computing of ride cost for user, using the type of card they possess and the duration of their ride
 * @author Edouard
 *
 */
public interface CardVisitor {
	//Exception are never thrown due to the construction of the visitor
	int visit(CardNone card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
	int visit(CardVLibre card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
	int visit(CardVMax card,Bycicle bycicle, int minutes) throws UnimplementedSubclassWithoutInputException;
}

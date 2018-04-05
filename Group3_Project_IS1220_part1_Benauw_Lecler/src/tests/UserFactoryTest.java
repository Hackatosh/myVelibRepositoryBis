package tests;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import myVelibCore.abstractFactoryPattern.AbstractFactory;
import myVelibCore.abstractFactoryPattern.FactoryProducer;
import myVelibCore.exceptions.BadInstantiationException;
import myVelibCore.exceptions.FactoryNullException;
import myVelibCore.exceptions.NetworkNameAlreadyUsedException;
import myVelibCore.exceptions.UserNameAlreadyUsedException;
import myVelibCore.stationPackage.Network;
import myVelibCore.userAndCardPackage.User;

class UserFactoryTest {

	@Test
	void testGetUser() throws BadInstantiationException, FactoryNullException, NetworkNameAlreadyUsedException, UserNameAlreadyUsedException {
		AbstractFactory NetworkFactory = FactoryProducer.getFactory("Network");
		Network network1 = NetworkFactory.getNetwork("testNetwork45");
		AbstractFactory UserFactory = FactoryProducer.getFactory("User");
		User user = UserFactory.getUser("John2",network1);
		User user2 = UserFactory.getUser("Jean2",network1);
		assertTrue(user.getName()== "John2");
		assertTrue(user.getId() != user2.getId());
	}

}

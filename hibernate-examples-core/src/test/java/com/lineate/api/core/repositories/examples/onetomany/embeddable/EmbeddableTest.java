package com.lineate.api.core.repositories.examples.onetomany.embeddable;

import com.lineate.api.core.domain.examples.onetomany.embeddable.Address;
import com.lineate.api.core.domain.examples.onetomany.embeddable.Shipment;
import com.lineate.api.core.domain.examples.onetomany.embeddable.User;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetomany.embeddable.EmbeddableUtils.createAddress;
import static com.lineate.api.core.domain.examples.onetomany.embeddable.EmbeddableUtils.createShipment;
import static com.lineate.api.core.domain.examples.onetomany.embeddable.EmbeddableUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForEmbeddable.class)
public class EmbeddableTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    private User user;
    private Address deliveryAddress;
    private Shipment firstShipment;
    private Shipment secondShipment;

    @BeforeEach
    public void setUp() {
        user = createUser("johnDoe");
        deliveryAddress = createAddress("street", "city");
        user.setShippingAddress(deliveryAddress);
        testEntityManager.persist(user);

        firstShipment = createShipment();
        deliveryAddress.getDeliveries().add(firstShipment);
        testEntityManager.persist(firstShipment);

        secondShipment = createShipment();
        deliveryAddress.getDeliveries().add(secondShipment);
        testEntityManager.persist(secondShipment);
    }

    @Test
    public void should_find_deliveries() {
        User user1local = userRepository.getOne(user.getId());

        assertEquals(2, user1local.getShippingAddress().getDeliveries().size());
    }
}

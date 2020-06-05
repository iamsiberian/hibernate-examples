package com.lineate.api.core.domain.examples.onetomany.embeddable;

public class EmbeddableUtils {
    public static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    public static Address createAddress(String street, String city) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        return address;
    }

    public static Shipment createShipment() {
        Shipment shipment = new Shipment();

        return shipment;
    }
}

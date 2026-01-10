package br.com.biosecure.model;

import br.com.biosecure.utils.NumberUtils;
import br.com.biosecure.utils.StringUtils;
import br.com.biosecure.utils.NotificationContext;

public record Address(String state, String city, String neighborhood, String street, int number, String postalCode) {
    public Address {
        NotificationContext notification = new NotificationContext();

        NumberUtils.validateNumericalAttribute(number, 1, "number", 99999, notification);

        StringUtils.validateString(state, 2, "state name", notification);
        StringUtils.validateString(city, 2, "city name", notification);
        StringUtils.validateString(neighborhood, 2, "neighborhood name", notification);
        StringUtils.validateString(street, 2, "street name", notification);

        StringUtils.validateString(postalCode.replace("-", ""), 8, "postal code", 8, notification);

        if (notification.hasErrors()) {
            throw new InvalidAddressException(notification.getErrors());
        }

    }

    @Override
    public String toString() {
        return "Address [state: " + state + ", city: " + city + ", neighborhood: " + neighborhood + ", street: " + street + ", number: " + number + ", postalCode: " + postalCode + "]";
    }
}
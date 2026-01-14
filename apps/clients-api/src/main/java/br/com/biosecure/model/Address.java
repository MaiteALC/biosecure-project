package br.com.biosecure.model;

import br.com.biosecure.utils.NumberUtils;
import br.com.biosecure.utils.StringUtils;
import br.com.biosecure.utils.NotificationContext;

public record Address(String state, String city, String neighborhood, String street, int number, String postalCode) {
    public Address {
        NotificationContext notification = new NotificationContext();

        final int MIN_LENGTH = 2;
        final int MAX_LENGTH = 96;

        NumberUtils.validateNumericalAttribute(number, 1, "number", 99999, notification);

        StringUtils.validateString(state, MIN_LENGTH, "state name", MAX_LENGTH, false, notification);
        StringUtils.validateString(city, MIN_LENGTH, "city name", MAX_LENGTH, false, notification);
        StringUtils.validateString(neighborhood, MIN_LENGTH, "neighborhood name", MAX_LENGTH, true, notification);
        StringUtils.validateString(street, MIN_LENGTH, "street name", MAX_LENGTH, true, notification);

        if (notification.hasErrors()) {
            throw new InvalidAddressException(notification.getErrors());
        }

    }

    @Override
    public String toString() {
        return new StringBuilder("Address = ")
                .append("[state=").append(state)
                .append(", city=").append(city)
                .append(", neighborhood=").append(neighborhood)
                .append(", street=").append(street)
                .append(", number=").append(number)
                .append(", postalCode=").append(postalCode)
                .append(']').toString();
    }
}
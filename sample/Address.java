package sample;

import java.io.Serializable;
import java.util.Formatter;

public class Address implements Serializable {

    private String city, zipCode, street, houseNumber;

    public Address(String city, String zipCode, String street, String houseNumber) {

        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;

    }

    public String getCity() {
        return this.city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public String getAddress() {
        Formatter formatter = new Formatter();
        formatter.format("%-15s| %-6s| %-15s| %-7s", this.city, this.zipCode, this.street, this.houseNumber);
        return formatter.toString();
    }

}
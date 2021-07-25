package sample;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

public class Client implements Serializable {

    private Integer clientId;
    private String firstName, secondName, pesel;
    private Address address;
    private Double accountBalance;

    public Client(Integer clientId, String firstName, String secondName, String pesel, Address address, Double accountBalance) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.pesel = pesel;
        this.address = address;
        this.accountBalance = accountBalance;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getClient() {

        Formatter formatter = new Formatter();
        formatter.format(Locale.forLanguageTag("PL"), "%-2d| %-15s| %-15s| %s| %s| %,8.2f", this.clientId, this.firstName, this.secondName, this.pesel, address.getAddress(), this.accountBalance);
        return formatter.toString();
    }

    public static String getHeader() {
        Formatter formatter = new Formatter();
        formatter.format("%-2s| %-15s| %-15s| %-11s| %-15s| %-6s| %-15s| %-7s| %-10s\n\n", "id", "Imię", "Nazwisko", "PESEL", "Miasto", "Kod", "Ulica", "Nr domu", "Stan konta");
        return formatter.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPesel() {
        return pesel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }
}

package sample;

import java.util.regex.Pattern;

public class CheckCorrectnessData {

    static String checkFirstName(String firstName) throws WrongDataFormatException {
        if (firstName.isEmpty()){
            throw new WrongDataFormatException("Imie nie moze byc puste");
        }else{
            return firstName;
        }
    }

    static String checkSecondName(String secondName) throws WrongDataFormatException {
        if (secondName.isEmpty()){
            throw new WrongDataFormatException("Nazwisko nie moze byc puste");
        }else{
            return secondName;
        }
    }

    static String checkZipCode(String zipCode) throws WrongDataFormatException {
        Pattern zipCodePattern = Pattern.compile("\\d{2}-\\d{3}");

        if (!zipCodePattern.matcher(zipCode).matches()){
            throw new WrongDataFormatException("Kod pocztowy musi miec format XX-XXX");
        }else{
            return zipCode;
        }
    }

    static String checkPESEL(String pesel) throws WrongDataFormatException {
        Pattern PESELPattern = Pattern.compile("\\d{11}");

        if (!PESELPattern.matcher(pesel).matches()){
            throw new WrongDataFormatException("PESEL musi miec format XXXXXXXXXXX");
        }else{
            return pesel;
        }
    }

    static String checkCity(String city) throws WrongDataFormatException {

        if (city.isEmpty()){
            throw new WrongDataFormatException("Miasto nie moze byc puste");
        }else{
            return city;
        }
    }

    static String checkStreet(String street) throws WrongDataFormatException {

        if (street.isEmpty()){
            throw new WrongDataFormatException("Ulica nie moze byc pusta");
        }else{
            return street;
        }
    }

    static String checkHouseNumber(String houseNumber) throws WrongDataFormatException {

        if (houseNumber.isEmpty()){
            throw new WrongDataFormatException("Nr domu nie moze byc pusty");
        }else{
            return houseNumber;
        }
    }

}

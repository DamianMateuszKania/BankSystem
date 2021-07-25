package sample;

import java.io.*;
import java.util.LinkedList;

public class ClientsManager {

    LinkedList<Client> clientLinkedList;
    final String DATABASE = "clientsDatabase";

    public ClientsManager() throws IOException, ClassNotFoundException {
        try {
            loadData(DATABASE);
        } catch (IOException e) {
            createEmptyDatabase();
        }
    }

    public void loadData(String path) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

        clientLinkedList = (LinkedList<Client>) objectInputStream.readObject();
        objectInputStream.close();

    }


    private void savaData() throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATABASE));

        objectOutputStream.writeObject(clientLinkedList);
        objectOutputStream.close();
    }

    public void createEmptyDatabase() throws IOException {
        clientLinkedList = new LinkedList<>();
        savaData();
    }

    public void testClients() throws IOException {
        clientLinkedList = new LinkedList<>();
        clientLinkedList.add(new Client(0, "Alan", "Wójcicki", "12345678901", new Address("Radomsko", "90-900", "Chrobrego", "47/40"), 0.0));
        clientLinkedList.add(new Client(1, "Marian", "Kowalski", "11111111111", new Address("Radomsko", "90-900", "Piastowska", "40"), 0.0));
        clientLinkedList.add(new Client(2, "Alojzy", "Wójcicki", "11113111111", new Address("Radomsko", "90-900", "Słonecza", "31"), 0.0));
        clientLinkedList.add(new Client(3, "Katarzyna", "Nowak", "11111111311", new Address("Łódź", "22-900", "Deszczowa", "22"), 0.0));
        clientLinkedList.add(new Client(4, "Barbara", "Kapaszka", "55111111111", new Address("Katowice", "11-900", "Brzozowa", "44"), 0.0));
        clientLinkedList.add(new Client(5, "Krzysztof", "Dzięgiel", "12211111111", new Address("Warszawa", "34-900", "Powstańców", "1"), 0.0));
        clientLinkedList.add(new Client(6, "Alojzy", "Szpak", "12345678900", new Address("Gdańsk", "55-900", "Niebieska", "222"), 0.0));
        savaData();
    }

    public void addClient(String firstName, String secondName, String pesel, String city, String zipCode, String street, String houseNumber) throws IOException, WrongDataFormatException {
        Integer newClientId;
        if (clientLinkedList.isEmpty()) {
            newClientId = 1;
        } else {
            newClientId = clientLinkedList.getLast().getClientId() + 1;
        }

        Client client = new Client(
                newClientId, CheckCorrectnessData.checkFirstName(firstName), CheckCorrectnessData.checkSecondName(secondName),
                CheckCorrectnessData.checkPESEL(pesel), new Address(CheckCorrectnessData.checkCity(city), CheckCorrectnessData.checkZipCode(zipCode), CheckCorrectnessData.checkStreet(street),
                CheckCorrectnessData.checkHouseNumber(houseNumber)), 0.0);

        clientLinkedList.add(client);
        System.out.println(client.getClient());
        savaData();

    }

    public void deleteClient(Integer clientId) throws ClientNotFoundException, IOException {

        clientLinkedList.remove(returnClientsById(clientId));
        savaData();
    }

    public String showClientsByCity(String city) throws ClientNotFoundException {
        StringBuilder listOfClients = new StringBuilder("");

        for (Client currentClient : clientLinkedList) {
            if (currentClient.getAddress().getCity().equals(city)) {
                listOfClients.append(currentClient.getClient()).append("\n");
            }
        }
        if (listOfClients.isEmpty()) {
            throw new ClientNotFoundException("Nie znaleziono klienta o takim mieście");
        } else {
            return listOfClients.toString();
        }
    }

    public String showClientsByPesel(String pesel) throws ClientNotFoundException {
        for (Client currentClient : clientLinkedList) {
            if (currentClient.getPesel().equals(pesel)) {
                return currentClient.getClient();
            }
        }
        throw new ClientNotFoundException("Nie znaleziono klienta o takim nr PESEL");
    }

    public String showClientsBySecondName(String secondName) throws ClientNotFoundException {
        StringBuilder listOfClients = new StringBuilder("");

        for (Client currentClient : clientLinkedList) {
            if (currentClient.getSecondName().equals(secondName)) {
                listOfClients.append(currentClient.getClient()).append("\n");
            }
        }
        if (listOfClients.isEmpty()) {
            throw new ClientNotFoundException("Nie znaleziono klienta o takim nazwisku");
        } else {
            return listOfClients.toString();
        }
    }

    public String showClientsByFirstName(String firstName) throws ClientNotFoundException {
        StringBuilder listOfClients = new StringBuilder("");

        for (Client currentClient : clientLinkedList) {
            if (currentClient.getFirstName().equals(firstName)) {
                listOfClients.append(currentClient.getClient()).append("\n");
            }
        }
        if (listOfClients.isEmpty()) {
            throw new ClientNotFoundException("Nie znaleziono klienta o takim imieniu");
        } else {
            return listOfClients.toString();
        }
    }

    public Client returnClientsById(Integer clientId) throws ClientNotFoundException {

        for (Client currentClient : clientLinkedList) {
            if (currentClient.getClientId().equals(clientId)) {
                return currentClient;
            }
        }
        throw new ClientNotFoundException("Nie znaleziono klienta o takim ID");
    }

    public String showAllClients() {

        StringBuilder listOfClients = new StringBuilder("");

        for (Client currentClient : clientLinkedList) {

            listOfClients.append(currentClient.getClient()).append("\n");
        }

        return listOfClients.toString();

    }

    public Client payIn(Integer clientId, Double amount) throws ClientNotFoundException, IOException, WrongDataFormatException {
        if (amount <= 0 ){
            throw new WrongDataFormatException("Wprowadzona wartosc nie moze byc niedodatnia");
        }
        Client client = returnClientsById(clientId);

        client.setAccountBalance(client.getAccountBalance() + amount);
        savaData();
        return client;
    }

    public Client payOut(Integer clientId, Double amount) throws ClientNotFoundException, NotEnoughMoneyException, IOException, WrongDataFormatException {
        if (amount <= 0 ){
            throw new WrongDataFormatException("Wprowadzona wartosc nie moze byc niedodatnia");
        }
        Client client = returnClientsById(clientId);
        Double accountBalance = client.getAccountBalance() - amount;
        if (accountBalance > 0) {
            client.setAccountBalance(accountBalance);
        } else {
            throw new NotEnoughMoneyException("Na koncie nie ma wystarczajacej kwoty pieniedzy aby dokonac operacji.");
        }
        savaData();
        return client;
    }

    public void transferMoney(Integer sourceClientId, Integer destinationClientid, Double amount) throws ClientNotFoundException, NotEnoughMoneyException, IOException, WrongDataFormatException {

        payOut(sourceClientId, amount);

        payIn(destinationClientid, amount);

    }


    public LinkedList<Client> getClientLinkedList() {
        return clientLinkedList;
    }


}

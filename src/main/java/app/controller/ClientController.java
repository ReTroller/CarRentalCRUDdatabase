package app.controller;

import app.db.ClientDAO;
import app.model.Client;
import app.model.Clients;

public class ClientController {

    private final ClientDAO clientDAO;

    public ClientController() {
        this.clientDAO = new ClientDAO();
    }

    public Clients getAllClients() {
        return clientDAO.getAllClients();
    }

    public boolean addClient(String firstName, String lastName, String middleName, String address, String phone) {
        if (firstName == null || lastName == null || phone == null ||
                firstName.isBlank() || lastName.isBlank() || phone.isBlank()) {
            return false;
        }

        Client client = new Client(0, firstName.trim(), lastName.trim(),
                middleName != null ? middleName.trim() : "",
                address != null ? address.trim() : "",
                phone.trim());

        return clientDAO.addClient(client);
    }

    public boolean deleteClient(String idStr) {
        try {
            int id = Integer.parseInt(idStr.trim());
            return clientDAO.deleteClientById(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

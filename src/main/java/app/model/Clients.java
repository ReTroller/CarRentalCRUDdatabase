package app.model;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    private final List<Client> clients = new ArrayList<>();

    public void add(Client client) {
        clients.add(client);
    }

    public List<Client> getAll() {
        return clients;
    }

    public void printAll() {
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public int size() {
        return clients.size();
    }
}

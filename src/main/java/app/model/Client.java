package app.model;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String phone;

    public Client(int id, String firstName, String lastName, String middleName, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    @Override
    public String toString() {
        return String.format("Клиент #%d: %s %s %s, адрес: %s, тел: %s",
                id, lastName, firstName, middleName, address, phone);
    }
}

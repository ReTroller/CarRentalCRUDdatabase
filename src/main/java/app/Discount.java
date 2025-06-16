package app;

public class Discount {
    private int id;
    private int value;
    private String description;

    public Discount(int id, int value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public int getId() { return id; }
    public int getValue() { return value; }
    public String getDesccription() { return description; }

    @Override
    public String toString() {
        return "Скидка #" + id + ": " + value + "%" + " " + description + " ";
    }
}


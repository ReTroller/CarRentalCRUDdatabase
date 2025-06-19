package app.model;

public class Penalty {
    private int id;
    private int value;
    private String description;

    public Penalty(int id, int value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public int getId() { return id; }
    public int getValue() { return value; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Штраф #" + id + ": " + value + "₽ — " + description;
    }
}

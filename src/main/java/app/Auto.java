package app;

public class Auto {
    private int id;
    private String brand;
    private String type;
    private String manufacture_year;
    private String cost_per_day;


    public Auto(int id, String brand, String type, String manufacture_year, String cost_per_day) {

        this.id = id;
        this.brand = brand;
        this.type = type;
        this.manufacture_year = manufacture_year;
        this.cost_per_day = cost_per_day;

    }

    public int getId() { return id; }
    public String getBrandName() { return brand; }
    public String getType() { return type; }
    public String getManufacture_year() { return manufacture_year; }
    public String getCost_per_day() { return cost_per_day; }


    @Override
    public String toString() {
        return String.format("Автомобиль #%d: %s %s %s, цена в день: %s",
                id, brand, type, manufacture_year, cost_per_day);
    }
}

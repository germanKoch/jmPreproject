package web.model;

public class Car {
    private String name;
    private String country;
    private int series;

    public Car(String name, String country, int series) {
        this.name = name;
        this.country = country;
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getSeries() {
        return series;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "name: " + name + ", " +
                "country: " + country + ", " +
                "series: " + series + ".";
    }
}

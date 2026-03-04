public class EastAsiaCountries extends Country {
    private String countryTerrain;

    // Constructor with parameters
    public EastAsiaCountries(String countryCode, String countryName, float totalArea, String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    }

    // Getter and Setter for countryTerrain
    public String getCountryTerrain() {
        return countryTerrain;
    }

    public void setCountryTerrain(String countryTerrain) {
        this.countryTerrain = countryTerrain;
    }

    // Override display method
    @Override
    public void display() {
        System.out.printf("%-15s%-25s%-15.1f%-15s\n", countryCode, countryName, totalArea, countryTerrain);
    }
}


public class Country {
    protected String countryCode;
    protected String countryName;
    protected float totalArea;

    // Constructor without parameter
    public Country() {
    }

    // Constructor with parameters
    public Country(String countryCode, String countryName, float totalArea) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.totalArea = totalArea;
    }

    // Getter and Setter methods
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(float totalArea) {
        this.totalArea = totalArea;
    }

    // Display method
    public void display() {
        System.out.printf("%-15s%-25s%-15.1f\n", countryCode, countryName, totalArea);
    }
}


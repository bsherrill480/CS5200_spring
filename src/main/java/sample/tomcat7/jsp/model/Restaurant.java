package sample.tomcat7.jsp.model;



/**
 * Created by brian on 11/11/16.
 */
public class Restaurant {
    public enum CuisineType {
        AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
    }
    private int restaurantId;
    private String name;
    private String description;
    private String menu;
    private String hours;
    private boolean active;
    private CuisineType cuisineType;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private int zip;
    private String companyName;

    public Restaurant(int restaurantId, String name, String description, String menu, String hours,
                      boolean active, CuisineType cuisineType, String street1, String street2,
                      String city, String state, int zip, String companyName) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.menu = menu;
        this.hours = hours;
        this.active = active;
        this.cuisineType = cuisineType;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.companyName = companyName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

package sample.tomcat7.jsp.model;

/**
 * Created by brian on 11/11/16.
 */
public class SitDownRestaurant extends Restaurant {
    private int capacity;

    public SitDownRestaurant(int restaurantId, String name, String description, String menu,
                             String hours, boolean active, CuisineType cuisineType, String street1,
                             String street2, String city, String state, int zip, String companyName,
                             int capacity) {
        super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

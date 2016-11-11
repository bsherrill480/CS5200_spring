package sample.tomcat7.jsp.model;

/**
 * Created by brian on 11/11/16.
 */
public class TakeOutRestaurant extends Restaurant {
    private int maxWaitTime;

    public TakeOutRestaurant(int restaurantId, String name, String description, String menu,
                             String hours, boolean active, CuisineType cuisineType, String street1,
                             String street2, String city, String state, int zip, String companyName,
                             int maxWaitTime) {
        super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
        this.maxWaitTime = maxWaitTime;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }
}

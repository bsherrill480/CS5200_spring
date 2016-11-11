package sample.tomcat7.jsp.model;

/**
 * Created by brian on 11/11/16.
 */
public class FoodCartRestaurant extends Restaurant {
    private boolean licensed;

    public FoodCartRestaurant(int restaurantId, String name, String description, String menu,
                              String hours, boolean active, CuisineType cuisineType, String street1,
                              String street2, String city, String state, int zip, String companyName,
                              boolean licensed) {
        super(restaurantId, name, description, menu, hours, active, cuisineType, street1, street2, city, state, zip, companyName);
        this.licensed = licensed;
    }

    public boolean getLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        this.licensed = licensed;
    }
}

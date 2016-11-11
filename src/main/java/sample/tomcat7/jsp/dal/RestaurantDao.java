package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Restaurant;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface RestaurantDao {
    Restaurant create(Restaurant restaurant);
    Restaurant getRestaurantById(int restaurantId);
    List<Restaurant> getRestaurantByCuisine(Restaurant.CuisineType cusine);
    List<Restaurant> getRestaurantByCompanyName(String companyName);
    Restaurant delete(Restaurant restaurant);
}

package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.FoodCartRestaurant;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface FoodCartRestaurantDao extends RestaurantDao {
    FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant);
    FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantId);
    List<FoodCartRestaurant> getFoodCartRestaurantByCompanyName(String companyName);
    FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant);
}

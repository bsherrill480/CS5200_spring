package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.TakeOutRestaurant;
import sample.tomcat7.jsp.model.TakeOutRestaurant;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface TakeOutRestaurantDao extends RestaurantDao {
    TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant);
    TakeOutRestaurant getTakeOutRestaurantById(int takeOutRestaurantId);
    List<TakeOutRestaurant> getTakeOutRestaurantByCompanyName(String companyName);
    TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant);
}

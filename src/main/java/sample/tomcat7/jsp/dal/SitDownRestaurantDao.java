package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.SitDownRestaurant;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface SitDownRestaurantDao extends RestaurantDao {
    SitDownRestaurant create(SitDownRestaurant sitDownRestaurant);
    SitDownRestaurant getSitDownRestaurantById(int sitDownRestaurantId);
    List<SitDownRestaurant> getSitDownRestaurantByCompanyName(String companyName);
    SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant);
}

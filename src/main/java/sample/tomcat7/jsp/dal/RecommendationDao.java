package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Recommendation;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface RecommendationDao {
    Recommendation create(Recommendation recommendation);
    Recommendation getRecommendationById(int recommendationId);
    List<Recommendation> getRecommendationByUserName(String userName);
    List<Recommendation> getRecommendationByRestaurantId(int restaurantId);
    Recommendation delete(Recommendation recommendation);
}

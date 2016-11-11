package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Review;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface ReviewDao {
    Review create(Review review);
    Review getReviewById(int reviewId);
    List<Review> getReviewByUserName(String userName);
    List<Review> getReviewByRestaurantId(int restaurantId);
    Review delete(Review review);
}

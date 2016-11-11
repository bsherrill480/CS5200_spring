package sample.tomcat7.jsp.model;

/**
 * Created by brian on 11/11/16.
 */
public class Recommendation {
    private int recommendationId;
    private String userName;
    private int restaurantId;

    public Recommendation(int recommendationId, String userName, int restaurantId) {
        this.recommendationId = recommendationId;
        this.userName = userName;
        this.restaurantId = restaurantId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}

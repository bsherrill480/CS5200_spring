package sample.tomcat7.jsp.model;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by brian on 11/11/16.
 */
public class Review {
    private int reviewId;
    private Timestamp created;
    private String content;
    private BigDecimal rating;
    private String userName;
    private int restaurantId;

    public Review(int reviewId, Timestamp created, String content, BigDecimal rating, String userName, int restaurantId) {
        this.reviewId = reviewId;
        this.created = created;
        this.content = content;
        this.rating = rating;
        this.userName = userName;
        this.restaurantId = restaurantId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
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

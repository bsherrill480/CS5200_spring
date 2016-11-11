package sample.tomcat7.jsp.model;

import java.sql.Timestamp;

/**
 * Created by brian on 11/11/16.
 */
public class Reservation {
    private int reservationId;
    private Timestamp start;
    private Timestamp end;
    private int size;
    private String userName;
    private int restaurantId;

    public Reservation(int reservationId, Timestamp start, Timestamp end, int size,
                       String userName, int restaurantId) {
        this.reservationId = reservationId;
        this.start = start;
        this.end = end;
        this.size = size;
        this.userName = userName;
        this.restaurantId = restaurantId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

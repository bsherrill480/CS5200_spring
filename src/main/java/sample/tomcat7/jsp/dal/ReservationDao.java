package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Reservation;

import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public interface ReservationDao {
    Reservation create(Reservation reservation);
    Reservation getReservationById(int reservationId);
    List<Reservation> getReservationByUserName(String userName);
    List<Reservation> getReservationBySitDownRestaurantId(int sitDownRestaurantId);
    Reservation delete(Reservation reservation);
}

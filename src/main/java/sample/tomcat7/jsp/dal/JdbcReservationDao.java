package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public class JdbcReservationDao extends MyJdbcDaoSupport implements ReservationDao {
    @Override
    public Reservation create(Reservation reservation) {
		String sql = "INSERT INTO Reservations (Start, End, Size, UserName, RestaurantId)" +
				" VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
		    conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, reservation.getStart());
			ps.setTimestamp(2, reservation.getEnd());
			ps.setInt(3, reservation.getSize());
			ps.setString(4, reservation.getUserName());
			ps.setInt(5, reservation.getRestaurantId());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				reservation.setReservationId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating restaurant failed, no ID obtained.");
			}
			ps.close();
		    return reservation;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    }

    @Override
    public Reservation getReservationById(int reservationId) {
		String sql = "SELECT * FROM Reservations WHERE ReservationId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reservationId);
			Reservation reservation = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				reservation = new Reservation(
						rs.getInt("ReservationId"),
						rs.getTimestamp("Start"),
						rs.getTimestamp("End"),
						rs.getInt("Size"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
			}
			rs.close();
			ps.close();
			return reservation;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
    }

    @Override
    public List<Reservation> getReservationByUserName(String userName) {
		String sql = "SELECT * FROM Reservations WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			List<Reservation> reservations = new ArrayList<Reservation>();
			if (rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("ReservationId"),
						rs.getTimestamp("Start"),
						rs.getTimestamp("End"),
						rs.getInt("Size"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
				reservations.add(reservation);
			}
			rs.close();
			ps.close();
			return reservations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
    }

    @Override
    public List<Reservation> getReservationBySitDownRestaurantId(int sitDownRestaurantId) {
		String sql = "SELECT * FROM Reservations WHERE RestaurantId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sitDownRestaurantId);
			ResultSet rs = ps.executeQuery();
			List<Reservation> reservations = new ArrayList<Reservation>();
			if (rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("ReservationId"),
						rs.getTimestamp("Start"),
						rs.getTimestamp("End"),
						rs.getInt("Size"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
				reservations.add(reservation);
			}
			rs.close();
			ps.close();
			return reservations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
    }

    @Override
    public Reservation delete(Reservation reservation) {
		String sql = "DELETE FROM Reservations WHERE ReservationId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reservation.getReservationId());
			ps.executeUpdate();
			ps.close();
			return reservation;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
    }
}

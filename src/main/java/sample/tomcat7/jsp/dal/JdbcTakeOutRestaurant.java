package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.Restaurant;
import sample.tomcat7.jsp.model.TakeOutRestaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
@Service("takeOutRestaurant")
public class JdbcTakeOutRestaurant extends JdbcRestaurantDao implements TakeOutRestaurantDao {
    /**
     *
     * @param takeOutRestaurant A restraunt that does not exist in the db.
     * @return Sitdown restaurant based on passed takeOutRestaurant
     */
    @Override
    public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) {
        Restaurant toMakeRestaurant = new Restaurant(
                0,
                takeOutRestaurant.getName(),
                takeOutRestaurant.getDescription(),
                takeOutRestaurant.getMenu(),
                takeOutRestaurant.getHours(),
                takeOutRestaurant.isActive(),
                takeOutRestaurant.getCuisineType(),
                takeOutRestaurant.getStreet1(),
                takeOutRestaurant.getStreet2(),
                takeOutRestaurant.getCity(),
                takeOutRestaurant.getState(),
                takeOutRestaurant.getZip(),
                takeOutRestaurant.getCompanyName()
        );
        Restaurant restaurant = create(toMakeRestaurant);
        String sql = "INSERT INTO TakeOutRestaurant VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurant.getRestaurantId());
            ps.setInt(2, takeOutRestaurant.getMaxWaitTime());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
            ps.close();
            return takeOutRestaurant;
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
    public TakeOutRestaurant getTakeOutRestaurantById(int takeOutRestaurantId) {
		String sql = "SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, MaxWaitTime " +
                "FROM TakeOutRestaurant " +
                "JOIN Restaurants ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE TakeOutRestaurant.RestaurantId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, takeOutRestaurantId);
			TakeOutRestaurant takeOutRestaurant = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				takeOutRestaurant = new TakeOutRestaurant(
						rs.getInt("RestaurantId"),
						rs.getString("Name"),
						rs.getString("Description"),
						rs.getString("Menu"),
						rs.getString("Hours"),
						rs.getBoolean("Active"),
						Restaurant.CuisineType.valueOf(rs.getString("CuisineType")),
						rs.getString("Street1"),
						rs.getString("Street2"),
						rs.getString("City"),
						rs.getString("State"),
						rs.getInt("Zip"),
						rs.getString("CompanyName"),
						rs.getInt("MaxWaitTime")
				);
			}
			rs.close();
			ps.close();
			return takeOutRestaurant;
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
    public List<TakeOutRestaurant> getTakeOutRestaurantByCompanyName(String companyName) {
		String sql = "SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, MaxWaitTime " +
                "FROM TakeOutRestaurant " +
                "JOIN Restaurants ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE Restaurants.CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, companyName);
			List<TakeOutRestaurant> takeOutRestaurants = new ArrayList<TakeOutRestaurant>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(
						rs.getInt("RestaurantId"),
						rs.getString("Name"),
						rs.getString("Description"),
						rs.getString("Menu"),
						rs.getString("Hours"),
						rs.getBoolean("Active"),
						Restaurant.CuisineType.valueOf(rs.getString("CuisineType")),
						rs.getString("Street1"),
						rs.getString("Street2"),
						rs.getString("City"),
						rs.getString("State"),
						rs.getInt("Zip"),
						rs.getString("CompanyName"),
						rs.getInt("MaxWaitTime")
				);
				takeOutRestaurants.add(takeOutRestaurant);
			}
			rs.close();
			ps.close();
			return takeOutRestaurants;
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
    public TakeOutRestaurant delete(TakeOutRestaurant takeOutRestaurant) {
		// DB is setup to cascade delete, just need to call super.
        super.delete(takeOutRestaurant);
        return takeOutRestaurant;
    }
}

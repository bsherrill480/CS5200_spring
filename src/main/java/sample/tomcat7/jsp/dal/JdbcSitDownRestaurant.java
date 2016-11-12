package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.Restaurant;
import sample.tomcat7.jsp.model.SitDownRestaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
@Service("sitDownRestaurant")
public class JdbcSitDownRestaurant extends JdbcRestaurantDao implements SitDownRestaurantDao {
    /**
     *
     * @param sitDownRestaurant A restraunt that does not exist in the db.
     * @return Sitdown restaurant based on passed sitDownRestaurant
     */
    @Override
    public SitDownRestaurant create(SitDownRestaurant sitDownRestaurant) {
        Restaurant toMakeRestaurant = new Restaurant(
                0,
                sitDownRestaurant.getName(),
                sitDownRestaurant.getDescription(),
                sitDownRestaurant.getMenu(),
                sitDownRestaurant.getHours(),
                sitDownRestaurant.isActive(),
                sitDownRestaurant.getCuisineType(),
                sitDownRestaurant.getStreet1(),
                sitDownRestaurant.getStreet2(),
                sitDownRestaurant.getCity(),
                sitDownRestaurant.getState(),
                sitDownRestaurant.getZip(),
                sitDownRestaurant.getCompanyName()
        );
        Restaurant restaurant = create(toMakeRestaurant);
        String sql = "INSERT INTO SitDownRestaurant VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurant.getRestaurantId());
            ps.setInt(2, sitDownRestaurant.getCapacity());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
            ps.close();
            return sitDownRestaurant;
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
    public SitDownRestaurant getSitDownRestaurantById(int sitDownRestaurantId) {
		String sql = "SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, Capacity " +
                "FROM SitDownRestaurant " +
                "JOIN Restaurants ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE SitDownRestaurant.RestaurantId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sitDownRestaurantId);
			SitDownRestaurant sitDownRestaurant = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sitDownRestaurant = new SitDownRestaurant(
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
						rs.getInt("Capacity")
				);
			}
			rs.close();
			ps.close();
			return sitDownRestaurant;
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
    public List<SitDownRestaurant> getSitDownRestaurantByCompanyName(String companyName) {
		String sql = "SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, Capacity " +
                "FROM SitDownRestaurant " +
                "JOIN Restaurants ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE Restaurants.CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, companyName);
			List<SitDownRestaurant> sitDownRestaurants = new ArrayList<SitDownRestaurant>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(
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
						rs.getInt("Capacity")
				);
				sitDownRestaurants.add(sitDownRestaurant);
			}
			rs.close();
			ps.close();
			return sitDownRestaurants;
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
    public SitDownRestaurant delete(SitDownRestaurant sitDownRestaurant) {
		// DB is setup to cascade delete, just need to call super.
        super.delete(sitDownRestaurant);
        return sitDownRestaurant;
    }
}

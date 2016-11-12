package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.Restaurant;
import sample.tomcat7.jsp.model.FoodCartRestaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
@Service("foodCartRestaurant")
public class JdbcFoodCartRestaurant extends JdbcRestaurantDao implements FoodCartRestaurantDao {
    /**
     *
     * @param foodCartRestaurant A restraunt that does not exist in the db.
     * @return Sitdown restaurant based on passed foodCartRestaurant
     */
    @Override
    public FoodCartRestaurant create(FoodCartRestaurant foodCartRestaurant) {
        Restaurant toMakeRestaurant = new Restaurant(
                0,
                foodCartRestaurant.getName(),
                foodCartRestaurant.getDescription(),
                foodCartRestaurant.getMenu(),
                foodCartRestaurant.getHours(),
                foodCartRestaurant.isActive(),
                foodCartRestaurant.getCuisineType(),
                foodCartRestaurant.getStreet1(),
                foodCartRestaurant.getStreet2(),
                foodCartRestaurant.getCity(),
                foodCartRestaurant.getState(),
                foodCartRestaurant.getZip(),
                foodCartRestaurant.getCompanyName()
        );
        Restaurant restaurant = create(toMakeRestaurant);
		foodCartRestaurant.setRestaurantId(restaurant.getRestaurantId());
        String sql = "INSERT INTO FoodCartRestaurant VALUES (?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurant.getRestaurantId());
            ps.setBoolean(2, foodCartRestaurant.getLicensed());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
            ps.close();
            return foodCartRestaurant;
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
    public FoodCartRestaurant getFoodCartRestaurantById(int foodCartRestaurantId) {
		String sql = "SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, Licensed " +
                "FROM FoodCartRestaurant " +
                "JOIN Restaurants ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE FoodCartRestaurant.RestaurantId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, foodCartRestaurantId);
			FoodCartRestaurant foodCartRestaurant = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				foodCartRestaurant = new FoodCartRestaurant(
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
						rs.getBoolean("Licensed")
				);
			}
			rs.close();
			ps.close();
			return foodCartRestaurant;
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
    public List<FoodCartRestaurant> getFoodCartRestaurantByCompanyName(String companyName) {
		String sql = "SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, " +
                "Description, Menu, Hours, Active, CuisineType, Street1, Street2, City, State, Zip, " +
                "CompanyName, Licensed " +
                "FROM FoodCartRestaurant " +
                "JOIN Restaurants ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
                "WHERE Restaurants.CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, companyName);
			List<FoodCartRestaurant> foodCartRestaurants = new ArrayList<FoodCartRestaurant>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(
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
						rs.getBoolean("Licensed")
				);
				foodCartRestaurants.add(foodCartRestaurant);
			}
			rs.close();
			ps.close();
			return foodCartRestaurants;
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
    public FoodCartRestaurant delete(FoodCartRestaurant foodCartRestaurant) {
		// DB is setup to cascade delete, just need to call super.
        super.delete(foodCartRestaurant);
        return foodCartRestaurant;
    }
}

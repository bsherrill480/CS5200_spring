package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
@Service("restaurantDao")
public class JdbcRestaurantDao extends MyJdbcDaoSupport implements RestaurantDao {
    @Override
    public Restaurant create(Restaurant restaurant) {
		String sql = "INSERT INTO Restaurants (Name, Description, Menu, Hours, Active, " +
				"CuisineType, Street1, Street2, City, State, Zip, CompanyName)" +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
		    conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getDescription());
			ps.setString(3, restaurant.getMenu());
			ps.setString(4, restaurant.getHours());
			ps.setBoolean(5, restaurant.isActive());
			ps.setString(6, restaurant.getCuisineType().name());
			ps.setString(7, restaurant.getStreet1());
			ps.setString(8, restaurant.getStreet2());
			ps.setString(9, restaurant.getCity());
			ps.setString(10, restaurant.getState());
			ps.setInt(11, restaurant.getZip());
			ps.setString(12, restaurant.getCompanyName());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				restaurant.setRestaurantId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating restaurant failed, no ID obtained.");
			}
			ps.close();
		    return restaurant;
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
    public Restaurant getRestaurantById(int restaurantId) {
		String sql = "SELECT * FROM Restaurants WHERE RestaurantId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, restaurantId);
			Restaurant restaurant = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				restaurant = new Restaurant(
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
						rs.getString("CompanyName")
				);
			}
			rs.close();
			ps.close();
			return restaurant;
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
    public List<Restaurant> getRestaurantByCuisine(Restaurant.CuisineType cusine) {
		String sql = "SELECT * FROM Restaurants WHERE CuisineType = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cusine.name());
			ResultSet rs = ps.executeQuery();
            List<Restaurant> restaurants = new ArrayList<Restaurant>();
			while(rs.next()) {
				Restaurant restaurant = new Restaurant(
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
						rs.getString("CompanyName")
				);
				restaurants.add(restaurant);
			}
			rs.close();
			ps.close();
			return restaurants;
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
    public List<Restaurant> getRestaurantByCompanyName(String companyName) {
		String sql = "SELECT * FROM Restaurants WHERE CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, companyName);
			ResultSet rs = ps.executeQuery();
            List<Restaurant> restaurants = new ArrayList<Restaurant>();
			while(rs.next()) {
				Restaurant restaurant = new Restaurant(
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
						rs.getString("CompanyName")
				);
				restaurants.add(restaurant);
			}
			rs.close();
			ps.close();
			return restaurants;
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
    public Restaurant delete(Restaurant restaurant) {
		String sql = "DELETE FROM Restaurants WHERE RestaurantId = ?";
		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, restaurant.getRestaurantId());
			ps.executeUpdate();
			ps.close();
			return restaurant;
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

/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.tomcat7.jsp.Controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.log4j.Logger;
import sample.tomcat7.jsp.dal.*;
import sample.tomcat7.jsp.model.*;

import javax.annotation.Resource;


@Controller
public class WelcomeController {

	static Logger log = Logger.getLogger(WelcomeController.class.getName());

	@Autowired
	private UserDao userDao;

	@Autowired
	private CreditCardDao creditCardDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
    @Qualifier("restaurantDao")
	private RestaurantDao restaurantDao;

	@Autowired
	@Qualifier("sitDownRestaurant")
	private SitDownRestaurantDao sitDownRestaurantDao;

	@Autowired
	@Qualifier("takeOutRestaurant")
	private TakeOutRestaurantDao takeOutRestaurantDao;

	@Autowired
	@Qualifier("foodCartRestaurant")
	private FoodCartRestaurantDao foodCartRestaurantDao;

	@Autowired
	private ReviewDao reviewDao;

//	@Autowired
//	private  RecommendationDao recommendationDao;
//
//	@Autowired
//	private ReservationDao reservationDao;

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		log.info("===========");
        log.info("Hello World");
		log.info("===========");
		model.put("time", new Date());
		model.put("message", this.message);

		// INSERTER STUFF
        User user = userExerciser();
        creditCardExerciser(user);
		Company company = companyExerciser();
		Restaurant restaurant = restaurantExerciser(company);
		SitDownRestaurant sitDownRestaurant = sitDownRestaurantExerciser(company);
		takeOutRestaurantExerciser(company);
        foodCartRestaurantExerciser(company);
        reviewExerciser(user, restaurant);
		model.put("user", user);
		return "welcome";
	}

	private User userExerciser() {
		 User bruceInit = new User(
				"bruce",
				"password",
				"Bruce",
				"Burns",
				"BB@gmail.com",
				"555-555-5555"
		);
		userDao.delete(bruceInit);
		userDao.create(bruceInit);
		return userDao.getUserByUserName("bruce");
	}

	private void creditCardExerciser(User user) {
		CreditCard creditCardInit1 = new CreditCard(
				1234123412341234L,
				new java.sql.Date(1483257600000L),
				user.getUserName()
		);
		CreditCard creditCardInit2 = new CreditCard(
				4321432143214321L,
				new java.sql.Date(1483257600000L),
				user.getUserName()
		);
        creditCardDao.delete(creditCardInit1);
		creditCardDao.delete(creditCardInit2);

		creditCardDao.create(creditCardInit1);
		creditCardDao.create(creditCardInit2);

		creditCardDao.getCreditCardByCardNumber(creditCardInit1.getCardNumber());
        creditCardDao.getCreditCardsByUserName(user.getUserName());

		creditCardDao.updateExpiration(creditCardInit1, new java.sql.Date(1483344000000L));
	}

	private Company companyExerciser() {
		Company companyInit1 = new Company(
				"CostMore",
				"Our stuff costs more"
		);
		companyDao.delete(companyInit1);
		companyDao.create(companyInit1);
		Company costMoreCompany = companyDao.getCompanyByCompanyName(companyInit1.getCompanyName());
        companyDao.updateAbout(costMoreCompany, "PLEASE BUY OUR STUFF");
		return costMoreCompany;
	}

	private Restaurant restaurantExerciser(Company company) {
		Restaurant restaurantInit1 = new Restaurant(
				0,
				"Chipotle",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName() // apparently now chipotle is owned by CostMore company
		);
		Restaurant restaurantInit2 = new Restaurant(
				0,
				"Chipotlee",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName() // apparently now chipotle is owned by CostMore company
		);
		restaurantDao.delete(restaurantInit1);
		restaurantDao.create(restaurantInit1);
		restaurantDao.getRestaurantByCompanyName(company.getCompanyName());
		restaurantDao.getRestaurantByCuisine(Restaurant.CuisineType.HISPANIC);
		return restaurantDao.getRestaurantById(restaurantInit1.getRestaurantId());
	}

	private SitDownRestaurant sitDownRestaurantExerciser(Company company) {
		SitDownRestaurant restaurantInit1 = new SitDownRestaurant(
				0,
				"Qdoba",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				12
		);
		SitDownRestaurant restaurantInit2 = new SitDownRestaurant(
				0,
				"qdobaa",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				15
		);
		sitDownRestaurantDao.delete(restaurantInit1);
		sitDownRestaurantDao.create(restaurantInit1);
		sitDownRestaurantDao.getSitDownRestaurantByCompanyName(company.getCompanyName());
		return sitDownRestaurantDao.getSitDownRestaurantById(restaurantInit1.getRestaurantId());
	}

	private TakeOutRestaurant takeOutRestaurantExerciser(Company company) {
		TakeOutRestaurant restaurantInit1 = new TakeOutRestaurant(
				0,
				"Agua Verde",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				60
		);
		TakeOutRestaurant restaurantInit2 = new TakeOutRestaurant(
				0,
				"Baja fresh",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				30
		);
		takeOutRestaurantDao.delete(restaurantInit1);
		takeOutRestaurantDao.create(restaurantInit1);
		takeOutRestaurantDao.getTakeOutRestaurantByCompanyName(company.getCompanyName());
		return takeOutRestaurantDao.getTakeOutRestaurantById(restaurantInit1.getRestaurantId());
	}

	private FoodCartRestaurant foodCartRestaurantExerciser(Company company) {
		FoodCartRestaurant restaurantInit1 = new FoodCartRestaurant(
				0,
				"burrito heaven",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				true
		);
		FoodCartRestaurant restaurantInit2 = new FoodCartRestaurant(
				0,
				"guanacos tacos",
				"Mexcan food? Eitherway it's delcious and now with less E-coli",
				"Food and stuff",
				"10-4",
				true,
				Restaurant.CuisineType.HISPANIC,
				"Here",
				"And there",
				"Seattle",
				"Wa",
				98105,
				company.getCompanyName(),
				false
		);
		foodCartRestaurantDao.delete(restaurantInit1);
		foodCartRestaurantDao.create(restaurantInit1);
		foodCartRestaurantDao.getFoodCartRestaurantByCompanyName(company.getCompanyName());
		return foodCartRestaurantDao.getFoodCartRestaurantById(restaurantInit1.getRestaurantId());
	}

	private void reviewExerciser(User user, Restaurant restaurant) {
		Review reviewInit1 = new Review(
				0,
				new java.sql.Timestamp(0L),
				"stuff",
				BigDecimal.ONE,
				user.getUserName(),
				restaurant.getRestaurantId()
		);
		Review reviewInit2 = new Review(
				0,
				new java.sql.Timestamp(0L),
				"stuff",
				BigDecimal.ONE,
				user.getUserName(),
				restaurant.getRestaurantId()
		);
		reviewDao.delete(reviewInit1);
		Review review1 = reviewDao.create(reviewInit1);
		Review review2 = reviewDao.create(reviewInit2);

		reviewDao.getReviewById(review1.getReviewId());
        reviewDao.getReviewByRestaurantId(restaurant.getRestaurantId());
		reviewDao.getReviewByUserName(user.getUserName());
	}
}

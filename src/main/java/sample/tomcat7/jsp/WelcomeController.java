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

package sample.tomcat7.jsp;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sample.tomcat7.jsp.dal.CustomerDAO;
import org.apache.log4j.Logger;
import sample.tomcat7.jsp.dal.JdbcCustomerDAO;
import sample.tomcat7.jsp.model.Customer;


@Controller
public class WelcomeController {

	static Logger log = Logger.getLogger(WelcomeController.class.getName());

	@Autowired
	private JdbcCustomerDAO jdbcCustomerDAO;

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		Customer customer = this.jdbcCustomerDAO.findByCustomerId(1);
		log.info("===============");
		log.info(this.jdbcCustomerDAO);
		log.info("===============");
		model.put("customer", customer);
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

}

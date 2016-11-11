package sample.tomcat7.jsp.dal;


import sample.tomcat7.jsp.model.Customer;

public interface CustomerDao {
    void insert(Customer customer);
    Customer findByCustomerId(int custId);
}

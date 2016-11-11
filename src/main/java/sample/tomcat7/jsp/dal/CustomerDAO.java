package sample.tomcat7.jsp.dal;


import sample.tomcat7.jsp.model.Customer;

public interface CustomerDAO {
    public void insert(Customer customer);
    public Customer findByCustomerId(int custId);
}

package springdemo.service;

import java.util.List;

import springdemo.entity.Customer;

public interface CustomerService {

    public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String customerName);
}

package springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query, sort by the last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
															Customer.class);
		
		// execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer to the db
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int customerId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve the customer using the primary key
		return currentSession.get(Customer.class, customerId);
	}


	@Override
	public void deleteCustomer(int customerId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the customer with the primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", customerId);
		
		theQuery.executeUpdate();
		
	}


	@Override
	public List<Customer> searchCustomers(String customerName) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = null;
		
		// only search by name if the customerName is not empty
		if (customerName != null && customerName.trim().length() > 0) {
			
			// search for first name or last name, case insensitive
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or "
					+ "lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + customerName.toLowerCase() + "%");
		} else {
			// the customer name is empty, so get all the customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		// execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

}

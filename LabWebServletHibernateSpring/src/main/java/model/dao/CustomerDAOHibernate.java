
package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import misc.SpringJavaConfiguration;
import model.CustomerBean;
import model.CustomerDAO;

@Repository
public class CustomerDAOHibernate implements CustomerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(SpringJavaConfiguration.class);
		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		CustomerDAO customerDao = (CustomerDAO) context.getBean("customerDAOHibernate");
		CustomerBean select = customerDao.select("Alex");
		System.out.println("select="+select);

		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	@Override
	public CustomerBean select(String custid) {
		return this.getSession().get(CustomerBean.class, custid);
	}
	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		CustomerBean temp = this.getSession().get(CustomerBean.class, custid);
		if(temp!=null) {
			temp.setPassword(password);
			temp.setEmail(email);
			temp.setBirth(birth);
			return true;
		}
		return false;
	}
}

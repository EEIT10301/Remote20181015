package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Repository;

import misc.SpringJavaConfiguration;
import model.ProductBean;
import model.ProductDAO;

@Repository
public class ProductDAOHibernate implements ProductDAO {
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

		ProductDAO productDAO = (ProductDAO) context.getBean("productDAOHibernate");
		List<ProductBean> selects = productDAO.select();
		System.out.println("selects="+selects);

		sessionFactory.getCurrentSession().getTransaction().commit();

	}

	@Override
	public ProductBean select(int id) {
		return this.getSession().get(ProductBean.class, id);
	}

	@Override
	public List<ProductBean> select() {
		return this.getSession().createQuery(
				"from model.ProductBean", ProductBean.class).setMaxResults(50).list();
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		if(bean!=null) {
			ProductBean temp = this.getSession().get(ProductBean.class, bean.getId());
			if(temp==null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public ProductBean update(String name, double price, Date make, int expire, int id) {
		ProductBean temp = this.getSession().get(ProductBean.class, id);
		if(temp!=null) {
			temp.setName(name);
			temp.setPrice(price);
			temp.setMake(make);
			temp.setExpire(expire);
		}
		return temp;
	}

	@Override
	public boolean delete(int id) {
		ProductBean temp = this.getSession().get(ProductBean.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}

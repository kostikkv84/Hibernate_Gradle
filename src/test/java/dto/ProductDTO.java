package dto;

import entity.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDTO  {
    HibernateInit hibernateInit = new HibernateInit();

public Products findById(int id){
    return HibernateInit.getSessionFactory().openSession().get(Products.class,id);
}

public List<Products> findAll(){
    List<Products> products = (List<Products>) HibernateInit.getSessionFactory().openSession().createQuery("from Products").list();
    return products;
}

public void save(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.save(product);
    tx.commit();
    session.close();
}

public void update(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.update(product);
    tx.commit();
    session.close();
}

public void delete(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.delete(product);
    tx.commit();
    session.close();
}

}


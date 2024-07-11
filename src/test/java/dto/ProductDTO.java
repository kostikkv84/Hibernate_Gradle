package dto;

import entity.Products;
import io.qameta.allure.Step;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDTO  {
    HibernateInit hibernateInit = new HibernateInit();

@Step("Находим продукт по Id")
public Products findById(int id){
    return HibernateInit.getSessionFactory().openSession().get(Products.class,id);
}

@Step("Находим все продукты")
public List<Products> findAll(){
    List<Products> products = (List<Products>) HibernateInit.getSessionFactory().openSession().createQuery("from Products").list();
    return products;
}

@Step("Сохраняем продукт")
public void save(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.save(product);
    tx.commit();
    session.close();
}

    @Step("Обновляем продукт")
public void update(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.update(product);
    tx.commit();
    session.close();
}

@Step("Удаляем продукт")
public void delete(Products product){
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    session.delete(product);
    tx.commit();
    session.close();
    }

}


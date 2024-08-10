package dto;

import entity.Products;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDTO   {

@Step("Находим продукт по Id")
public Products findById(int id){
    return HibernateInit.getSessionFactory().openSession().get(Products.class,id);
}

@Step ("Находим продукты по стоимости")
public List<Products> findPriceMoreThan(int price){
    Session session = HibernateInit.getSessionFactory().openSession();
    Query query = session.createQuery("from Products where price > " + price +  " order by id DESC");
    List<Products> products = query.getResultList();
    return products;
}

@Step("Находим все продукты в таблице Products")
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


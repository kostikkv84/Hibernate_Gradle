package dto;

import base.BaseMethods;
import entity.Products;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Уже не актуален - работа через CrudDTO
 * Класс содержит методы для работы с данными в БД
 */
public class ProductDTO extends BaseMethods {

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

@Step ("Находим продукты по стоимости")
public List<Products> findProductsOnPrice(Integer value){
    Session session = HibernateInit.getSessionFactory().openSession();
    Query query = session.createQuery("from Products where price = " + value +  " order by id DESC");
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

@Step("Удаление продукта по Id продукта")
public void deleteListProducts(List<Products> products) {
    Session session = HibernateInit.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    for (Products product : products) {
        session.delete(product);
    }
    tx.commit();
    session.close();
} // удалить список записей

}


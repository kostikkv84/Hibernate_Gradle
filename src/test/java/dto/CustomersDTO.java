package dto;

import entity.Customers;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomersDTO {

    @Step("Найти запись по Id")
    public Customers findById(int id) {
        return HibernateInit.getSessionFactory().openSession().get(Customers.class,id);
    }
    @Step("Найти запись по Name")
    public Customers findByName(String name) {
        return HibernateInit.getSessionFactory().openSession().get(Customers.class,name);
    }
    @Step("Найти все записи из таблицы Customers")
    public List<Customers> findAll() {
        List<Customers> customers = (List<Customers>) HibernateInit.getSessionFactory().openSession().createQuery("from Customers").list();
        return customers;
    }
    @Step("Найти последнюю запись из таблицы Customers")
    public Customers getLastCustomer() {
        Session session = HibernateInit.getSessionFactory().openSession();
        Query query = session.createQuery("from Customers order by idcustomer DESC limit 1");
       // query.setMaxResults(1);

        Customers last = (Customers) ((org.hibernate.query.Query<?>) query).uniqueResult();
        System.out.println(last);
        return last;
    }
    @Step("Запись в таблицу Customers")
    public void save(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(customers);
        tx.commit();
        session.close();
    }
    @Step("Удалить запись из таблицы Customers")
    public void delete(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(customers);
        tx.commit();
        session.close();
    }
    @Step("Обновить запись из таблицы Customers")
    public void update(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(customers);
        tx.commit();
        session.close();
    }

}

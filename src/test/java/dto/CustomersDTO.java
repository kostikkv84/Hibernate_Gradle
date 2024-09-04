package dto;

import base.BaseMethods;
import entity.Customers;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Уже не актуален - работа через CrudDTO
 * Класс содержит методы для работы с данными в БД
 */
public class CustomersDTO extends BaseMethods {

    @Step("Поиск покупателя по Id")
    public Customers findById(int id) {
        return HibernateInit.getSessionFactory().openSession().get(Customers.class,id);
    } // найти запись по Id

    @Step("Поиск покупателя по имени")
    public Customers findByName(String name) {
        return HibernateInit.getSessionFactory().openSession().get(Customers.class,name);
    } // найти запись по Name

    @Step("Вывести всех записи с покупателями")
    public List<Customers> findAll() {
        List<Customers> customers = (List<Customers>) HibernateInit.getSessionFactory().openSession().createQuery("from Customers").list();
        return customers;
    } // Найти все записи из таблицы Customers

    @Step("Найти последнюю запись с покупателем")
    public Customers getLastCustomer() {
        Session session = HibernateInit.getSessionFactory().openSession();
        Query query = session.createQuery("from Customers order by idcustomer DESC limit 1");
       // query.setMaxResults(1);
        Customers last = (Customers) ((org.hibernate.query.Query<?>) query).uniqueResult();
        return last;
    } // Найти последнюю запись из таблицы Customers

    @Step("Сохранение записи в таблицу Customers")
    public void save(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(customers);
        tx.commit();
        session.close();

    } // Добавить запись в таблицу Customers

    @Step("Удаление записи из таблицы Customers")
    public void delete(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(customers);
        tx.commit();
        session.close();
    } //Удалить запись из таблицы Customers

    @Step("Обновление записи в таблице Customers")
    public void update(Customers customers) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(customers);
        tx.commit();
        session.close();
    } //Обновить запись в таблице Customers */



}

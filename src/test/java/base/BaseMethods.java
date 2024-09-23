package base;

import dto.HibernateInit;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Работа с БД через дженерики
 */
public class BaseMethods {

    public enum Param{}

    @Step("Находим продукты по значению поля")
    public <T> List<T> findItemOnParamInt(Class<T> clazz, String paramName, int value){
        Session session = HibernateInit.getSessionFactory().openSession();
        List<T> items = null;

        try {
            String hql = "from " + clazz.getSimpleName() + " where " + paramName + " = :value";
            Query query = session.createQuery(hql, clazz);
            query.setParameter("value", value);
            items = query.getResultList();
        } finally {
            session.close(); // Закрываем сессию
        }

        return items;
    }

    @Step ("Находим продукты по значению поля")
    public <T> List<T> findItemOnParam(Class<T> clazz, String paramName, String value){
        Session session = HibernateInit.getSessionFactory().openSession();
        List<T> items = null;

        try {
            String hql = "from " + clazz.getSimpleName() + " where " + paramName + " = :value";
            Query query = session.createQuery(hql, clazz);
            query.setParameter("value", value);
            items = query.getResultList();
        } finally {
            session.close(); // Закрываем сессию
        }

        return items;
    }

    @Step("Сохранение записи в указанную таблицу ")
    public <T> void save(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(clazz);
        tx.commit();
        session.close();

    }

    @Step("Удаление записи из таблицы. ")
    public <T> void delete(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(clazz);
        tx.commit();
        session.close();
    } //Удалить запись из таблицы Customers

    @Step("Обновление записи в таблице Customers")
    public <T> void update(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(clazz);
        tx.commit();
        session.close();
    } //Обновить запись в таблице Customers

}

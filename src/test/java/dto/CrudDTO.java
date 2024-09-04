package dto;

import base.BaseMethods;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CrudDTO extends BaseMethods {

  //  @Step("Находим продукты по значению поля")
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

 //   @Step ("Находим продукты по значению поля")
    public <T> List<T> findItemOnParamStr(Class<T> clazz, String paramName, String value){
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

    // @Step("Найти последнюю запись с покупателем")
    public <T> T getLastItem(Class<T> clazz, String orderByField) {
        Session session = HibernateInit.getSessionFactory().openSession();
        T last = null;
        try {
            // Формируем HQL-запрос
            String hql = "FROM " + clazz.getSimpleName() + " ORDER BY " + orderByField + " DESC";

            Query query = session.createQuery(hql, clazz);
            query.setMaxResults(1);

            last = (T) ((org.hibernate.query.Query<?>) query).uniqueResult();
        } finally {
            session.close(); // Закрываем сессию в блоке finally
        }

        return last;
    }

   // @Step("Вывести всех записи из таблицы")
    public <T> List<T> findAll(Class<T> clazz) {
        List<T> items = (List<T>) HibernateInit.getSessionFactory().openSession().createQuery("from " + clazz.getSimpleName()).list();
        return items;
    } // Найти все записи из таблицы

 //   @Step("Сохранение записи в указанную таблицу ")
    public <T> void save(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(clazz);
        tx.commit();
        session.close();

    }

 //   @Step("Удаление записи из таблицы. ")
    public <T> void delete(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(clazz);
        tx.commit();
        session.close();
    } //Удалить запись из таблицы Customers

   // @Step("Обновление записи в таблице Customers")
    public <T> void update(T clazz) {
        Session session = HibernateInit.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(clazz);
        tx.commit();
        session.close();
    } //Обновить запись в таблице Customers






}

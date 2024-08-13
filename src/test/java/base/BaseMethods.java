package base;

import dto.HibernateInit;
import io.qameta.allure.Step;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class BaseMethods {

    public enum Param{}

    @Step("Находим продукты по значению поля")
    public <T> List<T> findProductsOnParamInt(Class<T> clazz, String paramName, int value){
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
    public <T> List<T> findProductsOnParamStr(Class<T> clazz, String paramName, String value){
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

}

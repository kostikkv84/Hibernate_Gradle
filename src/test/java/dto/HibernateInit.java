package dto;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

// Фабрика для создания сессий
public class HibernateInit {
    public static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Метод для вызова SessionFactory
    public static SessionFactory getSessionFactory (){
        if (sessionFactory == null){
            initSessionFactory();
        }
        return sessionFactory;
    }

    // Закрываем все соединения
    public static void shotDown() {
        getSessionFactory().close();
    }


}

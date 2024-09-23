package steps;

import dto.HibernateInit;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import org.hibernate.Session;

public class Hooks {
    private static Session session;


    @BeforeAll
    public static void initSession(){
        session = HibernateInit.getSessionFactory().openSession();
    }

    @After
    public static void closeSession(){
        HibernateInit.getSessionFactory().close();
    }


}

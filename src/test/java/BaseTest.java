import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.HibernateInit;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static Session session;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    ObjectMapper objectM = new ObjectMapper();

    public static Session getSession() {
        return session;
    }

    @BeforeAll
    public static void initSession(){
        session = HibernateInit.getSessionFactory().openSession();
    }

    @AfterAll
    public static void closeSession(){
        HibernateInit.getSessionFactory().close();
    }
}

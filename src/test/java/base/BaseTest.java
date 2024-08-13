package base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.HibernateInit;
import io.qameta.allure.Step;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private static Session session;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    public static ObjectMapper objectM = new ObjectMapper();
    static AllureAttachment attachment = new AllureAttachment();

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


    /**
     * Метод для получения объекта определенного класса из файла.
     *
     * @param clazz класс объекта, который необходимо получить
     * @param str путь к файлу, из которого нужно считать объект
     * @return объект указанного класса, считанный из файла
     * @throws IOException если возникают проблемы при чтении файла
     */
    @Step("Создан заполненный данными Entity")
    public static <T> T getEntity(Class<T> clazz, String str) throws IOException {
        //System.out.println("Создан класс - " + clazz.getName());
        File file = new File(str);
        T entity = objectM.readValue(file, clazz);
        attachment.attachObjectToAllureReport(clazz);
        return entity;
    }


}

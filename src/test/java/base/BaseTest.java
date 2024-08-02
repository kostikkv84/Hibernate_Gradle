package base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.HibernateInit;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {
    private static Session session;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    public static ObjectMapper objectM = new ObjectMapper();

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
        return entity;
    }

    /**
     * Allure - attachments
     */

    /**
     * Метод для прикрепления файла к отчету Allure.
     *
     * @param name     Название файла.
     * @param filePath Путь к файлу.
     * @return Массив байтов файла.
     * @throws IOException Если произошла ошибка при чтении файла.
     */
    @Attachment(value = "Attachment: {0}", type = "text/plain")
    public static byte[] attachFile(String name, String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * Метод для прикрепления объекта к отчету Allure.
     *
     * @param object Объект, который нужно прикрепить к отчету.
     */
    public static void attachObjectToAllureReport(Object object) {
        String objectAsString = object.toString();
        Allure.addAttachment("Object Details", objectAsString);
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/jsonFiles", resourceName));
    }

    /**
     * Текст к отчету
     * @param message
     * @return
     */
    @Attachment(value = "Expected result", type = "text/html")
    public static String expectedToReport(String message) {
        return message;
    }
    @Attachment(value = "Actual result", type = "text/html")
    public static String actualToReport(String message) {
        return message;
    }

}

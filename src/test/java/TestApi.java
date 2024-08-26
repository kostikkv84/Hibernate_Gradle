import base.BaseApiMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojoClassesJson.*;

import java.io.File;
import java.io.IOException;

import static specApi.Specification.*;

public class TestApi extends BaseApiMethods {
    private String URL = "https://qa-scooter.praktikum-services.ru";
    private String ReqURL = "https://reqres.in";

    ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(TestApi.class);

    /**
     *  тест ошибки логина - Samokat
     */
    @Test
    public void courierAuthError() throws IOException {
        installSpecification(requestSpec(URL), specResponseCode(404));
        File file = new File("src/test/jsonFilesApi/courierJsonResponse.json");
        CourierRequest courier = objectMapper.readValue(file, CourierRequest.class);
        CourierError error = new CourierError();

        Assertions.assertEquals("Учетная запись не найдена", error.getCourierResponse(URL, courier).getMessage());
    }
    @Test
    public void courierAuthSuccess() throws IOException {
        installSpecification(requestSpec(URL), specResponseCode(200));
        File file = new File("src/test/jsonFilesApi/courierJsonResponse.json");
        CourierRequest courier = objectMapper.readValue(file, CourierRequest.class);

        Assertions.assertEquals("12345", CourierResponse.getCourierResponse(URL, courier).getId());
    }

/*******************************************************************************
    /**
     * Полечение списка пользователей и проверка email.
     * Используется универсальный метод.
     */
    @Test
    public void checkEmails() {
        BaseApiMethods req = new BaseApiMethods();
        req.getListItems(ReqresInData.class, ReqURL, "/api/users", "page", "2").stream().forEach(x -> Assertions.assertTrue(x.getEmail().contains("@reqres.in"), "Окончание email не соответствует ожидаемому"));
        logger.info("Успешная проверка Emails");
    }

    /**
     * Проверка наличия записи. Через универсальный метод.
     */
    @Test
    public void checkUserData() {
        BaseApiMethods req = new BaseApiMethods();
        Assertions.assertEquals("Janet",req.getItem(ReqresInData.class, ReqURL, "/api/users/", "2").getFirst_name() );

        logger.info("Успешная проверка Emails");
    }

    /**
     * Создание новой записи. Используется универсальный метод
     * @throws IOException
     */
    @Test
    public void addUser() throws IOException {
        AddUserRequest user = AddUserRequest.builder().name("morpheus").job("leader").build();
        Assertions.assertEquals(createItem(AddUserResponse.class, user, ReqURL, "/api/users").getName(), "morpheus");
    }

    /**
     * Полное обновление записи. Используется универсальный метод
     * @throws IOException
     */
    @Test
    public void putUser() throws IOException {
        AddUserRequest user = AddUserRequest.builder().name("morpheus").job("zion resident").build();
        Assertions.assertEquals(putItem(AddUserResponse.class, user, ReqURL, "/api/users/", 2).getJob(), "zion resident");
    }

    /**
     * Полное обновление записи. Используется универсальный метод
     * @throws IOException
     */
    @Test
    public void patchUser() throws IOException {
        installSpecification(requestSpec(URL), specResponseCode(200));
        AddUserRequest user = AddUserRequest.builder().name("morpheus").job("zion President").build();
        Assertions.assertEquals(patchItem(AddUserResponse.class, user, ReqURL, "/api/users/", 2).getJob(), "zion President");
    }

    /**
     * Проверка наличия записи. Через универсальный метод.
     */
    @Test
    public void deleteUser() {
        BaseApiMethods req = new BaseApiMethods();
        req.deleteItem(ReqURL, "/api/users/", 2);
        Assertions.assertEquals(null, req.getItem(ReqresInData.class, ReqURL, "/api/users/", "2").getFirst_name() );
        logger.info("Успешная проверка Emails");
    }

}

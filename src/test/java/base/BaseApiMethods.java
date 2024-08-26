package base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

import java.util.List;

import static base.BaseXml.allureAttachment;
import static io.restassured.RestAssured.given;
import static specApi.Specification.*;

public class BaseApiMethods extends AllureAttachment{
   // Specification specification = new Specification();

    /**
     * Получение списка записей по параметрам.
     * @param clazz
     * @param url
     * @param endpoint
     * @param paramName
     * @param paramValue
     * @return
     * @param <T>
     */
    @Step
    public <T> List<T> getListItems(Class<T> clazz, String url, String endpoint, String paramName, String paramValue) {
        installSpecification(requestSpec(url), specResponseCode(200));
        List<T> userData = RestAssured.given()
                .param(paramName, paramValue)
                .when()
                .get(url + endpoint)
                .then()
                // .log().all()
                .extract().body().jsonPath().getList("data", clazz);

        allureAttachment.attachListOfObjectToAllureReport(userData); // метод должен поддерживать работу с T

        return userData;
    }
    /**
     * Получение записи. Универсальный метод.
     * @param clazz
     * @param url
     * @param endpoint
     * @param param
     * @return
     * @param <T>
     */
    @Step
    public <T> T getItem(Class<T> clazz, String url, String endpoint, String param) {
        installSpecification(requestSpec(url), specResponseCode(201));
        T userData = RestAssured.given()
                .when()
                .get(url + endpoint + param)
                .then()
                .log().all()
                .extract().body().as(clazz);

        allureAttachment.attachObjectToAllureReport(userData); // метод должен поддерживать работу с T

        return userData;
    }
    /**
     * СОздание новой записи
     * @param clazz
     * @param requestBody
     * @param url
     * @param endpoint
     * @return
     * @param <T>
     * @param <A>
     */
    public <T, A> T createItem(Class<T> clazz, A requestBody,String url, String endpoint) {
        installSpecification(requestSpec(url), specResponseCode(201));
        T response = given()
                .body(requestBody)
                .when()
                .post(url + endpoint)
                .then()
               // .log().all()
                .extract().as(clazz);
        allureAttachment.attachObjectToAllureReport(requestBody);
        allureAttachment.attachObjectToAllureReport(response); // метод должен поддерживать работу с T
        return response;
    }

    /**
     * Вставка измененной записи целиком
     * @param clazz - Указываем любой класс Response
     * @param requestBody - Вставляем тело запроса
     * @param url - URL сервера
     * @param endpoint - указываем сервис - endpoin
     * @perem id - указываем id меняемой записи
     * @return
     * @param <T>
     * @param <A>
     */
    public <T, A> T putItem(Class<T> clazz, A requestBody,String url, String endpoint, Integer id) {
        installSpecification(requestSpec(url), specResponseCode(200));
        T response = given()
                .body(requestBody)
                .when()
                .put(url + endpoint + id)
                .then()
               // .log().all()
                .extract().body().as(clazz);
        allureAttachment.attachObjectToAllureReport(requestBody);
        allureAttachment.attachObjectToAllureReport(response);
        return response;
    }

    /**
     * Вставка частично измененной записи.
     * @param clazz
     * @param requestBody
     * @param url
     * @param endpoint
     * @return
     * @param <T>
     * @param <A>
     */
    public <T, A> T patchItem(Class<T> clazz, A requestBody,String url, String endpoint, Integer id) {
        T response = given()
                .body(requestBody)
                .when()
                .patch(url + endpoint + id)
                .then()
                // .log().all()
                .extract().body().as(clazz);
        allureAttachment.attachObjectToAllureReport(requestBody);
        allureAttachment.attachObjectToAllureReport(response);
        return response;
    }
    /**
     * Удаление нужной записи, по Id
     * @param url
     * @param endpoint
     * @param id
     */
    public void deleteItem(String url, String endpoint, Integer id) {
        installSpecification(requestSpec(url), specResponseCode(204));
        given()
                .when()
                .delete(url + endpoint + id)
                .then()
                .log().all();
    }



}

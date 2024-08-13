package specApi;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Спецификация для API тестов. Обработчики ответов за запрос.
 */

public class Specification {
    public static RequestSpecification requestSpec(String url){
        RestAssured.authentication = RestAssured.basic("admin", "admin");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification specResponse200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification specResponse400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    @Step("Проверка кода ответа")
    // Ответ спецификация на  код
    public static ResponseSpecification specResponseCode(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    @Step("Вызов спецификации")
    // Вызов спецификации.
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}

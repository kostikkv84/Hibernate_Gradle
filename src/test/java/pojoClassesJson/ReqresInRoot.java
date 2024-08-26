package pojoClassesJson;

import base.AllureAttachment;
import io.qameta.allure.Step;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReqresInRoot {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private ArrayList<ReqresInData> data;
    private Support support;

    AllureAttachment allureAttachment = new AllureAttachment();

    @Step
    public List<ReqresInData> getUserData(String url){
        List<ReqresInData> userData = new ArrayList<>();
        userData = given()
                .param("page", "2")
                .when()
                .get(url + "/api/users")
                .then()
              // .log().all()
                .extract().body().jsonPath().getList("data", ReqresInData.class);
       // System.out.println(userData);
        allureAttachment.attachListOfObjectToAllureReport(userData); // надо привести к ToString

        return userData;
    }



}

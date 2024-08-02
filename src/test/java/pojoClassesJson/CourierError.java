package pojoClassesJson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierError {
    private Integer code;
    private String message;


    public static CourierError getCourierResponse(String url, CourierRequest courier) {
        CourierError response = given()
                .body(courier)
                .when()
                .post(url + "/api/v1/courier/login")
                .then().log().all()
                .extract().as(CourierError.class);
        return response;
    }
}

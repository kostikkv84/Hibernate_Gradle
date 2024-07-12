package pojoClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierResponse {
    private int id;

    public static CourierResponse getCourierResponse(String url, CourierRequest courier) {
        CourierResponse response = given()
                .body(courier)
                .when()
                .post(url + "/api/v1/courier/login")
                .then().log().all()
                .extract().as(CourierResponse.class);
        return response;
    }
}

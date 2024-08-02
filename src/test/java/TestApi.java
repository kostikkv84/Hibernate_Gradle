import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojoClassesJson.CourierError;
import pojoClassesJson.CourierRequest;
import pojoClassesJson.CourierResponse;

import java.io.File;
import java.io.IOException;

import static spec.Specification.*;

public class TestApi {
    private String URL = "https://qa-scooter.praktikum-services.ru";

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  тест ошибки логина
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

    

}

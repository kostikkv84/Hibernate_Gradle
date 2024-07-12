import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojoClasses.CourierRequest;
import pojoClasses.CourierResponse;

import java.io.File;
import java.io.IOException;

import static spec.Specification.*;

public class TestApi {
    private String URL = "https://qa-scooter.praktikum-services.ru/docs/#api-Courier";

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void courierAuthSuccess() throws IOException {
        installSpecification(requestSpec(URL), specResponseCode(405));
        File file = new File("src/test/jsonFilesApi/courierJsonResponse.json");
        CourierRequest courier = objectMapper.readValue(file, CourierRequest.class);

        Assertions.assertEquals("12345", CourierResponse.getCourierResponse(URL, courier).getId());
    }



}

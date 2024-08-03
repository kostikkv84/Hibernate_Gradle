package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;
import pojoClassesXml.Company;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseXml
{
    private static ObjectMapper objectMapper = new XmlMapper();

    /**
     * XML валидатор по XSD
     * @param xmlPath
     * @param xsdPath
     * @return
     */
    @Step("Валидация XML по XSD")
    public static String xmlXSDvalidation(String xmlPath, String xsdPath){
        String result = "";
        try {
            String xml = Files.readString(Path.of(xmlPath));
           // System.out.println("Validate XML against XSD Schema");
           // System.out.println(xml);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));

            result = ("Validation is successful");
        } catch (IOException e) {
            // handle exception while reading source
        } catch (SAXException e) {
            result = ("Error when validate XML against XSD Schema");
            System.out.println("Message: " + e.getMessage());
        }
        return result;
    }


    /**
     * Преобразует содержимое XML-файла в объект указанного класса.
     *
     * <p>Этот метод читает XML-файл по указанному пути, десериализует его содержимое
     * и возвращает объект указанного типа.</p>
     *
     * @param clazz класс, в который будет преобразовано содержимое XML
     * @param str путь к XML-файлу
     * @param <T> тип объекта, в который будет преобразовано содержимое XML
     * @return объект указанного класса, созданный на основе содержимого XML-файла
     * @throws IOException если произошла ошибка при чтении файла или десериализации
     */
    @Step("Десериализация их XML файла в Pojo объект")
    public static <T> T getXmlPojo(Class<T> clazz, String str) throws IOException {
        // Чтение из XML и преобразование в POJO
        T pojo = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get(str)), StandardCharsets.UTF_8),
                clazz
        );
        return pojo;
    }

    @Step("Проверяем name в записи Pojo объекта")
    public static void assertCompanyEmployeeName(String expectedName, String xmlFilePath, int i) throws IOException {
        String actualName = getXmlPojo(Company.class, xmlFilePath).getEmployees().getEmployee().get(i).name;
        System.out.println("Expected: " + expectedName);
        System.out.println("Actual: " + actualName);
        Assertions.assertEquals(expectedName, actualName);
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

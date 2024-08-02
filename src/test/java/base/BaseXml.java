package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

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
    public static <T> T getXmlPojo(Class<T> clazz, String str) throws IOException {
        // Чтение из XML и преобразование в POJO
        T pojo = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get(str)), StandardCharsets.UTF_8),
                clazz
        );
        return pojo;
    }



}

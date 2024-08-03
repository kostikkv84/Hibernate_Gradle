import base.BaseXml;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import pojoClassesXml.Company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestXml extends BaseXml {

    @Test
    public void xmlValidateOnXSD() throws IOException, SAXException {
        Assertions.assertEquals("Validation is successful", xmlXSDvalidation("src/test/xml/xmlFile.xml", "src/test/xml/xsdFile.xsd"));
    }


    // Проверка на прямую
   @Test
    public void assertNameFieldXml() throws IOException {
        Assertions.assertEquals("John Doe", getXmlPojo(Company.class, "src/test/xml/xmlFile.xml").getEmployees().getEmployee().get(0).name);
    }

    //проверка через отдельный метод ассершион
    @Test
    public void assertNameFieldXml1() throws IOException {
            assertCompanyEmployeeName("John Doe", "src/test/xml/xmlFile.xml", 0);
    }


    @Test
    @Step("Создание xml из Pojo объекта")
    public void pojoToXMLFile() throws IOException, SAXException {
        ObjectMapper objectMapper = new XmlMapper();
        // Reads from XML and converts to POJO
        Company employee = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("src/test/xml/xmlFile.xml")), StandardCharsets.UTF_8),
                Company.class);
        System.out.println(employee);
        // Reads from POJO and converts to XML
        objectMapper.writeValue(new File("src/test/xml/xmlFile_new.xml"), employee);

        attachFile("xmlFile_new.xml", "src/test/xml/xmlFile_new.xml");
    }
}

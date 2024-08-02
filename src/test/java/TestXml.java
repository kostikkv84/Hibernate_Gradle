import base.BaseXml;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

    @Test
    public void getXml() throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        // Reads from XML and converts to POJO
        Company company = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("src/test/xml/xmlFile.xml")), StandardCharsets.UTF_8),
                Company.class);
        System.out.println(company);

       Assertions.assertEquals("John Doe", company.getEmployees().getEmployee().get(0).name);
    }

    @Test
    public void pojoToXMLFile() throws IOException, SAXException {
        ObjectMapper objectMapper = new XmlMapper();
        // Reads from XML and converts to POJO
        Company employee = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("src/test/xml/xmlFile.xml")), StandardCharsets.UTF_8),
                Company.class);
        System.out.println(employee);

        // Reads from POJO and converts to XML
        objectMapper.writeValue(new File("src/test/xml/xmlFile_new.xml"), employee);
    }
}

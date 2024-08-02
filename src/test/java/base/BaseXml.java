package base;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseXml
{
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



}

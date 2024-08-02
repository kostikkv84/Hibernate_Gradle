package pojoClassesXml;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    public int id;
    @JacksonXmlProperty(localName = "name")
    public String name;
    @JacksonXmlProperty(localName = "position")
    public String position;
    @JacksonXmlProperty(localName = "department")
    public String department;


}

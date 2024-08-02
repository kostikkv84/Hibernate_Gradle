package pojoClassesXml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "company")
public class Company {
    @JacksonXmlElementWrapper(localName = "employees", useWrapping = false)
    public Employees employees;


}

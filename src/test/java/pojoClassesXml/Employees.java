package pojoClassesXml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "employees")
public class Employees {
    @JacksonXmlElementWrapper(localName = "employee", useWrapping = false)
    public List<Employee> employee;
}

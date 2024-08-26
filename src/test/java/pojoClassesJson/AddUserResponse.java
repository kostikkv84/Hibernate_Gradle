package pojoClassesJson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserResponse {
    public String name;
    public String job;
    public String id;
    public Date createdAt;


}

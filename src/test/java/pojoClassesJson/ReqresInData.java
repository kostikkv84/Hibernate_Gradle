package pojoClassesJson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresInData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SomeTests {

    @Test
    @Disabled
    public void summ(){
        int a = 5;
        int b = 5;
        Assertions.assertTrue(a==b);
    }

}

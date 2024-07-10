package base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      /*  Allure.getLifecycle().addAttachment(
                "исходный json", "test/jsonFiles", new File("test/jsonFiles/" + file)
        );*/
    }

}

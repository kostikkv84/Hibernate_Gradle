package base;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AllureAttachment {

    /**
     * Allure - attachments
     */

    /**
     * Метод для прикрепления файла к отчету Allure.
     *
     * @param name     Название файла.
     * @param filePath Путь к файлу.
     * @return Массив байтов файла.
     * @throws IOException Если произошла ошибка при чтении файла.
     */
    @Attachment(value = "Attachment: {0}", type = "text/plain")
    public static byte[] attachFile(String name, String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * Метод для прикрепления объекта к отчету Allure.
     *
     * @param object Объект, который нужно прикрепить к отчету.
     */
    public static void attachObjectToAllureReport(Object object) {
        String objectAsString = object.toString();
        Allure.addAttachment("Object Details", objectAsString);
    }

    /**
     * Метод для прикрепления списка объектов к отчету Allure.
     */
    public static <T> void attachListOfObjectToAllureReport(List<T> objects) {
        /* List<T> object = new ArrayList<Object>(); */
        Allure.addAttachment("Object Details", objects.toString());
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/jsonFiles", resourceName));
    }

    /**
     * Текст к отчету
     * @param message
     * @return
     */
    @Attachment(value = "Expected result", type = "text/html")
    public static String expectedToReport(String message) {
        return message;
    }
    @Attachment(value = "Actual result", type = "text/html")
    public static String actualToReport(String message) {
        return message;
    }


}

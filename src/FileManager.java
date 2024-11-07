
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    public static List<String> readFile(String filename) {

        try {
            Path path = Path.of(filename);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла");
        }
    }


    public static void writeFile(String filename, String text) {
        try {
            Path path = Path.of(filename);
            Files.writeString(path, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

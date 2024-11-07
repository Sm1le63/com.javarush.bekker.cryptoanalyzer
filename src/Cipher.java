import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Cipher {

    public static final char[] ALPHABET_RU = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л',
            'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э',
            'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};  // length = 42


    public static String crypt(String text, int key) {
        StringBuilder sb = new StringBuilder();

        if (key > ALPHABET_RU.length) {
            key %= ALPHABET_RU.length;
        }
        if (key < 0) {
            key %= -ALPHABET_RU.length;
            key += ALPHABET_RU.length;
        }


        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < ALPHABET_RU.length; j++) {
                if (text.charAt(i) == ALPHABET_RU[j]) {
                    if (j + key < ALPHABET_RU.length) {
                        sb.append(ALPHABET_RU[j + key]);
                    } else {
                        sb.append(ALPHABET_RU[(j + key) % ALPHABET_RU.length]);
                    }
                }
            }
        }
        return sb.toString();
    }


    public static String decrypt(String text, int key) {
        return crypt(text, -key);
    }

    public static void brutForce(String text, String path) {
        try {
            if (!Files.exists(Path.of(path))) {
                Files.createDirectory(Path.of(path));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Integer i = 0; i < ALPHABET_RU.length; i++) {
            String s = fileToString(text, i.toString());

            FileManager.writeFile(path + "/" + (i + 1) + ".txt", s);
        }
    }

    public static String fileToString(String file, String key) {
        List<String> list = FileManager.readFile(file.toString());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);
            String cryptText = Cipher.crypt(line, Integer.parseInt(key));
            sb.append(cryptText).append("\n");
        }
        return sb.toString();
    }
}






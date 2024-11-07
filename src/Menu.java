
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Validator validator = new Validator();

        System.out.println("Выберите действие");
        System.out.print("1. Шифрование\n");
        System.out.print("2. Расшифровка с помощью ключа\n");
        System.out.print("3. Расшифровка с помощью brute force\n");
        System.out.print("0. Выход\n");


        switch (console.nextInt()) {
            case 1: {
                console.nextLine();

                System.out.println("Введите путь к файлу для шифрования");
                String file = console.nextLine();
                System.out.println("Введите ключ (целое число) для шифрования");
                String key = console.nextLine();
                System.out.println("Введите путь для нового файла");
                String cryptedFile = console.nextLine();

                if (validator.isFileExists(file) && validator.isFileNotEmpty(file) && validator.isValidKey(key)) {

                    List<String> list = FileManager.readFile(file);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        String line = list.get(i);
                        String cryptText = Cipher.crypt(line, Integer.parseInt(key));
                        sb.append(cryptText).append("\n");
                    }
                    FileManager.writeFile(cryptedFile, Cipher.fileToString(file, key));
                }
                System.out.println("Файл зашифрован и записан по указанному пути");
                break;
            }

            case 2: {
                console.nextLine();

                System.out.println("Введите путь зашифрованного файла");
                String text = console.nextLine();
                System.out.println("Введите ключ (целое число) для расшифровки");
                String key = console.nextLine();
                System.out.println("Введите путь для нового файла");
                String deCryptedFile = console.nextLine();

                if (validator.isFileExists(text) && validator.isFileNotEmpty(text) && validator.isValidKey(key)) {
                    List<String> list = FileManager.readFile(text);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        String line = list.get(i);
                        String decryptText = Cipher.decrypt(line, Integer.parseInt(key));
                        sb.append(decryptText).append("\n");
                    }
                    FileManager.writeFile(deCryptedFile, sb.toString());
                }
                System.out.println("Файл расшифрован и записан по указанному пути");
                break;
            }

            case 3: {
                console.nextLine();

                System.out.println("Введите путь файла для расшифровки");
                String path = console.nextLine();
                System.out.println("Введите директорию для расшифрованных файлов");
                String newPath = console.nextLine();

                if (validator.isFileExists(path) && validator.isFileNotEmpty(path)) {
                    Cipher.brutForce(path, newPath);
                }
            }
            System.out.println("Расшифрованные файлы записаны в указанную директорию");
            break;
            case 0:
                System.exit(0);
                System.out.println("Завершаем работу программы");
        }
    }
}






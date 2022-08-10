import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "..\\src\\main\\resources\\airports.csv";

        if(args.length == 1) {
            Storage storage = new Storage(path, Integer.parseInt(args[0]));
            Scanner console = new Scanner(System.in);

            while (true) {
                System.out.println("Введите строку:");
                String consoleInput = console.nextLine();

                if (consoleInput.equals("!quit")) {
                    System.out.println("Программа завершена");
                    System.exit(200);
                }

                long startTime = System.currentTimeMillis();
                TreeMap<String, String> result = storage.searchOnColumn(consoleInput);
                long endTime = System.currentTimeMillis();

                if (result.size() > 0) {
                    for (String key : result.keySet()) {
                        System.out.println("\"" + key + "\" [" + result.get(key) + "]");
                    }
                }

                System.out.print("Количество: " + result.size());
                System.out.println(" Поиск: " + (endTime - startTime) + " мс");
            }
        } else {
            System.out.println("Не верное количество аргументов для запуска!\r\nПравильное использование: \r\njava -Xmx7m -jar untitled-1.0-SNAPSHOT.jar 2");
        }
    }
}

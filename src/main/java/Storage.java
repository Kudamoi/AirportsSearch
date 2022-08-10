import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.TreeMap;

public class Storage {
    private int column;
    private final HashMap<String, String> storage = new HashMap<>();

    public Storage(String path, int column) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            boolean checker = true;
            String line = br.readLine();

            while (line != null) {
                String[] arrRow = line.split(",");

                if(checker) {
                    if (arrRow.length + 1 >= column && column > 0) {
                        this.column = column;
                    } else {
                        System.out.println("По данной колонке невозможно совершить поиск!");
                        System.exit(400);
                    }
                    checker = false;
                }

                for (int i = 0; i < arrRow.length; i++) {
                    arrRow[i] = arrRow[i].replaceAll("\"([^\"]*)\"", "$1");
                    storage.put(arrRow[this.column - 1], line);
                }

                line = br.readLine();
            }
        }
    }

    public TreeMap<String, String> searchOnColumn(String prefix) {
        TreeMap<String, String> result = new TreeMap<>();

        for (String key : this.storage.keySet()) {
            if (key.startsWith(prefix)) {
                result.put(key, this.storage.get(key));
            }
        }

        return result;
    }
}

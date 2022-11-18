import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    Scanner scanner;
    public FileReader(String fileName) throws IOException {
        this.scanner = new Scanner(new File(fileName));
    }

    public void close() {
        scanner.close();
    }

    public List<String> getListOfAnswers(){
        List<String> list = new ArrayList<>();
        list.clear();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

}

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Rule {
    private File file;
    private List<String> stringRules = new ArrayList<>();

    public Rule() throws IOException {ScanRule();}

    public void ScanRule() throws IOException {
        this.file = new File(System.getProperty("user.dir") + "/resources/gameRules.txt");
        Scanner myReader = new Scanner(file,  Charset.defaultCharset().name());
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.stringRules.add(data);
        }
    }

    public String getLine(int i) {
        return stringRules.get(i);
    }

    public int getNumberLines() {
        return this.stringRules.size();
    }

}


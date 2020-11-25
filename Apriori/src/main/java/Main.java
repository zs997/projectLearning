import java.io.File;
import java.util.List;

public class Main {
    public static void main(String args[]){

        List<List<String>> record = getRecord();
        System.out.println("data");
        for (int i = 0; i < record.size(); i++) {
            for (String s : record.get(i)) {
                System.out.print(s+"，");
            }
            System.out.println();
        }
    }
    private static List<List<String>> getRecord() {
        TxtReader readRecord = new TxtReader();
        return readRecord.getRecord();
    }
}

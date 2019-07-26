import java.io.*;
import java.util.*;

public class DataInsert2 {

    public static void main(String[] args) {

        try {
            // add store
            BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark2.csv"));
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 25; j++) {
                    writer.write("haspromotion," + Integer.toString(i + 1) + "," + Integer.toString(j + 1));
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
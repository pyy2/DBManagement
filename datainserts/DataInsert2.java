import java.io.*;
import java.util.*;

public class DataInsert2 {

    public static void main(String[] args) {
        Random rand = new Random();

        try {
            String str = "bc.addPurchase(";
            BufferedReader reader = new BufferedReader(new FileReader("coffee.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark4.csv"));
            int[][] temp = new int[100][100];
            String[] st = null;
            String[] l = new String[51];
            String line = "";

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 5; j++)
                    writer.write("topstores," + Integer.toString(i + 1) + "," + Integer.toString(j + 1) + '\n');

            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
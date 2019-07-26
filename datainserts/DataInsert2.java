import java.io.*;
import java.util.*;

public class DataInsert2 {

    public static void main(String[] args) {
        Random rand = new Random();

        try {
            String str = "bc.addPurchase(";
            BufferedReader reader = new BufferedReader(new FileReader("coffee.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark3.csv"));
            int[][] temp = new int[100][100];
            String[] st = null;
            String[] l = new String[51];
            String line = "";

            int x = 0;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                st = line.split(",");
                x = Integer.parseInt(st[0]);
                if (l[x - 1] == null) {
                    l[x - 1] = st[1];
                } else
                    l[x - 1] = l[x - 1] + "," + st[1];
            }
            for (int i = 0; i < l.length - 1; i++)
                System.out.println(l[i]);

            int num = 0;
            int store = 0;
            for (int i = 0; i < 50; i++) {
                num = rand.nextInt(3) + 1;
                store = rand.nextInt(50) + 1;
                writer.write(str); // purchase
                writer.write(Integer.toString(rand.nextInt(50) + 1) + ","); // customerid
                writer.write(Integer.toString(store) + ",");
                writer.write("Date.valueOf(");
                writer.write(Integer.toString(rand.nextInt(3) + 2016) + "-" + Integer.toString(rand.nextInt(12) + 1)
                        + "-" + Integer.toString(rand.nextInt(30) + 1) + ","); // date
                writer.write("");
                if (l[store].contains(",")) {
                    st = l[store].split(",");
                    for (int j = 0; j < num; j++) {

                        writer.write(st[j] + ",");
                    }
                }

                else {
                    writer.write(l[store]);
                    num = 1;
                }

                writer.write("),");
                writer.write("Arrays.asList(");
                for (int j = 0; j < num; j++)
                    writer.write(Integer.toString(rand.nextInt(4) + 1) + ",");
                writer.write("),");
                writer.write("Arrays.asList(");
                for (int j = 0; j < num; j++)
                    writer.write(Integer.toString(rand.nextInt(4) + 1));
                writer.write(");");
                writer.write("\n");
            }

            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
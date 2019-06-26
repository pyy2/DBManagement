import java.io.*;
import java.util.*;

public class DataInsert {

    public static void main(String[] args) {

        Scanner scanner = null;
        Random rand = new Random();
        String[][] info = new String[20][3];
        String line = "";
        StringTokenizer st = null;

        try {
            String str = "insert into customer values (";
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("customers.sql"));

            int count = 0;
            while ((line = reader.readLine()) != null && count < 20) {
                st = new StringTokenizer(line);
                info[count][0] = st.nextToken();
                info[count][1] = st.nextToken();
                info[count][2] = info[count][0].charAt(0) + info[count][1] + "@gmail.com";
                count++;
            }

            String currentLine = reader.readLine();
            for (int i = 0; i < 20; i++) {
                writer.write(str);
                writer.write(Integer.toString(rand.nextInt(100000)) + ","); // id
                writer.write("'" + info[i][0] + "'" + "," + "'" + info[i][1] + "'" + "," + "'" + info[i][2] + "'" + ","
                        + Integer.toString(rand.nextInt(3) + 1) + "," + Integer.toString(rand.nextInt(1000)));

                writer.write(");\n");
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(2);
        }
    }
}

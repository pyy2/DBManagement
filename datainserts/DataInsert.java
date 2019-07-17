import static java.util.Calendar.DAY_OF_YEAR;

import java.io.*;
import java.util.*;

public class DataInsert {

    public static void main(String[] args) {

        Scanner scanner = null;
        Random rand = new Random();
        String[][] info = new String[20][4];
        String line = "";
        StringTokenizer st = null;

        try {
            String str = "insert into customer values (";
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("customers.sql"));

            int count = 0;
            while ((line = reader.readLine()) != null && count < 20) {
                st = new StringTokenizer(line);
                info[count][0] = Integer.toString(count + 1);
                info[count][1] = st.nextToken();
                info[count][2] = st.nextToken();
                info[count][3] =  info[count][1] + "@gmail.com";
                count++;
            }

            String currentLine = reader.readLine();
            for (int i = 0; i < 20; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write("DEFAULT,");
                writer.write("'" + info[i][1] + "'" + "," + "'" + info[i][2] + "'" + "," + "'" + info[i][3] + "'" + ","
                        + Integer.toString(rand.nextInt(3) + 1) + "," + Integer.toString(rand.nextInt(1000)));
                writer.write(");\n");
            }
            writer.write("\n");
            // Purchase_ID int,
            // Customer_ID int,
            // Store_ID int,
            // Purchase_Time date, YYYY-MM-DD
            // primary key (Purchase_ID),
            // foreign key (Customer_ID) references customer(Customer_ID),
            // foreign key (Store_ID) references store(Store_ID)

            str = "insert into purchase values (";

            for (int i = 0; i < 50; i++) {

                writer.write(str + "DEFAULT" + "," + info[rand.nextInt(20)][0] + ","
                        + Integer.toString(rand.nextInt(3) + 1) + "," + "'" + Integer.toString(rand.nextInt(3) + 2016)
                        + "-" + Integer.toString(rand.nextInt(12) + 1) + "-" + Integer.toString(rand.nextInt(30) + 1)
                        + "');\n");

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

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}

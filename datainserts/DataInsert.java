import static java.util.Calendar.DAY_OF_YEAR;

import java.io.*;
import java.util.*;

public class DataInsert {

    public static void main(String[] args) {

        Scanner scanner = null;
        Random rand = new Random();
        String[][] info = new String[50][4];
        String line = "";
        StringTokenizer st = null;
        int random = 0;
        int random2 = 0;

        try {
            // add store
            String str = "insert into store values (";
            BufferedReader reader = new BufferedReader(new FileReader("addStore_name.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("customersBM.sql"));
            // "store_id\",\"name\", \"address\",
            // \"store_type\", \"gps_long\", \"gps_lat\") VALUES (DEFAULT,?,?,?,?,?)
            // RETURNING store_id";

            // store name
            int count = 0;
            while ((line = reader.readLine()) != null && count < 50) {
                info[count][0] = line;
                count++;
            }
            reader.close();
            reader = new BufferedReader(new FileReader("addStore_addr.txt"));

            // store addr
            count = 0;
            while ((line = reader.readLine()) != null && count < 50) {
                info[count][1] = line;
                count++;
            }

            // store type
            reader.close();
            reader = new BufferedReader(new FileReader("addStore_type.txt"));
            String sType[] = new String[100];
            // store addr
            count = 0;
            while ((line = reader.readLine()) != null && count < 5) {
                sType[count] = line;
                count++;
            }

            for (int i = 0; i < 50; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write("DEFAULT,"); // id
                writer.write("'" + info[i][0] + "',"); // name
                writer.write("'" + info[i][1] + "',"); // addr
                random = rand.nextInt(5) + 1; // random type
                writer.write("'" + sType[random - 1] + "',");
                random = rand.nextInt(100);
                random2 = rand.nextInt(100);
                writer.write("'" + random + "." + random2 + "',"); // gps_long
                random = rand.nextInt(100); // random type
                random2 = rand.nextInt(100);
                writer.write("'" + random + "." + random2 + "'"); // gps_lat

                writer.write(");\n");
            }

            reader.close();

            // Add coffee
            str = "insert into coffee values (";
            reader = new BufferedReader(new FileReader("addCoffee_name.txt"));
            String[] coffee = new String[45];

            // String name,
            count = 0;
            while ((line = reader.readLine()) != null && count < 45) {
                coffee[count] = line;
                count++;
            }
            reader.close();

            reader = new BufferedReader(new FileReader("addCoffee_desc.txt"));

            // String desc,
            count = 0;
            while ((line = reader.readLine()) != null && count < 15) {
                sType[count] = line;
                count++;
            }
            reader.close();
            for (int i = 0; i < 45; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write("DEFAULT,"); // id
                writer.write("'" + coffee[i] + "',"); // name

                random = rand.nextInt(15) + 1;
                writer.write("'" + sType[random] + "',"); // desc

                random = rand.nextInt(10) + 1; // intensity
                writer.write("'" + random + "',");

                random = rand.nextInt(10);
                random2 = rand.nextInt(100);
                writer.write("'" + random + "." + random2 + "',"); // price

                random = rand.nextInt(5);
                random2 = rand.nextInt(100);
                writer.write("'" + random + "." + random2 + "',"); // reward points

                random = rand.nextInt(5);
                random2 = rand.nextInt(100);
                writer.write("'" + random + "." + random2 + "'"); // redeem points

                writer.write(");\n");
            }
            reader.close();

            // // offer coffee
            str = "insert into offercoffee values (";
            for (int i = 0; i < 50; i++) {
                random = rand.nextInt(10) + 1;// store offer 15 coffees
                int j = 0;
                while (j < random) {
                    writer.write(str);
                    writer.write(i + 1 + ","); // store id
                    random2 = rand.nextInt(45);
                    writer.write(Integer.toString(random2 + 1));
                    writer.write(");\n");
                    j++;
                }
            }

            reader = new BufferedReader(new FileReader("addPromo_name.txt"));
            count = 0;
            while ((line = reader.readLine()) != null && count < 25) {
                sType[count] = line;
                count++;
            }
            String date = "";
            str = "insert into promotion values (";
            for (int i = 0; i < 25; i++) {
                writer.write(str);
                writer.write("DEFAULT,"); // id
                writer.write("'" + sType[i] + "',"); // name
                date = Integer.toString(rand.nextInt(3) + 2016); // year
                date = date + "-" + Integer.toString(rand.nextInt(12) + 1); // month
                date = date + "-" + Integer.toString(rand.nextInt(30) + 1); // day
                writer.write("'" + date + "',");
                date = Integer.toString(rand.nextInt(3) + 2016); // year
                date = date + "-" + Integer.toString(rand.nextInt(12) + 1); // month
                date = date + "-" + Integer.toString(rand.nextInt(30) + 1); // day
                writer.write("'" + date + "'");
                writer.write(");\n");
            }

            str = "insert into promotefor values (";
            for (int i = 0; i < 25; i++) {
                writer.write(str);
                random = rand.nextInt(25) + 1;
                writer.write(Integer.toString(random) + ",");
                random2 = rand.nextInt(45) + 1;
                writer.write(Integer.toString(random2));
                writer.write(");\n");
            }

            str = "insert into haspromotion values (";
            for (int i = 0; i < 100; i++) {
                writer.write(str);
                random = rand.nextInt(50) + 1;
                writer.write(Integer.toString(random) + ",");
                random2 = rand.nextInt(25) + 1;
                writer.write(Integer.toString(random2));
                writer.write(");\n");
            }
            // add customer

            reader.close();

            str = "insert into customer values (";
            reader = new BufferedReader(new FileReader("addCustomer_name.txt"));

            count = 0;
            while ((line = reader.readLine()) != null && count < 50) {
                st = new StringTokenizer(line);
                info[count][0] = Integer.toString(count + 1);
                info[count][1] = st.nextToken();
                info[count][2] = st.nextToken();
                info[count][3] = info[count][1] + "@pitt.edu";
                count++;
            }

            for (int i = 0; i < 50; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write("DEFAULT,");
                writer.write("'" + info[i][1] + "'" + "," + "'" + info[i][2] + "'" + "," + "'" + info[i][3] + "'" + ","
                        + Integer.toString(rand.nextInt(5) + 1) + "," + Integer.toString(rand.nextInt(1000)));
                writer.write(");\n");
            }

            // Purchase_ID int,
            // Customer_ID int,
            // Store_ID int,
            // Purchase_Time date, YYYY-MM-DD
            // primary key (Purchase_ID),
            // foreign key (Customer_ID) references customer(Customer_ID),
            // foreign key (Store_ID) references store(Store_ID)

            str = "insert into purchase values (";

            for (int i = 0; i < 200; i++) {

                writer.write(str + "DEFAULT" + "," + info[rand.nextInt(50)][0] + ","
                        + Integer.toString(rand.nextInt(50) + 1) + "," + "'" + Integer.toString(rand.nextInt(3) + 2016)
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

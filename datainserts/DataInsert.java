import static java.util.Calendar.DAY_OF_YEAR;

import java.io.*;
import java.util.*;

public class DataInsert {

    public static void main(String[] args) {

        Scanner scanner = null;
        Random rand = new Random();
        String[][] info = new String[500][4];
        String line = "";
        StringTokenizer st = null;
        int random = 0;
        int random2 = 0;

        final int MAX = 50;
        try {
            // add store
            String str = "store,";
            BufferedReader reader = new BufferedReader(new FileReader("addStore_name.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("benchmark2.csv"));
            // "store_id\",\"name\", \"address\",
            // \"store_type\", \"gps_long\", \"gps_lat\") VALUES (DEFAULT,?,?,?,?,?)
            // RETURNING store_id";
            writer.write(
                    "memberlevel,private,1\nmemberlevel,corporal,1.5\nmemberlevel,sergeant,2\nmemberlevel,master,2.5\nmemberlevel,commander,3\n");
            // store name
            int count = 0;
            while ((line = reader.readLine()) != null && count < MAX) {
                info[count][0] = line;
                count++;
            }
            reader.close();
            reader = new BufferedReader(new FileReader("addStore_addr.txt"));

            // store addr
            count = 0;
            while ((line = reader.readLine()) != null && count < MAX) {
                info[count][1] = line;
                count++;
            }

            // store type
            reader.close();
            reader = new BufferedReader(new FileReader("addStore_type.txt"));
            String sType[] = new String[MAX];
            // store addr
            count = 0;
            while ((line = reader.readLine()) != null && count < 5) {
                sType[count] = line;
                count++;
            }

            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                // writer.write("DEFAULT,"); // id
                writer.write(info[i][0] + ","); // name
                writer.write(info[i][1] + ","); // addr
                random = rand.nextInt(5) + 1; // random type
                writer.write(sType[random - 1] + ",");
                random = rand.nextInt(100);
                random2 = rand.nextInt(100);
                writer.write(random + "." + random2 + ","); // gps_long
                random = rand.nextInt(100); // random type
                random2 = rand.nextInt(100);
                writer.write(random + "." + random2); // gps_lat

                writer.write("\n");
            }

            reader.close();

            // Add coffee
            str = "coffee,";
            reader = new BufferedReader(new FileReader("addCoffee_name.txt"));
            String[] coffee = new String[MAX];

            // String name,
            count = 0;
            while ((line = reader.readLine()) != null && count < MAX) {
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
            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write(coffee[i] + ","); // name

                random = rand.nextInt(15) + 1;
                writer.write(sType[random] + ","); // desc

                random = rand.nextInt(10) + 1; // intensity
                writer.write(random + ",");

                random = rand.nextInt(10);
                random2 = rand.nextInt(100);
                writer.write(random + "." + random2 + ","); // price

                random = rand.nextInt(5);
                random2 = rand.nextInt(100);
                writer.write(random + "." + random2 + ","); // reward points

                random = rand.nextInt(5);
                random2 = rand.nextInt(100);
                writer.write(random + "." + random2); // redeem points

                writer.write("\n");
            }
            reader.close();

            // // offer coffee
            str = "offercoffee,";
            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                writer.write(i + 1 + ","); // store id
                writer.write(Integer.toString(i + 1)); // coffee offered
                writer.write("\n");
            }

            reader = new BufferedReader(new FileReader("addPromo_name.txt"));
            count = 0;
            while ((line = reader.readLine()) != null && count < MAX) {
                sType[count] = line;
                count++;
            }
            String date = "";
            str = "promotion";
            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                writer.write("," + sType[i] + ","); // name
                int year = rand.nextInt(4) + 2015; // year
                date = Integer.toString(year);
                date = date + "-" + Integer.toString(rand.nextInt(12) + 1); // month
                date = date + "-" + Integer.toString(rand.nextInt(30) + 1); // day
                writer.write(date + ",");
                date = Integer.toString(year + 2); // year
                date = date + "-" + Integer.toString(rand.nextInt(12) + 1); // month
                date = date + "-" + Integer.toString(rand.nextInt(30) + 1); // day
                writer.write(date);
                writer.write("\n");
            }

            str = "promotefor,";
            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                random = rand.nextInt(MAX) + 1;
                writer.write(Integer.toString(random) + ",");
                random2 = rand.nextInt(MAX) + 1;
                writer.write(Integer.toString(random2));
                writer.write("\n");
            }

            reader.close();

            str = "customer,";
            reader = new BufferedReader(new FileReader("addCustomer_name.txt"));

            count = 0;
            while ((line = reader.readLine()) != null && count < MAX) {
                st = new StringTokenizer(line);
                info[count][0] = Integer.toString(count + 1);
                info[count][1] = st.nextToken();
                info[count][2] = st.nextToken();
                info[count][3] = info[count][1] + "@pitt.edu";
                count++;
            }

            for (int i = 0; i < MAX; i++) {
                writer.write(str);
                // writer.write(info[i][0] + ","); // id
                writer.write(info[i][1] + "," + info[i][2] + "," + info[i][3] + ","
                        + Integer.toString(rand.nextInt(5) + 1) + "," + Integer.toString(rand.nextInt(1000)));
                writer.write("\n");
            }
            reader.close();

            // reader = new BufferedReader(new FileReader("benchmark2.csv"));
            // int[][] temp = new int[1000][1000];
            // String[][] l = new String[501][2];
            // String[] s = null;
            // int x = 0;
            // count = 0;
            // for(int i = 0; i < MAX; i++){
            // if (line.contains("offercoffee")) {
            // s = line.split(",");
            // l[count][0] = s[1]; // store id
            // l[count][1] = s[2]; //
            // }
            // count++;
            // }
            // while ((line = reader.readLine()) != null) {

            // }

            str = "purchase,";

            for (int i = 0; i < MAX; i++) {
                writer.write(str); // purchase
                writer.write(Integer.toString(rand.nextInt(MAX) + 1) + ","); // customerid
                writer.write((Integer.toString(i + 1) + ","));
                writer.write("");
                writer.write(Integer.toString(rand.nextInt(4) + 2015) + "-" + Integer.toString(rand.nextInt(12) + 1)
                        + "-" + Integer.toString(rand.nextInt(30) + 1) + ","); // date
                writer.write(Integer.toString(i + 1));
                writer.write("," + Integer.toString(1) + ",");
                writer.write(Integer.toString(1));
                writer.write("\n");
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

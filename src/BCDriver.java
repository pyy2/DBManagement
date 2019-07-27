import java.sql.Date;
import java.util.*;
import java.io.*;

public class BCDriver {
    public static void main(String[] args) {

        // init variables
        Scanner scan = new Scanner(System.in);
        Console console = System.console();

        String name = "";
        String address = "";
        String storeType = "";
        String description = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String date = "";
        String keyword1 = "";
        String keyword2 = "";

        int intensity = -1;
        int storeId = -1;
        int coffeeId = -1;
        int promotionId = -1;
        int memberLevelId = -1;
        int customerId = -1;
        int k = -1;
        int x = -1;

        double gpsLong = 0.0;
        double gpsLat = 0.0;
        double price = 0.0;
        double rewardPoints = 0.0;
        double redeemPoints = 0.0;
        double boosterFactor = 0.0;
        double totalPoints = 0.0;

        Date startDate = null;
        Date endDate = null;
        Date purchaseTime = null;

        List<Integer> coffeeIds = null;
        List<Integer> purchaseQuantities = null;
        List<Integer> redeemQualities = null;

        String in = ""; // temp
        String[] tempS = new String[100];
        double tempD = 0.0;
        List<Integer> tempL = null;
        List<Integer> temp1 = null;
        List<Integer> temp2 = null;
        List<Integer> temp3 = null;

        // for benchmark testing
        long startTime;
        long endTime;

        // prompt for username/password
        System.out.print("Username: ");
        String username = scan.nextLine();
        String password = new String(console.readPassword("Password: "));

        // call function class
        BoutiqueCoffee bc = new BoutiqueCoffee(username, password);

        // repeat option menu
        int choice = 0;
        int result = -1;
        System.out.println("Welcome to Boutique Coffee");

        while (true) {

            result = -1;
            System.out.println("Select one of the following:");
            System.out.println("1:\tAdd Store");
            System.out.println("2:\tAdd Coffee");
            System.out.println("3:\tOffer Coffee");
            System.out.println("4:\tAdd Promotion");
            System.out.println("5:\tPromote For");
            System.out.println("6:\tHas Promotion");
            System.out.println("7:\tAdd Member Level");
            System.out.println("8:\tAdd Customer");
            System.out.println("9:\tAdd Purchase");
            System.out.println("10:\tGet Coffee");
            System.out.println("11:\tGet Coffee (By keyword)");
            System.out.println("12:\tGet Points (By Customer ID)");
            System.out.println("13:\tGet Top X Stores in Past Y Months");
            System.out.println("14:\tGet Top X Customers in Past Y Months");
            System.out.println("15:\tBenchmark");
            System.out.println("#########################");

            choice = Integer.parseInt(scan.nextLine());
            System.out.println("#########################");

            switch (choice) {

            // add store
            case 1:

                System.out.println("Enter store name: ");
                name = scan.nextLine();
                System.out.println("Enter store address: ");
                address = scan.nextLine();
                System.out.println("Enter store type: ");
                storeType = scan.nextLine();
                System.out.println("Enter store gps longitude: ");
                gpsLong = Double.parseDouble(scan.nextLine());
                System.out.println("Enter store gps latitude: ");
                gpsLat = Double.parseDouble(scan.nextLine());

                result = bc.addStore(name, address, storeType, gpsLong, gpsLat);

                if (result == -1)
                    System.out.println("Failed to add Store");
                else
                    System.out.println("Store Successfully Added: " + result);
                break;

            // add coffee
            case 2:
                System.out.println("Enter coffee name: ");
                name = scan.nextLine();
                System.out.println("Enter description: ");
                description = scan.nextLine();
                System.out.println("Enter intensity: ");
                intensity = Integer.parseInt(scan.nextLine());
                System.out.println("Enter price: ");
                price = Double.parseDouble(scan.nextLine());
                System.out.println("Enter reward points: ");
                rewardPoints = Double.parseDouble(scan.nextLine());
                System.out.println("Enter redeem points: ");
                redeemPoints = Double.parseDouble(scan.nextLine());

                result = bc.addCoffee(name, description, intensity, price, rewardPoints, redeemPoints);
                if (result == -1)
                    System.out.println("Failed to add Coffee");
                else
                    System.out.println("Coffee Successfully Added: " + result);

                break;

            // offer coffee
            case 3:
                System.out.println("Enter store id: ");
                storeId = Integer.parseInt(scan.nextLine());
                System.out.println("Enter coffee id: ");
                coffeeId = Integer.parseInt(scan.nextLine());

                if (bc.offerCoffee(storeId, coffeeId) == -1)
                    System.out.println("Error Offering Coffee");
                else
                    System.out.println("Successfully Offered Coffee!");

                break;

            // add promotion
            case 4:
                System.out.println("Promotion Name: ");
                name = scan.nextLine();
                System.out.println("Start Date: ");
                Date apStartDate = new Date(scan.nextLong()); // date takes a long which is the milliseconds since jan 1
                                                              // 1970 00:00:00
                System.out.println("End Date: ");
                Date apEndDate = new Date(scan.nextLong());

                int apResult = bc.addPromotion(name, apStartDate, apEndDate);

                if (apResult == -1)
                    System.out.println("Error Adding Promotion");
                else
                    System.out.println("Successfully Added Promotion " + apResult);

                break;

            // promote for
            case 5:
                System.out.println("Promote for: ");
                promotionId = Integer.parseInt(scan.nextLine());
                System.out.println("Coffee Id: ");
                coffeeId = Integer.parseInt(scan.nextLine());

                if (bc.promoteFor(promotionId, coffeeId) == -1)
                    System.out.println("Error Promoting");
                else
                    System.out.println("Successfully Promoting Coffee!");

                break;

            // has promotion
            case 6:
                System.out.println("Store Id: ");
                storeId = Integer.parseInt(scan.nextLine());
                System.out.println("Promotion Id: ");
                promotionId = Integer.parseInt(scan.nextLine());

                if (bc.hasPromotion(storeId, promotionId) == -1)
                    System.out.println("Store does not have that promotion!");
                else
                    System.out.println("Store has specified promotion!");

                break;

            // add membership level
            case 7:
                System.out.println("Membership Name: ");
                name = scan.nextLine();
                System.out.println("Booster Factor: ");
                boosterFactor = Double.parseDouble(scan.nextLine());

                result = bc.addMemberLevel(name, boosterFactor);
                if (result == -1)
                    System.out.println("Membership failed to add");
                else
                    System.out.println("Membership added: " + result);
                break;

            // add customer
            case 8:
                System.out.println("Customer First Name: ");
                firstName = scan.nextLine();
                System.out.println("Customer Last Name: ");
                lastName = scan.nextLine();
                System.out.println("Customer Email (auto appends @gmail.com): ");
                email = scan.nextLine();
                email = email + "@gmail.com";
                if (email.length() > 20) {
                    System.out.println("Email is too long");
                    break;
                }
                System.out.println("Customer Member Level: ");
                memberLevelId = Integer.parseInt(scan.nextLine());
                System.out.println("Customer Total Points: ");
                totalPoints = Double.parseDouble(scan.nextLine());

                result = bc.addCustomer(firstName, lastName, email, memberLevelId, totalPoints);
                if (result == -1)
                    System.out.println("Error Adding Customer");
                else
                    System.out.println("Customer Successfully Added: " + result);

                break;

            // add purchase
            case 9:
                System.out.println("Enter Customer Id: ");
                customerId = Integer.parseInt(scan.nextLine());
                System.out.println("Enter Store Id:");
                storeId = Integer.parseInt(scan.nextLine());
                System.out.println("Enter Purchase Time (YYYY-MM-DD):");
                date = scan.nextLine();
                purchaseTime = Date.valueOf(date);
                coffeeIds = new ArrayList<>();
                purchaseQuantities = new ArrayList<>();
                redeemQualities = new ArrayList<>();

                System.out.println("List Coffee Ids (-1 to stop):");
                while ((result = scan.nextInt()) != -1) {
                    coffeeIds.add(result);
                }
                in = scan.nextLine();

                System.out.println("List Purchase Quantities (-1 to stop)");
                while ((result = scan.nextInt()) != -1) {
                    purchaseQuantities.add(result);
                }
                in = scan.nextLine();

                System.out.println("List Redeem Qualities (csv):");
                while ((result = scan.nextInt()) != -1) {
                    redeemQualities.add(result);
                }
                in = scan.nextLine();

                result = bc.addPurchase(customerId, storeId, purchaseTime, coffeeIds.subList(0, coffeeIds.size()),
                        purchaseQuantities, redeemQualities);

                if (result == -1)
                    System.out.println("Operation Failed");
                else
                    System.out.println("Purchase added successfully!");

                break;

            case 10:
                tempL = bc.getCoffees();
                if (tempL == null)
                    System.out.println("Error retrieving coffeelist");
                else if (tempL.isEmpty())
                    System.out.println("No coffees in database");
                else
                    for (Integer i : tempL)
                        System.out.println(i);
                break;

            // get coffee by keywords
            case 11:
                System.out.println("Enter keyword 1:");
                keyword1 = scan.nextLine();
                System.out.println("Enter keyword 2:");
                keyword2 = scan.nextLine();

                tempL = bc.getCoffeesByKeywords(keyword1, keyword2);
                if (tempL == null || tempL.isEmpty())
                    System.out.println("No Coffee Ids Available");
                else {
                    for (Integer i : tempL)
                        System.out.println(i);
                }
                break;

            // get customer points
            case 12:
                System.out.println("Enter Customer Id:");
                customerId = Integer.parseInt(scan.nextLine());

                tempD = bc.getPointsByCustomerId(customerId);

                if (tempD == -1)
                    System.out.println("Error Retrieving Customer Points");
                else
                    System.out.println(
                            "Customer " + Integer.toString(customerId) + " Total Points: " + Double.toString(tempD));
                break;

            // get top K stores past X months
            case 13:
                System.out.println("Enter # Customers: ");
                k = Integer.parseInt(scan.nextLine());
                System.out.println("Enter Past # Months: ");
                x = Integer.parseInt(scan.nextLine());
                List<Integer> topKStores = bc.getTopKStoresInPastXMonth(k, x);

                if (topKStores == null || topKStores.isEmpty())
                    System.out.println("Operation Failed or no stores");
                else
                    for (int i : topKStores)
                        System.out.println(i);
                break;

            // get top K customers past X months
            case 14:
                System.out.println("Enter # Customers: ");
                k = Integer.parseInt(scan.nextLine());
                System.out.println("Enter Past # Months: ");
                x = Integer.parseInt(scan.nextLine());
                List<Integer> topKCustomers = bc.getTopKCustomersInPastXMonth(k, x);

                if (topKCustomers == null || topKCustomers.isEmpty())
                    System.out.println("Operation Failed or no customers");
                else
                    for (int i : topKCustomers)
                        System.out.println(i);
                break;

            case 15:
                String line = "";
                String s[] = null;
                try {
                    System.out.println("Choose Benchmark File:");
                    System.out.println("1.\t500 inserts");
                    System.out.println("2.\t250 inserts");
                    System.out.println("3.\t50 inserts");
                    choice = Integer.parseInt(scan.nextLine());
                    BufferedReader reader = null;
                    if (choice == 1)
                        reader = new BufferedReader(new FileReader("./benchmark.csv"));
                    else if (choice == 2)
                        reader = new BufferedReader(new FileReader("./benchmark2.csv"));
                    else if (choice == 3)
                        reader = new BufferedReader(new FileReader("./benchmark3.csv"));

                    List<Integer> count = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        count.add(0);
                    }

                    long store_time = 0;
                    long coffee_time = 0;
                    long oc_time = 0;
                    long pTime = 0;
                    long pfTime = 0;
                    long hpTime = 0;
                    long cTime = 0;
                    long purTime = 0;
                    long cIDtime = 0;
                    long topStime = 0;

                    while ((line = reader.readLine()) != null) {
                        s = line.split(",");
                        if (s[0].equals("memberlevel")) {
                            startTime = System.nanoTime();
                            result = bc.addMemberLevel(s[1], Double.parseDouble(s[2]));
                            endTime = System.nanoTime();
                            if (result == -1)
                                System.out.println("Membership failed to add");
                            else
                                System.out.println("Membership added: " + result);
                        }
                        if (s[0].equals("store")) {
                            count.set(0, count.get(0) + 1);
                            startTime = System.nanoTime();
                            result = bc.addStore(s[1], s[2], s[3], Double.parseDouble(s[4]), Double.parseDouble(s[5]));
                            endTime = System.nanoTime();
                            store_time = store_time + ((endTime - startTime));

                            if (result == -1)
                                System.out.println("Failed to add Store");
                            else
                                System.out.println("Store Successfully Added: " + result);

                        }
                        if (s[0].equals("coffee")) {
                            count.set(1, count.get(1) + 1);
                            startTime = System.nanoTime();
                            result = bc.addCoffee(s[1], s[2], Integer.parseInt(s[3]), Double.parseDouble(s[4]),
                                    Double.parseDouble(s[5]), Double.parseDouble(s[6]));
                            endTime = System.nanoTime();
                            coffee_time = coffee_time + ((endTime - startTime));
                            if (result == -1)
                                System.out.println("Failed to add Coffee");
                            else
                                System.out.println("Coffee Successfully Added: " + result);
                        }
                        if (s[0].equals("offercoffee")) {
                            count.set(2, count.get(2) + 1);
                            startTime = System.nanoTime();
                            result = bc.offerCoffee(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                            endTime = System.nanoTime();
                            oc_time = oc_time + ((endTime - startTime));

                            if (result == -1)
                                System.out.println("Error Offering Coffee");
                            else
                                System.out.println("Successfully Offered Coffee!");
                        }
                        if (s[0].equals("promotion")) {
                            count.set(3, count.get(3) + 1);
                            startTime = System.nanoTime();
                            bc.addPromotion(s[1], Date.valueOf(s[2]), Date.valueOf(s[3]));
                            endTime = System.nanoTime();
                            pTime = pTime + ((endTime - startTime));
                        }
                        if (s[0].equals("promotefor")) {
                            count.set(4, count.get(4) + 1);
                            startTime = System.nanoTime();
                            result = bc.promoteFor(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                            endTime = System.nanoTime();
                            pfTime = pfTime + ((endTime - startTime));
                            if (result == -1)
                                System.out.println("Error Promoting");
                            else
                                System.out.println("Successfully Promoting Coffee: " + s[2]);
                        }
                        if (s[0].equals("customer")) {
                            count.set(6, count.get(6) + 1);
                            startTime = System.nanoTime();
                            result = bc.addCustomer(s[1], s[2], s[3], Integer.parseInt(s[4]), Double.parseDouble(s[5]));
                            endTime = System.nanoTime();
                            cTime = cTime + ((endTime - startTime));
                            if (result == -1)
                                System.out.println("Error Adding Customer");
                            else
                                System.out.println("Customer Successfully Added: " + result);
                        }

                        if (s[0].equals("purchase")) {
                            count.set(7, count.get(7) + 1);
                            temp1 = new ArrayList<>();
                            temp2 = new ArrayList<>();
                            temp3 = new ArrayList<>();

                            temp1.add(Integer.parseInt(s[4]));
                            temp2.add(Integer.parseInt(s[5]));
                            temp3.add(Integer.parseInt(s[6]));

                            startTime = System.nanoTime();
                            bc.addPurchase(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Date.valueOf(s[3]), temp1,
                                    temp2, temp3);
                            endTime = System.nanoTime();
                            purTime = purTime + ((endTime - startTime));
                        }
                        if (s[0].equals("customerid")) {
                            count.set(8, count.get(8) + 1);
                            startTime = System.nanoTime();
                            tempD = bc.getPointsByCustomerId(Integer.parseInt(s[1]));
                            endTime = System.nanoTime();
                            cIDtime = cIDtime + ((endTime - startTime));
                            if (tempD == -1)
                                System.out.println("Error Retrieving Customer Points");
                            else
                                System.out.println("Customer " + s[1] + " Total Points: " + Double.toString(tempD));
                        }
                        if (s[0].equals("topstores")) {
                            startTime = System.nanoTime();
                            List<Integer> topS = bc.getTopKStoresInPastXMonth(Integer.parseInt(s[1]),
                                    Integer.parseInt(s[2]));
                            endTime = System.nanoTime();
                            topStime = topStime + ((endTime - startTime));
                            count.set(9, count.get(9) + 1);
                            if (topS == null || topS.isEmpty())
                                System.out.println("Operation Failed or no stores");
                            else
                                for (int i : topS)
                                    System.out.println(i);
                        }
                    }
                    System.out.println("\n\n#### BENCHMARK ####: " + store_time / (count.get(0) * 100000) + "ms");
                    System.out.println("avg add store time: " + store_time / (count.get(0) * 100000) + "ms");
                    System.out.println("avg add coffee time: " + coffee_time / (count.get(1) * 100000) + "ms");
                    System.out.println("avg offer coffee time: " + oc_time / (count.get(2) * 100000) + "ms");
                    System.out.println("avg add promo time: " + pTime / (count.get(3) * 100000) + "ms");
                    System.out.println("avg promofor time: " + pfTime / (count.get(4) * 100000) + "ms");
                    System.out.println("avg add customer time: " + cTime / (count.get(6) * 100000) + "ms");
                    System.out.println("avg purchase time: " + purTime / (count.get(7) * 100000) + "ms");
                    System.out.println("avg get cID time: " + cIDtime / (count.get(8) * 100000) + "ms");
                    System.out.println("avg top store time: " + purTime / (count.get(9) * 100000) + "ms");

                    reader.close();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            default:
                System.out.println("Example not found for your entry: " + choice);
                break;
            }

            System.out.println("#########################");

        }
    }
}

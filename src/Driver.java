import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) {

        // init variables
        Scanner scan = new Scanner(System.in);
        Console console = System.console();

        // prompt for username/password
        System.out.print("Username: ");
        String username = scan.nextLine();
        String password = new String(console.readPassword("Password: "));

        // call function class
        BoutiqueCoffee bc = new BoutiqueCoffee(username, password);

        // repeat option menu
        int choice = 0;
        System.out.println("Welcome to Boutique Coffee");

        while (true) {

            System.out.println("Select one of the following:");
            System.out.println("0:\tAdd Store");
            System.out.println("1:\tAdd Coffee");
            System.out.println("2:\tOffer Coffee");
            System.out.println("3:\tAdd Promotion");
            System.out.println("4:\tPromote For");
            System.out.println("5:\tHas Promotion");
            System.out.println("6:\tAdd Member Level");
            System.out.println("7:\tAdd Customer");
            System.out.println("8:\tAdd Purchase");
            System.out.println("9:\tGet Coffee");
            System.out.println("10:\tGet Coffee (By keyword)");
            System.out.println("11:\tGet Points (By Customer ID)");
            System.out.println("12:\tGet Top X Stores in Past Y Months");
            System.out.println("13:\tGet Top X Customers in Past Y Months");
            System.out.println(">> ");

            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
            case 0:

                System.out.println("Enter store name: ");
                String name = scan.nextLine();
                System.out.println("Enter store address: ");
                String address = scan.nextLine();
                System.out.println("Enter store type: ");
                String storeType = scan.nextLine();
                System.out.println("Enter store gps longitude: ");
                double gpsLong = Double.parseDouble(scan.nextLine());
                System.out.println("Enter store gps latitude: ");
                double gpsLat = Double.parseDouble(scan.nextLine());

                if (bc.addStore(name, address, storeType, gpsLong, gpsLat) == -1)
                    System.out.println("Operation Failed");
                else
                    System.out.println("Store Successfully Added");
                break;
            // case 1:
            // addCoffee();
            // break;
            // case 2:
            // offerCoffee();
            // break;
            // case 3:
            // addPromotion();
            // break;
            // case 4:
            // promoteFor();
            // break;
            // case 5:
            // hasPromotion();
            // break;
            // case 6:
            // addMemberLevel();
            // break;
            // case 7:
            // addCustomer();
            // break;
            // case 8:
            // addPurchase();
            // break;
            // case 9:
            // getCoffees();
            // break;
            // case 10:
            // getCoffeesByKeywords();
            // break;
            // case 11:
            // getPointsByCustomerId();
            // break;
            // case 12:
            // getTopKStoresInPastXMonth();
            // break;
            // case 13:
            // getTopKCustomersInPastXMonth();
            // break;
            default:
                System.out.println("Example not found for your entry: " + choice);
                break;
            }
        }
    }
}

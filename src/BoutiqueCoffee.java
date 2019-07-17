
import java.io.*;
import java.util.*;
import java.sql.*; //import the file containing definitions for the parts
import java.text.ParseException; //needed by java for database connection and manipulation
import java.sql.DriverManager;

public class BoutiqueCoffee {
        private Scanner scan;
        private static Connection connection; // used to hold the jdbc connection to the DB
        private Statement statement; // used to create an instance of the connection
        private PreparedStatement prepStatement; // used to create a prepared statement, that will be later reused
        private ResultSet resultSet; // used to hold the result of your query (if one exists)
        private String query; // this will hold the query we are using

        public BoutiqueCoffee(String username, String password) {
                scan = new Scanner(System.in);
                try {
                        // Class.forName("org.postgresql.Driver");
                        String url = "jdbc:postgresql://localhost/postgres?currentSchema=cs1555/";
                        Properties props = new Properties();
                        System.out.println("Connecting to Postgres...");
                        props.setProperty("user", username);
                        props.setProperty("password", password);
                        connection = DriverManager.getConnection(url, props);
                } catch (SQLException e) {
                        System.err.println(e);
                        System.exit(0);
                }

        }

        // @return the auto-generated ID of this store or -1 if the operation is not
        // possible or failed
        public int addStore(String name, String address, String storeType, double gpsLong, double gpsLat) {
                int id = 0;
                return id;
        }

        // // @return the auto-generated ID of this coffee or -1 if the operation is not
        // // possible or failed
        // public int addCoffee(String name, String description, int intensity, double
        // price, double rewardPoints,
        // double redeemPoints) {
        // int id = 0;
        // return id;
        // }

        // // @return 1 if the operation succeeded or -1 if the operation is not
        // possible
        // // or failed
        // public int offerCoffee(int storeId, int coffeeId) {
        // int success = -1;
        // return success;
        // }

        // // @return the auto-generated ID of this promotion or -1 if the operation is
        // not
        // // possible or failed
        // public int addPromotion(String name, Date startDate, Date endDate) {
        // int id = 0;
        // return id;
        // }

        // // @return 1 if the operation succeeded or -1 if the operation is not
        // possible
        // // or failed
        // public int promoteFor(int promotionId, int coffeeId) {
        // int success = -1;
        // return success;
        // }

        // // @return 1 if the operation succeeded or -1 if the operation is not
        // possible
        // // or failed
        // public int hasPromotion(int storeId, int promotionId) {
        // int success = -1;
        // return success;
        // }

        // // @return the auto-generated ID of this member level or -1 if the operation
        // is
        // // not possible orfailed
        // public int addMemberLevel(String name, double boosterFactor) {
        // int id = 0;
        // return id;
        // }

        // // @return the auto-generated ID of this customer or -1 if the operation is
        // not
        // // possible or failed
        // public int addCustomer(String firstName, String lastName, String email, int
        // memberLevelId, double totalPoints) {
        // int id = 0;
        // return id;
        // }

        // // @param coffeeIds - ID’s of the coffees being bought by this purchase
        // // @param purchaseQuantities - Mapping 1-to-1 to coffeeIds, indicating the
        // // purchase quantity ofeach coffee in this purchase.
        // // @param redeemQuantities - Mapping 1-to-1 to coffeeIds, indicating the
        // redeem
        // // quantity ofeach coffee in this purchase.
        // // @return the auto-generated ID of this purchase or -1 if the operation is
        // not
        // // possible or failedNotes:
        // // - Examples of failures: not having enough points to redeem coffees,
        // // certain coffee not beingsold in the store, etc.
        // public int addPurchase(int customerId, int storeId, Date purchaseTime,
        // List<Integer> coffeeIds,
        // List<Integer> purchaseQuantities, List<Integer> redeemQuantities) {
        // int id = 0;
        // return id;
        // }

        // // @return a list of ID of all coffees in the database.
        // // It returns an empty list if no coffee is in thedatabase or the operation
        // // failed.
        // public List<Integer> getCoffees() {
        // List<Integer> id = new List<Integer>();
        // return id;
        // }

        // // @return a list of ID of all coffees, each of which has both keywords in
        // its
        // // name, in the database.
        // // It returns an empty list if no coffee satisfying the conditions is in the
        // // database or the operation failed.
        // public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2) {
        // List<Integer> id = new List<Integer>();
        // return id;
        // }

        // // @return the total points of the customer identified by the customerId or
        // -1
        // // if the operation isnot possible or failed
        // public double getPointsByCustomerId(int customerId) {
        // double id = 0;
        // return id;
        // }

        // // @param k - the K in top K@param x - the timespan in month
        // // @return a list of ID of top k stores having the highest revenue in the
        // past x
        // // monthNotes:
        // // - Revenue is defined as sum of money that the customers pay for the
        // coffees.
        // // - 1 month is defined as 30 days counting back starting from the current
        // date
        // // time.
        // // - The returned list should be sorted, with ID of the store having the
        // highest
        // // revenue at itshead.
        // // - If multiple stores have the same revenue, the order of their ID’s in the
        // // returned list can bearbitrary.
        // // - If multiple stores have the same revenue in the Kth highest revenue
        // // position, their ID’s shouldall be returned.
        // // - It returns an empty list if no store is in the database or the operation
        // // failed.
        // public List<Integer> getTopKStoresInPastXMonth(int k, int x) {
        // List<Integer> id = new List<Integer>();
        // return id;
        // }

        // // @param k - the K in top K
        // // @param x - the timespan in month
        // // @return a list of ID of top k customers having spent most money in buying
        // // coffee in the pastx monthNotes:
        // // - 1 month is defined as 30 days counting back starting from the current
        // date
        // // time.
        // // - The returned list should be sorted, with ID of the customer having spent
        // // most money at itshead.
        // // - If multiple customers have the same spending, the order of their ID’s in
        // // the returned list canbe arbitrary.
        // // - If multiple customers have the same spending in the Kth highest spending
        // // position, their ID’sshould all be returned.
        // // - It returns an empty list if no customer is in the database or the
        // operation
        // // failed.
        // public List<Integer> getTopKCustomersInPastXMonth(int k, int x) {
        // List<Integer> id = new List<Integer>();
        // return id;
        // }

}
import java.util.Date;
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
        private ResultSet rs; // used to hold the result of your query (if one exists)
        private String query; // this will hold the query we are using

        public BoutiqueCoffee(String username, String password) {
                scan = new Scanner(System.in);
                statement = null;
                connection = null;
                prepStatement = null;
                rs = null;
                query = "";

                try {
                        String url = "jdbc:postgresql://localhost/postgres";
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
                int result = -1;
                try {

                        // get the last serial value
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT MAX(store_id) FROM store");
                        rs.next();
                        int storeId = rs.getInt(1) + 1;
                        statement.close();

                        // insert
                        query = "INSERT INTO store VALUES (?,?,?,?,?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, storeId);
                        prepStatement.setString(2, name);
                        prepStatement.setString(3, address);
                        prepStatement.setString(4, storeType);
                        prepStatement.setDouble(5, gpsLong);
                        prepStatement.setDouble(6, gpsLat);
                        prepStatement.executeUpdate();
                        result = storeId;

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return the auto-generated ID of this coffee or -1 if the operation is not
        // possible or failed
        public int addCoffee(String name, String description, int intensity, double price, double rewardPoints,
                        double redeemPoints) {
                int result = -1;
                try {

                        // get the last serial value
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT MAX(coffee_id) FROM coffee");
                        rs.next();
                        int coffeeId = rs.getInt(1) + 1;
                        statement.close();

                        // insert
                        query = "INSERT INTO coffee VALUES (?,?,?,?,?,?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, coffeeId);
                        prepStatement.setString(2, name);
                        prepStatement.setString(3, description);
                        prepStatement.setInt(4, intensity);
                        prepStatement.setDouble(5, price);
                        prepStatement.setDouble(6, rewardPoints);
                        prepStatement.setDouble(7, redeemPoints);
                        prepStatement.executeUpdate();

                        result = coffeeId;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int offerCoffee(int storeId, int coffeeId) {
                int result = -1;
                try {
                        query = "INSERT INTO offercoffee VALUES (?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, storeId);
                        prepStatement.setInt(2, coffeeId);
                        prepStatement.executeUpdate();
                        result = 1;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return the auto-generated ID of this promotion or -1 if the operation is not
        // possible or failed
        public int addPromotion(String name, Date startDate, Date endDate) {
                int id = 0;
                return id;
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int promoteFor(int promotionId, int coffeeId) {
                int result = -1;
                try {
                        query = "INSERT INTO promotefor VALUES (?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, promotionId);
                        prepStatement.setInt(2, coffeeId);
                        prepStatement.executeUpdate();
                        result = 1;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int hasPromotion(int storeId, int promotionId) {
                int result = -1;
                try {
                        query = "INSERT INTO haspromotion VALUES (?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, storeId);
                        prepStatement.setInt(2, promotionId);
                        prepStatement.executeUpdate();
                        result = 1;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return the auto-generated ID of this member level or -1 if the operation is
        // not possible orfailed
        public int addMemberLevel(String name, double boosterFactor) {
                int result = -1;
                try {

                        // get the last serial value
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT MAX(memberlevel_id) FROM memberlevel");
                        rs.next();
                        int memberId = rs.getInt(1) + 1;
                        statement.close();

                        // insert
                        query = "INSERT INTO memberlevel VALUES (?,?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, memberId);
                        prepStatement.setString(2, name);
                        prepStatement.setDouble(3, boosterFactor);
                        prepStatement.executeUpdate();

                        result = memberId;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return the auto-generated ID of this customer or -1 if the operation is not
        // possible or failed
        public int addCustomer(String firstName, String lastName, String email, int memberLevelId, double totalPoints) {
                int result = -1;
                try {

                        // get the last serial value
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT MAX(customer_id) FROM customer");
                        rs.next();
                        int customerId = rs.getInt(1) + 1;
                        statement.close();

                        // insert
                        query = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, customerId);
                        prepStatement.setString(2, firstName);
                        prepStatement.setString(3, lastName);
                        prepStatement.setString(4, email);
                        prepStatement.setInt(5, memberLevelId);
                        prepStatement.setDouble(6, totalPoints);
                        prepStatement.executeUpdate();

                        result = customerId;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @param coffeeIds - ID’s of the coffees being bought by this purchase
        // @param purchaseQuantities - Mapping 1-to-1 to coffeeIds, indicating the
        // purchase quantity ofeach coffee in this purchase.
        // @param redeemQuantities - Mapping 1-to-1 to coffeeIds, indicating the redeem
        // quantity ofeach coffee in this purchase.
        // @return the auto-generated ID of this purchase or -1 if the operation is not
        // possible or failedNotes:
        // - Examples of failures: not having enough points to redeem coffees,
        // certain coffee not beingsold in the store, etc.
        public int addPurchase(int customerId, int storeId, Date purchaseTime, List<Integer> coffeeIds,
                        List<Integer> purchaseQuantities, List<Integer> redeemQuantities) {
                int result = -1;
                try {

                        // get the last serial value
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT MAX(purchase_id) FROM purchase");
                        rs.next();
                        int purchaseId = rs.getInt(1) + 1;
                        statement.close();

                        // insert
                        query = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
                        prepStatement = connection.prepareStatement(query);
                        // prepStatement.setInt(1, purchaseId);
                        // prepStatement.setInt(2, firstName);
                        // prepStatement.setInt(3, lastName);
                        // prepStatement.setDate(4, email);
                        // prepStatement.setInt(5, memberLevelId);
                        // prepStatement.setDouble(6, totalPoints);
                        prepStatement.executeUpdate();

                        result = purchaseId;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }

        // @return a list of ID of all coffees in the database.
        // It returns an empty list if no coffee is in thedatabase or the operation
        // failed.
        public List<Integer> getCoffees() {
                List<Integer> id = new ArrayList<>();
                return id;
        }

        // @return a list of ID of all coffees, each of which has both keywords in its
        // name, in the database.
        // It returns an empty list if no coffee satisfying the conditions is in the
        // database or the operation failed.
        public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2) {
                List<Integer> id = new ArrayList<>();
                return id;
        }

        // @return the total points of the customer identified by the customerId or -1
        // if the operation isnot possible or failed
        public double getPointsByCustomerId(int customerId) {
                double id = 0;
                return id;
        }

        // @param k - the K in top K@param x - the timespan in month
        // @return a list of ID of top k stores having the highest revenue in the past x
        // monthNotes:
        // - Revenue is defined as sum of money that the customers pay for the coffees.
        // - 1 month is defined as 30 days counting back starting from the current date
        // time.
        // - The returned list should be sorted, with ID of the store having thehighest

        // revenue at itshead.
        // - If multiple stores have the same revenue, the order of their ID’s in the
        // returned list can bearbitrary.
        // - If multiple stores have the same revenue in the Kth highest revenue
        // position, their ID’s shouldall be returned.
        // - It returns an empty list if no store is in the database or the operation
        // failed.
        public List<Integer> getTopKStoresInPastXMonth(int k, int x) {
                List<Integer> id = new ArrayList<>();
                return id;
        }

        // @param k - the K in top K
        // @param x - the timespan in month
        // @return a list of ID of top k customers having spent most money in buying
        // coffee in the pastx monthNotes:
        // - 1 month is defined as 30 days counting back starting from the current date

        // time.
        // - The returned list should be sorted, with ID of the customer having spent
        // most money at itshead.
        // - If multiple customers have the same spending, the order of their ID’s in
        // the returned list canbe arbitrary.
        // - If multiple customers have the same spending in the Kth highest spending
        // position, their ID’sshould all be returned.
        // - It returns an empty list if no customer is in the database or the operation
        // failed.
        public List<Integer> getTopKCustomersInPastXMonth(int k, int x) {
                List<Integer> id = new ArrayList<>();
                return id;
        }

}
import java.sql.Date;
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
        private int result;

        public BoutiqueCoffee(String username, String password) {
                scan = new Scanner(System.in);
                statement = null;
                connection = null;
                prepStatement = null;
                rs = null;
                query = "";
                result = -1;

                try {
                        String url = "jdbc:postgresql://localhost:5432/";
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
                try {
                        query = "INSERT INTO store (\"store_id\",\"name\", \"address\", \"store_type\", \"gps_long\", \"gps_lat\") VALUES (DEFAULT,?,?,?,?,?) RETURNING store_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, name);
                        prepStatement.setString(2, address);
                        prepStatement.setString(3, storeType);
                        prepStatement.setDouble(4, gpsLong);
                        prepStatement.setDouble(5, gpsLat);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }
                        return result;

                } catch (Exception e) {
                        return -1;
                }
        }

        // @return the auto-generated ID of this coffee or -1 if the operation is not
        // possible or failed
        public int addCoffee(String name, String description, int intensity, double price, double rewardPoints,
                        double redeemPoints) {
                try {
                        query = "INSERT INTO coffee (\"coffee_id\", \"name\", \"description\", \"intensity\", \"price\", \"reward_points\", \"redeem_points\") VALUES (DEFAULT,?,?,?,?,?,?) RETURNING coffee_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, name);
                        prepStatement.setString(2, description);
                        prepStatement.setInt(3, intensity);
                        prepStatement.setDouble(4, price);
                        prepStatement.setDouble(5, rewardPoints);
                        prepStatement.setDouble(6, redeemPoints);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }
                        return result;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int offerCoffee(int storeId, int coffeeId) {
                try {
                        query = "INSERT INTO offercoffee VALUES (?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, storeId);
                        prepStatement.setInt(2, coffeeId);
                        prepStatement.executeUpdate();
                        return 1;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return the auto-generated ID of this promotion or -1 if the operation is not
        // possible or failed
        public int addPromotion(String name, Date startDate, Date endDate) {
                try {
                        query = "INSERT INTO promotion (\"promotion_id\", \"name\", \"start_date\", \"end_date\") VALUES (DEFAULT,?,?,?) RETURNING promotion_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, name);
                        prepStatement.setDate(2, startDate);
                        prepStatement.setDate(3, endDate);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }
                        return result;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int promoteFor(int promotionId, int coffeeId) {
                try {
                        query = "INSERT INTO promotefor VALUES (?,?)";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, promotionId);
                        prepStatement.setInt(2, coffeeId);
                        prepStatement.executeUpdate();

                        query = "select store_id, promotion_id from offerCoffee NATURAL JOIN promoteFor EXCEPT SELECT * FROM haspromotion";
                        prepStatement = connection.prepareStatement(query);
                        rs = prepStatement.executeQuery();
                        while (rs.next()) {
                                query = "INSERT INTO haspromotion VALUES (?,?)";
                                prepStatement = connection.prepareStatement(query);
                                prepStatement.setInt(1, rs.getInt(1));
                                prepStatement.setInt(2, rs.getInt(2));
                                prepStatement.executeUpdate();
                        }
                        return 1;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return 1 if the operation succeeded or -1 if the operation is not possible
        // or failed
        public int hasPromotion(int storeId, int promotionId) {
                try {
                        query = "SELECT * FROM haspromotion WHERE store_Id = ? AND promotion_id = ?";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, storeId);
                        prepStatement.setInt(2, promotionId);
                        rs = prepStatement.executeQuery();

                        if (rs.next())
                                return 1;
                        else
                                return -1;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return the auto-generated ID of this member level or -1 if the operation is
        // not possible orfailed
        public int addMemberLevel(String name, double boosterFactor) {
                int result = -1;
                try {
                        query = "INSERT INTO memberlevel (\"memberlevel_id\", \"name\", \"booster_factor\") VALUES (DEFAULT,?,?) RETURNING memberlevel_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, name);
                        prepStatement.setDouble(2, boosterFactor);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }
                        return result;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return the auto-generated ID of this customer or -1 if the operation is not
        // possible or failed
        public int addCustomer(String firstName, String lastName, String email, int memberLevelId, double totalPoints) {
                int result = -1;
                try {
                        query = "INSERT INTO customer (\"customer_id\", \"first_name\", \"last_name\", \"email\", \"memberlevel_id\", \"total_points\") VALUES (DEFAULT,?,?,?,?,?) RETURNING customer_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, firstName);
                        prepStatement.setString(2, lastName);
                        prepStatement.setString(3, email);
                        prepStatement.setInt(4, memberLevelId);
                        prepStatement.setDouble(5, totalPoints);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }
                        return result;
                } catch (Exception e) {
                        return -1;
                }
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
                List<Double> addPoints = new ArrayList<>();
                List<Double> subPoints = new ArrayList<>();

                try {
                        // check 1 to 1 mapping
                        if (coffeeIds.size() != purchaseQuantities.size())
                                throw new IllegalArgumentException();
                        if (coffeeIds.size() != redeemQuantities.size())
                                throw new IllegalArgumentException();

                        // make sure store offers specified coffees
                        for (int i : coffeeIds) {
                                query = "SELECT * FROM offercoffee WHERE store_Id = ? AND coffee_Id = ?";
                                prepStatement = connection.prepareStatement(query);
                                prepStatement.setInt(1, storeId);
                                prepStatement.setInt(2, i);
                                rs = prepStatement.executeQuery();
                                if (rs.next())
                                        System.out.println("Coffee Does Not Exist @ Specified Store");
                                else
                                        return -1;

                        }

                        // get reward points/redeem points from coffee
                        for (int i : coffeeIds) {
                                query = "SELECT reward_points, redeem_points FROM coffee WHERE coffee_Id = ?";
                                prepStatement = connection.prepareStatement(query);
                                prepStatement.setInt(1, i);
                                rs = prepStatement.executeQuery();

                                if (rs.next()) {
                                        addPoints.add(rs.getDouble(1));
                                        subPoints.add(rs.getDouble(2));
                                }
                        }

                        // get membership level
                        int memberlevel = 0;
                        query = "SELECT memberlevel_id FROM customer WHERE customer_id = ?";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, customerId);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                memberlevel = rs.getInt(1);
                        }

                        // get boosterfactor
                        double booster = 0.0;
                        query = "SELECT booster_factor FROM memberlevel WHERE memberlevel_id = ?";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, memberlevel);
                        rs = prepStatement.executeQuery();
                        if (rs.next()) {
                                booster = rs.getInt(1);
                        }

                        // get total customer points
                        double points = getPointsByCustomerId(customerId);
                        double rewardTotal = 0.0;
                        double redeemTotal = 0.0;
                        // int promote = 1;

                        // not enough points to redeem
                        if (points < redeemTotal)
                                throw new IllegalArgumentException();

                        connection.setAutoCommit(false);

                        // otherwise add it to the purchase table
                        query = "INSERT INTO purchase (\"purchase_id\", \"customer_id\", \"store_id\", \"purchase_time\") VALUES (DEFAULT,?,?,?) RETURNING purchase_id";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, customerId);
                        prepStatement.setInt(2, storeId);
                        prepStatement.setDate(3, purchaseTime);
                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                result = rs.getInt(1);
                        }

                        double update = points - redeemTotal;
                        query = "UPDATE customer SET total_points = ? WHERE customer_id = ?";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setDouble(1, update);
                        prepStatement.setInt(2, customerId);
                        prepStatement.executeUpdate();

                        for (int i = 0; i < coffeeIds.size(); i++) {
                                query = "INSERT INTO buycoffee VALUES (?,?,?,?)";
                                prepStatement = connection.prepareStatement(query);
                                prepStatement.setInt(1, result);
                                prepStatement.setInt(2, coffeeIds.get(i));
                                prepStatement.setInt(3, purchaseQuantities.get(i));
                                prepStatement.setInt(4, redeemQuantities.get(i));
                                prepStatement.executeUpdate();
                        }
                        connection.commit();
                        return result;
                } catch (Exception e) {
                        return -1;
                }
        }

        // @return a list of ID of all coffees in the database.
        // It returns an empty list if no coffee is in thedatabase or the operation
        // failed.
        public List<Integer> getCoffees() {
                List<Integer> id = new ArrayList<>();
                try {

                        // get the ids of all coffees
                        statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT (Coffee_ID) FROM Coffee");

                        // iterate through each row of rs and add coffee_id to id list
                        while (rs.next()) {
                                int i = rs.getInt("Coffee_ID");
                                id.add(i);
                        }
                        statement.close();

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return id;
        }

        // @return a list of ID of all coffees, each of which has both keywords in its
        // name, in the database.
        // It returns an empty list if no coffee satisfying the conditions is in the
        // database or the operation failed.
        public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2) {
                List<Integer> id = new ArrayList<>();
                try {

                        // get the ids of all coffees satisfying condition
                        query = "SELECT (Coffee_ID) " + "FROM Coffee " + "WHERE Name LIKE '%?%' AND Name LIKE '%?%'";

                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setString(1, keyword1);
                        prepStatement.setString(2, keyword2);

                        rs = prepStatement.executeQuery();

                        // iterate through each row of rs and add coffee_id to id list
                        while (rs.next()) {
                                int i = rs.getInt("Coffee_ID");
                                id.add(i);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return id;
        }

        // @return the total points of the customer identified by the customerId or -1
        // if the operation isnot possible or failed
        public double getPointsByCustomerId(int customerId) {
                double points = -1;
                try {

                        // get the ids of all coffees satisfying condition
                        query = "SELECT (Total_Points) " + "FROM Customer " + "WHERE Customer_ID = ?";
                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, customerId);

                        rs = prepStatement.executeQuery();

                        if (rs.next()) {
                                points = rs.getDouble("Total_Points");
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return points;
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
                try {

                        // past number of days
                        int days = 30 * x;

                        query = "SELECT store_id, sum(purchase_quantity) FROM purchase JOIN buycoffee b ON purchase.purchase_id = b.purchase_id"
                                        + "WHERE current_date - purchase_time <= ?" + "GROUP BY store_id"
                                        + "ORDER BY sum(b.purchase_quantity) DESC" + "LIMIT ?";

                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, days);
                        prepStatement.setInt(2, k);

                        rs = prepStatement.executeQuery();

                        // iterate through each row of rs and add coffee_id to id list
                        while (rs.next()) {
                                int i = rs.getInt("Store_ID");
                                id.add(i);
                        }
                        return id;

                } catch (Exception e) {
                        return null;
                }
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

                try {

                        // past number of days
                        int days = 30 * x;

                        query = "SELECT customer_id, sum(purchase_quantity) FROM purchase JOIN buycoffee b ON purchase.purchase_id = b.purchase_id"
                                        + "WHERE current_date - purchase_time <= ?" + "GROUP BY customer_id"
                                        + "ORDER BY sum(b.purchase_quantity) DESC" + "LIMIT ?";

                        prepStatement = connection.prepareStatement(query);
                        prepStatement.setInt(1, days);
                        prepStatement.setInt(2, k);

                        rs = prepStatement.executeQuery();

                        // iterate through each row of rs and add coffee_id to id list
                        while (rs.next()) {
                                int i = rs.getInt("Customer_ID");
                                id.add(i);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return id;
        }

}
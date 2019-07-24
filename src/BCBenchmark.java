import java.io.Console;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/*
 * This is a benchmark program to stress test stability of system
 * It will call each method from BoutiqueCoffee multiple times 
 * 
 */



public class BCBenchmark {
	public static void main(String[] args) {
	
	    BoutiqueCoffee bc = new BoutiqueCoffee("postgres", "postgres");
	        
	    // add store
	    bc.addStore("Store1", "1234", "", 0, 0);
	    bc.addStore("Store2", "5678", "", 0, 0);
	    bc.addStore("Store3", "9101", "", 0, 0);
	    
	    
	    // get coffee and add coffee
	    bc.getCoffees();
	    bc.addCoffee("coffee1", "", 1, 5, 10, 10);
	    bc.getCoffeesByKeywords("coffee1", "coffee2");
	    bc.getCoffees();
	    bc.addCoffee("coffee2", "", 2, 6, 10, 10);
	    bc.getCoffeesByKeywords("mocha", "coffee");
	    bc.getCoffees();
	    bc.addCoffee("coffee3", "", 3, 7, 10, 10);
	    bc.getCoffeesByKeywords("frap", "coffee3");
	    bc.getCoffees();
	    
	    
	    // offer coffee 
	    bc.offerCoffee(10, 5);
	    bc.offerCoffee(15, 10);
	    bc.offerCoffee(18, 15);
	    
	    
	    // add promotion
	    bc.addPromotion("promotion1", Date.valueOf("2017-06-05"), null);
	    bc.addPromotion("promotion2", Date.valueOf("2017-06-05"), null);
	    bc.addPromotion("promotion3", Date.valueOf("2017-06-05"), null);
	    
	    
	    // promote for
	    bc.promoteFor(5, 7);
	    bc.promoteFor(3, 6);
	    bc.promoteFor(1, 10);
	    
	    
	    // has promotion
	    bc.hasPromotion(3, 5);
	    bc.hasPromotion(5, 3);
	    bc.hasPromotion(10, 15);
	    
	    
	    // add member level
	    bc.addMemberLevel("expert", 2.5);
	    bc.addMemberLevel("platinum", 4);
	    bc.addMemberLevel("gold", 3);
	    
	    
	    // add customers
	    bc.addCustomer("Brian", "Torpey", "brt52", 1, 10);
	    bc.addCustomer("Joe", "Shmo", "asdf", 3, 15);
	    bc.addCustomer("Random", "Person", "jkl", 2, 20);
	    
	    
	    // add purchases
	    bc.addPurchase(7, 3, Date.valueOf("2017-06-05"), null, null, null);
	    bc.addPurchase(8, 4, Date.valueOf("2018-08-10"), null, null, null);
	    bc.addPurchase(9, 5, Date.valueOf("2012-03-01"), null, null, null);	    
	    
	    
	    // get points by customer id
	    bc.getPointsByCustomerId(10);
	    bc.getPointsByCustomerId(20);
	    bc.getPointsByCustomerId(5);
	    
	    // get top k stores in past x month
	    bc.getTopKStoresInPastXMonth(3, 15);
	    bc.getTopKStoresInPastXMonth(6, 30);
	    bc.getTopKStoresInPastXMonth(9, 45);
	    
	    
	    // get top k customers in past x month
	    bc.getTopKCustomersInPastXMonth(10, 15);
	    bc.getTopKCustomersInPastXMonth(15, 30);
	    bc.getTopKCustomersInPastXMonth(20, 45);
	    
	    
	   
	    
		
		
		
		
		
		
	}

}

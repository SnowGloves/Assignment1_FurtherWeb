/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {

    // Function to search by customer ID and find related claim IDs
    public void searchByCustomer(String customerId) {
        File customerFile = new File("src/customer.txt");
        File claimFile = new File("src/claim.txt");
        try {
            Scanner customerScanner = new Scanner(customerFile);
            List<String> cardNumbers = new ArrayList<>();

            // First, find the card number associated with the customer ID
            boolean customerFound = false;
            while (customerScanner.hasNextLine()) {
                String line = customerScanner.nextLine();
                String[] details = line.split(",");
                if (details[0].trim().equalsIgnoreCase(customerId)) {
                    System.out.println("Customer Details Found:");
                    System.out.println("Customer ID: " + details[0].trim());
                    System.out.println("Full Name: " + details[1].trim());
                    System.out.println("Insurance Card ID: " + details[2].trim());
                    cardNumbers.add(details[2].trim());
                    customerFound = true;
                    break;
                }
            }
            if (!customerFound) {
                System.out.println("No details found for customer ID: " + customerId);
                return;
            }

            // Now, search for all claims associated with the card number
            Scanner claimScanner = new Scanner(claimFile);
            boolean claimFound = false;
            while (claimScanner.hasNextLine()) {
                String line = claimScanner.nextLine();
                String[] details = line.split(",");
                if (cardNumbers.contains(details[3].trim())) {
                    System.out.println("Related Claim ID: " + details[0].trim());
                    claimFound = true;
                }
            }
            if (!claimFound) {
                System.out.println("No related claims found for customer ID: " + customerId);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while accessing the files.");
            e.printStackTrace();
        }
    }
}

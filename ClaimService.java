/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimService {

    public void searchByClaimId(String claimId) {
        File file = new File("src/claim.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Assuming the claim ID is the first item on each line
                if (line.startsWith(claimId)) {
                    System.out.println("Claim Details Found:");
                    System.out.println(line);

                    // Extract the card number from the claim details
                    String cardNumber = extractDetail(line, 3);

                    // Generate the list of documents following the format ClaimId_CardNumber_DocumentName.pdf
                    List<String> documents = generateListOfDocuments(claimId, cardNumber);

                    System.out.println("List of Documents:");
                    documents.forEach(System.out::println);

                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No details found for Claim ID: " + claimId);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while accessing claim.txt.");
            e.printStackTrace();
        }
    }

    private String extractDetail(String line, int detailIndex) {
        String[] details = line.split(",");
        return details.length > detailIndex ? details[detailIndex].trim() : "";
    }

    private List<String> generateListOfDocuments(String claimId, String cardNumber) {
        // Placeholder list of document names for demonstration purposes
        // In a real scenario, you would retrieve these names from a data store or generate them based on existing criteria
        List<String> documentNames = new ArrayList<>();
        documentNames.add(claimId + "_" + cardNumber + "_Document1.pdf");

        // Add logic to generate the actual document names based on your application's requirements
        return documentNames;
    }
}

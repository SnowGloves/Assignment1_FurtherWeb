/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        CardService cardService = new CardService();
        ClaimService claimService = new ClaimService();
        ClaimProcessManager claimManager = new ClaimServiceImpl();


        while (true) {
            System.out.println("Please choose your option:");
            System.out.println("1: Search by customer");
            System.out.println("2: Search by insurance");
            System.out.println("3: Search by claim");
            System.out.println("4: Manage the claim lists");
            System.out.println("0: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID (c- followed by 7 numbers): ");
                    String customerId = scanner.nextLine();
                    customerService.searchByCustomer(customerId);
                    break;
                case 2:
                    System.out.print("Enter Card ID (10 digits): ");
                    String cardId = scanner.nextLine();
                    CardService.searchByCardId(cardId);
                    break;
                case 3:
                    System.out.print("Enter Claim ID (f- followed by 10 numbers): ");
                    String claimId = scanner.nextLine();
                    claimService.searchByClaimId(claimId);
                    break;
                case 4:
                    manageClaims(scanner, claimManager);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 0 to exit.");
            }
        }

    }
    private static void manageClaims(Scanner scanner, ClaimProcessManager claimManager) {
        int manageChoice;
        do {
            System.out.println("Claim Management:");
            System.out.println("1: Add a claim");
            System.out.println("2: Update a claim");
            System.out.println("3: Delete a claim");
            System.out.println("4: Get one claim");
            System.out.println("5: Get all claims");
            System.out.println("0: Return to main menu");
            System.out.print("Enter your choice: ");
            manageChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (manageChoice) {
                case 1:
                    Claim claim = new Claim();
                    System.out.print("Please input the claim ID: ");
                    claim.setId(scanner.nextLine());

                    System.out.print("Please input the claim date (YYYY-MM-DD): ");
                    claim.setClaimDate(scanner.nextLine());

                    System.out.print("Please input the insured person's name: ");
                    claim.setInsuredPerson(scanner.nextLine());

                    System.out.print("Please input the card number: ");
                    claim.setCardNumber(scanner.nextLine());

                    System.out.print("Please input the exam date (YYYY-MM-DD): ");
                    claim.setExamDate(scanner.nextLine());

                    System.out.print("Please input the claim amount: ");
                    claim.setClaimAmount(scanner.nextDouble());
                    scanner.nextLine(); // Consume newline left-over

                    System.out.print("Please input the status (New, Processing, Done): ");
                    claim.setStatus(scanner.nextLine());

                    System.out.print("Please input the receiver's banking info (Bank – Name – Number): ");
                    claim.setReceiverBankingInfo(scanner.nextLine());

                    System.out.println("Please input the list of documents separated by semicolon (;): ");
                    String documentsInput = scanner.nextLine();
                    List<String> documents = Arrays.asList(documentsInput.split(";"));
                    claim.setDocuments(documents);

                    claimManager.add(claim);
                    System.out.println("Claim added successfully!");
                    break;

                case 2:
                    System.out.print("Enter the Claim ID you wish to update: ");
                    String claimId = scanner.nextLine();

                    System.out.println("Which information would you like to update?");
                    System.out.println("1: Update claim date");
                    System.out.println("2: Update insured person");
                    System.out.println("3: Update card number");
                    System.out.println("4: Update exam date");
                    System.out.println("5: Update claim amount");
                    System.out.println("6: Update status");
                    System.out.println("7: Update receiver's banking info");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    Claim claimToUpdate = new Claim();
                    switch (updateChoice) {
                        case 1:
                            System.out.print("Enter the new claim date (YYYY-MM-DD): ");
                            claimToUpdate.setClaimDate(scanner.nextLine());
                            break;
                        case 2:
                            System.out.print("Enter the new insured person's name: ");
                            claimToUpdate.setInsuredPerson(scanner.nextLine());
                            break;
                        case 3:
                            System.out.print("Enter the new card number: ");
                            claimToUpdate.setCardNumber(scanner.nextLine());
                            break;
                        case 4:
                            System.out.print("Enter the new exam date (YYYY-MM-DD): ");
                            claimToUpdate.setExamDate(scanner.nextLine());
                            break;
                        case 5:
                            System.out.print("Enter the new claim amount: ");
                            claimToUpdate.setClaimAmount(scanner.nextDouble());
                            scanner.nextLine(); // Consume newline left-over
                            break;
                        case 6:
                            System.out.print("Enter the new status: ");
                            claimToUpdate.setStatus(scanner.nextLine());
                            break;
                        case 7:
                            System.out.print("Enter the new receiver's banking info: ");
                            claimToUpdate.setReceiverBankingInfo(scanner.nextLine());
                            break;
                    }

                    claimManager.update(claimId, claimToUpdate);
                    System.out.println("Claim updated successfully!");
                    break;
                case 3:
                    System.out.print("Enter the Claim ID you wish to delete: ");
                    String claimIdToDelete = scanner.nextLine();
                    claimManager.delete(claimIdToDelete);
                    break;

                case 4:
                    System.out.print("Enter the Claim ID you wish to retrieve: ");
                    String claimIdToGet = scanner.nextLine();
                    claim = claimManager.getOne(claimIdToGet);
                    if (claim != null) {
                        System.out.println("Claim details:");
                        System.out.println("ID: " + claim.getId());
                        System.out.println("Claim Date: " + claim.getClaimDate());
                        System.out.println("Insured Person: " + claim.getInsuredPerson());
                        System.out.println("Card Number: " + claim.getCardNumber());
                        System.out.println("Exam Date: " + claim.getExamDate());
                        System.out.println("Claim Amount: " + claim.getClaimAmount());
                        System.out.println("Status: " + claim.getStatus());
                        System.out.println("Receiver's Banking Info: " + claim.getReceiverBankingInfo());
                    } else {
                        System.out.println("Claim with ID " + claimIdToGet + " not found.");
                    }
                    break;

                case 5:
                    List<Claim> allClaims = claimManager.getAll();
                    if (allClaims.isEmpty()) {
                        System.out.println("There are no claims to display.");
                    } else {
                        System.out.println("All claims:");
                        for (Claim c : allClaims) {
                            System.out.println("ID: " + c.getId());
                            System.out.println("Claim Date: " + c.getClaimDate());
                            System.out.println("Insured Person: " + c.getInsuredPerson());
                            System.out.println("Card Number: " + c.getCardNumber());
                            System.out.println("Exam Date: " + c.getExamDate());
                            System.out.println("Claim Amount: " + c.getClaimAmount());
                            System.out.println("Status: " + c.getStatus());
                            System.out.println("Receiver's Banking Info: " + c.getReceiverBankingInfo());
                            System.out.println(); // Print a blank line between claims
                        }
                    }
                    break;
                case 0:
                    // Exit the claim management submenu
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (manageChoice != 0);
    }
}

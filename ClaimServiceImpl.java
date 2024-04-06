/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ClaimServiceImpl implements ClaimProcessManager {
    private static final String CLAIMS_FILE = "src/claim.txt";

    @Override
    public void add(Claim claim) {
        try (FileWriter fw = new FileWriter(CLAIMS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String claimData = String.join(",",
                    claim.getId(),
                    claim.getClaimDate(),
                    claim.getInsuredPerson(),
                    claim.getCardNumber(),
                    claim.getExamDate(),
                    claim.getClaimAmount(),
                    claim.getStatus(),
                    claim.getReceiverBankingInfo(),
                    String.join(";", claim.getDocuments())); // Assuming documents are separated by semicolons
            out.println(claimData);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the claims file.");
            e.printStackTrace();
        }

    }

    @Override
    public void update(String claimId, Claim updatedClaim) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(CLAIMS_FILE)));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).startsWith(claimId)) {
                    // Assume that the claim details are comma-separated in the file
                    String[] data = fileContent.get(i).split(",");
                    if (updatedClaim.getClaimDate() != null) data[1] = (String) updatedClaim.getClaimDate();
                    if (updatedClaim.getInsuredPerson() != null) data[2] = (String) updatedClaim.getInsuredPerson();
                    if (updatedClaim.getCardNumber() != null) data[3] = (String) updatedClaim.getCardNumber();
                    if (updatedClaim.getExamDate() != null) data[4] = (String) updatedClaim.getExamDate();
                    if (updatedClaim.getClaimAmount() != null) data[5] = updatedClaim.getClaimAmount();
                    if (updatedClaim.getStatus() != null) data[6] = (String) updatedClaim.getStatus();
                    if (updatedClaim.getReceiverBankingInfo() != null)
                        data[7] = (String) updatedClaim.getReceiverBankingInfo();
                    // Reconstruct the claim line and update it
                    fileContent.set(i, String.join(",", data));
                    break;
                }
            }
            // Write the new content to the file
            Files.write(Paths.get(CLAIMS_FILE), fileContent);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the claims file.");
            e.printStackTrace();
        }

    }
    @Override
    public void delete(String claimId) {
        Path pathToFile = Paths.get(CLAIMS_FILE);
        try {
            List<String> fileContent = Files.readAllLines(pathToFile);
            List<String> updatedContent = fileContent.stream()
                    .filter(line -> !line.startsWith(claimId))
                    .collect(Collectors.toList());

            Files.write(pathToFile, updatedContent);
            System.out.println("Claim with ID " + claimId + " has been deleted.");

        } catch (IOException e) {
            System.err.println("An error occurred while attempting to delete the claim.");
            e.printStackTrace();
        }
    }

        @Override
        public Claim getOne(String claimId) {
            Path pathToFile = Paths.get(CLAIMS_FILE);
            try {
                // Read all lines from the file into a List
                List<String> fileContent = Files.readAllLines(pathToFile);

                // Find the first line that matches the claim ID
                for (String line : fileContent) {
                    if (line.startsWith(claimId)) {
                        // Assuming that the claim details are comma-separated in the file
                        String[] data = line.split(",");
                        // Create and return a Claim object with the data
                        Claim claim = new Claim(data[0], data[1], data[2], data[3], data[4],
                                Double.parseDouble(data[5]), data[6], data[7]);
                        return claim;
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while trying to read the claims file.");
                e.printStackTrace();
            }
            return null; // Return null if the claim ID is not found
        }

            @Override
            public List<Claim> getAll() {
                Path pathToFile = Paths.get(CLAIMS_FILE);
                try {
                    List<String> lines = Files.readAllLines(pathToFile);

                    // If there's a header, skip it
                    lines.removeIf(line -> line.startsWith("id,")); // or whatever the header starts with

                    return lines.stream().map(line -> {
                        String[] data = line.split(",");
                        Double claimAmount = null;
                        try {
                            claimAmount = Double.parseDouble(data[5]); // Assuming claim amount is at index 5
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing claim amount: " + data[5]);
                        }
                        return new Claim(data[0], data[1], data[2], data[3], data[4],
                                claimAmount, data[6], data[7]);
                    }).collect(Collectors.toList());
                } catch (IOException e) {
                    System.err.println("An error occurred while reading the claims file.");
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }
}

/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardService {

    public static void searchByCardId(String cardId) {
        File file = new File("src/card.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Assuming the card number is the first item on each line
                if (line.startsWith(cardId)) {
                    System.out.println("Card Details Found: ");
                    System.out.println(line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No details found for Card ID: " + cardId);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while accessing card.txt.");
            e.printStackTrace();
        }
    }
}

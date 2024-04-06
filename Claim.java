/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.util.List;

public class Claim {
    private String id;
    private String claimDate;
    private String insuredPerson;
    private String cardNumber;
    private String examDate;

    private double claimAmount;
    private String status; // New, Processing, Done
    private String receiverBankingInfo; // Bank – Name – Number



    public Claim() {

    }

    public Claim(String datum, String datum1, String datum2, String datum3, String datum4, Double claimAmount, String datum5, String datum6) {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setClaimDate(String s) {
    }

    public void setInsuredPerson(String s) {
    }

    public void setCardNumber(String s) {
    }

    public void setExamDate(String s) {
    }

    public void setClaimAmount(String v) {
    }

    public void setStatus(String s) {
    }

    public void setReceiverBankingInfo(String s) {
    }

    public void setDocuments(List<String> documents) {
    }

    public String  getClaimDate() {
        return claimDate;
    }

    public CharSequence getInsuredPerson() {
        return insuredPerson;
    }

    public CharSequence getCardNumber() {
        return cardNumber;
    }

    public CharSequence getExamDate() {
        return examDate;
    }

    public String getClaimAmount() {
        return String.valueOf(claimAmount);
    }
    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public CharSequence getStatus() {
        return status;
    }

    public CharSequence getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public CharSequence getDocuments() {
        return null;
    }
}

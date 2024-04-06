/**
 * @author <Kang Hyeonseok - s3963294>
 */
import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(String claimId, Claim updatedClaim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
}


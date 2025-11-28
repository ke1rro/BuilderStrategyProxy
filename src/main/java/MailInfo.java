import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailInfo {
    private final Client client;
    private final MailCode mailCode;
}

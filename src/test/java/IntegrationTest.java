import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    void testAllPatternsIntegration() {
        User user = User.builder()
                .name("Integration Test User")
                .age(30)
                .gender(Gender.MALE)
                .build();
        assertNotNull(user);

        Client client = new Client("Test Client", 25, Gender.FEMALE);
        MailBox mailBox = new MailBox();
        mailBox.addMailInfo(new MailInfo(client, MailCode.BIRTHDAY));
        assertDoesNotThrow(() -> mailBox.sendAll());

        MyImage proxyImage = new ProxyImage("test.jpg");
        assertNotNull(proxyImage);
    }
}

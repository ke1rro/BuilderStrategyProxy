import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MailSystemTest {

    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        client1 = new Client("Alice", 28, Gender.FEMALE);
        client2 = new Client("Bob", 65, Gender.MALE);
        client3 = new Client("Charlie", 15, Gender.MALE);
    }

    @Test
    void testClientIdAutoIncrement() {
        Client c1 = new Client("Test1", 20, Gender.MALE);
        Client c2 = new Client("Test2", 25, Gender.FEMALE);
        Client c3 = new Client("Test3", 30, Gender.MALE);

        assertTrue(c2.getId() > c1.getId());
        assertTrue(c3.getId() > c2.getId());
    }

    @Test
    void testClientProperties() {
        assertEquals("Alice", client1.getName());
        assertEquals(28, client1.getAge());
        assertEquals(Gender.FEMALE, client1.getSex());
    }

    @Test
    void testMailInfoCreation() {
        MailInfo mailInfo = new MailInfo(client1, MailCode.BIRTHDAY);

        assertEquals(client1, mailInfo.getClient());
        assertEquals(MailCode.BIRTHDAY, mailInfo.getMailCode());
    }

    @Test
    void testBirthdayMailStrategy() {
        MailStrategy strategy = new BirthdayMailStrategy();
        String content = strategy.generateMailContent(client1);

        assertTrue(content.contains("Alice"));
        assertTrue(content.contains("Birthday"));
        assertTrue(content.contains("Ms."));
    }

    @Test
    void testGiftMailStrategy() {
        MailStrategy strategy = new GiftMailStrategy();

        String contentAdult = strategy.generateMailContent(client2);
        assertTrue(contentAdult.contains("Bob"));
        assertTrue(contentAdult.contains("voucher"));

        String contentChild = strategy.generateMailContent(client3);
        assertTrue(contentChild.contains("toy"));
    }

    @Test
    void testPromotionMailStrategy() {
        MailStrategy strategy = new PromotionMailStrategy();

        String contentSenior = strategy.generateMailContent(client2);
        assertTrue(contentSenior.contains("25%"));

        String contentRegular = strategy.generateMailContent(client1);
        assertTrue(contentRegular.contains("15%"));
    }

    @Test
    void testNewsletterMailStrategy() {
        MailStrategy strategy = new NewsletterMailStrategy();
        String content = strategy.generateMailContent(client1);

        assertTrue(content.contains("Newsletter"));
        assertTrue(content.contains("Alice"));
    }

    @Test
    void testMailStrategyFactory() {
        MailStrategy birthdayStrategy = MailStrategyFactory.getStrategy(MailCode.BIRTHDAY);
        assertInstanceOf(BirthdayMailStrategy.class, birthdayStrategy);

        MailStrategy giftStrategy = MailStrategyFactory.getStrategy(MailCode.GIFT);
        assertInstanceOf(GiftMailStrategy.class, giftStrategy);

        MailStrategy promotionStrategy = MailStrategyFactory.getStrategy(MailCode.PROMOTION);
        assertInstanceOf(PromotionMailStrategy.class, promotionStrategy);

        MailStrategy newsletterStrategy = MailStrategyFactory.getStrategy(MailCode.NEWSLETTER);
        assertInstanceOf(NewsletterMailStrategy.class, newsletterStrategy);
    }

    @Test
    void testMailSenderSendMail() {
        MailSender sender = new MailSender();
        MailInfo mailInfo = new MailInfo(client1, MailCode.BIRTHDAY);

        assertDoesNotThrow(() -> sender.sendMail(mailInfo));
    }

    @Test
    void testMailBoxAddAndSend() {
        MailBox mailBox = new MailBox();

        mailBox.addMailInfo(new MailInfo(client1, MailCode.BIRTHDAY));
        mailBox.addMailInfo(new MailInfo(client2, MailCode.GIFT));

        assertDoesNotThrow(() -> mailBox.sendAll());
    }

    @Test
    void testMailBoxMultipleSends() {
        MailBox mailBox = new MailBox();

        mailBox.addMailInfo(new MailInfo(client1, MailCode.BIRTHDAY));
        mailBox.sendAll();

        mailBox.addMailInfo(new MailInfo(client2, MailCode.GIFT));
        assertDoesNotThrow(() -> mailBox.sendAll());
    }

    @Test
    void testGenderBasedGreeting() {
        MailStrategy strategy = new BirthdayMailStrategy();

        String maleMail = strategy.generateMailContent(client2);
        assertTrue(maleMail.contains("Mr."));

        String femaleMail = strategy.generateMailContent(client1);
        assertTrue(femaleMail.contains("Ms."));
    }
}

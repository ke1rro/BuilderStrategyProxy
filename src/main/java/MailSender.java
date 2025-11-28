public class MailSender {
    public void sendMail(MailInfo mailInfo) {
        MailStrategy strategy = MailStrategyFactory.getStrategy(mailInfo.getMailCode());
        String content = strategy.generateMailContent(mailInfo.getClient());

        System.out.println("========================================");
        System.out.println("Sending email to: " + mailInfo.getClient().getName());
        System.out.println("Email: client" + mailInfo.getClient().getId() + "@example.com");
        System.out.println("Mail Type: " + mailInfo.getMailCode());
        System.out.println("----------------------------------------");
        System.out.println(content);
        System.out.println("========================================\n");
    }
}

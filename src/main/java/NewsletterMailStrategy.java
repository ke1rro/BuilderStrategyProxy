public class NewsletterMailStrategy implements MailStrategy {
    @Override
    public String generateMailContent(Client client) {
        String greeting = client.getSex() == Gender.MALE ? "Dear Mr. " : "Dear Ms. ";

        return String.format("%s%s,\n\n" +
                "Monthly Newsletter 📰\n" +
                "Stay updated with our latest news, products, and services.\n" +
                "This month we have exciting updates just for you!\n\n" +
                "Best regards,\n" +
                "Your Team",
                greeting, client.getName());
    }
}

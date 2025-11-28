public class PromotionMailStrategy implements MailStrategy {
    @Override
    public String generateMailContent(Client client) {
        String greeting = client.getSex() == Gender.MALE ? "Dear Mr. " : "Dear Ms. ";
        int discount = client.getAge() > 60 ? 25 : 15;

        return String.format("%s%s,\n\n" +
                "Special Promotion Alert! 🎊\n" +
                "Enjoy %d%% off on all products this week!\n" +
                "Don't miss this exclusive opportunity to save big.\n\n" +
                "Happy shopping,\n" +
                "Your Team",
                greeting, client.getName(), discount);
    }
}

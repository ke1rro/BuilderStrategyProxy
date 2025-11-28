public class GiftMailStrategy implements MailStrategy {
    @Override
    public String generateMailContent(Client client) {
        String greeting = client.getSex() == Gender.MALE ? "Dear Mr. " : "Dear Ms. ";
        String giftSuggestion = client.getAge() < 18 ? "a special toy" : "an exclusive voucher";

        return String.format("%s%s,\n\n" +
                "Great news! We have %s waiting for you! 🎁\n" +
                "Visit our store to claim your special gift.\n" +
                "This offer is exclusively for you as our valued customer.\n\n" +
                "Warm regards,\n" +
                "Your Team",
                greeting, client.getName(), giftSuggestion);
    }
}

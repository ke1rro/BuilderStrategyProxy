public class MailStrategyFactory {
    public static MailStrategy getStrategy(MailCode mailCode) {
        switch (mailCode) {
            case BIRTHDAY:
                return new BirthdayMailStrategy();
            case GIFT:
                return new GiftMailStrategy();
            case PROMOTION:
                return new PromotionMailStrategy();
            case NEWSLETTER:
                return new NewsletterMailStrategy();
            default:
                throw new IllegalArgumentException("Unknown mail code: " + mailCode);
        }
    }
}

public class BirthdayMailStrategy implements MailStrategy {
    @Override
    public String generateMailContent(Client client) {
        String greeting = client.getSex() == Gender.MALE ? "Dear Mr. " : "Dear Ms. ";
        return String.format("%s%s,\n\n" +
                "Happy Birthday! 🎉\n" +
                "Wishing you a wonderful day filled with joy and happiness.\n" +
                "May this year bring you prosperity and success!\n\n" +
                "Best regards,\n" +
                "Your Team",
                greeting, client.getName());
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Lab 11: Design Patterns Demo ===\n");

        demonstrateBuilderPattern();
        demonstrateStrategyPattern();
        demonstrateProxyPattern();
    }

    private static void demonstrateBuilderPattern() {
        System.out.println("=== TASK 1: BUILDER PATTERN ===");
        System.out.println("Problem: User class had multiple constructors (bloated)");
        System.out.println("Solution: Use Lombok @Builder for flexible object creation\n");

        User user1 = User.builder()
                .name("John Doe")
                .age(25)
                .gender(Gender.MALE)
                .weight(75.5)
                .height(180.0)
                .build();

        User user2 = User.builder()
                .name("Jane Smith")
                .age(30)
                .gender(Gender.FEMALE)
                .build();

        User user3 = User.builder()
                .name("Bob Johnson")
                .gender(Gender.MALE)
                .age(45)
                .build();

        System.out.println("Created users:");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println();
    }

    private static void demonstrateStrategyPattern() {
        System.out.println("=== TASK 2: STRATEGY PATTERN ===");
        System.out.println("Problem: Need flexible system to send different types of emails");
        System.out.println("Solution: Use Strategy pattern for different mail generation strategies\n");

        Client client1 = new Client("Alice Brown", 28, Gender.FEMALE);
        Client client2 = new Client("Charlie Wilson", 65, Gender.MALE);
        Client client3 = new Client("Emma Davis", 15, Gender.FEMALE);

        MailBox mailBox = new MailBox();

        mailBox.addMailInfo(new MailInfo(client1, MailCode.BIRTHDAY));
        mailBox.addMailInfo(new MailInfo(client2, MailCode.PROMOTION));
        mailBox.addMailInfo(new MailInfo(client3, MailCode.GIFT));
        mailBox.addMailInfo(new MailInfo(client1, MailCode.NEWSLETTER));

        mailBox.sendAll();
        System.out.println();
    }

    private static void demonstrateProxyPattern() {
        System.out.println("=== TASK 3: PROXY PATTERN ===");
        System.out.println("Problem: RealImage loads everything in constructor (heavy)");
        System.out.println("Solution: Use ProxyImage for lazy initialization\n");

        System.out.println("Creating ProxyImage...");
        MyImage image = new ProxyImage("sample.jpg");

        System.out.println("\nProxyImage created but RealImage not loaded yet.");
        System.out.println("Heavy work is deferred until display() is called.\n");

        System.out.println("Calling display() for the first time:");
        image.display();

        System.out.println("\nNote: RealImage was created only when needed (lazy initialization)");
        System.out.println("This saves resources if display() is never called.");
        System.out.println();
    }
}

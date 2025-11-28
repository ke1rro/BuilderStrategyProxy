# Lab 11: Design Patterns - Builder, Strategy, and Proxy

[![Java CI with Maven](https://github.com/ke1rro/BuilderStrategyProxy/actions/workflows/maven.yml/badge.svg)](https://github.com/ke1rro/BuilderStrategyProxy/actions/workflows/maven.yml)

This project demonstrates the implementation of three fundamental design patterns in Java: Builder, Strategy, and Proxy patterns.

## 📋 Overview

### Task 1: Builder Pattern
**Problem**: The `User` class had "telescoping constructors" - multiple overloaded constructors to handle different parameter combinations, leading to bloated and hard-to-maintain code.

**Solution**: Implemented the Builder pattern using Lombok's `@Builder` annotation, providing:
- Fluent API for object construction
- Clear indication of which fields are being set
- Easy addition of new optional fields
- Elimination of constructor bloat

**Key Annotations Used**:
- `@Builder` - Generates builder pattern implementation
- `@Getter` - Generates getter methods
- `@ToString` - Generates toString method

### Task 2: Strategy Pattern
**Problem**: Need a flexible system to send different types of emails to clients based on various criteria (age, gender, mail type).

**Solution**: Implemented the Strategy pattern with:
- `MailStrategy` interface defining the contract for mail generation
- Multiple concrete strategies: `BirthdayMailStrategy`, `GiftMailStrategy`, `PromotionMailStrategy`, `NewsletterMailStrategy`
- `MailStrategyFactory` for creating appropriate strategies
- `MailBox` for batch email sending
- Easy extensibility - new mail types can be added without modifying existing code

**Components**:
- `Client` - Customer with auto-incrementing ID
- `MailInfo` - Contains client and mail type information
- `MailSender` - Sends individual emails using strategies
- `MailBox` - Stores and sends multiple emails in batch

### Task 3: Proxy Pattern
**Problem**: `RealImage` class is heavy - it loads the entire image, creates frames, and initializes UI components in the constructor, wasting resources if the image is never displayed.

**Solution**: Implemented the Proxy pattern with `ProxyImage`:
- **Lazy initialization** - RealImage is only created when `display()` is called
- Acts as a lightweight placeholder
- Same interface (`MyImage`) as RealImage
- Transparent to client code
- Saves memory and processing time

## 🏗️ Project Structure

```
lab_11_oop/
├── .github/
│   └── workflows/
│       └── maven.yml          # GitHub Actions CI/CD pipeline
├── src/
│   ├── main/java/
│   │   ├── User.java          # Task 1: Builder pattern
│   │   ├── Client.java        # Task 2: Strategy pattern
│   │   ├── MailCode.java
│   │   ├── MailInfo.java
│   │   ├── MailStrategy.java
│   │   ├── BirthdayMailStrategy.java
│   │   ├── GiftMailStrategy.java
│   │   ├── PromotionMailStrategy.java
│   │   ├── NewsletterMailStrategy.java
│   │   ├── MailStrategyFactory.java
│   │   ├── MailSender.java
│   │   ├── MailBox.java
│   │   ├── MyImage.java       # Task 3: Proxy pattern
│   │   ├── RealImage.java
│   │   ├── ProxyImage.java
│   │   └── Main.java
│   └── test/java/
│       ├── UserTest.java
│       ├── MailSystemTest.java
│       ├── ProxyImageTest.java
│       └── IntegrationTest.java
└── pom.xml
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/ke1rro/BuilderStrategyProxy.git
cd BuilderStrategyProxy
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn exec:java -Dexec.mainClass="Main"
```

## 🧪 Running Tests

Run all tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=UserTest
mvn test -Dtest=MailSystemTest
mvn test -Dtest=ProxyImageTest
```

## 📚 Dependencies

- **Lombok 1.18.30** - For Builder pattern annotations
- **MailJet Client 5.2.5** - Email service API
- **JUnit Jupiter 5.10.1** - Testing framework

## 🎯 Design Patterns Explained

### Builder Pattern
```java
// Before (Bloated):
User user = new User("John", 25, Gender.MALE, 75.5, 180.0);

// After (Clean):
User user = User.builder()
    .name("John")
    .age(25)
    .gender(Gender.MALE)
    .weight(75.5)
    .height(180.0)
    .build();
```

### Strategy Pattern
```java
// Different strategies for different mail types
MailBox mailBox = new MailBox();
mailBox.addMailInfo(new MailInfo(client, MailCode.BIRTHDAY));
mailBox.addMailInfo(new MailInfo(client, MailCode.GIFT));
mailBox.sendAll();
```

### Proxy Pattern
```java
// Lightweight proxy creation
MyImage image = new ProxyImage("large-image.jpg");

// Heavy loading only happens when needed
image.display(); // RealImage created here, not before
```

## 🔄 CI/CD Pipeline

The project includes a GitHub Actions workflow that:
- Runs on every push and pull request to main/master branch
- Sets up Java 11 environment
- Builds the project with Maven
- Runs all tests
- Generates test reports

## 📖 Resources

### Builder Pattern
- [Refactoring Guru - Builder](https://refactoring.guru/design-patterns/builder)
- [Wikipedia - Builder Pattern](https://en.wikipedia.org/wiki/Builder_pattern)
- [Lombok @Builder Documentation](https://projectlombok.org/features/Builder)

### Strategy Pattern
- [Refactoring Guru - Strategy](https://refactoring.guru/design-patterns/strategy)
- [Wikipedia - Strategy Pattern](https://en.wikipedia.org/wiki/Strategy_pattern)

### Proxy Pattern
- [Refactoring Guru - Proxy](https://refactoring.guru/design-patterns/proxy)
- [Wikipedia - Proxy Pattern](https://en.wikipedia.org/wiki/Proxy_pattern)

### MailJet
- [MailJet Official Website](https://www.mailjet.com/)
- [MailJet API Documentation](https://dev.mailjet.com/)

## 👨‍💻 Author

**Nikita Lenyk** (@ke1rro)

## 📝 License

This project is part of an OOP course assignment.

## 🤝 Contributing

This is an educational project. Feel free to fork and experiment with different design patterns!

## ⭐ Acknowledgments

- Design Patterns: Elements of Reusable Object-Oriented Software (Gang of Four)
- Refactoring Guru for excellent design pattern explanations
- Project Lombok for reducing boilerplate code

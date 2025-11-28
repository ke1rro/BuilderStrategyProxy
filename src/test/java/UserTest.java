import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testBuilderWithAllFields() {
        User user = User.builder()
                .name("John Doe")
                .age(25)
                .gender(Gender.MALE)
                .weight(75.5)
                .height(180.0)
                .build();

        assertEquals("John Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals(Gender.MALE, user.getGender());
        assertEquals(75.5, user.getWeight());
        assertEquals(180.0, user.getHeight());
    }

    @Test
    void testBuilderWithPartialFields() {
        User user = User.builder()
                .name("Jane Smith")
                .age(30)
                .build();

        assertEquals("Jane Smith", user.getName());
        assertEquals(30, user.getAge());
        assertEquals(0.0, user.getWeight());
        assertEquals(0.0, user.getHeight());
    }

    @Test
    void testBuilderWithOnlyName() {
        User user = User.builder()
                .name("Bob")
                .build();

        assertEquals("Bob", user.getName());
        assertEquals(0, user.getAge());
        assertNull(user.getGender());
    }

    @Test
    void testBuilderFluentAPI() {
        User user = User.builder()
                .name("Alice")
                .age(28)
                .gender(Gender.FEMALE)
                .weight(60.0)
                .height(165.0)
                .build();

        assertNotNull(user);
        assertEquals("Alice", user.getName());
    }

    @Test
    void testToString() {
        User user = User.builder()
                .name("Charlie")
                .age(35)
                .gender(Gender.MALE)
                .build();

        String toString = user.toString();
        assertTrue(toString.contains("Charlie"));
        assertTrue(toString.contains("35"));
        assertTrue(toString.contains("MALE"));
    }
}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class ProxyImageTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testProxyImageCreationIsLightweight() {
        MyImage proxyImage = new ProxyImage("test.jpg");

        String output = outContent.toString();

        assertTrue(output.contains("ProxyImage created"));
        assertFalse(output.contains("Loading image from disk"));
        assertNotNull(proxyImage);
    }

    @Test
    void testProxyImplementsMyImageInterface() {
        MyImage proxyImage = new ProxyImage("test.jpg");

        assertInstanceOf(MyImage.class, proxyImage);
    }

    @Test
    void testDifferentFilenames() {
        MyImage proxy1 = new ProxyImage("image1.jpg");
        MyImage proxy2 = new ProxyImage("image2.png");

        assertNotNull(proxy1);
        assertNotNull(proxy2);
    }
}

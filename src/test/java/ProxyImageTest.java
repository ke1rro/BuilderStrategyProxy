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
        String output = outContent.toString();

        MyImage proxyImage = new ProxyImage("test.jpg");

        output = outContent.toString();

        assertTrue(output.contains("ProxyImage created"));
        assertFalse(output.contains("Loading image from disk"));
    }

    @Test
    void testLazyInitialization() {
        MyImage proxyImage = new ProxyImage("test.jpg");

        outContent.reset();

        assertDoesNotThrow(() -> proxyImage.display());

        String output = outContent.toString();
        assertTrue(output.contains("Creating RealImage") || output.contains("Loading"));
    }

    @Test
    void testProxyImplementsMyImageInterface() {
        MyImage proxyImage = new ProxyImage("test.jpg");

        assertInstanceOf(MyImage.class, proxyImage);
    }

    @Test
    void testRealImageImplementsMyImageInterface() {
        MyImage realImage = new RealImage("test.jpg");

        assertInstanceOf(MyImage.class, realImage);
    }

    @Test
    void testProxyDelegatestoRealImage() {
        MyImage proxyImage = new ProxyImage("sample.jpg");

        assertDoesNotThrow(() -> proxyImage.display());
    }

    @Test
    void testDifferentFilenames() {
        MyImage proxy1 = new ProxyImage("image1.jpg");
        MyImage proxy2 = new ProxyImage("image2.png");

        assertNotNull(proxy1);
        assertNotNull(proxy2);
    }
}

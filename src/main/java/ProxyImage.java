public class ProxyImage implements MyImage {
    private RealImage realImage;
    private final String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
        System.out.println("ProxyImage created (lightweight) for: " + filename);
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("ProxyImage: Creating RealImage on first display()...");
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

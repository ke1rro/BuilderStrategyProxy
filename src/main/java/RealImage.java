import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RealImage implements MyImage {
    private JFrame frame;
    private ConfettiPanel confettiPanel;

    public RealImage(String filename) {
        frame = new JFrame("Image with Confetti");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(filename);
        Image scaledImage = icon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        if (scaledImage.getHeight(null) > 300) {
            scaledImage = icon.getImage().getScaledInstance(-1, 300, Image.SCALE_SMOOTH);
        }
        JLabel label = new JLabel(new ImageIcon(scaledImage));
        frame.add(label, BorderLayout.CENTER);

        JButton confettiButton = new JButton("Show Confetti 🎉");
        frame.add(confettiButton, BorderLayout.SOUTH);

        confettiPanel = new ConfettiPanel();
        frame.setGlassPane(confettiPanel);

        confettiButton.addActionListener(e -> confettiPanel.startConfetti());

        frame.pack();

        Dimension size = frame.getSize();
        int width = Math.min(size.width, 300);
        int height = Math.min(size.height, 300);
        frame.setSize(width, height);
        frame.setResizable(false);
    }

    static class ConfettiPanel extends JComponent {
        private final int NUM_CONFETTI = 100;
        private final Color[] COLORS = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.ORANGE
        };
        private final Random rand = new Random();
        private int[] x = new int[NUM_CONFETTI];
        private int[] y = new int[NUM_CONFETTI];
        private Color[] color = new Color[NUM_CONFETTI];
        private boolean running = false;

        public ConfettiPanel() {
            setOpaque(false);
            setVisible(false);
            for (int i = 0; i < NUM_CONFETTI; i++) {
                x[i] = rand.nextInt(300);
                y[i] = rand.nextInt(300);
                color[i] = COLORS[rand.nextInt(COLORS.length)];
            }
        }

        public void startConfetti() {
            running = true;
            setVisible(true);
            new Timer(30, e -> {
                if (!running)
                    ((Timer) e.getSource()).stop();
                for (int i = 0; i < NUM_CONFETTI; i++) {
                    y[i] += 5 + rand.nextInt(5);
                    if (y[i] > getHeight()) {
                        y[i] = 0;
                        x[i] = rand.nextInt(getWidth());
                    }
                }
                repaint();
            }).start();

            new Timer(3000, e -> {
                running = false;
                setVisible(false);
            }).start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!running)
                return;
            for (int i = 0; i < NUM_CONFETTI; i++) {
                g.setColor(color[i]);
                g.fillOval(x[i], y[i], 8, 8);
            }
        }
    }

    @Override
    public void display() {
        frame.setVisible(true);
    }
}

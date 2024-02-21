import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {
    public mainFrame() {
        init();
    }
    public void init() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        formPanel formPanel = new formPanel();

        container.add(formPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new mainFrame();
    }
}

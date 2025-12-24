import javax.swing.*;
import java.awt.*;

public class Visualization extends JFrame {

    public Visualization(double[][] vertices) {
        setTitle("Прямоугольник");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                g.setColor(Color.BLUE);
                for (int i = 0; i < 4; i++) {
                    int next = (i + 1) % 4;
                    g.drawLine(
                            (int)(vertices[i][0]*20 + 200),
                            (int)(vertices[i][1]*20 + 200),
                            (int)(vertices[next][0]*20 + 200),
                            (int)(vertices[next][1]*20 + 200)
                    );
                }

                g.setColor(Color.RED);
                String[] labels = {"A", "B", "C", "D"};
                for (int i = 0; i < 4; i++) {
                    int x = (int)(vertices[i][0]*20 + 200);
                    int y = (int)(vertices[i][1]*20 + 200);
                    g.fillOval(x-4, y-4, 8, 8);
                    g.drawString(labels[i], x+5, y-5);
                }
            }
        });

        setVisible(true);
    }
}
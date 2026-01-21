import javax.swing.*;
import java.awt.*;

public class Visualization extends JFrame {

    public Visualization(double[][] vertices, int[][] points, double[][] longestLine) {
        setTitle("Прямоугольник и прямая с наибольшим пересечением");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                Rectangle rect = new Rectangle(new int[0][0]);
                rect.rectangleVertices = vertices;

                g.setColor(Color.BLUE);
                for (int i = 0; i < 4; i++) {
                    int next = (i + 1) % 4;
                    g.drawLine(
                            (int)(vertices[i][0]),
                            (int)(vertices[i][1]),
                            (int)(vertices[next][0]),
                            (int)(vertices[next][1])
                    );
                }

                g.setColor(Color.RED);
                for (int i = 0; i < 4; i++) {
                    int x = (int)(vertices[i][0]);
                    int y = (int)(vertices[i][1]);
                    g.fillOval(x-4, y-4, 8, 8);
                }

                for (int i = 0; i < points.length; i++) {
                    int x = (int)(points[i][0]);
                    int y = (int)(points[i][1]);

                    boolean inside = rect.isPointInside(points[i][0], points[i][1]);

                    if (inside) {
                        g.setColor(Color.GREEN);
                        g.fillOval(x-4, y-4, 8, 8);
                    } else {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(x-4, y-4, 8, 8);
                    }

                    g.setColor(Color.BLACK);
                    g.drawString("P" + (i+1), x+5, y-5);
                }

                g.setColor(Color.ORANGE);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                g.drawLine(
                        (int)(longestLine[0][0]),
                        (int)(longestLine[0][1]),
                        (int)(longestLine[1][0]),
                        (int)(longestLine[1][1])
                );

                g.setColor(Color.BLACK);
                int midX = (int)((longestLine[0][0] + longestLine[1][0]) / 2);
                int midY = (int)((longestLine[0][1] + longestLine[1][1]) / 2);
                g.drawString("max", midX, midY - 10);
            }
        });

        setVisible(true);
    }
}
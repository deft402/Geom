import javax.swing.*;
import java.awt.*;

public class Visualization extends JFrame {

    public Visualization(double[][] vertices, int[][] points, double[][] longestLine) {
        setTitle("Прямоугольник и прямая с наибольшим пересечением");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            minX = Math.min(minX, vertices[i][0]);
            maxX = Math.max(maxX, vertices[i][0]);
            minY = Math.min(minY, vertices[i][1]);
            maxY = Math.max(maxY, vertices[i][1]);
        }

        for (int i = 0; i < points.length; i++) {
            minX = Math.min(minX, points[i][0]);
            maxX = Math.max(maxX, points[i][0]);
            minY = Math.min(minY, points[i][1]);
            maxY = Math.max(maxY, points[i][1]);
        }

        final double centerX = (minX + maxX) / 2;
        final double centerY = (minY + maxY) / 2;
        final double scale = Math.min(700 / (maxX - minX + 20), 700 / (maxY - minY + 20));

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                int width = getWidth();
                int height = getHeight();

                Rectangle rect = new Rectangle(new int[0][0]);
                rect.rectangleVertices = vertices;

                g.setColor(Color.BLUE);
                for (int i = 0; i < 4; i++) {
                    int next = (i + 1) % 4;
                    int x1 = (int)((vertices[i][0] - centerX) * scale + width/2);
                    int y1 = (int)((vertices[i][1] - centerY) * scale + height/2);
                    int x2 = (int)((vertices[next][0] - centerX) * scale + width/2);
                    int y2 = (int)((vertices[next][1] - centerY) * scale + height/2);
                    g.drawLine(x1, y1, x2, y2);
                }

                g.setColor(Color.RED);
                for (int i = 0; i < 4; i++) {
                    int x = (int)((vertices[i][0] - centerX) * scale + width/2);
                    int y = (int)((vertices[i][1] - centerY) * scale + height/2);
                    g.fillOval(x-4, y-4, 8, 8);
                }

                for (int i = 0; i < points.length; i++) {
                    int x = (int)((points[i][0] - centerX) * scale + width/2);
                    int y = (int)((points[i][1] - centerY) * scale + height/2);

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
                int x1 = (int)((longestLine[0][0] - centerX) * scale + width/2);
                int y1 = (int)((longestLine[0][1] - centerY) * scale + height/2);
                int x2 = (int)((longestLine[1][0] - centerX) * scale + width/2);
                int y2 = (int)((longestLine[1][1] - centerY) * scale + height/2);
                g.drawLine(x1, y1, x2, y2);

                g.setColor(Color.BLACK);
                int midX = (x1 + x2) / 2;
                int midY = (y1 + y2) / 2;
                g.drawString("max", midX, midY - 10);
            }
        });

        setVisible(true);
    }
}
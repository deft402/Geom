public class Rectangle {
    int[][] pointsR;
    double[][] rectangleVertices;

    Rectangle(int[][] mas) {
        this.pointsR = mas;
    }

    void calculateVertexes() {
        int x1 = pointsR[0][0];
        int y1 = pointsR[0][1];
        int x2 = pointsR[1][0];
        int y2 = pointsR[1][1];
        int x3 = pointsR[2][0];
        int y3 = pointsR[2][1];

        int A = y2 - y1;
        int B = x1 - x2;
        int C = x2 * y1 - x1 * y2;

        int high = A * x3 + B * y3 + C;

        double nx = -(y2 - y1);
        double ny = x2 - x1;
        double length = Math.sqrt(nx * nx + ny * ny);
        double unitX = nx / length;
        double unitY = ny / length;

        double offsetX = high * unitX;
        double offsetY = high * unitY;

        double x4 = x1 + offsetX;
        double y4 = y1 + offsetY;
        double x5 = x2 + offsetX;
        double y5 = y2 + offsetY;

        this.rectangleVertices = new double[4][2];
        this.rectangleVertices[0][0] = x1;
        this.rectangleVertices[0][1] = y1;
        this.rectangleVertices[1][0] = x2;
        this.rectangleVertices[1][1] = y2;
        this.rectangleVertices[2][0] = x5;
        this.rectangleVertices[2][1] = y5;
        this.rectangleVertices[3][0] = x4;
        this.rectangleVertices[3][1] = y4;
    }

    double[][] getVertices() {
        return this.rectangleVertices;
    }

    boolean isPointInside(int x, int y) {
        // Для каждой стороны проверяем, что точка находится по одну сторону
        for (int i = 0; i < 4; i++) {
            int next = (i + 1) % 4;
            double x1 = rectangleVertices[i][0];
            double y1 = rectangleVertices[i][1];
            double x2 = rectangleVertices[next][0];
            double y2 = rectangleVertices[next][1];

            // Векторное произведение (x2-x1)*(y-y1) - (y2-y1)*(x-x1)
            double cross = (x2 - x1) * (y - y1) - (y2 - y1) * (x - x1);

            // Если точка снаружи относительно этой стороны
            if (cross < 0) {
                return false;
            }
        }
        return true;
    }
}
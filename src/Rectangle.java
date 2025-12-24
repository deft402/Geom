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

        new Visualization(this.rectangleVertices);
    }

    double[][] getVertices() {
        return this.rectangleVertices;
    }
}
public class IntersectionLine {
    private double[][] rectangleVertices;
    private int[][] points;
    private double[][] longestLinePoints;
    private double maxIntersectionLength;

    public IntersectionLine(double[][] vertices, int[][] points) {
        this.rectangleVertices = vertices;
        this.points = points;
        this.longestLinePoints = new double[2][2];
        this.maxIntersectionLength = 0;
    }

    public double findMaxIntersection() {
        maxIntersectionLength = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double intersection = calculateIntersectionLength(points[i][0], points[i][1], points[j][0], points[j][1]);

                if (intersection > maxIntersectionLength) {
                    maxIntersectionLength = intersection;
                    longestLinePoints[0][0] = points[i][0];
                    longestLinePoints[0][1] = points[i][1];
                    longestLinePoints[1][0] = points[j][0];
                    longestLinePoints[1][1] = points[j][1];
                }
            }
        }

        return maxIntersectionLength;
    }

    private double calculateIntersectionLength(int x1, int y1, int x2, int y2) {
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        double rectMinX = Math.min(Math.min(rectangleVertices[0][0], rectangleVertices[1][0]),
                Math.min(rectangleVertices[2][0], rectangleVertices[3][0]));
        double rectMaxX = Math.max(Math.max(rectangleVertices[0][0], rectangleVertices[1][0]),
                Math.max(rectangleVertices[2][0], rectangleVertices[3][0]));
        double rectMinY = Math.min(Math.min(rectangleVertices[0][1], rectangleVertices[1][1]),
                Math.min(rectangleVertices[2][1], rectangleVertices[3][1]));
        double rectMaxY = Math.max(Math.max(rectangleVertices[0][1], rectangleVertices[1][1]),
                Math.max(rectangleVertices[2][1], rectangleVertices[3][1]));

        if (maxX < rectMinX || minX > rectMaxX || maxY < rectMinY || minY > rectMaxY) {
            return 0;
        }

        double intersectMinX = Math.max(minX, rectMinX);
        double intersectMaxX = Math.min(maxX, rectMaxX);
        double intersectMinY = Math.max(minY, rectMinY);
        double intersectMaxY = Math.min(maxY, rectMaxY);

        if (x1 == x2) {
            return intersectMaxY - intersectMinY;
        } else if (y1 == y2) {
            return intersectMaxX - intersectMinX;
        } else {
            double length = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            double intersectLength = Math.sqrt((intersectMaxX-intersectMinX)*(intersectMaxX-intersectMinX) +
                    (intersectMaxY-intersectMinY)*(intersectMaxY-intersectMinY));
            return intersectLength;
        }
    }

    public double[][] getLongestLinePoints() {
        return longestLinePoints;
    }
}
    import java.util.Scanner;
    import java.io.PrintStream;
    public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        int [][] masT =  new int[3][2];
        for(int a = 0; a < 3; a++){
            for(int b = 0; b < 2; b++){
                if (a == 2 && b == 0)
                {
                   out.print("Введите X координату точки, лежащей на противоположной стороне: ");
                   masT[a][b] = in.nextInt();
                }
                if (a == 2 && b == 1)
                {
                    out.print("Введите Y координату точки, лежащей на противоположной стороне: ");
                    masT[a][b] = in.nextInt();
                }
                if (b == 0 && a != 2)
                {
                    out.print("Введите X координату " + (a+1) + " вершины: ");
                    masT[a][b] = in.nextInt();
                }
                if (b == 1 && a != 2)
                {
                    out.print("Введите Y координату " + (a+1) + " вершины: ");
                    masT[a][b] = in.nextInt();
                }
            }
        }

        Rectangle rect = new Rectangle(masT);

        rect.calculateVertexes();

        out.print("\nВведите количество точек: ");
        int n = in.nextInt();
        int [][] masPoint = new int[n][2];
        for(int a = 0; a < n; a++){
            for(int b = 0; b < 2; b++){
                if (b == 0)
                {
                    out.print("Введите X координату " + (a+1) + " точки: ");
                    masPoint[a][b] = in.nextInt();
                }
                if (b == 1)
                {
                    out.print("Введите Y координату " + (a+1) + " точки: ");
                    masPoint[a][b] = in.nextInt();
                }
            }
        }


    }
}

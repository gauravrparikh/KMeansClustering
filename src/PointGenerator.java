import java.util.ArrayList;

public class PointGenerator {


    public static ArrayList<Point> pointGenerator(int dim, int n, int bound, boolean isCentroid){
        ArrayList<Point> points =new ArrayList<>();
        for (int i =0; i < n; i++){
            double[] temp=new double[dim];
            for (int j=0;j<dim;j++){
                temp[j]=(Math.random()*bound);
            }
            if (isCentroid) points.add(new Point(temp,i));
            else points.add(new Point(temp));
            
        }
        
        return points;

    }

    
    public static void main(String[] args) throws Exception {
       ArrayList<Point> temp=pointGenerator(2, 2,10,false);
       System.out.println(temp);
       System.out.println(temp.get(0).l2Norm(temp.get(1)));
    }
}

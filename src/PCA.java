import java.util.ArrayList;

public class PCA {
    public static int[] principalComp(ArrayList<Point> points, int numComps){

        int dim=Runner.dim;
        int n=Runner.np;
        if (dim<=2) {
            int [] components={0,1};
            return components;
        }
        int[] components =new int[numComps]; 
        Point means= new Point(dim);
        Point variance= new Point(dim);
        for (int i=0;i<n;i++){
            means=means.sum(points.get(i));
        }
        means=means.div(n);
        for (int i=0;i<n;i++){
            variance=variance.sum((points.get(i).diff(means)).pow(2));
        }
        for (int i=0;i<numComps;i++){
            components[i]=variance.argMax();
            variance.getX()[components[i]]=0;
        }
        return components;
        
    }


    public static void main(String[] args) throws Exception {
        ;
    }
}

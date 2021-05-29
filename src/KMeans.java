import java.util.ArrayList;

public class KMeans {
    public static void kMeans(ArrayList<Point> points,ArrayList<Point> centroids ){
        int clusts=Runner.nc;
        //Point Cluster Assignment 
        for (int i=0; i<points.size();i++){
            double temp=Double.MAX_VALUE;
            for (int j=0; j<clusts;j++){
                if (points.get(i).l2Norm(centroids.get(j))<temp){
                    temp=points.get(i).l2Norm(centroids.get(j));
                    points.get(i).setCluster(j);
                }
            }
        }
        //Updating Centroids
        int[] count=new int[clusts];
        ArrayList<Point> meanSum= new ArrayList<Point>();
        for (int i=0;i<clusts;i++){
            meanSum.add(new Point(points.get(0).getX().length));
        }
        for (int i=0; i<points.size();i++){
            int index=points.get(i).getCluster();
            meanSum.set(index,meanSum.get(index).sum(points.get(i)));
            count[index]++;
        }
        for (int i=0;i<clusts;i++){
            meanSum.set(i,meanSum.get(i).div(count[i]));
        }
        for (int i=0;i<clusts;i++){
            int temp = centroids.get(i).getCluster();
            centroids.set(i, meanSum.get(i)) ;
            centroids.get(i).setCluster(temp);
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Point> tempPoints=PointGenerator.pointGenerator(2, 10,100,false);
        ArrayList<Point> tempClusts=PointGenerator.pointGenerator(2, 2,100,true);
        System.out.println(tempPoints);
        System.out.println(tempClusts);
        kMeans(tempPoints, tempClusts);
        System.out.println(tempClusts);
        kMeans(tempPoints, tempClusts);
        System.out.println(tempClusts);
        kMeans(tempPoints, tempClusts);
        System.out.println(tempClusts);
    }
}

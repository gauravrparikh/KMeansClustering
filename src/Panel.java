import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class Panel extends JPanel{
public static final int SIZE=10;
    public Panel(){
        super();
        addMouseListener(new CentroidSetter());
        
    }

    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        ArrayList<Point> pcaPoints = dimReduction(Runner.points);
        //ArrayList<Point> normalisePoints= normalize(pcaPoints);
        ArrayList<Point> transPoints = transform(pcaPoints);
        for (int i=0;i<transPoints.size();i++){
            int PC1=(int)transPoints.get(i).getX()[0];
            int PC2=(int) transPoints.get(i).getX()[1];
            
            g2.setColor(Runner.colors[transPoints.get(i).getCluster()]);
            g2.drawOval(PC1-SIZE/2, PC2-SIZE/2, SIZE, SIZE);
        }
        if (!Runner.manualClustering){
            ArrayList<Point> pcaCentroids = dimReduction(Runner.centroids);
            ArrayList<Point> transCentroids=  transform(pcaCentroids);
            for (int i=0;i<transCentroids.size();i++){
                int PC1=(int)transCentroids.get(i).getX()[0];
                int PC2=(int)transCentroids.get(i).getX()[1];
                g2.setColor(Runner.colors[transCentroids.get(i).getCluster()]);
                g2.fillRect(PC1-SIZE/2, PC2-SIZE/2, SIZE, SIZE);
            }

        }
        else {//THIS NEEDS TO BE FIXED ?
            ArrayList<Point> pcaCentroids = dimReduction(Runner.centroids);
            for (int i=0;i<pcaCentroids.size();i++){
                int PC1=(int)pcaCentroids.get(i).getX()[0];
                int PC2=(int)pcaCentroids.get(i).getX()[1];
                g2.setColor(Runner.colors[pcaCentroids.get(i).getCluster()]);
                g2.fillRect(PC1-SIZE/2, PC2-SIZE/2, SIZE, SIZE);
            }
        }
        
    }
    public static ArrayList<Point> dimReduction(ArrayList<Point> points){
        try {
            if (points.get(0).getX().length<=2) return points;   
        } catch (Exception e) {}
        ArrayList<Point> pcaPoints=new ArrayList<Point>();
        for (Point p: points){
            Point n= new Point(2);
            double[] ar= {p.getX()[Runner.pcas[0]],p.getX()[Runner.pcas[1]]};
            n.setX(ar);
            n.setCluster(p.getCluster());
            pcaPoints.add(n);
        }
        return pcaPoints;
    }

    public static ArrayList<Point> transform(ArrayList<Point> pcaPoints){
        ArrayList<Point> transPoints=new ArrayList<Point>();
        int b = Runner.SUB_FRAME_SIZE;
        int B = Runner.FRAME_SIZE;
        for (Point p: pcaPoints){
            Point n = new Point(2);
            double[] ar = {(p.getX()[0]/B)*b,(p.getX()[1]/B)*b};
            n.setX(ar);
            n.setCluster(p.getCluster());
            transPoints.add(n);
        }
        return transPoints;
    }
    public static ArrayList<Point> normalize(ArrayList<Point> points){
        ArrayList<Point> normalPoints=new ArrayList<>();
        int dim = Runner.dim;
        double[] minarr= new double[dim];
        double[] maxarr= new double[dim];
        for (int i=0;i<dim;i++){
            minarr[i]=Double.MAX_VALUE;
            maxarr[i]=Double.MIN_VALUE;
        }
        for (Point p: points){
            for (int i=0;i<dim;i++){
                minarr[i]=Math.min(p.getX()[i],minarr[i]);
                maxarr[i]=Math.max(p.getX()[i],maxarr[i]);
             }
        }
        Point minimum = new Point(minarr);
        Point maximum = new Point(maxarr);
        minimum=minimum.div(-1);
        maximum=maximum.sum(minimum);
        for (Point p: points){
            normalPoints.add(p.sum(minimum));
            normalPoints.get(normalPoints.size()-1).setCluster(p.getCluster());
        }
        double currentMax=Double.MIN_VALUE;
        for (int i=0;i<maxarr.length;i++){
            if (currentMax<maximum.getX()[i]) currentMax=maximum.getX()[i];
        }
        double n= (currentMax/Runner.FRAME_SIZE);
    
        for (int i=0;i<normalPoints.size();i++){
            normalPoints.set(i, normalPoints.get(i).div(n));
        }
        return normalPoints;
    }
            
}

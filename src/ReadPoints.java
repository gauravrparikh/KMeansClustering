import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;  
public class ReadPoints {


    public static ArrayList<Point> readCSV(File f) throws FileNotFoundException{
        ArrayList<Point> points=new ArrayList<>();
        
        Scanner scan = new Scanner(f);
        scan.useDelimiter(",|\\n");
        //System.out.println(scan.next().trim());
        int np =Integer.parseInt(scan.next().trim().replaceAll("\\p{C}", ""));
        Runner.np=np;
        int dim =Integer.parseInt(scan.next().trim().replaceAll("\\p{C}", ""));
        Runner.dim=dim;
        for (int i=0;i<np;i++){
            double[] array= new double[dim];
            for (int j=0;j<dim;j++){
                 array[j]=Double.parseDouble(scan.next().trim().replaceAll("\\p{C}", ""));
                 System.out.println(array[j]);
            }
            Point p= new Point(array);
            points.add(p);
        }
        scan.close();
        return points;

    }

}


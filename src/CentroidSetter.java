import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class CentroidSetter implements MouseInputListener{
    public static int counter=0;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Runner.isClustering && Runner.manualClustering){
            double[] coord= {e.getX(),e.getY()};
            Point p = new Point(coord);
            p.setCluster(counter);
            Runner.centroids.set(counter, p);
            counter++;
            if (counter>=Runner.nc)counter=0;
            Runner.panel.repaint();
        }
        else{
            counter=0;
        }
           
        //System.out.println("X: "+e.getX()+"Y "+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}

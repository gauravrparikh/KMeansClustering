

public class Point {
    private double[] x;
    private int cluster;

    public Point(double[] xIn){
        this.x=(xIn);

    }   
    public Point(double[] xIn, int i){
        this.x=(xIn);
        this.cluster=i;

    }   

    public Point(int dim) {
        double[] x = new double[dim];
        this.x = x;
    }

    public double[] getX(){
        return this.x;
    }

    public int getCluster(){
        return this.cluster;
    }

    public void setX(double[] x){
        this.x=x;
    }

    public void setCluster(int cluster){
        this.cluster=cluster;
    }

    public double l2Norm(Point P){
        double val=0.0;
        for (int i=0;i<P.x.length; i++){
            val+=Math.pow((P.x[i]-this.x[i]),2);
        }
        return Math.sqrt(val);
    }

    public double l1Norm(Point P){
        double val=0.0;
        for (int i=0;i<P.x.length; i++){
            val+=Math.abs((P.x[i]-this.x[i]));
        }
        return Math.sqrt(val);
    }

    public Point sum(Point P){
        double[] sum=new double[P.x.length];
        for (int i=0;i<P.x.length;i++){
            sum[i]=P.x[i]+this.x[i];
        }
        return new Point(sum);
    }

    public Point diff(Point P){
        double[] sum=new double[P.x.length];
        for (int i=0;i<P.x.length;i++){
            sum[i]=P.x[i]-this.x[i];
        }
        return new Point(sum);
    }
    public Point prod(Point P){
        double[] prod=new double[P.x.length];
        for (int i=0;i<P.x.length;i++){
            prod[i]=P.x[i]*this.x[i];
        }
        return new Point(prod);
    }
    
    public Point div(double n){
        double[] div=new double[this.x.length];
        for (int i=0;i<this.x.length;i++){
            div[i]=this.x[i]/n;
        }
        Point p=new Point(div);
        p.setCluster(this.cluster);
        return p;
    }
    public Point pow(int pow){
        double[] power=new double[this.x.length];
        for (int i=0;i<this.x.length;i++){
            power[i]=Math.pow(this.x[i],pow);
        }
        return new Point(power);
    }
    public int argMax(){
        double max= Double.MIN_VALUE;
        int argMax=0;
        for (int i=0; i<this.x.length;i++){
            if (this.x[i]>max){
                max=this.x[i];
                argMax=i;
            }
        }
        return argMax;
    }

    

    public String toString(){
        String s="(";
        for (int i=0;i<this.x.length;i++){
            s+=this.x[i]+" ";
        }
        s+=")";
        return s;
    }

    public static void main(String[] args) throws Exception {
        double [] temp1={1,1};
        double [] temp2={0,0};
        Point p1=new Point(temp1); 
        Point p2=new Point(temp2); 
        System.out.println(p1.l2Norm(p2));
        System.out.println(p2.l2Norm(p1));
      }
}

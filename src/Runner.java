import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Runner extends JFrame{
    public static ArrayList<Point> points= new ArrayList<>();
    public static ArrayList<Point> centroids= new ArrayList<>();
    public static int[] pcas;
    public static boolean isClustering=false;
    public static Panel panel= new Panel();
    public static JPanel buttonPanel= new JPanel();
    public static JButton settingsButton= new JButton("Settings");
    public static JButton randomButton = new JButton("Random");
    public static JButton clusterButton = new JButton("Cluster");
    public static JButton fileSelectButton = new JButton("Select File");
    public static final int FRAME_SIZE=800;
    public static final int MARGIN_SIZE=50;
    public static final int SUB_FRAME_SIZE=FRAME_SIZE-2*MARGIN_SIZE;
    public static final Color[] colors= {new Color(255, 0,0),new Color(91, 217, 119 ),new Color(92, 90, 216),new Color(200, 0, 200),new Color(100, 0, 77),new Color(64, 124, 108),new Color(255,140,70)};
    public static Runner run;

    //Hyperparameters
    public static int dim=2;
    public static int np=100;
    public static int nc=4;
    public static boolean manualClustering=false;
    public Runner(){
        super("Visualiser");
        setSize(FRAME_SIZE,FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(52,91,235));
        add(panel);
        add(buttonPanel);
        Border border = BorderFactory.createLineBorder(new Color(52,91,235),5);
        border.isBorderOpaque();
        panel.setBorder(border);
        panel.setBounds(MARGIN_SIZE, MARGIN_SIZE-5, SUB_FRAME_SIZE, SUB_FRAME_SIZE);
        panel.setBackground(new Color(230,230,230));
        buttonPanel.setBounds(0, 0, FRAME_SIZE,MARGIN_SIZE-15);
        settingsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame settingsFrame =new JFrame("Settings");
                settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                settingsFrame.setSize(400, 400);
                settingsFrame.setResizable(false);
                settingsFrame.setLocationRelativeTo(null);
                settingsFrame.setVisible(true);
                settingsFrame.addFocusListener( new FocusListener() {
                    private boolean gained = false;
                    @Override
                    public void focusGained(FocusEvent e) {
                      gained = true;
                      System.out.println("THIS IS NOT WORKING");
                    }  
                    @Override
                    public void focusLost(FocusEvent e) {
                      if(gained) {
                        settingsFrame.dispose();
                        System.out.println("TTESTEEETT");
                      }
                      System.out.println("    DFGHJKJNBVBN");
                    }
                  } );
                Container settingsContainer= settingsFrame.getContentPane();
                JPanel dimPanel = new JPanel();
                JPanel pPanel= new JPanel();
                JPanel cPanel= new JPanel();
                JPanel manualPanel = new JPanel();
        
                settingsContainer.setLayout(new BoxLayout(settingsContainer, BoxLayout.Y_AXIS));
                dimPanel.setLayout(new BoxLayout(dimPanel, BoxLayout.LINE_AXIS));
                dimPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                dimPanel.add(Box.createHorizontalStrut(2));
                pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.LINE_AXIS));
                pPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                pPanel.add(Box.createHorizontalStrut(2));
                cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.LINE_AXIS));
                cPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                cPanel.add(Box.createHorizontalStrut(2));
                manualPanel.setLayout(new BoxLayout(manualPanel, BoxLayout.LINE_AXIS));
                manualPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                manualPanel.add(Box.createHorizontalStrut(2));

                JSlider dimSlider= new JSlider(1, 9, dim);
                dimSlider.setPaintTicks(true);
                dimSlider.setSnapToTicks(true);
                dimSlider.setPaintLabels(true);
                dimSlider.setMajorTickSpacing(1);
                
                JSlider pSlider= new JSlider(0, 500, np);
                pSlider.setPaintTicks(true);
                pSlider.setPaintLabels(true);
                pSlider.setMajorTickSpacing(100);
                pSlider.setMinorTickSpacing(50);

                JSlider cSlider= new JSlider(1, colors.length, nc);
                cSlider.setPaintTicks(true);
                cSlider.setSnapToTicks(true);
                cSlider.setPaintLabels(true);
                cSlider.setMajorTickSpacing(1);
                dimSlider.addChangeListener(new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        dim=dimSlider.getValue();
                        settingsContainer.repaint();
                    }  
                });
                pSlider.addChangeListener(new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        np=pSlider.getValue();        
                    }
                });
                cSlider.addChangeListener(new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        nc=cSlider.getValue();
                    }
                });
                JLabel dimLabel = new JLabel("Dimensions");
                JLabel pLabel = new JLabel("Points");
                JLabel cLabel = new JLabel("Clusters");
                JRadioButton manualCentroids = new JRadioButton("Manual Centroids");
                manualCentroids.setSelected(manualClustering);
                manualCentroids.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (manualClustering) manualClustering=false;
                        else manualClustering=true;
                        CentroidSetter.counter=0;
                        centroids=new ArrayList<Point>();
                        for (int i=0;i<nc;i++){
                            centroids.add(new Point(new double[2]));
                        }
                        panel.repaint();
                        settingsContainer.repaint();
                    }        
                });

                dimPanel.add(dimLabel);
                dimPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                dimPanel.add(dimSlider);
                pPanel.add(pLabel);
                pPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                pPanel.add(pSlider);
                cPanel.add(cLabel);
                cPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                cPanel.add(cSlider);
                manualPanel.add(manualCentroids);

                settingsContainer.add(dimPanel);
                settingsContainer.add(pPanel);
                settingsContainer.add(cPanel);
                settingsContainer.add(manualPanel);
            }
        });
        randomButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                isClustering=false;
                points=PointGenerator.pointGenerator(dim, np, FRAME_SIZE,false);
                if (!manualClustering)centroids=PointGenerator.pointGenerator(dim, nc, FRAME_SIZE,true);
                else {
                    CentroidSetter.counter=0;
                    centroids=new ArrayList<Point>();
                
                    for (int i=0;i<nc;i++){
                        centroids.add(new Point(new double[2]));
                    }
                }
                pcas=PCA.principalComp(points,2);
                panel.repaint();     
            }    
        });
        clusterButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                isClustering=true;
                KMeans.kMeans(points, centroids);
                panel.repaint();

            }       
        });
        fileSelectButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                isClustering=false;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +chooser.getSelectedFile().getName());
                    try {
                        points = ReadPoints.readCSV(chooser.getSelectedFile());
                        centroids=PointGenerator.pointGenerator(dim, nc, FRAME_SIZE,true);
                        pcas=PCA.principalComp(points,2);
                        panel.repaint();
                    } catch (FileNotFoundException e1) {
                    }
                }
            }

        });
        buttonPanel.add(randomButton);
        buttonPanel.add(clusterButton);
        buttonPanel.add(fileSelectButton);
        buttonPanel.add(settingsButton);
        setVisible(true);   
    }
    public static void main(String[] args) throws Exception {
       new Runner();

    }
}

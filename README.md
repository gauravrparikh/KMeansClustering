## Getting Started

This project implements the KMeans Clustering Algorithm in Java using n-dimensional inout data. Data can be read from a csv file or be randomly generated. The visual panel displays the clustering in higher dimensional data by performing a principal component analysis to showcase the 2 dimensions of maximum variance. The standard implementation performs complete clustering with an l-2 norm but has support for l1 norm implementation. 

## Folder Structure
The workspace the following classes
- `CentroidSetter`: Handles manual setting of centorids via Mouse input, when centroid setting is set to manual mode.
- `KMeans`: Contains the KMeans clustering algorithm.
- `PCA`: Finds principal compenents by looking at dimensions of maximal variance. 
- `Panel`: Contains the JPanel to showcase visualisation
- `Point`: Blueprint for point object. Point object used to depict n dimensional data and calculate norms. 
- `PointGenerator`: Generates points randomly if input data is not given and/or if centroids are set to automatic mode. 
- `ReadPoints`: Provides an implementation to read data from a .csv file. Follows the format 'num points', /n 'num dimesnions' /n followed by the coordinates of points on each new line.
- `Runner`:Runs program, holds important field variables shared by all classes.

##
Version 2.0 updates (in process)
- Finding a Loss function that can exclude points so as to perform incomplete clusters instead of absolute clustering. 
- Provide implementation for K fold cross validation to determine optimal clustering 
- Provide implementation for dropping fringe or outlier points.
- Addition of Kernel functions (Radial Basis, Polynomial)
- Fix normalization method for resizing csv data to fit within bounds of the visual panel. 

 

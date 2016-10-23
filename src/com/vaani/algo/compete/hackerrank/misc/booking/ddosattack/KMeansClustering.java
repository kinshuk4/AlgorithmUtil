package com.vaani.algo.compete.hackerrank.misc.booking.ddosattack;

public class KMeansClustering implements KMeansParameters{
	
	// N is the number of points 
	private int N;
	// Array of points
	private long[][] points; 
	// normalizing points using logarithm function
	private double[][] logPoints;
	// maximum service requests
	private long maxPoint;
	// minimum service requests
	private long minPoint;

	// Array of Points determining the cluster in which it is contained
	private int[] clusters;
	// centroids
	private double centroid1;
	private double centroid2;
	
	/*
	 *  Constructor for KMeansClustering Class
	 */
	public KMeansClustering(int N, long[][] points2) {
		setN(N);
		points = new long[N][2];
		logPoints = new double[N][2];
		centroid1 = Double.MAX_VALUE;
		centroid2 = Double.MIN_VALUE;
		minPoint = Long.MAX_VALUE;
		maxPoint = Long.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				points[i][j] = points2[i][j];
			}
			if (points[i][1] < minPoint) {
				minPoint = points[i][1];
			}
			if (points[i][1] > maxPoint) {
				maxPoint = points[i][1];
			}
		}
		for (int i = 0; i < N; i++) {
			logPoints[i][0] = (double)points[i][0];
			logPoints[i][1] = (double)Math.log(minPoint) + Math.log((1+points[i][1]))/Math.log(1+maxPoint);
		}
		
		clusters = new int[N];
	}
	/*
	 * Getter method for N
	 */
	public int getN() {
		return N;
	}
	/*
	 * Setter method for N
	 */
	public void setN(int n) {
		N = n;
	}
	
	// initialization based on Random Partition method
	public void initialization() {
		for (int i = 0; i < N; i++) {
			double r = Math.random();
			if (r < 0.5) {
				clusters[i] = 1;
			} else {
				clusters[i] = 2;
			}
		}
		updateCentroids();
	}
	
	/*
	 * Assign points to either cluster 1 or 2
	 */
	public void assignClusters() {
		for (int i = 0; i < N; i++) {
			double distance1 = Math.pow(centroid1 - logPoints[i][1],2);
			double distance2 = Math.pow(centroid2 - logPoints[i][1],2);
			if (distance1 < distance2) {
				clusters[i] = 1;
			} else {
				clusters[i] = 2;
			}
		}
	}
	
	/*
	 * update centroids based on means
	 */
	public void updateCentroids() {
		double sum1 = 0;
		double sum2 = 0;
		int n1 = 0;
		int n2 = 0;
		for (int i = 0; i < N; i++) {
			if (clusters[i] == 1) {
				sum1 += logPoints[i][1];
				n1++;
			} else {
				sum2 += logPoints[i][1];
				n2++;
			}
		}
		centroid1 = (double)sum1/n1;
		centroid2 = (double)sum2/n2;
	}
	
	/*
	 * Begin k-means clustering (after initialization
	 */
	public void startClustering() {
		
		for (int iter = 1; iter <= NUM_ITERATIONS; iter++) {
			assignClusters();
			updateCentroids();
		}
	}
	
	// Display clusters - one of the clusters will suggest DDoS
	int ddosCluster = 1;
	public void displayEpochsDDoS() {
		/*
		System.out.println("Cluster 1: ");
		for (int i = 0; i  < N; i++) { 
			if (clusters[i] == 1) {
				System.out.println("\t["+points[i][0]+", "+points[i][1]+"]");
			}
		}
		System.out.println("\nCluster 2: ");
		for (int i = 0; i  < N; i++) { 
			if (clusters[i] == 2) {
				System.out.println("\t["+points[i][0]+", "+points[i][1]+"]");
			}
		}
		*/
		for (int i = 0; i < N; i++) {
			if (points[i][1] == maxPoint) {
				ddosCluster = clusters[i];
				break;
			}
		}
		/*
		System.out.println("====================================");
		System.out.println("        DDoS time intervals         ");
		System.out.println("====================================");
		*/
		long start = 0, end=0;
		System.out.print("[");
		boolean first = true;
		for (int i = 0; i < N; i++) {
			if (clusters[i] == ddosCluster) {
				start = points[i][0];
				end = points[i][0];
				i++;
				while(clusters[i] == ddosCluster && points[i][0] == end+1) {
					end = points[i][0];
					i++;
				}
				if (first == true) {
					System.out.print("["+start+", "+end+"]");
					first = false;
				}
				else {
					System.out.print(", ["+start+", "+end+"]");
				}
			}
			
		}
		System.out.print("]");
	}
	
	
}

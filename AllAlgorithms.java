package evolutionaryalgorithms;

/**
* Interface for all data mining algorithms
*/

public interface AllAlgorithms {

    public static final int ITERATION_COUNT = 200; 
    
    public void execute();
    
    /**
     *
     * @param pos   represents the coordinates of the current position of some particle 
     * @return      the value of the fitness function at the given point
     */
    public double calculateFitness(double[] pos);
    
}

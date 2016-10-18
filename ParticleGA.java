package evolutionaryalgorithms;

/**
 * Represents individual particle in the GA clustering
 */
public class ParticleGA {
    
    private static int sDefaultGeneLength = 10;

    private int[] mGenes;
    
    private int mFitness = 0;
    
    public static int getDefaultGeneLength() {
        return sDefaultGeneLength;
    }

    public static void setDefaultGeneLength(int sDefaultGeneLength) {
        ParticleGA.sDefaultGeneLength = sDefaultGeneLength;
    }

    /*
    Get and Set functions
    */
    public int[] getGenes() {
        return mGenes;
    }

    public int getGenes(int index){
        return mGenes[index];
    }
    
    public void setGenes(int[] mGenes) {
        this.mGenes = mGenes;
    }

    public void setGenes(int index, int number){
        this.mGenes[index] = number;
    }
    
    public int getFitness() {
        mFitness = GeneticAlgorithm.calculateFitness(this);
        return mFitness;
    }

    public void setFitness(int mFitness) {
        this.mFitness = mFitness;
    }

    /**
     * Constructor for individual to initialize its string
     */
    public ParticleGA(){
        mGenes = new int[sDefaultGeneLength];
        for(int i=0; i<sDefaultGeneLength; i++){
            mGenes[i] = (int)Math.round(Math.random());
        }
    }
    
    /**
     * 
     * @param geneLength the length of gene
     * changes the default length of gene to geneLength 
     * Constructor for individual to initialize its string
     */
    public ParticleGA(int geneLength){
        sDefaultGeneLength = geneLength;
        mGenes = new int[sDefaultGeneLength];
        for(int i=0; i<sDefaultGeneLength; i++){
            mGenes[i] = (int)Math.round(Math.random());
        }
    }

    /**
    * Prints the genome of the particle
    */
    public static void print(ParticleGA particle){
        for(int i=0; i<sDefaultGeneLength; i++)System.out.print(particle.mGenes[i]);
        System.out.println("");
    }
}

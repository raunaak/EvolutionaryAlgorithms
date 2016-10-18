package evolutionaryalgorithms;

/**
 * Represents the collection of individual particles
 * 
 */
public class PopulationGA {

    private int mPopulationSize;
    
    private ParticleGA[] allParticles;
    
    /**
     * Constructor for creating population for genetic algorithm
     * @param mPopulationSize    the number of particles in the population
     * @param initialize        initialize is set true only for the first generation
     * 
     */
    public PopulationGA(int mPopulationSize, boolean initialize){
        this.mPopulationSize = mPopulationSize;
        allParticles = new ParticleGA[mPopulationSize];
        // initialize is true only for the first generation particles
        if(initialize){
            for(int i=0; i<mPopulationSize; i++){
                ParticleGA particle = new ParticleGA();
                allParticles[i] = particle;
            }
        }
    }

    /*getters*/
    public ParticleGA[] getAllParticles() {
        return allParticles;
    }
    
    public ParticleGA getParticle(int index){
        return allParticles[index];
    }

    /**
     * 
     * @return the reference of particle which resembles closest to the solution gene 
     */
    public ParticleGA getFittest(){
        int bestfitness = -1; int index = -1;
        System.out.println("FITTEST GENE in the generation has fitness " + this.mPopulationSize);
        for(int i=0; i<this.mPopulationSize; i++){
            int fitness = this.getParticle(i).getFitness();
            if(fitness>bestfitness){bestfitness = fitness; index = i;}
        }
        return this.getParticle(index);
    }
    
    /*setters*/
    public void setParticle(int index, ParticleGA particle){
        this.allParticles[index] = particle;
    }
    
    /**
     * 
     * @return the number of particles in the population
     */
    public int getPopulationSize(){
        return this.mPopulationSize;
    }
    
    /**
     * 
     * @param pop the reference of population (group of all particles)
     * Prints the gene of every particle in one line 
     * Prints the fitness levels of every particle in another line
     */
    public static void print(PopulationGA pop){
        System.out.println("NEW GENERATION STARTS");
        for(int i=0; i<pop.mPopulationSize; i++){
            System.out.print("Gene of particle " + i);
            ParticleGA.print(pop.allParticles[i]);
            System.out.println("Fitness of particle " + i + " = " + pop.allParticles[i].getFitness());
        }
    }
}
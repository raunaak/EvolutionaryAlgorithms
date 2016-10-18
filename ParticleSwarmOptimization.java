package evolutionaryalgorithms;

import java.util.Iterator;

/**
 * Implements the interface AllAlgorithms and its execute method implements Particle Swarm Optimization
 * 
 */

public class ParticleSwarmOptimization implements AllAlgorithms{
    
    private int mParticleSize;
    
    private int mDimension;
    
    private ParticlePSO[] allParticles; 
    
    private int mMinVelocity;
    
    private int mMaxVelocity;

    /**
     * Constructor to create all particles in the swarm
     * 
     * @param mParticleSize the number of particles present in the swarm
     * @param mMaxVelocity  the maximum velocity any particle could achieve in each dimension
     * @param mMinVelocity  the minimum velocity any particle could achieve in each dimension
     * @param mDimension    the dimension of the space
     */
    public ParticleSwarmOptimization(int mParticleSize, int mMaxVelocity, int mMinVelocity, int mDimension){
        this.mDimension = mDimension;
        this.mParticleSize = mParticleSize;
        allParticles = new ParticlePSO[mParticleSize];
        for(int i=0; i<mParticleSize; i++)allParticles[i] = new ParticlePSO(mMinVelocity, mMaxVelocity, mDimension);
        this.mMinVelocity = mMinVelocity;
        this.mMaxVelocity = mMaxVelocity;
    }

    /**
     * 
     * @param pos   represents the coordinates of the current position of some particle 
     * @return      the value of the fitness function at the given point
     */
    public double calculateFitness(double[] pos){
        double sum = 0;
        for(int i=0; i<pos.length; i++)sum += pos[i]*pos[i];
        return sum;
    }

    /**
     * 
     * @param particle  the reference to some particle
     * @return          the fitness of the current coordinates of the given particle
     */
    public double calculateFitness(ParticlePSO particle){
        double fitness = calculateFitness(particle.currPos);
        particle.setmCurrentFitness(fitness);
        return fitness;
    }
    
    /**
     *  creates a complete graph where each particle has all other particles as neighbors
     */
    public void setGBestSwarm(){
        for(int i=0; i<this.mParticleSize; i++){
            for(int j=0; j<this.mParticleSize; j++){
                if(j==i)continue;
                allParticles[i].mNeighbour.add(j);
            }
        }
    }
    
    /**
     * 
     * @param particle  the reference of the particle whose
     * best position is to be updated to the current position
     * Also, update the best fitness to current fitness
     * 
     */
    public void updateBestFitness(ParticlePSO particle){
        for(int i=0; i<particle.getmDimension(); i++){
            particle.bestPos[i] = particle.currPos[i];
        }
        particle.setmBestFitness(particle.getmCurrentFitness());
    }
    
    
    /**
     * 
     * @param velocity the velocity of some particle in some direction
     * @return         the velocity by narrowing it down to some interval
     */
    public double constrict(double velocity){
        if(velocity<mMinVelocity) velocity = mMinVelocity;
        else if(velocity>mMaxVelocity) velocity = mMaxVelocity;
        return velocity;
    }
    
    /**
     * 
     * @param p   the reference of particle whose neighbor
     *            with the best fitness is to be calculated
     * @return    the reference of neighbor with best fitness 
     */
    private ParticlePSO getNeighbourWithBestFitness(ParticlePSO p){
        Iterator<Integer> iterator = p.mNeighbour.iterator();
        double bestFitness = Integer.MAX_VALUE;
        int bestIndex = -1;
        while(iterator.hasNext()){
            int index = iterator.next();
            double fitness = allParticles[index].getmBestFitness();
            if(bestFitness>fitness){
                bestFitness = fitness;
                bestIndex = index;
            }
        }
        return allParticles[bestIndex];
    }

    /**
     * This function executes the particle swarm optimization
     * The function is divided into 3 parts with corresponding details
     */
    public void execute(){
        /* Step 1: 
            Initialize velocities of all particles
            Initialize next positions of all particles
            Set the current best fitness to maximum
        */
        for(int i=0; i<allParticles.length; i++){
            for(int d=0; d<mDimension; d++){
                allParticles[i].nextPos[d] = Math.random();
                allParticles[i].vel[d] = Math.random()*(mMaxVelocity - mMinVelocity) + mMinVelocity;
            }
            allParticles[i].setmBestFitness(Integer.MAX_VALUE);
        }
        
        for(int i=0; i<ITERATION_COUNT; i++){
            /* Step 2:
                Update the current position of every particle 
                Calculate fitness for current position for every particle
                Update best fitness if necessary 
            */
            for(int p=0; p<this.mParticleSize; p++){
                for(int d=0; d<mDimension; d++){
                    allParticles[p].currPos[d] = allParticles[p].nextPos[d];
                }
                
                calculateFitness(allParticles[p]);
                double currentFitness = allParticles[p].getmCurrentFitness();
                double bestFitness = allParticles[p].getmBestFitness();
                
                if(bestFitness>currentFitness){
                    updateBestFitness(allParticles[p]);
                }
                
            }
            
            /* Step 3
                Obtain neighbor with best fitness for 
                every particle to update its velocity and 
                next position
            */
            double inertiaFactor = 0.9;
            double learningFactor1 = 1;
            double learningFactor2 = 1;
            for(int p=0; p<this.mParticleSize; p++){
                ParticlePSO bestNeighbor = this.getNeighbourWithBestFitness(allParticles[p]); 
                for(int d=0; d<this.mDimension; d++){
                    double delta1 = Math.random()*learningFactor1*(allParticles[p].bestPos[d] - allParticles[p].currPos[d]);
                    double delta2 = Math.random()*learningFactor2*(bestNeighbor.currPos[d] - allParticles[p].currPos[d]);
                    allParticles[p].vel[d] = inertiaFactor*allParticles[p].vel[d] + delta1 + delta2;
                    allParticles[p].vel[d] = this.constrict(allParticles[p].vel[d]);
                    allParticles[p].nextPos[d] = allParticles[p].currPos[d] + allParticles[p].vel[d];
                }
            }
            //Function to print the location of the best particle
            this.printBestParticle();
            
            }
    }
    
        /*
        Extra functions for checking
        */
        private void printBestParticle(){
            int bestIndex = -1;
            double bestFitness = Integer.MAX_VALUE;
            for(int i=0; i<this.mParticleSize; i++){
                double fitness = allParticles[i].getmBestFitness();
                if(fitness<bestFitness){bestIndex = i; bestFitness = fitness;}
            }
            for(int i=0; i<this.mDimension; i++){System.out.print(allParticles[bestIndex].bestPos[i]+" ");}
            System.out.println("");
        }
}


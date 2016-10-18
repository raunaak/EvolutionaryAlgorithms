package evolutionaryalgorithms;

public class EvolutionaryAlgorithms {
    
    public static void main(String[] args) {
        
        //ParticleSwarmOptimization swarm = new ParticleSwarmOptimization(10,5,-5,3);
        //swarm.setGBestSwarm();
        //swarm.execute();
        
        GeneticAlgorithm.setSolution("1001001001");
        GeneticAlgorithm.execute();
    }

}

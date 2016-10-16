package evolutionaryalgorithms;

import java.util.HashSet;

public class ParticlePSO {
private int limit = 10000;

    private int mDimension;

    private int mMaxVel;

    private int mMinVel;

    private double mCurrentFitness;
    
    private double mBestFitness;
    
    public HashSet<Integer> mNeighbour; 

    public void setmMaxVel(int mMaxVel) {
        this.mMaxVel = mMaxVel;
    }

    public void setmMinVel(int mMinVel) {
        this.mMinVel = mMinVel;
    }

    public int getmDimension() {
        return mDimension;
    }

    public int getmMaxVel() {
        return mMaxVel;
    }

    public int getmMinVel() {
        return mMinVel;
    }
    
    public double getmCurrentFitness() {
        return mCurrentFitness;
    }

    public void setmCurrentFitness(double mCurrentFitness) {
        this.mCurrentFitness = mCurrentFitness;
    }

    public double getmBestFitness() {
        return mBestFitness;
    }

    public void setmBestFitness(double mBestFitness) {
        this.mBestFitness = mBestFitness;
    }
    
    public double[] vel;

    public double[] currPos;

    public double[] bestPos;

    public double[] nextPos;
    
    /**
     * Constructor to initialize the default settings for particle
     * 
     * @param mMaxVelocity    the maximum velocity a particle is allowed     
     * @param mMinVelocity    the minimum velocity a particle is allowed
     * @param mDimension      the dimension of position vector
     * 
     */
    public ParticlePSO(int mMaxVelocity, int mMinVelocity, int mDimension){
        this.mDimension = mDimension;
        vel = new double[mDimension];
        currPos = new double[mDimension];
        bestPos = new double[mDimension];
        nextPos = new double[mDimension];
        this.mMinVel = mMinVelocity;
        this.mMaxVel = mMaxVelocity;
        this.mNeighbour = new HashSet<Integer>();
    }
    
}
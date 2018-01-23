package resources;
import java.util.*;

public class World {
	private Organism[][] population;
	private String[] genePool;
	private boolean initialized;
	private int size, migrationSize, migrationFrequency, duration;
	private double frequencyDominant, frequencyRecessive;
	private double frequencyDominantNew, frequencyRecessiveNew;
	private int generation = 1;

	public World(int size, int migrationSize, int migrationFrequency, int duration, double frequencyDominant, double frequencyDominantNew){
		setAll(size, migrationSize, migrationFrequency, duration, frequencyDominant, frequencyDominantNew);	
	}
	public void setAll(int size, int migrationSize, int migrationFrequency, int duration, double frequencyDominant, double frequencyDominantNew){
		this.size = size;
		this.migrationSize = migrationSize;
		this.migrationFrequency = migrationFrequency;
		this.duration = duration;
		this.frequencyDominant = frequencyDominant;
		this.frequencyRecessive = 1.0 - frequencyDominant;
		this.frequencyDominantNew = frequencyDominantNew;
		this.population = new Organism[duration][size];
		this.genePool = new String[duration + 1];
		genePool[0] = Double.toString(frequencyDominant);
		this.frequencyRecessiveNew = 1.0 - frequencyDominantNew;
		this.initialized = true;
	}
	public void setSize(int size){
		this.size = size;
	}
	public int getGeneration(){
		return generation;
	}
	public int getDuration(){
		return duration;
	}
	public void setFrequencies(double frequency1, double frequency2){
		this.frequencyDominant = frequency1;
		this.frequencyRecessive = frequency2;
	}
	public boolean initialized(){
		return this.initialized;
	}
	public void printResults(){
		System.out.println("DOMINANT ALLELE FREQUENCIES");
		System.out.println(Arrays.toString(genePool));
	}
	private void updatePopulation(){
		for (int i = 0; i < this.size; i++){
			double probability = Math.random();
			String gene = "";
			if (probability < this.frequencyDominant){
				gene += "P";
			} else {
				gene += "Q";
			}
			probability = Math.random();
			if (probability < this.frequencyDominant){
				gene += "P";
			} else {
				gene += "Q";
			} 
			population[generation - 1][i] = new Organism(gene);
		}
	}
	private void updateFrequencies(){
		double dominant = 0;
		double recessive = 0;
		for (int i = 0; i < this.size; i++){
			if (population[generation - 1][i].getGene().equals("PP")){
				dominant += 2;
			} else if(population[generation - 1][i].getGene().equals("PQ") || population[generation - 1][i].getGene().equals("QP")){
				dominant++;
				recessive++;
			} else if(population[generation - 1][i].getGene().equals("QQ")){
				recessive += 2;
			}
		}
		this.frequencyDominant = dominant / (2 * this.size);
		this.frequencyRecessive = recessive / (2 * this.size);
	}
	private void migration(){
		for (int i = 0; i < this.migrationSize; i++){
			double probability = Math.random();
			String gene = "";
			if (probability < this.frequencyDominantNew){
				gene += "P";
			} else {
				gene += "Q";
			}
			probability = Math.random();
			if (probability < this.frequencyDominantNew){
				gene += "P";
			} else {
				gene += "Q";
			} 
			population[generation - 1][i] = new Organism(gene);
		}
	}
	public void run(){
		while (this.generation <= this.duration){
			updatePopulation();
			if ((this.generation) % this.migrationFrequency == 0){
				migration();
			}
			updateFrequencies();
			if (this.generation <= this.duration){
				updateHistory();	
			}
			this.generation++;
		}
	}
	private void updateHistory(){
		genePool[generation] = Double.toString(frequencyDominant);

	}
}

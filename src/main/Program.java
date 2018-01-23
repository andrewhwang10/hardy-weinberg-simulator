package main;
import java.util.*;
import resources.*;

public class Program {

	private static World simulation;
	private static World preset = new World(100, 50, 10, 50, 0.5, 0.7);
	private static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("HARDY-WEINBURG EQUILIBRIUM SIMULATION");
		System.out.println("");
		help();
		mainLoop();
		System.out.println("Goodbye");

	}
	public static void mainLoop(){
		boolean running = true;
		String input;
		while(running) {
			input = console.next();
			if (input.equalsIgnoreCase("initialize")){
				initialize();
			} else if (input.equalsIgnoreCase("preset")){
				simulation = preset;
			} else if (input.equalsIgnoreCase("run")){
				if (simulation.initialized()){
					simulation.run();

				} else {
					System.out.println("Variables haven't been initialized");
				}				
			} else if (input.equalsIgnoreCase("help")){
				help();
			} else if (input.equalsIgnoreCase("print")){
				if (simulation.getGeneration() > simulation.getDuration()){
					simulation.printResults();
				} else {
					System.out.println("Simulation hasnt been run");
				}
			} else if (input.equalsIgnoreCase("quit")){
				running = false;
			} else {
				System.out.println("Invalid command");
			}

		}
	}
	public static void initialize(){
		System.out.println("INITIALIZE");

		System.out.print("Population Size: ");
		int size = console.nextInt();

		System.out.print("Size of Migration: ");
		int immigrationSize = console.nextInt();

		System.out.print("Migration Every: ");
		int immigrationFrequency = console.nextInt();

		System.out.print("Duration of Simulation: ");
		int duration = console.nextInt();

		System.out.print("Frequency of Dominant Allele: ");
		double frequencyDominant = console.nextDouble();

		System.out.print("Frequency of Dominant Allele in Immigrants: ");
		double frequencyDominantNew = console.nextDouble();

		simulation = new World(size, immigrationSize, immigrationFrequency, duration, frequencyDominant, frequencyDominantNew);
	}
	public static void help(){
		System.out.println("COMMANDS");
		System.out.println("\"initialize\" : initialize variables");
		System.out.println("\"run\"        : run world simulation");
		System.out.println("\"help\"       : call up this menu");
		System.out.println("\"print\"      : print results");
		System.out.println("\"quit\"       : exit program");
		System.out.println("");
	}

}

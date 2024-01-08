package com.workoutplanner.maven.classes;

public class Exercise {

	private int sets;
	private int reps;
	private String name;
	
	public Exercise() {
		name = "not specified";
		sets = 3;
		reps = 10;
	}
	
	public Exercise(String eName, int nSets, int nReps) {
		sets = nSets;
		reps = nReps;
		name = eName;
	}
	
	public int getSets() {
		return sets;
	}
	
	public int getReps() {
		return reps;
	}
	
	public String getName() {
		return name;
	}
	
	public void print() {
		System.out.println("=========");
		System.out.println("Name: "+name);
		for(int i = 1; i < (sets+1); i++) {
			System.out.println("Set "+(i)+": "+reps);
		}
		System.out.println("=========");
	}
	
}

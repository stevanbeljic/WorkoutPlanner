package gui;

import java.util.ArrayList;

public class Exercise {
	
	public final int DEFAULTSETAMOUNT = 3;
	public final int DEFAULTREPAMOUNT = 12;

	private ArrayList<Set> setsArray;
	private String name;
	
	public Exercise() {
		name = "not specified";
		setsArray = new ArrayList<Set>();
		for(int i = 0; i < DEFAULTSETAMOUNT; i++) {
			setsArray.add(new Set(DEFAULTREPAMOUNT));
		}
	}
	
	public Exercise(String eName, int nSets, int nReps) {
		name = eName;
		setsArray = new ArrayList<Set>();
		for(int i = 0; i < nSets; i++) {
			setsArray.add(new Set(nReps));
		}
	}
	
	public Exercise(String eName, int reps[]) {
		name = eName;
		setsArray = new ArrayList<Set>();
		for(int i : reps) {
			setsArray.add(new Set(i));
		}
	}
	
	public ArrayList<Set> getSets() {
		return setsArray;
	}
	
	public String getName() {
		return name;
	}
	
	public void print() {
		System.out.println("=========");
		System.out.println("Name: "+name);
		for(int i = 1; i < setsArray.size(); i++) {
			System.out.println("Set "+(i)+": "+setsArray.get(i).getReps());
		}
		System.out.println("=========");
	}
	
	public int getReps(int setIndex) {
		return setsArray.get(setIndex).getReps();
	}
	
}

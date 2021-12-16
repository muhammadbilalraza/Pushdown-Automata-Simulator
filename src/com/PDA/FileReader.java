package com.PDA;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class FileReader {

	private int nInpVar;
	private int nSVar;
	private int nGStates;
	private int nStates;
	private ArrayList<String> states = new ArrayList<>();
	private String startState;
	private ArrayList<String> goalStates = new ArrayList<>();
	private ArrayList<String> stackAlphabets = new ArrayList<>();
	private String initSSymbol;
	private ArrayList<Integer> inAlphabets = new ArrayList<>();
	private ArrayList<String> tTableTemp = new ArrayList<>();
	private List<ArrayList<String>> tTable;
	private ArrayList<String> inString = new ArrayList<>();

	private int totalLines; 
	private int transitionTableSize; 
	private int noOfInputs; 

	//default constructor
	public FileReader () {	}

	public void setArrayListStr (ArrayList<String> list, String str){
		String[] arr = str.split("\\s+");
		for (int i=0; i<arr.length; i++){
			list.add(arr[i]);
		}
	}
	public void setArrayListInt (ArrayList<Integer> list, String str){
		String[] arr = str.split("\\s+");
		for (int i=0; i<arr.length; i++){
			list.add(Integer.parseInt(arr[i]));
		}
	}

	//parameterized constructor 
	public FileReader (File fileObj) {
		try {
			Scanner reader = new Scanner(fileObj);

			nInpVar = Integer.parseInt(reader.nextLine());
			nSVar = Integer.parseInt(reader.nextLine());
			nGStates = Integer.parseInt(reader.nextLine());
			nStates = Integer.parseInt(reader.nextLine());
			setArrayListStr(states, reader.nextLine());
			startState = reader.nextLine();
			setArrayListStr(goalStates, reader.nextLine());
			setArrayListStr(stackAlphabets, reader.nextLine());
			initSSymbol = reader.nextLine();
			setArrayListInt(inAlphabets, reader.nextLine());

			String temp = "";
			boolean transitionFlag = true;
			boolean inputFlag = false;

			while (reader.hasNextLine()) {

				temp = reader.nextLine();

				//reading transition table
				if (transitionFlag && inAlphabets.contains(Character.getNumericValue(temp.charAt(0)))) {
					transitionFlag = false;
					inputFlag = true;
				} //end if

				if (transitionFlag)
					setArrayListStr(tTableTemp, temp);
				
				//reading rest of the inputs
				if (inputFlag)
					setArrayListStr(inString, temp);

			} //end while

			tTable = generateTuplesForTT(tTableTemp);

			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found in FileReader.java");
			e.printStackTrace();
		}
	}


	public List<ArrayList<String>> generateTuplesForTT(ArrayList<String> list){
		List<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		int listCount = 0;
		for (int i=0; i<list.size(); i+=5) listCount++;
		int index = 0;
		for (int i=0; i<listCount; i++){
			ArrayList<String> tempArr = new ArrayList<>();
			for (int j=0; j<list.size(); j+=5){
				tempArr.add(list.get(index));
				index++;
			}
			arrayList.add(tempArr);
		}

		return arrayList;
	}

// Getters & Setters
	public ArrayList<String> getStates() {
		return states;
	}
	public String getStartState() {
		return startState;
	}
	public ArrayList<String> getGoalStates() {
		return goalStates;
	}
	public ArrayList<String> getStackAlphabets() {
		return stackAlphabets;
	}
	public String getInitSSymbol() {
		return initSSymbol;
	}
	public ArrayList<Integer> getInAlphabets() {
		return inAlphabets;
	}
	public ArrayList<String> gettTableTemp() {
		return tTableTemp;
	}
	public ArrayList<String> getInString() {
		return inString;
	}
	public List<ArrayList<String>> gettTable() {
		return tTable;
	}

	public void printTuples(List<ArrayList<String>> arrayList ){
		// printing arrays
		for (int i=0; i<arrayList.size(); i++){
			System.out.println("Printing " + i + " tuple...");
			for (int j=0; j<arrayList.get(i).size(); j++){
				System.out.print(arrayList.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	public void printArrayListStr(ArrayList<String> list){
		for (int i = 0; i<list.size(); i++){
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
	public void printArrayListInt(ArrayList<Integer> list){
		for (int i=0; i<list.size(); i++){
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

}

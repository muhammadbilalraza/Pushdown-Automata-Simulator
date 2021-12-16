package com.PDA;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PDA {

    private FileReader fr;
    private ArrayList<String> pathTaken = new ArrayList<>();
    private Stack<String> stack;
    List<ArrayList<String>> tTable;

    public PDA(File file){
        fr = new FileReader(file);
        stack = new Stack<String>();
        tTable = fr.gettTable();

    }

    public ArrayList<String> getTransition(String currentInput, String currentState) {

        int i=0;
        while (i<tTable.size()) {
            if (tTable.get(i).get(0).equals(currentState) && tTable.get(i).get(1).equals(currentInput))
                return tTable.get(i);
            i++;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("NULL");
        return arrayList;
    }

    public void performPDA(){

        List<String> tempInput = fr.getInString();

        for (int i=0; i<tempInput.size(); i++){
            String currentState = fr.getStartState();
            pathTaken.add(currentState);
            String input = "e" + tempInput.get(i) + "e";
            for (int j=0; j<input.length(); j++){
                String currentInput = Character.toString(input.charAt(j));
                ArrayList<String> transition = getTransition(currentInput, currentState);

                if(transition.get(0).equals("NULL"))
                    break;

                if(!transition.get(2).equals("e") && !transition.get(2).equals(stack.peek()))
                    break;

                currentState = transition.get(4);
                pathTaken.add(currentState);

                if (!transition.get(2).equals("e")  && stack.peek().equals(transition.get(2)))
                        stack.pop();
                if (!transition.get(3).equals("e"))
                    stack.push(transition.get(3));

            }
//            currentState = "";
            while(stack.empty() == false)
                stack.pop();

            if (stack.empty() && fr.getGoalStates().contains(pathTaken.get(pathTaken.size()-1))) {
                for (int p=0; p<pathTaken.size(); p++)
                    System.out.print(pathTaken.get(p) + " ");
                System.out.println("\nAccepted");
            }
            else {
                for (int p=0; p<pathTaken.size(); p++)
                    System.out.print(pathTaken.get(p) + " ");
                System.out.println("\nRejected");

            }
            pathTaken.clear();
        }



    } //performPDA end



} //class end

package com.control;

import com.model.Model;

public class Evolution {

	public Evolution() {
		// TODO Auto-generated constructor stub
	}

	public void start(){


		boolean bCont  =true;
		Population pop = new Population();

		for (int test = 0; test < 150 ; test++) {


			bCont  =true;
			pop = new Population();
			pop.generatePopulation();
			int generation=0; 

			for (int i = 0; i < Model.TOTAL_GENERATION && bCont; i++) {
				generation = i+1;
				
				pop = pop.generateNextGeneration();
				//System.out.println(i+":"+pop.bestBoard.getPoint());
				if(pop.bestBoard.getPoint() == Model.MAX_POINT){
					bCont = false;
				}
				
			}

			if(pop.bestBoard.point == Model.MAX_POINT ){
				System.out.print("Partie "+ (test+1)+ " : Generation "+generation+" ** ");
				for (int i = 0; i < pop.bestBoard.queenOnBoard.size(); i++) {
					System.out.print((pop.bestBoard.getQueen(i).getLine()+1)+" ");
					
				}
				
				System.out.println();
			}else{
				System.out.println("Partie "+ test+ " : Pas de solution");
			}


		}

	}

}

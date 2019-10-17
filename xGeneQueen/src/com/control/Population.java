package com.control;

import java.util.ArrayList;

import com.model.Model;

public class Population {

	ArrayList<Board> population;
	Board bestBoard = null;
	
	public Population() {
		population = new ArrayList<Board>();
	}
	
	public void generatePopulation(){
		population = new ArrayList<Board>();
		Board boardTemp;
		
		for (int i = 0; i < Model.POPULATION_SIZE; i++) {
			boardTemp = new Board().randomGenerate();
			add(boardTemp);
		}
	}
	
	
	public void add(Board board){		
		population.add(board);
		if( (bestBoard == null) || (bestBoard.getPoint() < board.getPoint())){
			bestBoard = board;
		}
		
	}
	
	public Population generateNextGeneration(){
		Population nextGeneration = new Population();
		Board parent1;
		Board parent2;

		Board child1;
		Board child2;
		int cutTh;
		
		for (int i = 0; i < Model.POPULATION_SIZE/2; i++) {
			
			// select 2 candidates randomly			
			parent1 = getParent();
			parent2 = getParent();
			population.add(parent1);
			population.add(parent2);
			
			//cross the candidate			
			cutTh = 1+(int)((Model.BOARD_SIZE-1)*Math.random());
			child1 = new Board(parent1, parent2, cutTh);
			child2 = new Board(parent2, parent1, cutTh);
			
			// apply mutation 
			if(Math.random()<Model.MUTATION_RATE){
				child1.getRandomQueen().mutateLine();
				child1.evaluateBoard();
			}
			if(Math.random()<Model.MUTATION_RATE){
				child2.getRandomQueen().mutateLine();
				child2.evaluateBoard();
			}
			
			// to next generation
			nextGeneration.add(child1);
			nextGeneration.add(child2);
		}
		
		
		return nextGeneration;
	}
	
	
	private Board getParent(){
		Integer totalPoint = 0;
		Integer pointParentTh;
		Integer pointCpt;
		Integer parentIndex;
		
		for (int i = 0; i < population.size(); i++) {
			totalPoint = totalPoint+ population.get(i).getPoint();
		}
		pointParentTh = (int) (totalPoint*Math.random());
		
		pointCpt = population.get(0).getPoint();
		parentIndex=0;
		while(pointCpt<pointParentTh){
			parentIndex++;
			pointCpt = pointCpt + population.get(parentIndex).getPoint();

		}

		
		return population.remove(parentIndex.intValue());
	}
}

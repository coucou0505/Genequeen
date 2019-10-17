package com.control;

import java.util.ArrayList;

import com.model.Model;

public class Board {

	ArrayList<Queen> queenOnBoard;
	Integer point=0;
	
	public Board() {
		queenOnBoard = new ArrayList<Queen>();
	}

	public Board(Board parent1,Board parent2,int cut) {
		Queen queenTemp;
		
		queenOnBoard = new ArrayList<Queen>();
		
		for (int i = 0; i < cut; i++) {
			queenTemp = parent1.getQueen(i).clone();
			queenOnBoard.add(queenTemp);
		}
		for (int i = cut; i < Model.BOARD_SIZE; i++) {
			queenTemp = parent2.getQueen(i).clone();			
			queenOnBoard.add(queenTemp);			
		}
		evaluateBoard();
	}
	
	public Board randomGenerate(){
		Queen queenTemp;
		queenOnBoard = new ArrayList<Queen>();
		for (int i = 0; i <Model.BOARD_SIZE; i++) {
			queenTemp = Queen.generateRandomQueen();
			queenTemp.setCol(i);
			queenOnBoard.add(queenTemp);
		}
		evaluateBoard();
		return this;
	}
	
	public int evaluateBoard(){
		point = 0;
		
		for (int i = 0; i < queenOnBoard.size()-1; i++) {
			for (int j = i+1; j < queenOnBoard.size(); j++) {
				if(! queenOnBoard.get(i).attaque(queenOnBoard.get(j))){
					point++;
				}
			}
		}		
		return point;
	}

	public Integer getPoint() {
		return point;
	}
	
	public Queen getRandomQueen(){
		return queenOnBoard.get((int)((queenOnBoard.size())*Math.random()));
	}
	
	public Queen getQueen(Integer index){
		return queenOnBoard.get(index);
	}
}

package com.control;

import com.model.Model;

public class Queen {
	static private Integer BOARD_SIZE = Model.BOARD_SIZE;
	
	private Integer line=0;
	private Integer col=0;
	
	
	public Queen() {		
	}
	
	public Queen(Integer line, Integer col) {
		this.line =line;
		this.col = col;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	
	public boolean attaque(Queen queen){
		boolean att = false;
		
		if(line == queen.getLine()){
			att = true;
		}
		
		if(col == queen.getCol()){
			att = true;
		}
		if(Math.abs(line - queen.getLine()) == Math.abs(col - queen.getCol()) ){
			att = true;
		}
		
		return att;
	}
	
	
	public void mutateLine(){
		line = (int)((line + Math.floor((BOARD_SIZE-1)*Math.random()))%BOARD_SIZE);
	}
	
	public void mutateCol(){
		col = (int)((col + Math.floor((BOARD_SIZE-1)*Math.random()))%BOARD_SIZE);
	}
	
	static public Queen generateRandomQueen(){
		return new Queen((int)((BOARD_SIZE)*Math.random()),(int)((BOARD_SIZE)*Math.random()));
	}
	
	public Queen clone(){
		return new Queen(line, col);
	}
	
	
}


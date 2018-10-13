package br.com.poli.classes;

public class SudokuNormal extends Sudoku{
	
	
	public SudokuNormal() {
		super ();
		this.initializeSudoku();
		}

	 public  void initializeSudoku() {

		 setRandom();
		 setHoles(difficulty.Normal);
		 
	 }
	 

}

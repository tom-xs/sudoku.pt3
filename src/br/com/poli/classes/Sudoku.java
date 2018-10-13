package br.com.poli.classes;

import java.time.LocalDateTime;
import java.util.Random;
import static java.time.temporal.ChronoUnit.SECONDS;

abstract public class Sudoku extends Game{

    private long totalTime;
    private Player player;
    private Cell[][] gridPlayer = new Cell[9][9];
	

    //construtor

    public Sudoku() {

        super();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gridPlayer[i][j] = new Cell();
            }
        }

        initializeSudoku();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gridPlayer[i][j].getValue() != 0) {
                    gridPlayer[i][j].setFixed(true);
                    gridPlayer[i][j].setValid(true);
                }
            }
        }
        setPossibleNumbers();
    }

     public abstract void initializeSudoku();
    

    //resolve sudoku

     public boolean solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(gridPlayer[i][j].getValue() == 0){
                    for(int k = 1 ; k < 10 ; k++){
                        gridPlayer[i][j].setValue(k);
                       try { if(checkValidation(getCell(i,j)) && solve()){
                            return true;
                        }}catch (CellIsFixedException e) {
                        	
                        	e.printStackTrace();
                        }
                        gridPlayer[i][j].setValue(0);
                    
                    
                    
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //atribui valores randomicos ao tabuleiro e verifica se há um solução

    protected void setRandom(){

        Random random = new Random();

        clearPossibleNumbers();
        setPossibleNumbers();


        // o do while serve pra garantir totalmente que o tabuleiro gerado terá ao menos 1 solução

        do {

            //reseta os valores do tabuleiro

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    gridPlayer[i][j].setValue(0);
                }
            }

            //atribui valores aleatorios ao tabuleiro

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                	int h = gridPlayer[i][j].POSSIBLE_NUMBERS.get(random.nextInt(gridPlayer[i][j].POSSIBLE_NUMBERS.size()));
                    if (checkPossible(gridPlayer[i][j], h))
                        gridPlayer[i][j].setValue(h);
                	
                	}
            }

            //verifica se o tabuleiro tem uma solução

            solve();

        }while(!isFilled());
    }

    //gera buracos em cada submatriz

    protected void holesSubmatriz(int comecoX, int fimX, int comecoY, int fimY, difficulty d){

        //valor de buracos para cada submatriz = h
    	double h=5;

    	switch(d) {
    	case Easy:
    		h = 5;
    		break;
    	case Normal:
    		h = 6;
    		break;
    	case Hard:
    		h = 7;
    		break;
    	}
        //numero total de posições que faltam analisar = k

        double k = 9;

        for (int i = comecoX; i < fimX; i++) {
            for (int j = comecoY; j < fimY; j++) {
                double holeChance = h / k;
                if (Math.random() <= holeChance) {
                    gridPlayer[i][j].setValue(0);
                    h--;
                }
                k--;
            }
        }
    }

    //buracos para cada sub-matriz

    protected void setHoles(difficulty d){
        holesSubmatriz(0,3,0,3, d );
        holesSubmatriz(0,3,3,6, d );
        holesSubmatriz(0,3,6,9, d );
        holesSubmatriz(3,6,0,3, d );
        holesSubmatriz(3,6,3,6, d );
        holesSubmatriz(3,6,6,9, d );
        holesSubmatriz(6,9,0,3, d );
        holesSubmatriz(6,9,3,6, d );
        holesSubmatriz(6,9,6,9, d );
    }

    //retorna true se o tauleiro estiver totalmente preenchido

    protected boolean isFilled(){
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(gridPlayer[i][j].getValue()==0)
                    return false;
            }
        }
        return true;
    }


    //seta os numeros possiveis pra cada cell

    protected void setPossibleNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int n = 1; n < 10; n++) {
                    if (checkPossible(gridPlayer[i][j],n))
                        getCell(i, j).POSSIBLE_NUMBERS.add(n);
                }
            }
        }
    }

    //limpa os numeros possives de cada cell( util para setar os valores aleatorios ao tabuleiro)

    protected void clearPossibleNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                getCell(i,j).POSSIBLE_NUMBERS.clear();
            }
        }
    }


    //seta o tempo total de jogo em formato de long

    public void setTotalTime(LocalDateTime startTime,LocalDateTime endTime){
        this.totalTime = startTime.until(endTime, SECONDS);
    }

    //retorna o tempo total de jogo

    public long returnTotalTime(){return totalTime;}



    @Override

    public void endGame(){
        super.endGame();
        setTotalTime(getStartTime(),getEndTime());
    }

    //retorna uma cell (será util para determinar qual cell pertence à tal posição(o teste)

    public Cell getCell(int x, int y){
        return gridPlayer[x][y];
    }

    //seta o valor para uma cell

    public void setValueGrid(int x, int y, int value) throws CellValueException,CellIsFixedException {
        if (value > 0 && value < 10) {
            gridPlayer[x][y].setValue(value);

            if (checkValidation(gridPlayer[x][y])) {
                gridPlayer[x][y].setValue(value);
                gridPlayer[x][y].setValid(true);
            } else {
                gridPlayer[x][y].setValue(value);
                gridPlayer[x][y].setValid(false);
            }
        }
    }

    //checa se o valor de teste passado para a cell é valido

    private boolean checkPossible(Cell cell,int value) {

        int oldValue = cell.getValue();

        cell.setValue(value);

       try {
    	   if (checkValidation(cell)) {
            cell.setValue(oldValue);
            return true;
        }
        else{
            cell.setValue(oldValue);
            return false;
        }
       }catch (CellIsFixedException e) {
    	   cell.setValue(oldValue);
    	   e.printStackTrace();
    	   return false;
       }
  
    
    
    
    }

    //checa os requisitos/regras do sudoku para ver se o valor passado para a cell é valido

    private boolean checkValidation(Cell cell)throws CellIsFixedException  {

        int i = 0;
        int j = 0;

        if (cell.isFixed()) {
        	/*comentei esse throw porque se descomentar e comentar o return false 
        	 * fica imprimindo o erro varias vezes*/
        	
        	//throw new CellIsFixedException();
        	return false;
        } else {
            for (int pox = 0; pox < 9; pox++) {
                for (int poy = 0; poy < 9; poy++) {
                    if (cell.toString().equals(gridPlayer[pox][poy].toString())) {
                        i = pox;
                        j = poy;
                        pox = 9;
                        poy = 9;
                    }
                }
            }

            //checagem de linha

            for (int k = 0; k < 9; k++) {
                if (cell.getValue() == gridPlayer[k][j].getValue() && k != i)
                    return false;
            }

            //checagem de coluna

            for (int k = 0; k < 9; k++) {
                if (cell.getValue() == gridPlayer[i][k].getValue() && k != j)
                    return false;
            }

            //checagem de sub-matrizes

            int comecarX = 0;
            int terminarX = 0;
            int terminarY = 0;
            int comecarY = 0;

            if ((i >= 0 && i < 3) && (j >= 0 && j < 3)) {
                comecarX = 0;
                terminarX = 3;
                comecarY = 0;
                terminarY = 3;
            }else

            if ((i >= 0 && i < 3) && (j >= 3 && j < 6)) {
                comecarX = 0;
                terminarX = 3;
                comecarY = 3;
                terminarY = 6;
            }else

            if ((i >= 0 && i < 3) && (j >= 6 && j < 9)) {
                comecarX = 0;
                terminarX = 3;
                comecarY = 6;
                terminarY = 9;
            }else

            if ((i >= 3 && i < 6) && (j >= 0 && j < 3)) {
                comecarX = 3;
                terminarX = 6;
                comecarY = 0;
                terminarY = 3;
            }else

            if ((i >= 3 && i < 6) && (j >= 3 && j < 6)) {
                comecarX = 3;
                terminarX = 6;
                comecarY = 3;
                terminarY = 6;
            }else

            if ((i >= 3 && i < 6) && (j >= 6 && j < 9)) {
                comecarX = 3;
                terminarX = 6;
                comecarY = 6;
                terminarY = 9;
            }else

            if ((i >= 6 && i < 9) && (j >= 0 && j < 3)) {
                comecarX = 6;
                terminarX = 9;
                comecarY = 0;
                terminarY = 3;
            }else
            if ((i >= 6 && i < 9) && (j >= 3 && j < 6)) {
                comecarX = 6;
                terminarX = 9;
                comecarY = 3;
                terminarY = 6;
            }else
            if ((i >= 6 && i < 9) && (j >= 6 && j < 9)) {
                comecarX = 6;
                terminarX = 9;
                comecarY = 6;
                terminarY = 9;
            }
            for (int k = comecarX; k < terminarX; k++) {
                for (int l = comecarY; l < terminarY; l++) {
                    if (cell.getValue() == gridPlayer[k][l].getValue() && k != i && l != j)
                        return false;
                }
            }
            return true;
        }
    }
}

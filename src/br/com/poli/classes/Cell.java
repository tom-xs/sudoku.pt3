package br.com.poli.classes;

import java.util.ArrayList;

public class Cell {

    private int value;
    private boolean valid;
    private boolean fixed;
    ArrayList<Integer> POSSIBLE_NUMBERS = new ArrayList<Integer>(1);
    


    //construtores para Cell
    public Cell() { }

    public Cell(int value, boolean valid, boolean fixed) {
        this.value=value;
        this.valid=valid;
        this.fixed=fixed;

    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public boolean isValid() { return valid; }

    public void setValid(boolean valid) { this.valid = valid; }

    public boolean isFixed() { return fixed; }

    public void setFixed(boolean fixed) { this.fixed = fixed; }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegosudoku;

/**
 * Clase para tratar las diferentes excepciones de la clase Sudoku
 * @author David Pacheco
 * Poniente Formacion
 * @version: 25/03/2017
 */
public class SudokuException extends Exception {
     public SudokuException(String mensaje) {
            super(mensaje);
        }
}

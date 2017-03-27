/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegosudoku;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase main para jugar al Sudoku
 * @author David Pacheco
 * @version: 25/03/2017
 */
public class JuegoSudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicializamos variables
        Sudoku sudoku=new Sudoku();
        int opcion;
        Scanner scan=new Scanner(System.in);
        int fila,columna,elemento;
        // Creación del menu
        String menu="---------- SUDOKU -----------\n";
        menu+="1-Iniciar Sudoku\n";
        menu+="2-Realizar Movimiento\n";
        menu+="3-Vaciar casilla\n";
        menu+="4-Mostrar Sudoku\n";
        menu+="5-Terminar\n";
        menu+="Opcion: ";
        // Bucle para ir eligiendo las distintas opciones del menu hasta pulsar 5
        do{
            System.out.print(menu);
           
            opcion=scan.nextInt();
            switch(opcion){
                // 1-Iniciar Sudoku
                case 1:
                    sudoku.inicializar();
                break;
                // 2-Realizar Movimiento
                case 2:
                    System.out.println("Introduzca fila y columna separada por un espacio: ");
                    fila=scan.nextInt();
                    columna=scan.nextInt();
                    System.out.println("Introduzca el numero que desea añadir: ");
                    elemento=scan.nextInt();
                    try {
                        sudoku.modificarElemento(fila, columna, elemento);
                    } catch (SudokuException ex) {
                        System.out.println(ex.getMessage());
                    }
                break;
                // 3-Vaciar casilla
                case 3:
                    System.out.println("Introduzca fila y columna separada por un espacio: ");
                    fila=scan.nextInt();
                    columna=scan.nextInt();
                    sudoku.vaciarElemento(fila, columna);
                break;
                // 4-Mostrar Sudoku
                case 4:
                    System.out.println(sudoku.toString());
                break;
            
            }
            
        }while(opcion!=5);
 
       
        
    }
    
}

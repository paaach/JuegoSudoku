/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegosudoku;

import java.util.Random;

/**
 * Clase para crear, modificar y eliminar Sudokus generados de forma aleatoria
 * @author David Pacheco
 * @version: 25/03/2017
 */
public class Sudoku
{
    
    // Variables privadas de la clase que contendra la matriz 9x9
    private int[][]tablero;
    /**
     * Constructor por defecto para crear una matriz 9x9
     */
    public Sudoku()
    {
        tablero=new int[9][9];
    }
    /**
     * Función para inicializar un sudoku de forma aleatoria
     */
    public void inicializar()
    {
        //Creamos una matriz 9x9 de un sudoku resuelto y se le asigna a nuestro tablero
        int [][] resuelto={{8,3,9,6,7,4,5,2,1},{7,5,1,9,2,8,3,6,4},{6,4,2,5,1,3,9,8,7},{9,6,7,2,3,5,1,4,8},{2,8,5,4,6,1,7,9,3},{4,1,3,8,9,7,6,5,2},{1,9,6,7,4,2,8,3,5},{3,2,8,1,5,9,4,7,6},{5,7,4,3,8,6,2,1,9}};
        tablero=resuelto;
        Random rand=new Random();
        // Creamos una rotación aleatoria y varias permutaciones aleatorias
        int rotaciones=rand.nextInt(3)+1;
        int permutaciones=rand.nextInt(5)+1;
        // Dependiendo el número aleatorio entre 1 y 3 que salga aplicamos una rotación a la Matriz
        switch(rotaciones){
            case 1: rotar90();
            break;
            case 2: rotar180();
            break;
            case 3: rotar270();
            break;
        }
        //Bucle con las permutaciones aleatorias que se aplicarán a la Matriz, 
        //dentro del bucle se comprobara que el numero b nunca sea igual al a
        int a,b;
        for(int i=0;i<permutaciones;i++){
            a=rand.nextInt(9)+1;
            do{
                b=rand.nextInt(9)+1;
            }while(b==a);
            permutacion(a, b);
        }
        //Bucle para quitarle n elementos a la Matriz (Facil n=35, Medio n=50, Dificil n=65),
        // así creamos un sudoku aleatorio
        int n=50;
        for(int i=0;i<n;i++){
            do{
                a=rand.nextInt(9);
                b=rand.nextInt(9);
            }while(tablero[a][b]==0);
            tablero[a][b]=0;
        }
        // Información sacada de http://www.uaq.mx/ingenieria/publicaciones/eureka/n24/camepa01.pdf 
        // para poder crear sudokus aleatorios
    }
    /**
     * Función sobrecargada para mostrar el sudoku en un String
     * @return String del sudoku
     */
    @Override
    public String toString()
    {
        String resultadoFinal = "";
        for(int i=0;i<9;i++){
            if(i%3==0){
                resultadoFinal+="__________________";
                resultadoFinal+="\n";
            }
            for(int j=0;j<9;j++){
                if(j%3==0){
                    resultadoFinal+="|";
                }else
                    resultadoFinal+=" ";
                if(tablero[i][j]==0)
                    resultadoFinal+=" ";
                else
                resultadoFinal+=tablero[i][j];
            }

            resultadoFinal+="\n";
        }

        return resultadoFinal;
    }
    /**
     * Función para modifica un elemento pasandole unas coordenas y el valor a cambiar
     * @param fila posición de la fila a mirar
     * @param columna posición de la columna a mirar
     * @param elemento valor que se añadira
     * @throws juegosudoku.SudokuException
     */
    public void modificarElemento(int fila, int columna, int elemento) throws SudokuException
    {
        if(fila>0 && fila <10 && columna>0 && columna<10)
            if (elemento>0 && elemento < 10){
                 if(puedoInsertar(fila, columna, elemento))
                     tablero[fila-1][columna-1]=elemento;
                 else
                     throw new SudokuException("Posición incorrecta");
            }else
                throw new SudokuException("El número debe estar entre 1 y 9");
           
        else
            throw new SudokuException("Fila o columna incorrecta");

    }
    /**
     * Función para vaciar un elemento pasandole unas coordenadas
     * @param fila posición de la fila a mirar
     * @param columna posición de la columna a mirar
     */
    public void vaciarElemento(int fila, int columna)
    {
        if(fila>0 && fila <10 && columna>0 && columna<10)
            tablero[fila-1][columna-1]=0;
    }
    /**
     * Función para comprobar si el elemento que se pasa como parametro no esta repetido en la fila
     * @param fila posición de la fila a mirar
     * @param elemento valor que se comprobara
     * @return True si no esta el elemento en la fila, False si ya esta el elemento en la fila .
     */
    private boolean comprobarFila(int fila, int elemento)
    {
        boolean resultado = true;
        int i=0;
        fila--;
        while(i<9 && resultado){
            if (tablero[fila][i]==elemento)
                resultado=false;
            i++;
        }
        return resultado;
    }
    /**
     * Función para comprobar si el elemento que se pasa como parametro no esta repetido en la columna
     * @param columna posición de la columna a mirar
     * @param elemento valor que se comprobara
     * @return True si no esta el elemento en la columna, False si ya esta el elemento en la columna
     */
    private boolean comprobarColumna(int columna, int elemento)
    {
        boolean resultado = true;
        int i=0;
        columna--;
        while(i<9 && resultado){
            if (tablero[i][columna]==elemento)
                resultado=false;
            i++;
        }
        return resultado;
    }
    /**
     * Función para comprobar si el elemento que se pasa como parametro no esta repetido en el cuadrante de las coordenadas
     * @param fila posición de la fila a mirar
     * @param columna posición de la columna a mirar
     * @param elemento valor que se comprobara
     * @return True si no esta el elemento en el cuadrante, False si ya esta el elemento en el cuadrante .
     */
    private boolean comprobarCuadrante(int fila, int columna, int elemento)
    {
        boolean resultado = true;
        int filaC=((fila-1)/3)*3;
        int columnaC=((columna-1)/3)*3;
        int i=filaC,j=columnaC;
        int topeF=i+3,topeC=j+3;
        while(i<topeF && resultado){
            j=columnaC;
            while(j<topeC && resultado){
                if(tablero[i][j]==elemento)
                    resultado=false;
                j++;
            }
            i++;
        }

        return resultado;
    }
    /**
     * Función para comprobar si el elemento que se pasa como parametro no esta repetido en la fila, columna y cuadrante
     * @param fila posición de la fila a mirar
     * @param columna posición de la columna a mirar
     * @param elemento valor que se comprobara
     * @return True si se puede insertar, False si no se puede insertar .
     */
    private boolean puedoInsertar(int fila, int columna, int elemento)
    {
        boolean resultado=comprobarColumna(columna, elemento)&& comprobarFila(fila, elemento)&& comprobarCuadrante(fila, columna, elemento);
        return resultado;
    }
    /**
     * Función para rotar la matriz 90 grados
     */
    private void rotar90(){
        int [][] rotado=new int[9][9];
        int fila=0,columna=0;
        for(int i=0;i<9;i++){
            fila=0;
            for (int j=8;j>=0;j--){
                rotado[fila][columna]=tablero[i][j];
                fila++;
            }
            columna++;
        }
        tablero=rotado;

    }
    /**
     * Función para rotar la matriz 180 grados
     */
    private void rotar180(){
        int [][] rotado=new int[9][9];
        int fila=8,columna=0;
        for(int i=0;i<9;i++){
            columna=0;
            for (int j=8;j>=0;j--){
                rotado[fila][columna]=tablero[i][j];
                columna++;
            }
            fila--;
        }
        tablero=rotado;

    }
    /**
     * Función para rotar la matriz 270 grados
     */
    private void rotar270(){
        int [][] rotado=new int[9][9];
        int fila=0,columna=8;
        for(int i=0;i<9;i++){
            fila=0;
            for (int j=0;j<9;j++){
                rotado[fila][columna]=tablero[i][j];
                fila++;
            }
            columna--;
        }
        tablero=rotado;       
    }
    /**
     * Función para permutar un elemento por otro en la Matriz
     * @param a entero entre 1 y 9
     * @param b entero entre 1 y 9
     */
    private void permutacion(int a,int b){
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                if (tablero[i][j]==a)
                    tablero[i][j]=b;
                else if (tablero[i][j]==b)
                    tablero[i][j]=a;
            }
    }
 
}

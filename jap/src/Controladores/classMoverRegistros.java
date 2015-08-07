/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.List;

/**
 *
 * @author Wsr1389
 */
public class classMoverRegistros<T> {

    public classMoverRegistros() {
    }
    
    private List<T> lista;
    private int mov = -1;
    
    public classMoverRegistros(List<T> lista) {
        this.lista = lista;
    }
    
    public void setItem(List<T> lista){
        mov = 0;
        this.lista = lista;
    }
    
    public T getInicio(){
        mov = 0;
        return lista.get(0);
    }
    
    public T getFin(){
        mov = lista.size() - 1;
        return lista.get(mov);        
    }
    
    public T getNext(){
        if(mov + 1 == lista.size()){
            return lista.get(mov);        
        }
        mov++;
        return lista.get(mov);              
    }
    
    public T getBack(){
        if(mov == 0){
            return lista.get(mov);
        }
        mov--;
        return lista.get(mov);
    }
    
    public int getPosAct(){
        return mov;
    }
    
    
}

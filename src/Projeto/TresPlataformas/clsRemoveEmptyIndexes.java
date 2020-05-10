/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas; 

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanele
 * @param <T>
 */
public class clsRemoveEmptyIndexes<T> {
    public List<T> mRemoveEmptyIndexes(T[] array)
    {
        List<T> values = new ArrayList<>();
        for(T data: array) {
            if(data != null) { 
                values.add(data);
            }
        }
      return values;
    }
}

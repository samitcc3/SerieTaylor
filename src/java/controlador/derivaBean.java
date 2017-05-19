/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class derivaBean {

    /**
     * Creates a new instance of derivaBean
     */
    public derivaBean() {
    }
    
    public String derivar(String funcion){
        String derivada = "";
        
        DJep calcDerivada = new DJep();
        calcDerivada.addStandardConstants();
        calcDerivada.addStandardFunctions();
        calcDerivada.addComplex();
        calcDerivada.setAllowUndeclared(true);
        calcDerivada.setAllowAssignment(true);
        calcDerivada.setImplicitMul(true);
        calcDerivada.addStandardDiffRules();
        
        try{
            
            
            Node nodo = calcDerivada.parse(funcion);
            Node diff = calcDerivada.differentiate(nodo, "x");        
            Node sim = calcDerivada.simplify(diff);
            derivada = calcDerivada.toString(sim);
           
            
            
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        return derivada;
    }
    
}

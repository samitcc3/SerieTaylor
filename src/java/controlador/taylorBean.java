/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.serie;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class taylorBean {

    private  String fun;
    private  String as;
    private  int en;
    private derivaBean derivaBean = new derivaBean();
    private serie series = new serie();
    private ArrayList<serie> listDer = new ArrayList<serie>();
    private ArrayList<String> graficar = new ArrayList<String>();

    /**
     * Creates a new instance of taylorBean
     */
    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public int getEn() {
        return en;
    }

    public void setEn(int en) {
        this.en = en;
    }

    public ArrayList<serie> getListDer() {
        return listDer;
    }

    public ArrayList<String> getGraficar() {
        return graficar;
    }

    public void setGraficar(ArrayList<String> graficar) {
        this.graficar = graficar;
    }

    public void setListDer(ArrayList<serie> listDer) {
        this.listDer = listDer;
    }

    public serie getSeries() {
        return series;
    }

    public void setSeries(serie series) {
        this.series = series;
    }
    
    
    
    public void calcularSerie(){
        listDer.removeAll(listDer);
        //Estructura de la serie
        // f(a) + f(a)'/1! * (x-a)^1 + f(a)''/2! * (x-a)^2...
        // f(a) + { f(a)^(n derivadas) / n! } * { x - a }^n 
        if(as.equals("0") || as == null){
            //Serie Maclaurin a=0
            int cont=0;            
            String deriv=null;
            String resulFun=null;
            
            for(int i=0; i<en+1; i++){
                serie stemp = new serie();
                if(i==0){
                    
                    stemp.setN( cont );
                    stemp.setSerie( "[ "+fun + " ] +");
                    cont=cont+1;
                    listDer.add( stemp );
                    deriv = fun;
                    graficar.add(deriv);
                }else{
                    stemp.setN( cont );
                    deriv = derivaBean.derivar(deriv);
                    resulFun = "[ \\frac {[ "+deriv+" ]} {"+cont+"! }] *  ( x )^"+cont+"  +";
                    stemp.setSerie( resulFun );
                    cont=cont+1;
                    listDer.add( stemp );
                    graficar.add(deriv);
                }
                
            }
            
        }else{
            //Serie de Taylor a>0
            int cont=0;            
            String deriv=null;
            String resulFun=null;
            
            for(int i=0; i<en+1; i++){
                serie stemp = new serie();
                if(i==0){
                    
                    stemp.setN( cont );
                    stemp.setSerie( "[ "+fun + " ] +" );
                    cont=cont+1;
                    listDer.add( stemp );
                    deriv = fun;
                    
                }else{
                    stemp.setN( cont );
                    deriv = derivaBean.derivar(deriv);
                    resulFun = " [ \\frac {[ "+deriv+" ]} {"+cont+"! }]    *  ( x -"+as+" )^"+cont+"  +";
                    stemp.setSerie( resulFun );
                    cont=cont+1;
                    listDer.add( stemp );
                }
                
            }
            
        }
        
    }      
       
    
    
}

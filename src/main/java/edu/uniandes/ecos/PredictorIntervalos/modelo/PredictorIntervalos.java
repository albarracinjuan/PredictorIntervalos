package edu.uniandes.ecos.PredictorIntervalos.modelo;

import java.util.List;

/**
 * @name PredictorIntervalos
 * @author Juan
 * @version 1.0
 * @date 24/04/2016
 * @description Ejecuta los calculos necesarios para obtener los valores UPI y LPI
 */
public class PredictorIntervalos {
      
    /** Valor calculado para la siginificanccia. */
    private double significancia;
    
    /** Valor calculado para el rango */
    private double rango;
    
    /** Listas de datos a evaluar. */
    private List<Double> lstDatosX;
    private List<Double> lstDatosY;
    
    /** Valores de regresion. */
    private double tamEstimadoProxy;
    private double valorRegresion;
    
    /** Objetos calculadores de valores */
    private CalculadorRegresion calculadorRegresion;
    private IntegradorSimpson integradorSimpson;
    private BuscadorX buscadorX;

    //method
    /**
     * Constructor con parametros de la clase.
     * @param lstDatosX lista de datos en x
     * @param lstDatosY lista de datos en y
     * @param tamEstimadoProxy tamanio estimado del proxy
     * @throws java.lang.Exception
     */
    public PredictorIntervalos(List<Double> lstDatosX, List<Double> lstDatosY,
            double tamEstimadoProxy) throws Exception {
        this.lstDatosX = lstDatosX;
        this.lstDatosY = lstDatosY;
        this.tamEstimadoProxy = tamEstimadoProxy;
        this.calculadorRegresion = new CalculadorRegresion(lstDatosX, lstDatosY);
        this.buscadorX = new BuscadorX();
        this.valorRegresion = this.calculadorRegresion.calcularRegresion(tamEstimadoProxy);
        this.calcularSignificancia();
        this.calcularRango();  
    }
    
    //method
    /**
     * Calcula la significancia. 
     * @throws Exception 
     */
    private void calcularSignificancia() throws Exception{
        double p;
        double x = (Math.abs(this.calculadorRegresion.getCoeficienteR()) * Math.sqrt((double)this.lstDatosX.size()-(double)2))/
                Math.sqrt((double)1-Math.pow(this.calculadorRegresion.getCoeficienteR(), 2));
        this.integradorSimpson = new IntegradorSimpson(10, this.lstDatosX.size()-2, x);
        p = this.integradorSimpson.integrar();
        this.significancia = (double)1-(double)2*p;              
    }
    
    //method
    /**
     * Calcula el Rango de prediccion de los intervalos.
     * @throws Exception 
     */
    private void calcularRango() throws Exception{
        double tInverso = this.buscadorX.buscarX(0.35, 1.0, this.lstDatosX.size()-2, 10);
        double desvEstandar = this.calcularDesvEstandar();
        double segDentroRaiz;
        double sumDentroRaiz = 0.0;
        
        //Se calcula la sumatoria dentro de la raiz
        for (int i = 0; i < this.lstDatosX.size(); i++) {
            sumDentroRaiz += Math.pow(this.lstDatosX.get(i) - this.calculadorRegresion.getMediaDatosX(), 2);
        }
        
        //se calcula el segmento completo dentro de la raiz.
        segDentroRaiz = (double)1 + ((double)1/this.lstDatosX.size()) + 
                (Math.pow(this.tamEstimadoProxy - this.calculadorRegresion.getMediaDatosX(), 2)/sumDentroRaiz);
        
        //Se obtiene el rango con los parametros calculados anteriormente.
        this.rango = tInverso * desvEstandar * Math.sqrt(segDentroRaiz);
    }
    
    //method
    /**
     * Obtiene el valor UPI.
     * @return 
     */
    public double obtenerUPI(){
        double valorUPI = this.valorRegresion + this.rango;
        
        return valorUPI;
    }
    
    //method
    /**
     * Obtiene el valor LPI.
     * @return 
     */
    public double obtenerLPI(){
        double valorLPI = this.valorRegresion - this.rango;
        
        return valorLPI;
    }
    
    //method
    /**
     * Calcula la desviacion estandar de las dos listas de datos. 
     * @return desviacion estandar
     */
    private double calcularDesvEstandar(){
        double sumatoria = 0.0;
        
        for (int i = 0; i < this.lstDatosX.size(); i++) {
            sumatoria += Math.pow((this.lstDatosY.get(i) - 
                    this.calculadorRegresion.getParamB0() - 
                    this.calculadorRegresion.getParamB1() * this.lstDatosX.get(i)), 2);
        }
        
        double desvEstandar = Math.sqrt(((double)1/((double)this.lstDatosX.size()-2)) * sumatoria);
      
        return desvEstandar;
    }
    
    /**
     * @return the significancia
     */
    public double getSignificancia() {
        return significancia;
    }

    /**
     * @param significancia the significancia to set
     */
    public void setSignificancia(double significancia) {
        this.significancia = significancia;
    }

    /**
     * @return the rango
     */
    public double getRango() {
        return rango;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(double rango) {
        this.rango = rango;
    }

    /**
     * @return the tamEstimadoProxy
     */
    public double getTamEstimadoProxy() {
        return tamEstimadoProxy;
    }

    /**
     * @param tamEstimadoProxy the tamEstimadoProxy to set
     */
    public void setTamEstimadoProxy(double tamEstimadoProxy) {
        this.tamEstimadoProxy = tamEstimadoProxy;
    }

    /**
     * @return the valorRegresion
     */
    public double getValorRegresion() {
        return valorRegresion;
    }

    /**
     * @param valorRegresion the valorRegresion to set
     */
    public void setValorRegresion(double valorRegresion) {
        this.valorRegresion = valorRegresion;
    }

    /**
     * @return the calculadorRegresion
     */
    public CalculadorRegresion getCalculadorRegresion() {
        return calculadorRegresion;
    }
    
    
}

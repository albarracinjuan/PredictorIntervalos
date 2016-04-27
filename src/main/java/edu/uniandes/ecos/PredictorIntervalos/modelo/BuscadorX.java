package edu.uniandes.ecos.PredictorIntervalos.modelo;

/**
 * @name BuscadorX
 * @author Juan
 * @version 1.0
 * @date 07/04/2016
 * @description ejecuta los calculos de integracion de la funcion
 * para hallar el valor de x que cumpla con el resultado p definido.
 */
public class BuscadorX {

    /** Objeto integrador de la funcion t. */
    private IntegradorSimpson integradorSimpson;

    /**
     * Constructor por omision de la clase
     */
    public BuscadorX() {
    }
     
    //method
    /**
     * Busca el valor de x con el cual se obtiene el resultado p en la integracion.
     * 
     * @param paramP
     * @param paramXInicial
     * @param paramDof
     * @param paramNumSeg
     * @return valor de x con el cual se haya el resultado p
     * @throws java.lang.Exception
     */
    public double buscarX(double paramP, double paramXInicial,
            int paramDof, int paramNumSeg) throws Exception{
        double valorX = paramXInicial;
        double pTemporal;
        double factorD = 0.5;
        double errorActual;
        boolean encontroX = false;
        int iteracion = 0;
        String operadorDAnterior = "";
        String operadorDActual = "";
        integradorSimpson = new IntegradorSimpson(paramNumSeg, paramDof, paramXInicial);
        
        //Se itera sucesivamente hasta encontrar el valor de x
        while (!encontroX) {
            integradorSimpson.setParamX(valorX);
            pTemporal = integradorSimpson.integrar();
            errorActual = paramP - pTemporal;
            if (Math.abs(errorActual) < IntegradorSimpson.PARAM_E) {
                encontroX = true;
            }else{
                if (errorActual < 0) {
                    operadorDAnterior = operadorDActual.isEmpty() ? "-" : operadorDActual;
                    operadorDActual = "-";
                }else{
                    operadorDAnterior = operadorDActual.isEmpty() ? "+" : operadorDActual;
                    operadorDActual = "+";
                }           
                
                //El factorD en la primera iteracion es 0.5
                if (iteracion > 0) {
                    boolean operadoresIguales = operadorDAnterior.equals(operadorDActual);                   
                    factorD = ajustarD(factorD, operadoresIguales); 
                }
                if (pTemporal < paramP) {
                    valorX += factorD;
                }else{
                    valorX -= factorD;
                }
            }  
            iteracion++;
        }
        
        return valorX;
    }
    
    //method
    /**
     * Ajusta el valor para el factor d, necesario para una nueva iteracion. 
     * 
     * @param factorD
     * @param errorActual
     * @return el factorD ajustado
     */
    private double ajustarD(double factorD, boolean operadoresIguales){
        double factorDAjustado = factorD;
        
        if (!operadoresIguales) {
            factorDAjustado /= 2;
        }
        
        return factorDAjustado;
    }
    
}

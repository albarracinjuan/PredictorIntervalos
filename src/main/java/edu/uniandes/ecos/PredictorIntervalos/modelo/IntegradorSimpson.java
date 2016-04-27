package edu.uniandes.ecos.PredictorIntervalos.modelo;

/**
 * @name IntegradorSimpson
 * @author Juan
 * @version 1.0
 * @date 25/03/2016
 * @description ejecuta los calculos de integracion de la funcion
 * de distribucion t a traves de la aplicacion de la regla de Simpson.
 */
public class IntegradorSimpson {
    
    /** Error acepatdo. */
    public static final double PARAM_E = 0.00001;
    
    /** Numero de segmentos para integrar la funcion. Cualquier numero par. */
    private int paramNumSeg;
    
    /** Tamanio del segmento. */
    private double paramW;
    
    /** Grados de libertad de la integracion. */
    private double  paramDof;
    
    /** Valor de X para evaluar la funcion.  */
    private double paramX;
    
    //method
    /**
     * Constructor con parametros de la clase
     * 
     * @param paramNumSeg
     * @param paramDof
     * @param paramX 
     */
    public IntegradorSimpson(int paramNumSeg, int paramDof, double paramX) {
        this.paramNumSeg = paramNumSeg;
        this.paramDof = paramDof;
        this.paramX = paramX;
        this.paramW = this.paramX/this.paramNumSeg;
    }
    
    //method
    /**
     * Integra la funcion de distribucion t a traves de la aplicacion
     * de la regla de simpson.
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public double integrar() throws Exception{
        double p1;
        double p2;
        
        p1 = this.aplicarRegla();
        //Ahora se evalua para num_seg * 2
        this.paramNumSeg *= 2;
        this.paramW = this.paramX/this.paramNumSeg;
        p2 = this.aplicarRegla();
        
        /*if (Math.abs(p1 - p2) < PARAM_E) {*/
            return p2;
        /*}else{
            throw new Exception("No se pudo integrar la funcion con los valores ingresados");
        }*/
    }
    
    //method
    /**
     * Calcula el segmento de la funcion de distribucion t en donde no se evalua la funcion Gamma.
     * 
     * @param paramXi
     * @return 
     */
    private double calcularSegmentoNoGamma(double paramXi) throws Exception{
       double segNoGamma = 0.0;
       
        if (this.paramDof == 0) {
            throw new Exception("El parametro dof no puede ser cero.");
        }
        else{
            segNoGamma = Math.pow(1 + (Math.pow(paramXi, 2) / this.paramDof),
                    -((this.paramDof + 1) / 2));
        }
      
       return segNoGamma; 
    }
    
    //method
    /**
     * Calcula el segmento de la funcion de distribucion t en donde no se evalua la funcion Gamma.
     * 
     * @return 
     */
    private double calcularSegmentoGamma(){  
        //Se calcula el valor del segmento
        double segGamma = calcularFuncionGamma(this.paramDof + 1)/((Math.pow(this.paramDof * Math.PI, 0.5))
                * calcularFuncionGamma(this.paramDof));
        
        return segGamma;
    }
    
    //method
    /**
     * Evalua la funcion Gamma para el parametro dof.
     * 
     * @param esEntero
     * @return 
     */
    private double calcularFuncionGamma(double numeroEvaluar) {
        double resultado;
        
        //Se identifica si el numero a evaluar es entero o fraccion.
        boolean esEntero = numeroEvaluar % 2 == 0;
        //Se calcula el factorial
        resultado = factorial((numeroEvaluar/2)-1);
        if (!esEntero) {
            resultado *= 0.5;
            resultado *= Math.sqrt(Math.PI);
        }

        return resultado;
    }
    
    //method
    /**
     * Caclula el factorial del numero ingresado por parametro.
     * 
     * @param number
     * @return 
     */
    private double factorial(double number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
    
    //method
    /**
     * Evalua la funcion de distribucion t para los parametros definidos del programa.
     * 
     * @param segNoGamma
     * @param segGamma
     * @return 
     */
    private double calcularFuncionDistribucionT(double segNoGamma, double segGamma){
        double resultado = segNoGamma * segGamma;
               
        return resultado;
    }
    
    //method
    /**
     * Retorna el multiplicador para la iteracion especifica.
     * 
     * @param i
     * @return 
     */
    private int obtenerMultiplicador(int i){
        if (i == 0 || i == this.paramNumSeg) {
            return 1;
        }else{
            return i % 2 == 0 ? 2 : 4;
        }
    }
    
    //method
    /**
     * Aplica la regla de simpson a la funcion de distribucion t.
     * 
     * @param resultadoFuncion
     * @param multiplicador
     * @return 
     */
    private double aplicarRegla() throws Exception{
        double resultado;
        double xi = 0.0;
        
        //Se ejecuta la primera iteracion
        double segNoGamma = calcularSegmentoNoGamma(xi);
        double segGamma = calcularSegmentoGamma();
        double funcionEvaluada = calcularFuncionDistribucionT(segNoGamma, segGamma);
        int multiplicador = obtenerMultiplicador(0);
        resultado = (this.paramW/3)*multiplicador*funcionEvaluada;
        
        //Se ejecutan el resto de iteraciones
        for (int i = 1; i <= this.paramNumSeg; i++) {
            xi += this.paramW;
            segNoGamma = calcularSegmentoNoGamma(xi);
            funcionEvaluada = calcularFuncionDistribucionT(segNoGamma, segGamma);
            multiplicador = obtenerMultiplicador(i);
            resultado += (this.paramW / 3) * multiplicador * funcionEvaluada;
        }
        
        return resultado;
    }
    
    //method
    /**
     * Redondea el valor ingresado por parametro al numero de decimales seleccionado. 
     * 
     * @param valor
     * @param numDecimales
     * @return 
     */
    public static double redondear(double valor, int numDecimales){
        int decimales = (int) Math.pow(10, numDecimales);
        return Math.rint(valor * decimales) / decimales;
    }
    
    /**
     * @return the paramNumSeg
     */
    public int getParamNumSeg() {
        return paramNumSeg;
    }

    /**
     * @param paramNumSeg the paramNumSeg to set
     */
    public void setParamNumSeg(int paramNumSeg) {
        this.paramNumSeg = paramNumSeg;
    }

    /**
     * @return the paramW
     */
    public double getParamW() {
        return paramW;
    }

    /**
     * 
     * @param paramW
     */
    public void setParamW(double paramW) {
        this.paramW = paramW;
    }

    /**
     * @return the paramDof
     */
    public double getParamDof() {
        return paramDof;
    }

    /**
     * @param paramDof the paramDof to set
     */
    public void setParamDof(int paramDof) {
        this.paramDof = paramDof;
    }

    /**
     * @return the paramX
     */
    public double getParamX() {
        return paramX;
    }

    /**
     * @param paramX the paramX to set
     */
    public void setParamX(double paramX) {
        this.paramX = paramX;
    }
}


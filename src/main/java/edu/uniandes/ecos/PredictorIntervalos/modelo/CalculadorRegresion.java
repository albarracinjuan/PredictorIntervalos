package edu.uniandes.ecos.PredictorIntervalos.modelo;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @name CalculadorRegresion
 * @author Juan
 * @version 1.0
 * @date 01/03/2015
 * @description Calcula los parametros de regresion y los coeficientes de
 * correlacion.
 */
public class CalculadorRegresion {

    /**
     * Constantes con el formato decimal. 
     */
    private static final DecimalFormat FORMATO_DECIMAL = new DecimalFormat("0.000");
    
    /**
     * Listas con los n pares de datos
     */
    private List<Double> lstDatosX;
    private List<Double> lstDatosY;

    /**
     * Valores con parametros para el calculo de la regresion
     */
    private double sumDatosX;
    private double sumDatosY;
    private double mediaDatosX;
    private double mediaDatosY;
    private double sumMultip;
    private double sumCuadradosX;
    private double sumCuadradosY;
    private double paramB1;
    private double paramB0;
    private double coeficienteR;
    
    //method
    /**
     * Constructor por omision.
     */
    public CalculadorRegresion() {
        this.lstDatosX = new LinkedList<>();
        this.lstDatosY = new LinkedList<>();
    }

    //method
    /**
     * Intancia un nuevo objeto con las listas cargadas del archivo, hallando
     * valores necesarios para el calculo de los parametros de regresion.
     *
     * @param lstDatosX
     * @param lstDatosY
     */
    public CalculadorRegresion(List<Double> lstDatosX, List<Double> lstDatosY) throws Exception {
        this.lstDatosX = lstDatosX;
        this.lstDatosY = lstDatosY;
        this.sumDatosX = OperadorFunciones.
                obtenerSumatoria(this.lstDatosX);
        this.sumDatosY = OperadorFunciones.
                obtenerSumatoria(this.lstDatosY);
        this.sumCuadradosX = OperadorFunciones.
                obtenerSumCuadrados(this.lstDatosX);
        this.sumCuadradosY = OperadorFunciones.
                obtenerSumCuadrados(this.lstDatosY);
        this.sumMultip = OperadorFunciones.
                obtenerSumMultip(this.lstDatosX, this.lstDatosY);
        this.mediaDatosX = OperadorFunciones.
                obtenerMedia(this.lstDatosX);
        this.mediaDatosY = OperadorFunciones.
                obtenerMedia(this.lstDatosY);
    }

    //method
    /**
     * Calcula el parametro de regresion B1
     *
     * @throws Exception
     */
    private void calcularParametroB1() throws Exception {
        this.paramB1 = (this.sumMultip - this.lstDatosX.size() * this.mediaDatosX * this.mediaDatosY)
                / (this.sumCuadradosX - (this.lstDatosX.size() * Math.pow(this.mediaDatosX, 2)));
    }

    //method
    /**
     * Calcula el parametro de regresion B0.
     *
     * @throws Exception
     */
    private void calcularParametroB0() throws Exception {
        this.paramB0 = this.mediaDatosY - this.paramB1 * this.mediaDatosX;
    }

    //method
    /**
     * Calcula el coeficiente de correlacion r.
     *
     * @throws Exception
     */
    private void calcularCoeficienteR() throws Exception {
        this.coeficienteR = (this.lstDatosX.size() * this.sumMultip - this.sumDatosX * this.sumDatosY)
                / Math.sqrt((this.lstDatosX.size() * this.sumCuadradosX - Math.pow(this.sumDatosX, 2))
                        * (this.lstDatosX.size() * this.sumCuadradosY - Math.pow(this.sumDatosY, 2)));
    }

    //method
    /**
     * Calcular el valor de la regresion lineal.
     *
     * @param tamanoProxy
     * @return valorRegresion
     * @throws Exception
     */
    public double calcularRegresion(double tamanoProxy) throws Exception {
        double valorRegresion = 0.0;

        try {
            calcularParametroB1();
            calcularParametroB0();
            calcularCoeficienteR();
            valorRegresion = this.paramB0 + this.paramB1 * tamanoProxy;

            return valorRegresion;
        } catch (NullPointerException e) {
            throw new Exception("Alguno de los parametros no pudo ser calculado, por favor verifique los datos del archivo.");
        } catch (Exception e1) {
            throw new Exception(e1.getMessage());
        }
    }

    /**
     * @return the lstDatosX
     */
    public List<Double> getLstDatosX() {
        return lstDatosX;
    }

    /**
     * @param lstDatosX the lstDatosX to set
     */
    public void setLstDatosX(List<Double> lstDatosX) {
        this.lstDatosX = lstDatosX;
    }

    /**
     * @return the lstDatosY
     */
    public List<Double> getLstDatosY() {
        return lstDatosY;
    }

    /**
     * @param lstDatosY the lstDatosY to set
     */
    public void setLstDatosY(List<Double> lstDatosY) {
        this.lstDatosY = lstDatosY;
    }

    /**
     * @return the sumDatosX
     */
    public double getSumDatosX() {
        return sumDatosX;
    }

    /**
     * @param sumDatosX the sumDatosX to set
     */
    public void setSumDatosX(double sumDatosX) {
        this.sumDatosX = sumDatosX;
    }

    /**
     * @return the sumDatosY
     */
    public double getSumDatosY() {
        return sumDatosY;
    }

    /**
     * @param sumDatosY the sumDatosY to set
     */
    public void setSumDatosY(double sumDatosY) {
        this.sumDatosY = sumDatosY;
    }

    /**
     * @return the mediaDatosX
     */
    public double getMediaDatosX() {
        return mediaDatosX;
    }

    /**
     * @param mediaDatosX the mediaDatosX to set
     */
    public void setMediaDatosX(double mediaDatosX) {
        this.mediaDatosX = mediaDatosX;
    }

    /**
     * @return the mediaDatosY
     */
    public double getMediaDatosY() {
        return mediaDatosY;
    }

    /**
     * @param mediaDatosY the mediaDatosY to set
     */
    public void setMediaDatosY(double mediaDatosY) {
        this.mediaDatosY = mediaDatosY;
    }

    /**
     * @return the sumMultip
     */
    public double getSumMultip() {
        return sumMultip;
    }

    /**
     * @param sumMultip the sumMultip to set
     */
    public void setSumMultip(double sumMultip) {
        this.sumMultip = sumMultip;
    }

    /**
     * @return the sumCuadradosX
     */
    public double getSumCuadradosX() {
        return sumCuadradosX;
    }

    /**
     * @param sumCuadradosX the sumCuadradosX to set
     */
    public void setSumCuadradosX(double sumCuadradosX) {
        this.sumCuadradosX = sumCuadradosX;
    }

    /**
     * @return the sumCuadradosY
     */
    public double getSumCuadradosY() {
        return sumCuadradosY;
    }

    /**
     * @param sumCuadradosY the sumCuadradosY to set
     */
    public void setSumCuadradosY(double sumCuadradosY) {
        this.sumCuadradosY = sumCuadradosY;
    }

    /**
     * @return the paramB1
     */
    public double getParamB1() {
        return paramB1;
    }

    /**
     * @param paramB1 the paramB1 to set
     */
    public void setParamB1(double paramB1) {
        this.paramB1 = paramB1;
    }

    /**
     * @return the paramB0
     */
    public double getParamB0() {
        return paramB0;
    }

    /**
     * @param paramB0 the paramB0 to set
     */
    public void setParamB0(double paramB0) {
        this.paramB0 = paramB0;
    }

    /**
     * @return the coeficienteR
     */
    public double getCoeficienteR() {
        return coeficienteR;
    }

    /**
     * @param coeficienteR the coeficienteR to set
     */
    public void setCoeficienteR(double coeficienteR) {
        this.coeficienteR = coeficienteR;
    }
}


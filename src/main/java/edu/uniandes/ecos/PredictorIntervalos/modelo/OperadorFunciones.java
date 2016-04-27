package edu.uniandes.ecos.PredictorIntervalos.modelo;

import java.util.List;

/**
 * @name OperadorFunciones
 * @author Juan
 * @version 1.0
 * @date 01/03/2015
 * @description brinda operaciones matematicas comunes.
 */
public final class OperadorFunciones {

    //method
    /**
     * Obtiene la media de los numeros contenidos en la lista.
     *
     * @param lstDatos
     * @return
     */
    public static double obtenerMedia(List<Double> lstDatos) throws Exception {
        if (!lstDatos.isEmpty()) {
            double sumatoria = 0.0;
            for (Double dato : lstDatos) {
                sumatoria += dato;
            }
            double media = sumatoria / lstDatos.size();

            return media;
        } else {
            throw new Exception("La lista no tiene datos, por favor verifique.");
        }
    }

    //method
    /**
     * Obtiene la sumatoria de los numeros de la lista.
     *
     * @param lstDatos
     * @return sumatoria
     * @throws Exception
     */
    public static double obtenerSumatoria(List<Double> lstDatos) throws Exception {
        if (!lstDatos.isEmpty()) {
            double sumatoria = 0.0;
            for (Double dato : lstDatos) {
                sumatoria += dato;
            }

            return sumatoria;
        } else {
            throw new Exception("La lista no tiene datos, por favor verifique.");
        }
    }

    //method
    /**
     * Obtiene la sumatoria de los numeros de la lista cada uno elevado al
     * cuadrado.
     *
     * @param lstDatos
     * @return sumatoria
     * @throws Exception
     */
    public static double obtenerSumCuadrados(List<Double> lstDatos) throws Exception {
        if (!lstDatos.isEmpty()) {
            double sumaCuadrados = 0.0;
            for (Double dato : lstDatos) {
                sumaCuadrados += Math.pow(dato, 2);
            }

            return sumaCuadrados;
        } else {
            throw new Exception("La lista no tiene datos, por favor verifique.");
        }
    }

    //method
    /**
     * Obtiene la sumatoria de los valores de cada indice de las listas,
     * multiplizados entre si.
     *
     * @param lstDatosX
     * @param lstDatosY
     * @return sumatoria
     * @throws Exception
     */
    public static double obtenerSumMultip(List<Double> lstDatosX, List<Double> lstDatosY) throws Exception {
        if (!lstDatosX.isEmpty() && !lstDatosY.isEmpty()) {
            if (lstDatosX.size() == lstDatosY.size()) {
                double sumMultip = 0.0;
                for (int i = 0; i < lstDatosX.size(); i++) { //Se itera las listas y se multiplica el valor de cada posicion. 
                    sumMultip += (lstDatosX.get(i) * lstDatosY.get(i));
                }

                return sumMultip;
            } else {
                throw new Exception("Las listas no tienen el mismo numero de datos, por favor verifique.");
            }
        } else {
            throw new Exception("Las listas no tienen datos completos, por favor verifique.");
        }
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

}


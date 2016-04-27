package edu.uniandes.ecos.PredictorIntervalos.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @name ArchivoDatos
 * @author Juan
 * @version 1.0
 * @date 01/03/2015
 * @description Carga en memoria los datos del archivo seleccionado.
 */
public class ArchivoDatos {

    /**
     * Ruta del archivo a cargar.
     */
    private String ruta;
    /**
     * Delimitador para obtencion de datos.
     */
    public static final String DELIMITADOR = ";";
    /**
     * Listas de datos de las columnas del archivo.
     */
    private List<Double> lstDatosTabla1;
    private List<Double> lstDatosTabla2;

    //method
    /**
     * Constructor por omision de la clase.
     */
    public ArchivoDatos() {
        ruta = "";
        lstDatosTabla1 = new LinkedList<>();
        lstDatosTabla2 = new LinkedList<>();
    }

    //method
    /**
     * Instancia el archivo desde la ruta especificada.
     *
     * @param ruta
     */
    public ArchivoDatos(String ruta) {
        this.ruta = ruta;
        lstDatosTabla1 = new LinkedList<>();
        lstDatosTabla2 = new LinkedList<>();
    }

    //method
    /**
     * Carga en memoria los datos del archivo a evaluar.
     *
     * @throws Exception
     */
    public void cargarDatos() throws Exception {
        try {
            FileReader lector = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lector);
            String linea;

            while ((linea = bufferLectura.readLine()) != null) {
                String datos[] = linea.split(DELIMITADOR);
                lstDatosTabla1.add(new Double(datos[0]));
                lstDatosTabla2.add(new Double(datos[1]));

            }
        } catch (FileNotFoundException f) {
            throw new Exception("No se encontro el archivo seleccionado.");
        } catch (IOException e) {
            throw new Exception("Hubo un error al momento de leer los datos, por favor intentelo de nuevo.");
        }
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the lstDatosTabla1
     */
    public List<Double> getLstDatosTabla1() {
        return lstDatosTabla1;
    }

    /**
     * @param lstDatosTabla1 the lstDatosTabla1 to set
     */
    public void setLstDatosTabla1(List<Double> lstDatosTabla1) {
        this.lstDatosTabla1 = lstDatosTabla1;
    }

    /**
     * @return the lstDatosTabla2
     */
    public List<Double> getLstDatosTabla2() {
        return lstDatosTabla2;
    }

    /**
     * @param lstDatosTabla2 the lstDatosTabla2 to set
     */
    public void setLstDatosTabla2(List<Double> lstDatosTabla2) {
        this.lstDatosTabla2 = lstDatosTabla2;
    }

}

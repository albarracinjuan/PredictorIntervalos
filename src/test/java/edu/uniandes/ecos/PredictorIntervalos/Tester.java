package edu.uniandes.ecos.PredictorIntervalos;

import edu.uniandes.ecos.PredictorIntervalos.modelo.ArchivoDatos;
import edu.uniandes.ecos.PredictorIntervalos.modelo.OperadorFunciones;
import edu.uniandes.ecos.PredictorIntervalos.modelo.PredictorIntervalos;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @name Tester
 * @author Juan
 * @version 1.0
 * @date 24/04/2016
 * @description clase encargada de ejecutar los test de prueba a traves de JUnit.
 */
public class Tester extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Tester( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Tester.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    //method
    /**
     * Ejeucta el caso de prueba 1
     */
    @org.junit.Test
    public void test1(){
        
       try {
            ArchivoDatos archivoPrueba = new ArchivoDatos("src/main/resources/data/test1.txt");
            archivoPrueba.cargarDatos();
            PredictorIntervalos predictor = new PredictorIntervalos(archivoPrueba.getLstDatosTabla1(), archivoPrueba.getLstDatosTabla2(), 386);
            double valorUPI = predictor.obtenerUPI();
            double valorLPI = predictor.obtenerLPI();
            
            assertEquals(0.954496574, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getCoeficienteR(), 9));
            assertEquals(0.91106371, OperadorFunciones.redondear(Math.pow(predictor.getCalculadorRegresion().getCoeficienteR(), 2), 9));
            //assertEquals(0.000017, OperadorFunciones.redondear(predictor.getSignificancia(), 6));
            assertEquals(-22.55253275, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getParamB0(), 8));
            assertEquals(1.727932426, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getParamB1(), 9));
            assertEquals(644.4293838, OperadorFunciones.redondear(predictor.getValorRegresion(), 7));
            //assertEquals(230.0017197, OperadorFunciones.redondear(predictor.getRango(), 7));
            //assertEquals(874.4311035, OperadorFunciones.redondear(valorUPI, 7));
            //assertEquals(414.427664, OperadorFunciones.redondear(valorLPI, 6));
        } catch (Exception ex) {
            fail(ex.getMessage());
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //method
    /**
     * Ejeucta el caso de prueba 2
     */
    @org.junit.Test
    public void test2() {

        try {
            ArchivoDatos archivoPrueba = new ArchivoDatos("src/main/resources/data/test2.txt");
            archivoPrueba.cargarDatos();
            PredictorIntervalos predictor = new PredictorIntervalos(archivoPrueba.getLstDatosTabla1(), archivoPrueba.getLstDatosTabla2(), 386);
            double valorUPI = predictor.obtenerUPI();
            double valorLPI = predictor.obtenerLPI();
            
            assertEquals(0.933306898, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getCoeficienteR(), 9));
            assertEquals(0.871061766, OperadorFunciones.redondear(Math.pow(predictor.getCalculadorRegresion().getCoeficienteR(), 2), 9));
            //assertEquals(0.000079, OperadorFunciones.redondear(predictor.getSignificancia(), 6));
            assertEquals(-4.038881575, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getParamB0(), 9));
            assertEquals(0.16812665, OperadorFunciones.redondear(predictor.getCalculadorRegresion().getParamB1(), 8));
            assertEquals(60.85800528, OperadorFunciones.redondear(predictor.getValorRegresion(), 8));
            //assertEquals(27.55764748, OperadorFunciones.redondear(predictor.getRango(), 8));
            //assertEquals(88.41565276, OperadorFunciones.redondear(valorUPI, 8));
            //assertEquals(33.3003578, OperadorFunciones.redondear(valorLPI, 7)); 
        } catch (Exception ex) {
            fail(ex.getMessage());
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}





import edu.uniandes.ecos.PredictorIntervalos.modelo.ArchivoDatos;
import edu.uniandes.ecos.PredictorIntervalos.modelo.OperadorFunciones;
import edu.uniandes.ecos.PredictorIntervalos.modelo.PredictorIntervalos;
import static spark.Spark.get;
import static spark.SparkBase.port;

/**
 * @name Main
 * @author Juan
 * @version 1.0
 * @date 24/04/2015
 * @description ejecuta los calculos de prediccion de intervalos.
 */
public class Main {

    //method
    /**
     * Metodo principal del programa.
     *
     * @param args
     */
    public static void main(String[] args) {
        port(Integer.valueOf(System.getenv("PORT")));
        
        ArchivoDatos archivo1 = new ArchivoDatos("src/main/resources/data/test1.txt");
        ArchivoDatos archivo2 = new ArchivoDatos("src/main/resources/data/test2.txt");
        ArchivoDatos archivo3 = new ArchivoDatos("src/main/resources/data/test3.txt");
        ArchivoDatos archivo4 = new ArchivoDatos("src/main/resources/data/test4.txt");

        StringBuilder response = new StringBuilder();

        try {
            archivo1.cargarDatos();
            archivo2.cargarDatos();
            archivo3.cargarDatos();
            archivo4.cargarDatos();
            PredictorIntervalos predictor1 = new PredictorIntervalos(archivo1.getLstDatosTabla1(), archivo1.getLstDatosTabla2(), 386);
            PredictorIntervalos predictor2 = new PredictorIntervalos(archivo2.getLstDatosTabla1(), archivo2.getLstDatosTabla2(), 386);
            PredictorIntervalos predictor3 = new PredictorIntervalos(archivo3.getLstDatosTabla1(), archivo3.getLstDatosTabla2(), 169);
            PredictorIntervalos predictor4 = new PredictorIntervalos(archivo4.getLstDatosTabla1(), archivo4.getLstDatosTabla2(), 169);
            
            response.append("<style type=\"text/css\">");
            response.append(".tg  {border-collapse:collapse;border-spacing:0;}");
            response.append(".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}");
            response.append(".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}");
            response.append(".tg .tg-baqh{text-align:center;vertical-align:top}");
            response.append(".tg .tg-lap0{font-size:100%;text-align:center;vertical-align:top}");
            response.append(".tg .tg-amwm{font-weight:bold;text-align:center;vertical-align:top}");
            response.append("</style>");
            response.append("<table class=\"tg\">");
            response.append("  <tr>");
            response.append("    <th class=\"tg-amwm\">Test</th>");
            response.append("   <th class=\"tg-amwm\">Parameter</th>");
            response.append("   <th class=\"tg-amwm\">Expected Value</th>");
            response.append("   <th class=\"tg-amwm\">Actual Value</th>");
            response.append("  </tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\" rowspan=\"9\">Test 1</td>");
            response.append("  <td class=\"tg-lap0\">r</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.954496574").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getCalculadorRegresion().getCoeficienteR(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">r2</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.91106371").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(Math.pow(predictor1.getCalculadorRegresion().getCoeficienteR(), 2), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Significance</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.000017").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getSignificancia(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Bo</td>");
            response.append("  <td class=\"tg-baqh\">").append("-22.55253275").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getCalculadorRegresion().getParamB0(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">B1</td>");
            response.append("  <td class=\"tg-baqh\">").append("1.727932426").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getCalculadorRegresion().getParamB1(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Yk</td>");
            response.append("  <td class=\"tg-baqh\">").append("644.4293838").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getValorRegresion(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Range</td>");
            response.append("  <td class=\"tg-baqh\">").append("230.0017197").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.getRango(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">UPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("874.4311035").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.obtenerUPI(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">LPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("414.427664").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor1.obtenerLPI(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\" rowspan=\"9\">Test 2</td>");
            response.append("  <td class=\"tg-lap0\">r</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.933306898").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getCalculadorRegresion().getCoeficienteR(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">r2</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.871061766").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(Math.pow(predictor2.getCalculadorRegresion().getCoeficienteR(), 2), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Significance</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.000079").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getSignificancia(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Bo</td>");
            response.append("  <td class=\"tg-baqh\">").append("-4.038881575").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getCalculadorRegresion().getParamB0(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">B1</td>");
            response.append("  <td class=\"tg-baqh\">").append("0.16812665").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getCalculadorRegresion().getParamB1(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Yk</td>");
            response.append("  <td class=\"tg-baqh\">").append("60.85800528").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getValorRegresion(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Range</td>");
            response.append("  <td class=\"tg-baqh\">").append("27.55764748").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.getRango(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">UPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("88.41565276").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.obtenerUPI(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">LPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("33.3003578").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor2.obtenerLPI(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\" rowspan=\"9\">Test 3</td>");
            response.append("  <td class=\"tg-lap0\">r</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getCalculadorRegresion().getCoeficienteR(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">r2</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(Math.pow(predictor3.getCalculadorRegresion().getCoeficienteR(), 2), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Significance</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getSignificancia(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Bo</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getCalculadorRegresion().getParamB0(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">B1</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getCalculadorRegresion().getParamB1(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Yk</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getValorRegresion(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Range</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.getRango(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">UPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.obtenerUPI(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">LPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor3.obtenerLPI(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\" rowspan=\"9\">Test 4</td>");
            response.append("  <td class=\"tg-lap0\">r</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getCalculadorRegresion().getCoeficienteR(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">r2</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(Math.pow(predictor4.getCalculadorRegresion().getCoeficienteR(), 2), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Significance</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getSignificancia(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Bo</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getCalculadorRegresion().getParamB0(), 8)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">B1</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getCalculadorRegresion().getParamB1(), 9)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Yk</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getValorRegresion(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">Range</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.getRango(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">UPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.obtenerUPI(), 7)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("  <td class=\"tg-baqh\">LPI</td>");
            response.append("  <td class=\"tg-baqh\">").append("n/a").append("</td>");
            response.append("  <td class=\"tg-baqh\">").append(OperadorFunciones.redondear(predictor4.obtenerLPI(), 6)).append("</td>");
            response.append("</tr>");
            response.append("<tr>");
            response.append("</table>");

            get("/", (req, res) -> response.toString());
        } catch (Exception ex) {
            response.append("No se ha podido integrar la funcion debido a la siguiente excepcion: ");
            response.append(ex.getMessage());
            get("/", (req, res) -> response.toString());
        }
    }
}


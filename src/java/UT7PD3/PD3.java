package UT7PD3;

import Clases.TGrafoDirigido;
import Clases.UtilGrafos;

public class PD3 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD3/PD3Vertices.txt","./src/java/UT7PD3/PD3Aristas.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz de conexiones");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Floyd");


    }
}

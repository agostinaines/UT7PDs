package UT7PD2;

import Clases.TGrafoDirigido;
import Clases.UtilGrafos;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD2/PD2Vertices.txt","./src/java/UT7PD2/PD2Aristas.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        UtilGrafos.imprimirMatrizMejorado(gd.floyd(), gd.getVertices(), "Floyd gd");

        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("Excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }

        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
    }
}

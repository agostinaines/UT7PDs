package UT7PD4;

import Clases.TGrafoDirigido;
import Clases.UtilGrafos;

public class PD4 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD4/PD4Vertices.txt","./src/java/UT7PD4/PD4Aristas.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        gd.bpf();
    }
}

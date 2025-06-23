package UT7PD3;

import Clases.TGrafoDirigido;
import Clases.UtilGrafos;

import java.io.Console;
import java.sql.SQLOutput;

public class PD3 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD3/PD3Vertices.txt","./src/java/UT7PD3/PD3Aristas.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Floyd");

        boolean[][] mwarshall = gd.warshall();
        UtilGrafos.imprimirMatrizWarshall(mwarshall, gd.getVertices(), "Warshall");

        System.out.println("Origen: ");
        Comparable origen = System.console().readLine();
        System.out.println("Destino: ");
        Comparable destino = System.console().readLine();

        boolean conexionEs = gd.existeConexion(origen, destino);
        System.out.println(conexionEs);
    }
}

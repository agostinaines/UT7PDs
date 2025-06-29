package UT7PD3;

import Clases.TGrafoDirigido;
import Clases.TVertice;
import Clases.UtilGrafos;
import java.util.Collection;

public class PD3 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD3/PD3Vertices.txt","./src/java/UT7PD3/PD3Aristas.txt",
                false, TGrafoDirigido.class);

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Floyd");

        /*
            El costo de volar de Montevideo a Río de Janeiro es 3780.
            El costo de volar de Montevideo a Curitiba es 2580.
            El centro de mantenimiento debe ubicarse en Curitiba.
         */

        boolean[][] mwarshall = gd.warshall();
        UtilGrafos.imprimirMatrizWarshall(mwarshall, gd.getVertices(), "Warshall");

        /*
            ¿Existen conexiones entre Montevideo y Curitiba? Sí.
            ¿Existen conexiones entre Porto Alegre y Santos? Sí.
         */

        System.out.println("Origen: ");
        Comparable origen = System.console().readLine();
        System.out.println("Destino: ");
        Comparable destino = System.console().readLine();

        boolean conexionEs = gd.existeConexion(origen, destino);
        System.out.println(conexionEs);
        System.out.println();

        Collection<TVertice> bpfMontevideo = gd.bpf("Montevideo");
        System.out.println("La búsqueda en profundidad desde Montevideo es: ");
        for (TVertice vertice : bpfMontevideo) {
            System.out.print(vertice.getEtiqueta() + " ");
        }

        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo().getEtiqueta());
    }
}

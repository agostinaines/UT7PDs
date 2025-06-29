package UT7PD7;

import Clases.ManejadorArchivosGenerico;
import Clases.TGrafoDirigido;
import Clases.UtilGrafos;
import Interfaces.IVertice;
import java.util.Map;
import java.util.Queue;

public class PD7 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD7/PD7Vertices.txt","./src/java/UT7PD7/UT7Aristas.txt",
                false, TGrafoDirigido.class);

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        Map<Comparable, Integer> indegrees = gd.indegree();
        for (Map.Entry<Comparable, Integer> item : indegrees.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue());
        }

        System.out.println();

        Queue<IVertice> sort = gd.sortTopologico();
        for (IVertice vertice : sort) {
            System.out.println(vertice.getEtiqueta());
        }

        //ManejadorArchivosGenerico mag = new ManejadorArchivosGenerico();
        //mag.escribirArchivo("./src/java/UT7PD7/Salida.txt", );
    }
}

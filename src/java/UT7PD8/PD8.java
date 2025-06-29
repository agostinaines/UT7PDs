package UT7PD8;

import Clases.TGrafoDirigido;
import Clases.TVertice;
import Clases.UtilGrafos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PD8 {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/java/UT7PD8/PD8Vertices","./src/java/UT7PD8/PD8Aristas",
                false, TGrafoDirigido.class);

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        System.out.println();

        Map<Comparable, Integer> bpfNums = gd.bpfNumSet();
        for (Map.Entry<Comparable, Integer> item : bpfNums.entrySet()) {
            System.out.println(item.getKey() + "-" + item.getValue());
        }

        Collection<TVertice> vertices = gd.bpf();
        for (TVertice vertice : vertices) {
            System.out.println(vertice.getEtiqueta());
        }

        gd.imprimirArcos();
    }
}

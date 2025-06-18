package Clases;

import Interfaces.IGrafoDirigido;
import Interfaces.IVertice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (IVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            IVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return vertices.get(unaEtiqueta);
    }

    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            vertices.put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        if (vertices.isEmpty()) {
            return null;
        }

        Double[][] matrizFloyd = floyd();
        Object[] etiquetas = getEtiquetasOrdenado();
        Comparable centro = null;
        double excentricidadMinima = Double.MAX_VALUE;

        for (int i = 0; i < etiquetas.length; i++) {
            double excentricidad = 0;
            boolean esAlcanzable = true;

            for (int j = 0; j < etiquetas.length; j++) {
                if (i != j) {
                    if (matrizFloyd[i][j] == Double.MAX_VALUE) {
                        esAlcanzable = false;
                        break;
                    }
                    excentricidad = Math.max(excentricidad, matrizFloyd[i][j]);
                }
            }

            if (esAlcanzable && excentricidad < excentricidadMinima) {
                excentricidadMinima = excentricidad;
                centro = (Comparable) etiquetas[i];
            }
        }

        return centro;
    }

    @Override
    public Double[][] floyd() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[][] matrizFloyd = new Double[matrizCostos.length][matrizCostos[0].length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos[0].length; j++) {
                matrizFloyd[i][j] = matrizCostos[i][j];
            }
        }
        for (int k = 0; k < matrizFloyd.length; k++) {
            for (int i = 0; i < matrizFloyd[0].length; i++) {
                for (int j = 0; j < matrizFloyd[0].length; j++) {
                    if (matrizFloyd[i][k] + matrizFloyd[k][j] < matrizCostos[i][j]) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                    }
                }
            }
        }
        return matrizFloyd;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        if (!existeVertice(etiquetaVertice)) {
            return null;
        }

        Double[][] matrizFloyd = floyd();

        int index = 0;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) == vertices.get(etiquetaVertice)) {
                index = i;
                break;
            }
        }

        double excentricidad = 0;

        for (int i = 0; i < matrizFloyd.length; i++) {
            double distancia = matrizFloyd[index][i];
            if (distancia != Double.POSITIVE_INFINITY || distancia > excentricidad) {
                excentricidad = distancia;
            }
        }

        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(vertices);
        int n = matrizCostos.length;

        // Paso 1: construir la matriz de adyacencia
        boolean[][] matrizAdyacencia = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrizCostos[i][j] != null && !matrizCostos[i][j].equals(Double.MAX_VALUE)) {
                    matrizAdyacencia[i][j] = true;
                }
            }
        }

        // Paso 2: inicializar matrizWarshall con la matriz de adyacencia
        boolean[][] matrizWarshall = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizWarshall[i][j] = matrizAdyacencia[i][j];
            }
        }

        // Paso 3: aplicar el algoritmo de Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!matrizWarshall[i][j]) {
                        matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                    }
                }
            }
        }

        return matrizWarshall;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

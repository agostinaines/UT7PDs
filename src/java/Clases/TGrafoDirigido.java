package Clases;

import Interfaces.IAdyacencia;
import Interfaces.IGrafoDirigido;
import Interfaces.IVertice;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices;

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
    public Map<Comparable, Double> centroDelGrafo() {
        if (vertices.isEmpty()) {
            return null;
        }

        Comparable[] excentricidades = new Comparable[vertices.size()];
        int i = 0;

        for (Comparable key : vertices.keySet()) {
            excentricidades[i] = obtenerExcentricidad(key);
            i++;
        }

        java.util.Arrays.sort(excentricidades);

        Map<Comparable, Double> centroDelGrafo = new HashMap<>();
        return centroDelGrafo;
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
    public double obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] matrizFloyd = floyd();

        int index = 0;
        int i = 0;

        for (Comparable key : vertices.keySet()) {
            if (key ==  etiquetaVertice) {
                index = i;
                break;
            }

            i++;
        }

        double excentricidad = 0;

        for (int j = 0; j < matrizFloyd.length; j++) {
            double distancia = matrizFloyd[index][j];
            if (distancia > excentricidad && distancia != Double.MAX_VALUE) {
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

    // PD3 Ejercicio 2
    public boolean existeConexion(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        boolean[][] warshall = warshall();
        int origen = 0;
        int destino = 0;

        int i = 0;

        for (Comparable key : vertices.keySet()) {
            if (key.equals(etiquetaOrigen)) {
               origen = i;
            }

            if (key.equals(etiquetaDestino)) {
                destino = i;
            }

            i++;
        }

        if (warshall[origen][destino]) {
            return true;
        }

        return false;
    }

    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        ArrayList<TVertice> resultado = new ArrayList<>();
        for (TVertice vertice : getVertices().values()) {
            if (vertice.getVisitado()) {
                TVertice tVertice = (TVertice) vertice;
                resultado.addAll(bpf(tVertice.getEtiqueta()));
            }
        }

        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        IVertice vertice = buscarVertice(etiquetaOrigen);

        TVertice tVertice = (TVertice) vertice;
        desvisitarVertices();
        return bpf(tVertice);
    }

    public Collection<TVertice> bpf(TVertice vertice) {

        ArrayList<TVertice> resultado = new ArrayList<>();
        if (vertice != null && !vertice.getVisitado()) {
            vertice.setVisitado(true);
            resultado.add((TVertice) vertice);
            vertice.getAdyacentes().forEach((adyacente) -> {
                IAdyacencia ady = (IAdyacencia) adyacente;
                TVertice destino = ady.getDestino();
                if (!destino.getVisitado()) {
                    resultado.addAll(bpf((TVertice) destino));
                }
            });
        }
        return resultado;
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        IVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen == null) {
            return new TCaminos();
        }
        TCamino camino = new TCamino((TVertice) verticeOrigen);
        TCaminos caminos = new TCaminos();
        return verticeOrigen.todosLosCaminos(etiquetaDestino, camino, caminos);
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

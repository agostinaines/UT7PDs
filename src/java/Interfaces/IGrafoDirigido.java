package Interfaces;

import Clases.TArista;
import Clases.TCaminos;
import Clases.TVertice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface IGrafoDirigido {
    boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino);

    boolean eliminarVertice(Comparable nombreVertice);

    boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    boolean existeVertice(Comparable unaEtiqueta);

	Double [][] floyd();

    boolean insertarArista(TArista arista);

    boolean insertarVertice(TVertice vertice);

    double obtenerExcentricidad(Comparable etiquetaVertice);

    TVertice centroDelGrafo();

    boolean[][] warshall();
    
    Map<Comparable, TVertice> getVertices();

    Collection<TVertice> bpf();

    void desvisitarVertices();

    Collection<TVertice> bpf(Comparable etiquetaOrigen);

    Collection<TVertice> bpf(TVertice vertice);

    TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    boolean tieneCiclo();

    ArrayList<IVertice> ordenParcial();
}

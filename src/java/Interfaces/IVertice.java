package Interfaces;

import Clases.TAdyacencia;
import Clases.TVertice;

import java.util.LinkedList;

public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<TAdyacencia> getAdyacentes();

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    TVertice primerAdyacente();

    TVertice siguienteAdyacente(TVertice w);

    Comparable getEtiqueta();
}

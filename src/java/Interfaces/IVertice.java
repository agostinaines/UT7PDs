package Interfaces;

import Clases.TAdyacencia;
import Clases.TCamino;
import Clases.TCaminos;
import Clases.TVertice;

import java.util.LinkedList;
import java.util.List;

public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<TAdyacencia> getAdyacentes();

    boolean getVisitado();

    void setVisitado(boolean valor);

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    TVertice primerAdyacente();

    TVertice siguienteAdyacente(TVertice w);

    Comparable getEtiqueta();

    TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);

    boolean tieneCiclo(TCamino unCamino);

    TCaminos todosLosCaminosConCiclo(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);

    LinkedList<IVertice> ordenParcial(List<IVertice> vertices);
}

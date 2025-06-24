package Interfaces;

import Clases.TAdyacencia;
import Clases.TCamino;

public interface ICamino {

    boolean agregarAdyacencia(TAdyacencia adyacenciaActual);

    TCamino copiar();

    boolean eliminarAdyacencia(TAdyacencia adyacenciaActual);

    String imprimirEtiquetas();
}

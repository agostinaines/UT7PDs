package Clases;

import Interfaces.IAdyacencia;

public class TAdyacencia implements IAdyacencia {

    private Comparable etiqueta;
    private double costo;
    private TVertice destino;
    public Comparable arco;
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
 
    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public TVertice getDestino() {
        return destino;
    }

    public TAdyacencia(double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
        this.arco = null;
    }

    @Override
    public Comparable getArco() {
        return arco;
    }

    @Override
    public void setArco(Comparable arco) { this.arco = arco; }
}

package com.ams.sustainability.model;

public class OtherTransport {

    private int ave;
    private int largaDistancia;
    private int cercanias;
    private int autobusUrbano;
    private int autocar;
    private int metro;
    private int vueloCortaDistancia;
    private int vueloMediaDistancia;
    private int vueloLargaDistancia;

    public OtherTransport() {

    }

    public OtherTransport(int ave, int largaDistancia, int cercanias, int autobusUrbano, int autocar, int metro, int vueloCortaDistancia, int vueloMediaDistancia, int vueloLargaDistancia) {
        this.ave = ave;
        this.largaDistancia = largaDistancia;
        this.cercanias = cercanias;
        this.autobusUrbano = autobusUrbano;
        this.autocar = autocar;
        this.metro = metro;
        this.vueloCortaDistancia = vueloCortaDistancia;
        this.vueloMediaDistancia = vueloMediaDistancia;
        this.vueloLargaDistancia = vueloLargaDistancia;
    }

    public int getAve() {
        return ave;
    }

    public void setAve(int ave) {
        this.ave = ave;
    }

    public int getLargaDistancia() {
        return largaDistancia;
    }

    public void setLargaDistancia(int largaDistancia) {
        this.largaDistancia = largaDistancia;
    }

    public int getCercanias() {
        return cercanias;
    }

    public void setCercanias(int cercanias) {
        this.cercanias = cercanias;
    }

    public int getAutobusUrbano() {
        return autobusUrbano;
    }

    public void setAutobusUrbano(int autobusUrbano) {
        this.autobusUrbano = autobusUrbano;
    }

    public int getAutocar() {
        return autocar;
    }

    public void setAutocar(int autocar) {
        this.autocar = autocar;
    }

    public int getMetro() {
        return metro;
    }

    public void setMetro(int metro) {
        this.metro = metro;
    }

    public int getVueloCortaDistancia() {
        return vueloCortaDistancia;
    }

    public void setVueloCortaDistancia(int vueloCortaDistancia) {
        this.vueloCortaDistancia = vueloCortaDistancia;
    }

    public int getVueloMediaDistancia() {
        return vueloMediaDistancia;
    }

    public void setVueloMediaDistancia(int vueloMediaDistancia) {
        this.vueloMediaDistancia = vueloMediaDistancia;
    }

    public int getVueloLargaDistancia() {
        return vueloLargaDistancia;
    }

    public void setVueloLargaDistancia(int vueloLargaDistancia) {
        this.vueloLargaDistancia = vueloLargaDistancia;
    }

    public double OtherTransportCalculator() {

        return (getAve()*0.00313 + getLargaDistancia()*0.0033 + getCercanias()*0.0047 + getAutobusUrbano()*0.0081 + getAutocar()*0.0028
                + getMetro()*0.00501 + getVueloCortaDistancia()*0.1578 + getVueloMediaDistancia()*0.1624 + getVueloLargaDistancia()*0.1628)/1000;

    }
}

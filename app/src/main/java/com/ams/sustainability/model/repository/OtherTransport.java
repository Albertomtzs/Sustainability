package com.ams.sustainability.model.repository;

public class OtherTransport {

    private double ave;
    private double largaDistancia;
    private double cercanias;
    private double autobusUrbano;
    private double autocar;
    private double metro;
    private double vueloCortaDistancia;
    private double vueloMediaDistancia;
    private double vueloLargaDistancia;

    public OtherTransport() {

    }

    public OtherTransport(double ave, double largaDistancia, double cercanias, double autobusUrbano, double autocar, double metro, double vueloCortaDistancia, double vueloMediaDistancia, double vueloLargaDistancia) {
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

    public double getAve() {
        return ave;
    }

    public void setAve(double ave) {
        this.ave = ave;
    }

    public double getLargaDistancia() {
        return largaDistancia;
    }

    public void setLargaDistancia(double largaDistancia) {
        this.largaDistancia = largaDistancia;
    }

    public double getCercanias() {
        return cercanias;
    }

    public void setCercanias(double cercanias) {
        this.cercanias = cercanias;
    }

    public double getAutobusUrbano() {
        return autobusUrbano;
    }

    public void setAutobusUrbano(double autobusUrbano) {
        this.autobusUrbano = autobusUrbano;
    }

    public double getAutocar() {
        return autocar;
    }

    public void setAutocar(double autocar) {
        this.autocar = autocar;
    }

    public double getMetro() {
        return metro;
    }

    public void setMetro(double metro) {
        this.metro = metro;
    }

    public double getVueloCortaDistancia() {
        return vueloCortaDistancia;
    }

    public void setVueloCortaDistancia(int vueloCortaDistancia) {
        this.vueloCortaDistancia = vueloCortaDistancia;
    }

    public double getVueloMediaDistancia() {
        return vueloMediaDistancia;
    }

    public void setVueloMediaDistancia(int vueloMediaDistancia) {
        this.vueloMediaDistancia = vueloMediaDistancia;
    }

    public double getVueloLargaDistancia() {
        return vueloLargaDistancia;
    }

    public void setVueloLargaDistancia(int vueloLargaDistancia) {
        this.vueloLargaDistancia = vueloLargaDistancia;
    }

    public double OtherTransportCalculator() {

        return Math.round((getAve() * 0.00313 + getLargaDistancia() * 0.0033 + getCercanias() * 0.0047 + getAutobusUrbano() * 0.0081 + getAutocar() * 0.0028
                + getMetro() * 0.00501 + getVueloCortaDistancia() * 0.1578 + getVueloMediaDistancia() * 0.1624 + getVueloLargaDistancia() * 0.1628) / 1000 * 10.0) / 10.0;

    }
}

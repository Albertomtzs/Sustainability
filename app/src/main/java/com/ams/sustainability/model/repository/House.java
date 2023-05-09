package com.ams.sustainability.model;

public class House {

    private String renovables;
    private int numPersonas;
    private double electricidad;
    private double gasNatural;
    private double gasoil;
    private double butano;
    private double propano;
    private double carbon;
    private double pellets;

    public House() {
    }

    public House(String renovables, int numPersonas, double electricidad, double gasNatural, double gasoil, double butano, double propano, double carbon, double pellets) {
        this.renovables = renovables;
        this.numPersonas = numPersonas;
        this.electricidad = electricidad;
        this.gasNatural = gasNatural;
        this.gasoil = gasoil;
        this.butano = butano;
        this.propano = propano;
        this.carbon = carbon;
        this.pellets = pellets;
    }

    public String getRenovables() {
        return renovables;
    }

    public void setRenovables(String renovables) {
        this.renovables = renovables;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public double getElectricidad() {
        return electricidad;
    }

    public void setElectricidad(double electricidad) {
        this.electricidad = electricidad;
    }

    public double getGasNatural() {
        return gasNatural;
    }

    public void setGasNatural(double gasNatural) {
        this.gasNatural = gasNatural;
    }

    public double getGasoil() {
        return gasoil;
    }

    public void setGasoil(double gasoil) {
        this.gasoil = gasoil;
    }

    public double getButano() {
        return butano;
    }

    public void setButano(double butano) {
        this.butano = butano;
    }

    public double getPropano() {
        return propano;
    }

    public void setPropano(double propano) {
        this.propano = propano;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public double getPellets() {
        return pellets;
    }

    public void setPellets(double pellets) {
        this.pellets = pellets;
    }

    public double HouseCalculator() {

        if (getRenovables().equals("Sí")) {

            return Math.round(((getGasNatural() * 0.203 + getGasoil() * 2.868 + getButano() * 2.964 + getPropano() * 1.498 + getCarbon() * 2.444
                    + getPellets() * 0.073)/getNumPersonas())/1000 * 10.0)/10.0;
        } else {
            return Math.round(((getElectricidad() * 0.2460 + getGasNatural() * 0.203 + getGasoil() * 2.868 + getButano() * 2.964
                    + getPropano() * 1.498 + getCarbon() * 2.444 + getPellets() * 0.073)/getNumPersonas())/1000 * 10.0)/10.0;
        }
    }
}

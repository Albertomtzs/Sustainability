package com.ams.sustainability.model;

public class Clothes {

    private int pantalonVaquero;
    private int otroPantalon;
    private int camisas;
    private int camisetas;
    private int vestidos;
    private int calcetines;
    private int chaquetas;
    private int abrigos;
    private int jerseys;
    private int zapatos;
    private int zapatillasDeporte;
    private int ropaInterior;

    public Clothes() {
    }

    public Clothes(int pantalonVaquero, int otroPantalon, int camisas, int camisetas, int vestidos, int calcetines, int chaquetas, int abrigos, int jerseys, int zapatos, int zapatillasDeporte, int ropaInterior) {
        this.pantalonVaquero = pantalonVaquero;
        this.otroPantalon = otroPantalon;
        this.camisas = camisas;
        this.camisetas = camisetas;
        this.vestidos = vestidos;
        this.calcetines = calcetines;
        this.chaquetas = chaquetas;
        this.abrigos = abrigos;
        this.jerseys = jerseys;
        this.zapatos = zapatos;
        this.zapatillasDeporte = zapatillasDeporte;
        this.ropaInterior = ropaInterior;
    }

    public int getPantalonVaquero() {
        return pantalonVaquero;
    }

    public void setPantalonVaquero(int pantalonVaquero) {
        this.pantalonVaquero = pantalonVaquero;
    }

    public int getOtroPantalon() {
        return otroPantalon;
    }

    public void setOtroPantalon(int otroPantalon) {
        this.otroPantalon = otroPantalon;
    }

    public int getCamisas() {
        return camisas;
    }

    public void setCamisas(int camisas) {
        this.camisas = camisas;
    }

    public int getCamisetas() {
        return camisetas;
    }

    public void setCamisetas(int camisetas) {
        this.camisetas = camisetas;
    }

    public int getVestidos() {
        return vestidos;
    }

    public void setVestidos(int vestidos) {
        this.vestidos = vestidos;
    }

    public int getCalcetines() {
        return calcetines;
    }

    public void setCalcetines(int calcetines) {
        this.calcetines = calcetines;
    }

    public int getChaquetas() {
        return chaquetas;
    }

    public void setChaquetas(int chaquetas) {
        this.chaquetas = chaquetas;
    }

    public int getAbrigos() {
        return abrigos;
    }

    public void setAbrigos(int abrigos) {
        this.abrigos = abrigos;
    }

    public int getJerseys() {
        return jerseys;
    }

    public void setJerseys(int jerseys) {
        this.jerseys = jerseys;
    }

    public int getZapatos() {
        return zapatos;
    }

    public void setZapatos(int zapatos) {
        this.zapatos = zapatos;
    }

    public int getZapatillasDeporte() {
        return zapatillasDeporte;
    }

    public void setZapatillasDeporte(int zapatillasDeporte) {
        this.zapatillasDeporte = zapatillasDeporte;
    }

    public int getRopaInterior() {
        return ropaInterior;
    }

    public void setRopaInterior(int ropaInterior) {
        this.ropaInterior = ropaInterior;
    }

    public double ClothesCalculator() {

        return (getPantalonVaquero()*21.60 + getOtroPantalon()*10.64 + getCamisas()*11.04 + getCamisetas()*4.75
                + getVestidos()*49.10 + getCalcetines()*0.30 + getChaquetas()*26.97 + getAbrigos()*62.25 + getJerseys()*24.30
                + getZapatos()*10 + getZapatillasDeporte()*14 + getRopaInterior()*1.37)/1000;

    }
}

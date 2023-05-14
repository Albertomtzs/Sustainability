package com.ams.sustainability.model.repository;

public class Food {

    private double hortalizas;
    private double fruta;
    private double frutos_secos;
    private double pescado;
    private double mariscos;
    private double pavo;
    private double pollo;
    private double cerdo;
    private double ternera;
    private double huevos;
    private double cereales;
    private double dulces;
    private double aceites;
    private double refrescos;
    private double cerveza;
    private double vino;
    private double licores;
    private double leche;

    public Food() {
    }

    public Food(double hortalizas, double fruta, double frutos_secos, double pescado, double mariscos, double pavo, double pollo, double cerdo, double ternera, double huevos, double cereales, double dulces, double aceites, double refrescos, double cerveza, double vino, double licores, double leche) {
        this.hortalizas = hortalizas;
        this.fruta = fruta;
        this.frutos_secos = frutos_secos;
        this.pescado = pescado;
        this.mariscos = mariscos;
        this.pavo = pavo;
        this.pollo = pollo;
        this.cerdo = cerdo;
        this.ternera = ternera;
        this.huevos = huevos;
        this.cereales = cereales;
        this.dulces = dulces;
        this.aceites = aceites;
        this.refrescos = refrescos;
        this.cerveza = cerveza;
        this.vino = vino;
        this.licores = licores;
        this.leche = leche;
    }

    public double getHortalizas() {
        return hortalizas;
    }

    public void setHortalizas(double hortalizas) {
        this.hortalizas = hortalizas;
    }

    public double getFruta() {
        return fruta;
    }

    public void setFruta(double fruta) {
        this.fruta = fruta;
    }

    public double getFrutos_secos() {
        return frutos_secos;
    }

    public void setFrutos_secos(double frutos_secos) {
        this.frutos_secos = frutos_secos;
    }

    public double getPescado() {
        return pescado;
    }

    public void setPescado(double pescado) {
        this.pescado = pescado;
    }

    public double getMariscos() {
        return mariscos;
    }

    public void setMariscos(double mariscos) {
        this.mariscos = mariscos;
    }

    public double getPavo() {
        return pavo;
    }

    public void setPavo(double pavo) {
        this.pavo = pavo;
    }

    public double getPollo() {
        return pollo;
    }

    public void setPollo(double pollo) {
        this.pollo = pollo;
    }

    public double getCerdo() {
        return cerdo;
    }

    public void setCerdo(double cerdo) {
        this.cerdo = cerdo;
    }

    public double getTernera() {
        return ternera;
    }

    public void setTernera(double ternera) {
        this.ternera = ternera;
    }

    public double getHuevos() {
        return huevos;
    }

    public void setHuevos(double huevos) {
        this.huevos = huevos;
    }

    public double getCereales() {
        return cereales;
    }

    public void setCereales(double cereales) {
        this.cereales = cereales;
    }

    public double getDulces() {
        return dulces;
    }

    public void setDulces(double dulces) {
        this.dulces = dulces;
    }

    public double getAceites() {
        return aceites;
    }

    public void setAceites(double aceites) {
        this.aceites = aceites;
    }

    public double getRefrescos() {
        return refrescos;
    }

    public void setRefrescos(double refrescos) {
        this.refrescos = refrescos;
    }

    public double getCerveza() {
        return cerveza;
    }

    public void setCerveza(double cerveza) {
        this.cerveza = cerveza;
    }

    public double getVino() {
        return vino;
    }

    public void setVino(double vino) {
        this.vino = vino;
    }

    public double getLicores() {
        return licores;
    }

    public void setLicores(double licores) {
        this.licores = licores;
    }

    public double getLeche() {
        return leche;
    }

    public void setLeche(double leche) {
        this.leche = leche;
    }

    public double FoodCalculator() {

        return Math.round((getHortalizas() * 32.55 + getFruta() * 47.48 + getFrutos_secos() * 65.31 + getPescado() * 323.86
                + getMariscos() * 544.75 + getPavo() * 314.08 + getPollo() * 203.32 + getCerdo() * 297.96 + getTernera() * 1409.72
                + getHuevos() * 78 + getCereales() * 137.28 + getDulces() * 129.74 + getAceites() * 47.94 + getRefrescos() * 17.06
                + getCerveza() * 73.32 + getVino() * 83.20 + getLicores() * 154.44 + getLeche() * 69.68) / 1000 * 10d) / 10d;
    }

}

package com.ams.sustainability.model.repository;

public class Transport {

    private String typeCar;
    private String combustibleCar;
    private double kmCar;
    private double consumoCar;
    private double kmMoto;
    private double consumoMoto;
    private String cilindradaMoto;
    private String combustibleMoto;

    public Transport() {

    }

    public Transport(String typeCar, String combustibleCar, double kmCar, double consumoCar, double kmMoto, double consumoMoto, String cilindradaMoto, String combustibleMoto) {
        this.typeCar = typeCar;
        this.combustibleCar = combustibleCar;
        this.kmCar = kmCar;
        this.consumoCar = consumoCar;
        this.kmMoto = kmMoto;
        this.consumoMoto = consumoMoto;
        this.cilindradaMoto = cilindradaMoto;
        this.combustibleMoto = combustibleMoto;
    }

    public Transport(String typeCar, String combustibleCar, double kmCar, double consumoCar) {
        this.typeCar = typeCar;
        this.combustibleCar = combustibleCar;
        this.kmCar = kmCar;
        this.consumoCar = consumoCar;
    }

    public Transport(double kmMoto, double consumoMoto, String cilindradaMoto, String combustibleMoto) {
        this.kmMoto = kmMoto;
        this.consumoMoto = consumoMoto;
        this.cilindradaMoto = cilindradaMoto;
        this.combustibleMoto = combustibleMoto;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public String getCombustibleCar() {
        return combustibleCar;
    }

    public void setCombustibleCar(String combustibleCar) {
        this.combustibleCar = combustibleCar;
    }

    public double getKmCar() {
        return kmCar;
    }

    public void setKmCar(double kmCar) {
        this.kmCar = kmCar;
    }

    public double getConsumoCar() {
        return consumoCar;
    }

    public void setConsumoCar(double consumoCar) {
        this.consumoCar = consumoCar;
    }

    public double getKmMoto() {
        return kmMoto;
    }

    public void setKmMoto(double kmMoto) {
        this.kmMoto = kmMoto;
    }

    public double getConsumoMoto() {
        return consumoMoto;
    }

    public void setConsumoMoto(double consumoMoto) {
        this.consumoMoto = consumoMoto;
    }

    public String getCilindradaMoto() {
        return cilindradaMoto;
    }

    public void setCilindradaMoto(String cilindradaMoto) {
        this.cilindradaMoto = cilindradaMoto;
    }

    public String getCombustibleMoto() {
        return combustibleMoto;
    }

    public void setCombustibleMoto(String combustibleMoto) {
        this.combustibleMoto = combustibleMoto;
    }

    public double TransportCalculatorCar() {

        //double emissionTransport = 0;
        double emissionTransportCar = 0;

        switch (getTypeCar()) {
            case "Utilitario":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (560) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (633) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (559.75) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (641) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
            case "Familiar":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (714) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (730) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (714.33) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (867) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
            case "Berlina":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (843) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (845) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (842.50) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (1420) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
            case "Monovolumen pequeño":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (659) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (681) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (658.83) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (1136) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
            case "Monovolumen Familiar":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (792) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (852) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (791.75) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (1647) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;

                }
                break;
            case "SUV pequeño":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (656) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (673) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (656.52) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (1136) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
            case "SUV grande":
                switch (getCombustibleCar()) {
                    case "Gasolina":
                        emissionTransportCar = (884) + ((getConsumoCar() * 0.02156) * getKmCar());
                        break;
                    case "Gasoil":
                        emissionTransportCar = (900) + ((getConsumoCar() * 0.02494) * getKmCar());
                        break;
                    case "Gas natural":
                        emissionTransportCar = (884.25) + ((getConsumoCar() * 0.02743) * getKmCar());
                        break;
                    case "Eléctrico":
                        emissionTransportCar = (1647) + ((getConsumoCar() * 0.00246) * getKmCar());
                        break;
                }
                break;
        }


        return Math.round(emissionTransportCar / 1000 * 10.0) / 10.0;
    }

    public double TransportCalculatorMoto() {

        double emissionTransportMoto = 0;

        switch (getCilindradaMoto()) {
            case "Inferior a 125cc":
                switch (getCombustibleMoto()) {
                    case "Gasolina":
                        emissionTransportMoto = (350) + ((getConsumoMoto() * 0.02156) * getKmMoto());
                        break;
                    case "Eléctrico":
                        emissionTransportMoto = (350) + ((getConsumoMoto() * 0.00246) * getKmMoto());
                        break;

                }
                break;
            case "125–500cc":
                switch (getCombustibleMoto()) {
                    case "Gasolina":
                        emissionTransportMoto = (360) + ((getConsumoMoto() * 0.02156) * getKmMoto());
                        break;
                    case "Eléctrico":
                        emissionTransportMoto = (360) + ((getConsumoMoto() * 0.00246) * getKmMoto());
                        break;
                }
                break;
            case "> 500cc":
                switch (getCombustibleMoto()) {
                    case "Gasolina":
                        emissionTransportMoto = (370) + ((getConsumoMoto() * 0.02156) * getKmMoto());
                        break;
                    case "Eléctrico":
                        emissionTransportMoto = (370) + ((getConsumoMoto() * 0.00246) * getKmMoto());
                        break;
                }
                break;
            case "Eléctrica (5KW)":
                switch (getCombustibleMoto()) {
                    case "Gasolina":
                        emissionTransportMoto = (489) + ((getConsumoMoto() * 0.02156) * getKmMoto());
                        break;
                    case "Eléctrico":
                        emissionTransportMoto = (489) + ((getConsumoMoto() * 0.00246) * getKmMoto());
                        break;
                }
                break;
        }


        return Math.round(emissionTransportMoto / 1000 * 10.0) / 10.0;
    }
}




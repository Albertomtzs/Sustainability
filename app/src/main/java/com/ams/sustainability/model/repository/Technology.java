package com.ams.sustainability.model;

public class Technology {

    private int pc;
    private double yearPC;
    private int laptop;
    private double yearLaptop;
    private int tablet;
    private double yearTablet;
    private int smartphone;
    private double yearSmartphone;
    private int router;
    private double yearRouter;

    public Technology() {

    }

    public Technology(int pc, double yearPC, int laptop, double yearLaptop, int tablet, double yearTablet, int smartphone, double yearSmartphone, int router, double yearRouter) {
        this.pc = pc;
        this.yearPC = yearPC;
        this.laptop = laptop;
        this.yearLaptop = yearLaptop;
        this.tablet = tablet;
        this.yearTablet = yearTablet;
        this.smartphone = smartphone;
        this.yearSmartphone = yearSmartphone;
        this.router = router;
        this.yearRouter = yearRouter;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public double getYearPC() {
        return yearPC;
    }

    public void setYearPC(double yearPC) {
        this.yearPC = yearPC;
    }

    public int getLaptop() {
        return laptop;
    }

    public void setLaptop(int laptop) {
        this.laptop = laptop;
    }

    public double getYearLaptop() {
        return yearLaptop;
    }

    public void setYearLaptop(double yearLaptop) {
        this.yearLaptop = yearLaptop;
    }

    public int getTablet() {
        return tablet;
    }

    public void setTablet(int tablet) {
        this.tablet = tablet;
    }

    public double getYearTablet() {
        return yearTablet;
    }

    public void setYearTablet(double yearTablet) {
        this.yearTablet = yearTablet;
    }

    public int getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(int smartphone) {
        this.smartphone = smartphone;
    }

    public double getYearSmartphone() {
        return yearSmartphone;
    }

    public void setYearSmartphone(double yearSmartphone) {
        this.yearSmartphone = yearSmartphone;
    }

    public int getRouter() {
        return router;
    }

    public void setRouter(int router) {
        this.router = router;
    }

    public double getYearRouter() {
        return yearRouter;
    }

    public void setYearRouter(double yearRouter) {
        this.yearRouter = yearRouter;
    }


    public double TechnologyCalculator() {

        double resultado = 0;

        double pcYear = getYearPC();
        double laptopYear = getYearLaptop();
        double tabletYear = getYearTablet();
        double smartphoneYear = getYearSmartphone();
        double routerYear = getYearRouter();

        if (pcYear > 0) {
            resultado += (218.0 / pcYear) * getPc();
        }

        if (laptopYear > 0) {
            resultado += (281.0 / laptopYear) * getLaptop();
        }

        if (tabletYear > 0) {
            resultado += (80.0 / tabletYear) * getTablet();
        }

        if (smartphoneYear > 0) {
            resultado += (40.0 / smartphoneYear) * getSmartphone();
        }

        if (routerYear > 0) {
            resultado += (9.0 / routerYear) * getRouter();
        }

        return Math.round(resultado / 1000.0 * 10.0) / 10.0;

    }
}

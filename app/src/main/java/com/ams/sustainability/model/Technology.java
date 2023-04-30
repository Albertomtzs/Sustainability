package com.ams.sustainability.model;

public class Technology {

    private int pc;
    private int yearPC;
    private int laptop;
    private int yearLaptop;
    private int tablet;
    private int yearTablet;
    private int smartphone;
    private int yearSmartphone;
    private int router;
    private int yearRouter;

    public Technology() {

    }

    public Technology(int pc, int yearPC, int laptop, int yearLaptop, int tablet, int yearTablet, int smartphone, int yearSmartphone, int router, int yearRouter) {
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

    public int getYearPC() {
        return yearPC;
    }

    public void setYearPC(int yearPC) {
        this.yearPC = yearPC;
    }

    public int getLaptop() {
        return laptop;
    }

    public void setLaptop(int laptop) {
        this.laptop = laptop;
    }

    public int getYearLaptop() {
        return yearLaptop;
    }

    public void setYearLaptop(int yearLaptop) {
        this.yearLaptop = yearLaptop;
    }

    public int getTablet() {
        return tablet;
    }

    public void setTablet(int tablet) {
        this.tablet = tablet;
    }

    public int getYearTablet() {
        return yearTablet;
    }

    public void setYearTablet(int yearTablet) {
        this.yearTablet = yearTablet;
    }

    public int getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(int smartphone) {
        this.smartphone = smartphone;
    }

    public int getYearSmartphone() {
        return yearSmartphone;
    }

    public void setYearSmartphone(int yearSmartphone) {
        this.yearSmartphone = yearSmartphone;
    }

    public int getRouter() {
        return router;
    }

    public void setRouter(int router) {
        this.router = router;
    }

    public int getYearRouter() {
        return yearRouter;
    }

    public void setYearRouter(int yearRouter) {
        this.yearRouter = yearRouter;
    }

    public double TechnologyCalculator() {

        return ((218/getYearPC()) * getPc() + (281/getYearLaptop()) * getLaptop() + (80/getYearTablet()) * getTablet()
                + (40/getYearSmartphone())*getSmartphone() + (9/getYearRouter())*getRouter())/1000;
    }
}

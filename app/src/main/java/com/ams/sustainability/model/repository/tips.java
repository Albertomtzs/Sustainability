package com.ams.sustainability.model.repository;

import java.util.ArrayList;

public class tips {

    public ArrayList<String> getTipsTransport() {

        ArrayList<String> myData = new ArrayList<>();


        myData.add("Para pequeños desplazamientos dentro de la ciudad considere la posibilidad de ir a pie, en bicicleta o en transporte público.");
        myData.add("Ser inteligente en cuanto a cuándo y cómo conducir. Una conducción eficiente reduce el consumo de combustible y las emisiones de CO2.");
        myData.add("Tratar de usar el tren en tus próximas vacaciones o en trayectos de media distancia.");
        myData.add("Apostar por la tecnología híbrida o eléctrica.");

        return myData;
    }

    public ArrayList<String> getTipsHome() {

        ArrayList<String> myData = new ArrayList<>();

        myData.add("20 ºC en invierno y 25 ºC en verano es más que suficiente para conseguir una temperatura agradable en casa. Un grado más o uno menos hace que el consumo de energía aumente un 8 %.");
        myData.add("El 80 % del consumo energético de la lavadora se destina a calentar el agua. Lavar en frío es mucho más eficiente.");
        myData.add("Limitar el uso de la secadora y planchar todas las prendas de una vez.");
        myData.add("En el congelador, cuantas menos bolsas de plástico mejor. Si necesitas guardar alimentos, utiliza tuppers de cristal para no gastar electricidad de más.");
        myData.add("Desenchufar los aparatos electrónicos y no dejar el teléfono enchufado cuando la batería ya esté cargada.");
        myData.add("Optar por productos eficientes desde el punto de vista energético que lleven la etiqueta «A».");
        myData.add("para lámparas de uso frecuente, opta por LEDS.");
        myData.add("Reducir el volumen de basura generada, ya sea a través del reciclado o de la compra sustentable, permite reducir emisiones.");

        return myData;
    }

    public ArrayList<String> getTipsClothes() {

        ArrayList<String> myData = new ArrayList<>();

        myData.add("Comprar ropa fabricada de forma responsable, por ejemplo fabricada con material reciclado o con etiqueta ecológica.");
        myData.add("Usar la ropa 9 meses más podría reducir la huella de carbono entre un 20 y 30%.");
        myData.add("Tratar de intercambiarla, tomarla en préstamo, alquilarla o comprarla de segunda mano.");
        myData.add("Comprar menos moda, pero de más calidad porque dura más tiempo.");
        myData.add("Compra en tiendas físicas, pero acude en transporte público.");

        return myData;
    }

    public ArrayList<String> getTipsFood() {

        ArrayList<String> myData = new ArrayList<>();

        myData.add("Consumir productos locales y de temporada.");
        myData.add("Limitar el consumo de carne, especialmente de carne de vacuno.");
        myData.add("Consumir pescado obtenido mediante pesca sostenible.");
        myData.add("Utilizar bolsas de compra reutilizables y evitar los productos con un embalaje de plástico excesivo.");
        myData.add("Asegurarse de comprar solo lo que se necesita, para evitar el desperdicio de alimentos.");

        return myData;
    }

    public ArrayList<String> getTipsTechnology() {

        ArrayList<String> myData = new ArrayList<>();

        myData.add("No te compres el último modelo y reduce las recargas.");
        myData.add("Elimina los usos superfluos.");
        myData.add("Alargar la vida de tu smartphone y pc.");

        return myData;
    }

    public ArrayList<String> getTipsAll() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHCFT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHCTTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHFTTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCFTTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHFC() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHCT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHCTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHFT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHFTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCFT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCFTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsTFTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHF() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHC() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }


        return myData;
    }

    public ArrayList<String> getTipsHT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsHTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsHome()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCF() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsCTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsFT() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsFTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsFood()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<String> getTipsTTr() {

        ArrayList<String> myData = new ArrayList<>();

        for (String tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (String tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

}

package com.ams.sustainability.model.repository;

import com.ams.sustainability.R;

import java.util.ArrayList;

public class tips {

    public ArrayList<TipsItem> getTipsTransport() {
        ArrayList<TipsItem> myData = new ArrayList<>();

        // Lista de recursos de íconos asociados a cada tip
        int[] iconResources = {
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24,
                R.drawable.ic_transportation_24
        };

        String[] tips = {
                "Utiliza el transporte público, la bicicleta, o ve caminando en los trayectos que te lo permitan. El metro tiene menos emisiones por km que el autobús.",
                "Si tienes que coger el coche, intenta compartir tu viaje con otras personas y no usarlo siempre de forma individual.",
                "Mantén tu vehículo en buen estado y revisa la presión de los neumáticos. Así optimizarás el consumo de combustible.",
                "Al cambiar de vehículo, escoge el más eficiente. Considera las nuevas alternativas de motos o coches híbridos y eléctricos.",
                "Una conducción eficiente reduce el consumo de combustible y las emisiones de CO2.",
                "Si puedes escoger entre avión o tren para viajes largos y en similares condiciones, ten en cuenta que viajar en tren supone menos emisiones.",
                "Evita viajes de larga distancia en tus vacaciones. Los viajes en avión a zonas exóticas llevan consigo emisiones considerables."
        };

        // Asociar cada tip con su ícono correspondiente y agregarlo a la lista
        for (int i = 0; i < tips.length; i++) {
            TipsItem item = new TipsItem(tips[i], iconResources[i]);
            myData.add(item);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHome() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        int[] iconResources = {
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp,
                R.drawable.ic_home_24dp
        };

        String[] tips = {
                "Optimiza el aislamiento energético de tu casa para ahorrar en climatización. Se recomienda fijar el termostato en 21ºC en invierno y 24ºC en verano.",
                "Desconecta los aparatos que no estés utilizando. No los dejes en Stand-by.",
                "Elimina el exceso de bolsas de plástico, embalajes y hielo en el congelador, para reducir la potencia que el frigorífico necesita para enfriar.",
                "A la hora de cocinar, tapa las ollas y usa recipientes que te permitan ahorrar energía.",
                "Lava la ropa en frío siempre que sea posible.",
                "Contrata suministros de energía que garanticen la procedencia de fuentes renovables.",
                "Si tienes que cambiar de electrodomésticos, elige aquellos más eficientes energéticamente (A++).",
                "Cambia las viejas bombillas incandescentes por otras de bajo consumo, LED, etc."
        };

        // Asociar cada tip con su ícono correspondiente y agregarlo a la lista
        for (int i = 0; i < tips.length; i++) {
            TipsItem item = new TipsItem(tips[i], iconResources[i]);
            myData.add(item);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsClothes() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        int[] iconResources = {
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24,
                R.drawable.ic_clothes_24
        };

        String[] tips = {

                "Busca prendas que procedan de una producción responsable, a ser posible con materias primas de bajo impacto, naturales y/o recicladas.",
                "Usar la ropa 9 meses más podría reducir la huella de carbono entre un 20 y 30%.",
                "Da preferencia a las empresas que valoren la transparencia y controlen la trazabilidad de sus productos, a fin de reducir su huella de carbono.",
                "Considera la opción de alquilar ropa en aquellas prendas que sólo vayas a usar en pocas ocasiones. Reducirás tus gastos y tus emisiones.",
                "Considera si realmente es necesario cambiar de ropa. Las menores emisiones siempre vienen del ahorro.",
                "Compra en tiendas físicas, pero acude en transporte público.",
                "No tires tu ropa cuando ya no te sirva. Puedes reciclarla a través de organizaciones que pueden darle un segundo uso."

        };

        // Asociar cada tip con su ícono correspondiente y agregarlo a la lista
        for (int i = 0; i < tips.length; i++) {
            TipsItem item = new TipsItem(tips[i], iconResources[i]);
            myData.add(item);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsFood() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        int[] iconResources = {
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24,
                R.drawable.ic_food_24
        };

        String[] tips = {
                "Escoge productos de proximidad y de temporada frente a otros que vengan de lejos, pues su transporte incrementa las emisiones.",
                "Evita comprar productos excesivamente empaquetados y opta por otros que se vendan a granel.",
                "Utiliza botellas de cristal o termos en lugar de botellines de plástico para el agua.",
                "Utilizar bolsas de compra reutilizables y evitar los productos con un embalaje de plástico excesivo.",
                "Asegurarse de comprar solo lo que se necesita, para evitar el desperdicio de alimentos.",
                "Los líquidos envasados en tetra-brick tienen emisiones más bajas que cuando están envasados en plástico o cristal.",
                "Reduce el consumo de dulces y bollería industrial. Sustitúyelos por fruta o postres caseros.",
                "Evita los refrescos carbonatados y en su lugar toma agua o zumos naturales.",
                "Cuando sea posible, consume pescado o marisco de cercanía. La pesca de arrastre y las piscifactorías intensivas suponen mayor huella de carbono",
                "Reduce el consumo de productos lácteos. Intenta substituirlos por bebidas vegetales con propiedades similares y menor huella de carbono.",
                "Reduce el consumo de carnes, principalmente carnes rojas como ternera o cordero. Con una mayor ingesta de verdura y fruta fresca estarás reduciendo tu huella de carbono y mejorando tu salud."
        };

        // Asociar cada tip con su ícono correspondiente y agregarlo a la lista
        for (int i = 0; i < tips.length; i++) {
            TipsItem item = new TipsItem(tips[i], iconResources[i]);
            myData.add(item);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsTechnology() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        int[] iconResources = {
                R.drawable.ic_techonology_24,
                R.drawable.ic_techonology_24,
                R.drawable.ic_techonology_24,
                R.drawable.ic_techonology_24
        };

        String[] tips = {
                "Haz un uso responsable de la tecnología: ten en cuenta que los servidores para el uso de internet, videojuegos y las aplicaciones de nuestros móviles, requieren gran cantidad de energía, con sus consiguientes emisiones de gases de efecto invernadero.",
                "Alarga la vida de tu smartphone o pc.",
                "Abandona la práctica de usar y tirar. Reutiliza y recicla todo lo que sea posible.",
                "Infórmate sobre las nuevas alternativas para alquilar artículos o herramientas que normalmente no usas y tiene poco sentido comprar.",

        };

        // Asociar cada tip con su ícono correspondiente y agregarlo a la lista
        for (int i = 0; i < tips.length; i++) {
            TipsItem item = new TipsItem(tips[i], iconResources[i]);
            myData.add(item);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsAll() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHCFT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHCTTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHFTTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsCFTTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHFC() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHCT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHCTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHFT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHFTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsCFT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsCFTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsTFTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHF() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHC() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }


        return myData;
    }

    public ArrayList<TipsItem> getTipsHT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsHome()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsHTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        ArrayList<TipsItem> tipsHome = getTipsHome();
        ArrayList<TipsItem> tipsTransport = getTipsTransport();

        myData.addAll(tipsHome);
        myData.addAll(tipsTransport);

        return myData;
    }

    public ArrayList<TipsItem> getTipsCF() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsCT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsCTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsClothes()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsFT() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsFTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsFood()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public ArrayList<TipsItem> getTipsTTr() {

        ArrayList<TipsItem> myData = new ArrayList<>();

        for (TipsItem tip : getTipsTechnology()) {
            myData.add(tip);
        }

        for (TipsItem tip : getTipsTransport()) {
            myData.add(tip);
        }

        return myData;
    }

    public class TipsItem {
        private String tip;
        private int iconResource;

        public TipsItem(String tip, int iconResource) {
            this.tip = tip;
            this.iconResource = iconResource;
        }

        public String getTip() {
            return tip;
        }

        public int getIconResource() {
            return iconResource;
        }
    }

}

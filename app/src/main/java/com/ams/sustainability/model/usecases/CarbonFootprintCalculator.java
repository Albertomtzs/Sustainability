package com.ams.sustainability.model.usecases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ams.sustainability.view.GetStartedCalculator;
import com.ams.sustainability.R;
import com.ams.sustainability.model.repository.Clothes;
import com.ams.sustainability.model.repository.Food;
import com.ams.sustainability.model.repository.House;
import com.ams.sustainability.model.repository.OtherTransport;
import com.ams.sustainability.model.repository.Technology;
import com.ams.sustainability.model.repository.Transport;
import com.ams.sustainability.view.ResultsView;
//import com.ams.sustainability.ui.home.FragmentHome;

public class CarbonFootprintCalculator extends AppCompatActivity {


    /*--- SECCIÓN VIVIENDA */

    private EditText etNumPersonas, etElectricidad, etGasNatural, etGasoil, etButano, etPropano, etCarbon, etPellets;
    private int numPersonas;

    private String renovables;
    private double electricidad, gasNatural, gasoil, butano, propano, carbon, pellets;

    /*--- SECCIÓN ALIMENTACIÓN */

    private EditText etHortalizas, etFruta, etFrutosSecos, etPescado, etMariscos, etPavo, etPollo, etCerdo, etTernera, etHuevos, etCereales, etDulces, etAceites, etRefrescos, etCerveza, etVino, etLicores, etLeche;
    private double hortalizas, fruta, frutos_secos, pescado, mariscos, pavo, pollo, cerdo, ternera, huevos, cereales, dulces, aceites, refrescos, cerveza, vino, licores, leche;

    /*--- SECCIÓN ROPA */

    private EditText etPantalonVaquero, etOtroPantalon, etCamisas, etCamisetas, etVestidos, etCalcetines, etChaquetas, etAbrigos, etJerseys, etZapatos, etZapatillasDeporte, etRopaInterior;

    private int pantalonVaquero, otroPantalon, camisas, camisetas, vestidos, calcetines, chaquetas, abrigos, jerseys, zapatos, zapatillasDeporte, ropaInterior;

    /*--- SECCIÓN TRANSPORTE */

    private String OwnedCar, OwnedMoto;

    private EditText etConsumoLitrosCar, etCantidadKmCar, etConsumoLitrosMoto, etCantidadKmMoto;

    private double consumoLitrosCar, cantidadKmCar, consumoLitrosMoto, cantidadKmMoto;

    /*--- SECCIÓN OTROS TRANSPORTES */

    private EditText etAVE, etTrenL, etCercanias, etBusUrbano, etAutocar, etMetro, etVuelosCortos, etVuelosMedios, etVuelosLargos;

    private double AVE, trenL, cercanias, busUrbano, autocar, metro, vuelosCortos, vuelosMedios, vuelosLargos;

    /*--- SECCIÓN OTROS TECNOLOGIA */

    private EditText etUdsPC, etYearPC, etUdsLaptop, etYearLaptop, etUdsTablet, etYearTablet, etUdsSmartphone, etYearSmartphone, etUdsRouter, etYearRouter;
    private int udsPC, udsLaptop, udsTablet, udsSmartphone, udsRouter;
    private double yearPC, yearLaptop, yearTablet, yearSmartphone, yearRouter;

    private String house, food, clothes, transport, otherTransport, technology, sumSecciones, resultadoHouseDouble, resultadoFoodDouble, resultadoClothesDouble, resultadoTransportDouble, resultadoTransportDoubleCar, resultadoTransportDoubleMoto, resultadoOtherTransportDouble, resultadoTechnologyDouble;
    private int stage, backlines;

    private double sumCarbonFootprint;

    private TextView title, resultInput;
    private String result;
    private Button back, next;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_footprint_calculator);

        LinearLayout layout = findViewById(R.id.scrollLayout);
        title = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.btnBack);
        next = (Button) findViewById(R.id.btnNext);
        resultInput = (TextView) findViewById(R.id.resultInput);

        Intent intent = getIntent();
        stage = intent.getIntExtra("NEXT_STAGE", 0);
        backlines = intent.getIntExtra("BACK_LINES", 0);

        switch (stage) {
            case 0:
                title.setText(R.string.vivienda);
                back.setVisibility(View.INVISIBLE);
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_vivienda, null));
                break;
            case 1:
                title.setText(R.string.alimentacion);
                house = getIntent().getStringExtra("RESULT_HOUSE");
                resultInput.setText(house.replace(".", ","));
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_comida, null));
                break;
            case 2:
                title.setText(R.string.ropa);
                food = getIntent().getStringExtra("RESULT_HOUSE_FOOD");
                resultInput.setText(food.replace(".", ","));
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_ropa, null));
                break;
            case 3:
                title.setText(R.string.transporte);
                clothes = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES");
                resultInput.setText(clothes.replace(".", ","));
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_transporte, null));
                break;
            case 4:
                title.setText(R.string.otros_transportes);
                transport = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT");
                resultInput.setText(transport.replace(".", ","));
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_otros_transportes, null));
                break;
            case 5:
                title.setText(R.string.tecnologia);
                otherTransport = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT_TRANSPORT_OTHER");
                resultInput.setText(otherTransport.replace(".", ","));
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_tecnologia, null));
                break;

        }
    }

    public void onBackClicked(View view) {

        Intent back;
        if (stage == 0) {
            back = new Intent(this, GetStartedCalculator.class);
        } else {
            back = new Intent(this, CarbonFootprintCalculator.class);
            stage--;
            back.putExtra("NEXT_STAGE", stage);

        }
        startActivity(back);
        finish();
    }

    public void onNextClicked(View view) {

        switch (stage) {
            case 0:

                CaptionHouse();
                break;

            case 1:

                CaptionFood();
                break;

            case 2:

                CaptionClothes();
                break;

            case 3:
                CaptionTransport();
                break;

            case 4:
                CaptionOtherTransport();
                break;

            case 5:
                CaptionTechnology();
                break;

        }

    }


    private void CaptionHouse() {

        RadioGroup radioGroupHouse = (RadioGroup) findViewById(R.id.radioGroupRenovable);
        etNumPersonas = (EditText) findViewById(R.id.etNumPersonas);

        if (radioGroupHouse.getCheckedRadioButtonId() == -1) {

            toast("Debe seleccionar si la electricidad de su vivienda es de fuentes renovables");
            return;

        } else if (etNumPersonas.getText().toString().isEmpty()) {

            toast("Debe indicar cuántas personas viven en su domicilio");
            return;

        }

        numPersonas = getTextFromEditTextInt(R.id.etNumPersonas);
        electricidad = getTextFromEditTextDouble(R.id.etElectricidad);
        gasNatural = getTextFromEditTextDouble(R.id.etGasNatural);
        gasoil = getTextFromEditTextDouble(R.id.etGasoil);
        butano = getTextFromEditTextDouble(R.id.etGasButano);
        propano = getTextFromEditTextDouble(R.id.etGasPropano);
        carbon = getTextFromEditTextDouble(R.id.etCarbon);
        pellets = getTextFromEditTextDouble(R.id.etPellets);

        House house = new House(getRadioGroupSelectedText(radioGroupHouse), numPersonas, electricidad, gasNatural, gasoil, butano, propano, carbon, pellets);

        resultInput.setText(String.valueOf(house.HouseCalculator()).replace(".", ","));
        System.out.println("*******Prueba resultado vivienda: " + house.HouseCalculator());

        Intent intentHouse = new Intent(this, CarbonFootprintCalculator.class);

        intentHouse.putExtra("RESULT_HOUSE", String.valueOf(house.HouseCalculator()));
        intentHouse.putExtra("NEXT_STAGE", 1);
        intentHouse.putExtra("BACK_LINES", backlines);
        startActivity(intentHouse);
        finish();
    }

    private void CaptionFood() {

        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");

        hortalizas = getTextFromEditTextDouble(R.id.etHortalizas);
        fruta = getTextFromEditTextDouble(R.id.etFruta);
        huevos = getTextFromEditTextDouble(R.id.etHuevos);
        frutos_secos = getTextFromEditTextDouble(R.id.etFrutosSecos);
        pescado = getTextFromEditTextDouble(R.id.etPescado);
        mariscos = getTextFromEditTextDouble(R.id.etMarisco);
        pavo = getTextFromEditTextDouble(R.id.etPavo);
        pollo = getTextFromEditTextDouble(R.id.etPollo);
        cerdo = getTextFromEditTextDouble(R.id.etCerdo);
        ternera = getTextFromEditTextDouble(R.id.etTernera);
        huevos = getTextFromEditTextDouble(R.id.etHuevos);
        cereales = getTextFromEditTextDouble(R.id.etCereales);
        dulces = getTextFromEditTextDouble(R.id.etDulces);
        aceites = getTextFromEditTextDouble(R.id.etAceite);
        refrescos = getTextFromEditTextDouble(R.id.etRefrescos);
        cerveza = getTextFromEditTextDouble(R.id.etCerveza);
        vino = getTextFromEditTextDouble(R.id.etVino);
        licores = getTextFromEditTextDouble(R.id.etLicores);
        leche = getTextFromEditTextDouble(R.id.etLeche);

        Food food = new Food(hortalizas, fruta, frutos_secos, pescado, mariscos, pavo, pollo, cerdo, ternera, huevos, cereales, dulces, aceites, refrescos, cerveza, vino, licores, leche);

        sumCarbonFootprint = Double.parseDouble(resultadoHouseDouble) + food.FoodCalculator();
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

        System.out.println("Prueba resultado: " + food.FoodCalculator());
        System.out.println("Prueba resultado suma secciones Vivienda y alimentacion: " + sumCarbonFootprint);

        Intent intentFood = new Intent(this, CarbonFootprintCalculator.class);
        intentFood.putExtra("RESULT_HOUSE", resultadoHouseDouble);
        intentFood.putExtra("RESULT_FOOD", String.valueOf(food.FoodCalculator()));
        intentFood.putExtra("RESULT_HOUSE_FOOD", String.valueOf(sumCarbonFootprint));
        intentFood.putExtra("NEXT_STAGE", 2);
        intentFood.putExtra("BACK_LINES", backlines);
        startActivity(intentFood);
        finish();
    }

    private void CaptionClothes() {

        sumSecciones = getIntent().getStringExtra("RESULT_HOUSE_FOOD");
        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");
        resultadoFoodDouble = getIntent().getStringExtra("RESULT_FOOD");

        pantalonVaquero = getTextFromEditTextInt(R.id.etPantalon);
        otroPantalon = getTextFromEditTextInt(R.id.etOtrosPantalones);
        camisas = getTextFromEditTextInt(R.id.etCamisas);
        camisetas = getTextFromEditTextInt(R.id.etCamisetas);
        vestidos = getTextFromEditTextInt(R.id.etVestidos);
        calcetines = getTextFromEditTextInt(R.id.etCalcetines);
        chaquetas = getTextFromEditTextInt(R.id.etChaquetas);
        abrigos = getTextFromEditTextInt(R.id.etAbrigos);
        jerseys = getTextFromEditTextInt(R.id.etJerseys);
        zapatos = getTextFromEditTextInt(R.id.etZapatos);
        zapatillasDeporte = getTextFromEditTextInt(R.id.etZapatillasDeportivas);
        ropaInterior = getTextFromEditTextInt(R.id.etRopaInterior);

        Clothes clothes = new Clothes(pantalonVaquero, otroPantalon, camisas, camisetas, vestidos, calcetines, chaquetas, abrigos, jerseys, zapatos, zapatillasDeporte, ropaInterior);

        sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + clothes.ClothesCalculator()) * 10d) / 10d;
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

        System.out.println("********Prueba resultado: " + clothes.ClothesCalculator());
        System.out.println("********Prueba resultado suma secciones Vivienda, alimentacion y ropa: " + sumCarbonFootprint);
        Intent intentClothes = new Intent(this, CarbonFootprintCalculator.class);
        intentClothes.putExtra("RESULT_CLOTHES", String.valueOf(clothes.ClothesCalculator()));
        intentClothes.putExtra("RESULT_HOUSE", resultadoHouseDouble);
        intentClothes.putExtra("RESULT_FOOD", resultadoFoodDouble);
        intentClothes.putExtra("RESULT_HOUSE_FOOD_CLOTHES", String.valueOf(sumCarbonFootprint));
        intentClothes.putExtra("NEXT_STAGE", 3);
        intentClothes.putExtra("BACK_LINES", backlines);
        startActivity(intentClothes);
        finish();
    }

    private void CaptionTransport() {

        sumSecciones = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES");
        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");
        resultadoFoodDouble = getIntent().getStringExtra("RESULT_FOOD");
        resultadoClothesDouble = getIntent().getStringExtra("RESULT_CLOTHES");

        RadioGroup radioGroupCar = (RadioGroup) findViewById(R.id.radioGroupPropCar);
        RadioGroup radioGroupMoto = (RadioGroup) findViewById(R.id.radioGroupPropMoto);
        RadioGroup radioGroupCombustibleCar = (RadioGroup) findViewById(R.id.radioGroupTipoCombustibleCar);
        RadioGroup radioGroupCombustibleMoto = (RadioGroup) findViewById(R.id.radioGroupTipoCombustibleMoto);
        RadioGroup radioGroupTipoCar = (RadioGroup) findViewById(R.id.radioGroupTipoCar);
        RadioGroup radioGroupCCMoto = (RadioGroup) findViewById(R.id.radioGroupCC_Moto);

        if (radioGroupCar.getCheckedRadioButtonId() == -1) {

            toast("Debe seleccionar si posee un automóvil en propiedad");
            return;

        } else if (radioGroupMoto.getCheckedRadioButtonId() == -1) {

            toast("Debe seleccionar si posee una moto en propiedad");
            return;

        }

        consumoLitrosCar = getTextFromEditTextDouble(R.id.etConsumoLitrosCar);
        cantidadKmCar = getTextFromEditTextDouble(R.id.etCantidadKmCar);
        consumoLitrosMoto = getTextFromEditTextDouble(R.id.etConsumoLitrosMoto);
        cantidadKmMoto = getTextFromEditTextDouble(R.id.etCantidadKmMoto);

        Intent intentTransport = new Intent(this, CarbonFootprintCalculator.class);

        if (getRadioGroupSelectedText(radioGroupCar).equals("Sí") && getRadioGroupSelectedText(radioGroupMoto).equals("No")) {

            radioGroupMoto.clearCheck();

            Transport transporteCar = new Transport(getRadioGroupSelectedText(radioGroupTipoCar), getRadioGroupSelectedText(radioGroupCombustibleCar), cantidadKmCar, consumoLitrosCar);

            sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + transporteCar.TransportCalculatorCar()) * 10d) / 10d;
            System.out.println("********Prueba resultado: " + transporteCar.TransportCalculatorCar());
            resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

            System.out.println("********Prueba resultado suma secciones Vivienda, alimentacion y ropa: " + sumCarbonFootprint);

            intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
            intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
            intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
            intentTransport.putExtra("RESULT_TRANSPORT", String.valueOf(transporteCar.TransportCalculatorCar()));
            intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", String.valueOf(sumCarbonFootprint));
            intentTransport.putExtra("NEXT_STAGE", 4);
            intentTransport.putExtra("BACK_LINES", backlines);
            startActivity(intentTransport);
            finish();


        } else if (getRadioGroupSelectedText(radioGroupCar).equals("No") && getRadioGroupSelectedText(radioGroupMoto).equals("Sí")) {

            radioGroupCar.clearCheck();

            Transport transportMoto = new Transport(cantidadKmMoto, consumoLitrosMoto, getRadioGroupSelectedText(radioGroupCCMoto), getRadioGroupSelectedText(radioGroupCombustibleMoto));

            sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + transportMoto.TransportCalculatorMoto()) * 10d) / 10d;
            System.out.println("********Prueba resultado: " + transportMoto.TransportCalculatorMoto());
            resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

            System.out.println("********Prueba resultado suma secciones Vivienda, alimentacion y ropa: " + String.valueOf(sumCarbonFootprint));

            intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
            intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
            intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
            intentTransport.putExtra("RESULT_TRANSPORT", String.valueOf(transportMoto.TransportCalculatorMoto()));
            intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", String.valueOf(sumCarbonFootprint));
            intentTransport.putExtra("NEXT_STAGE", 4);
            intentTransport.putExtra("BACK_LINES", backlines);
            startActivity(intentTransport);
            finish();


        } else if (getRadioGroupSelectedText(radioGroupCar).equals("Sí") && getRadioGroupSelectedText(radioGroupMoto).equals("Sí")) {

            Transport transporteCarMoto = new Transport(getRadioGroupSelectedText(radioGroupTipoCar), getRadioGroupSelectedText(radioGroupCombustibleCar), cantidadKmCar, consumoLitrosCar, cantidadKmMoto, consumoLitrosMoto, getRadioGroupSelectedText(radioGroupCCMoto), getRadioGroupSelectedText(radioGroupCombustibleMoto));

            sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + transporteCarMoto.TransportCalculatorCar() + transporteCarMoto.TransportCalculatorMoto()) * 10d) / 10d;
            System.out.println("********Prueba resultado: " + Double.parseDouble(resultadoTransportDoubleCar) + Double.parseDouble(resultadoTransportDoubleMoto));
            resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));
            resultadoTransportDouble = String.valueOf(transporteCarMoto.TransportCalculatorCar() + transporteCarMoto.TransportCalculatorMoto());

            System.out.println("********Prueba resultado suma secciones Vivienda, alimentacion y ropa: " + sumSecciones);

            intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
            intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
            intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
            intentTransport.putExtra("RESULT_TRANSPORT", resultadoTransportDouble);
            intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", String.valueOf(sumCarbonFootprint));
            intentTransport.putExtra("NEXT_STAGE", 4);
            intentTransport.putExtra("BACK_LINES", backlines);
            startActivity(intentTransport);
            finish();

        } else {

            intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
            intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
            intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
            intentTransport.putExtra("RESULT_TRANSPORT", resultadoTransportDouble);
            intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", sumSecciones);
            intentTransport.putExtra("NEXT_STAGE", 4);
            intentTransport.putExtra("BACK_LINES", backlines);
            startActivity(intentTransport);
            finish();
        }

    }

    private void CaptionOtherTransport() {

        sumSecciones = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT");
        resultadoTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT");
        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");
        resultadoFoodDouble = getIntent().getStringExtra("RESULT_FOOD");
        resultadoClothesDouble = getIntent().getStringExtra("RESULT_CLOTHES");

        AVE = getTextFromEditTextInt(R.id.etAVE);
        trenL = getTextFromEditTextInt(R.id.etTrenL);
        cercanias = getTextFromEditTextInt(R.id.etCercanias);
        busUrbano = getTextFromEditTextInt(R.id.etBusUrbano);
        autocar = getTextFromEditTextInt(R.id.etAutocar);
        metro = getTextFromEditTextInt(R.id.etMetro);
        vuelosCortos = getTextFromEditTextInt(R.id.etVuelosCortos);
        vuelosMedios = getTextFromEditTextInt(R.id.etVuelosMedios);
        vuelosLargos = getTextFromEditTextInt(R.id.etVuelosLargos);

        OtherTransport otherTransport = new OtherTransport(AVE, trenL, cercanias, busUrbano, autocar, metro, vuelosCortos, vuelosMedios, vuelosLargos);

        sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + otherTransport.OtherTransportCalculator()) * 10d) / 10d;
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));
        System.out.println("*********************Prueba resultado OTROS TRANSPORTES: " + otherTransport.OtherTransportCalculator());


        Intent intentOtherTransport = new Intent(this, CarbonFootprintCalculator.class);
        intentOtherTransport.putExtra("RESULT_TRANSPORT_OTHER", String.valueOf(otherTransport.OtherTransportCalculator()));
        intentOtherTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT_TRANSPORT_OTHER", String.valueOf(sumCarbonFootprint));
        intentOtherTransport.putExtra("NEXT_STAGE", 5);
        intentOtherTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
        intentOtherTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
        intentOtherTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
        intentOtherTransport.putExtra("RESULT_TRANSPORT", resultadoTransportDouble);
        intentOtherTransport.putExtra("BACK_LINES", backlines);
        startActivity(intentOtherTransport);
        finish();
    }

    private void CaptionTechnology() {

        sumSecciones = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT_TRANSPORT_OTHER");
        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");
        resultadoFoodDouble = getIntent().getStringExtra("RESULT_FOOD");
        resultadoOtherTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT_OTHER");
        resultadoClothesDouble = getIntent().getStringExtra("RESULT_CLOTHES");
        resultadoTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT");

        udsPC = getTextFromEditTextInt(R.id.etUdsPC);
        yearPC = getTextFromEditTextDouble(R.id.etYearPC);
        udsLaptop = getTextFromEditTextInt(R.id.etUdsLaptop);
        yearLaptop = getTextFromEditTextDouble(R.id.etYearLaptop);
        udsTablet = getTextFromEditTextInt(R.id.etUdsTablets);
        yearTablet = getTextFromEditTextDouble(R.id.etYearTablets);
        udsSmartphone = getTextFromEditTextInt(R.id.etUdsMobile);
        yearSmartphone = getTextFromEditTextDouble(R.id.etUdsMobile);
        udsRouter = getTextFromEditTextInt(R.id.etUdsRouter);
        yearRouter = getTextFromEditTextDouble(R.id.etYearRouter);

        Technology technology = new Technology(udsPC, yearPC, udsLaptop, yearLaptop, udsTablet, yearTablet, udsSmartphone, yearSmartphone, udsRouter, yearRouter);

        sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + technology.TechnologyCalculator()) * 10d) / 10d;
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

        System.out.println("*********************Prueba resultado: " + technology.TechnologyCalculator());
        System.out.println("*********************Prueba resultado final: " + String.valueOf(sumCarbonFootprint));

        Intent intentTechnology = new Intent(this, ResultsView.class);
        intentTechnology.putExtra("RESULT_Technology", String.valueOf(technology.TechnologyCalculator()));
        intentTechnology.putExtra("RESULT_HOUSE", resultadoHouseDouble);
        intentTechnology.putExtra("RESULT_FOOD", resultadoFoodDouble);
        intentTechnology.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
        intentTechnology.putExtra("RESULT_TRANSPORT", resultadoTransportDouble);
        intentTechnology.putExtra("RESULT_TRANSPORT_OTHER", resultadoOtherTransportDouble);
        intentTechnology.putExtra("RESULT_ALL", String.valueOf(sumCarbonFootprint));
        intentTechnology.putExtra("BACK_LINES", backlines);
        startActivity(intentTechnology);
        finish();


    }

    public void toast(String message) {

        Drawable background = new GradientDrawable();
        ((GradientDrawable) background).setCornerRadius(200);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
        tv.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        toast.show();

    }

    public int getTextFromEditTextInt(int id) {
        return ((EditText) findViewById(id)).getText().toString().isEmpty() ? 0 : Integer.parseInt(((EditText) findViewById(id)).getText().toString());
    }

    public double getTextFromEditTextDouble(double id) {
        return ((EditText) findViewById((int) id)).getText().toString().isEmpty() ? 0 : Double.parseDouble(((EditText) findViewById((int) id)).getText().toString());
    }

    public String getRadioGroupSelectedText(RadioGroup radioGroup) {
        int radioButtonIDCar = radioGroup.getCheckedRadioButtonId();
        View radioButtonCar = radioGroup.findViewById(radioButtonIDCar);
        int idxCar = radioGroup.indexOfChild(radioButtonCar);
        RadioButton rCar = (RadioButton) radioGroup.getChildAt(idxCar);
        return rCar.getText().toString();
    }

}

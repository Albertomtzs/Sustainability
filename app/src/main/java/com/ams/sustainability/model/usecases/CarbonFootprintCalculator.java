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

public class CarbonFootprintCalculator extends AppCompatActivity {

    private String sumSecciones, resultadoHouseDouble, resultadoFoodDouble, resultadoClothesDouble, resultadoTransportDouble;
    private int stage, backlines;
    private double sumCarbonFootprint;
    private TextView resultInput;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_footprint_calculator);

        LinearLayout layout = findViewById(R.id.scrollLayout);
        TextView title = findViewById(R.id.title);
        Button back = findViewById(R.id.btnBack);
        Button next = findViewById(R.id.btnNext);
        resultInput = findViewById(R.id.resultInput);

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
                setResultInputText("RESULT_HOUSE");
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_comida, null));
                break;
            case 2:
                title.setText(R.string.ropa);
                setResultInputText("RESULT_HOUSE_FOOD");
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_ropa, null));
                break;
            case 3:
                title.setText(R.string.transporte);
                setResultInputText("RESULT_HOUSE_FOOD_CLOTHES");
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_transporte, null));
                break;
            case 4:
                title.setText(R.string.otros_transportes);
                setResultInputText("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT");
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_otros_transportes, null));
                break;
            case 5:
                title.setText(R.string.tecnologia);
                setResultInputText("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT_TRANSPORT_OTHER");
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_tecnologia, null));
                break;
        }

    }

    private void setResultInputText(String extraKey) {
        String value = getIntent().getStringExtra(extraKey);
        resultInput.setText((value != null) ? value.replace(".", ",") : "0,0");
    }


    public void onBackClicked(View view) {

        Intent backIntent;
        if (stage == 0) {
            backIntent = new Intent(this, GetStartedCalculator.class);
        } else {
            backIntent = new Intent(this, CarbonFootprintCalculator.class);
            stage--;
            backIntent.putExtra("NEXT_STAGE", stage);
            startActivity(backIntent);
            finish();

        }

    }

    public void onNextClicked(View view) {

        switch (stage) {
            case 0:

                CaptionHouse();
                finish();
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

    public void CaptionHouse() {

        RadioGroup radioGroupHouse = findViewById(R.id.radioGroupRenovable);
        EditText etNumPersonas = findViewById(R.id.etNumPersonas);

        if (radioGroupHouse.getCheckedRadioButtonId() == -1) {

            toast("Debe seleccionar si la electricidad de su vivienda es de fuentes renovables");
            return;

        } else if (etNumPersonas.getText().toString().isEmpty()) {

            toast("Debe indicar cuántas personas viven en su domicilio");
            return;
        }

        int numPersonas = getTextFromEditTextInt(R.id.etNumPersonas);
        double electricidad = getTextFromEditTextDouble(R.id.etElectricidad);
        double gasNatural = getTextFromEditTextDouble(R.id.etGasNatural);
        double gasoil = getTextFromEditTextDouble(R.id.etGasoil);
        double butano = getTextFromEditTextDouble(R.id.etGasButano);
        double propano = getTextFromEditTextDouble(R.id.etGasPropano);
        double carbon = getTextFromEditTextDouble(R.id.etCarbon);
        double pellets = getTextFromEditTextDouble(R.id.etPellets);

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

        double hortalizas = getTextFromEditTextDouble(R.id.etHortalizas);
        double fruta = getTextFromEditTextDouble(R.id.etFruta);
        double huevos = getTextFromEditTextDouble(R.id.etHuevos);
        double frutos_secos = getTextFromEditTextDouble(R.id.etFrutosSecos);
        double pescado = getTextFromEditTextDouble(R.id.etPescado);
        double mariscos = getTextFromEditTextDouble(R.id.etMarisco);
        double pavo = getTextFromEditTextDouble(R.id.etPavo);
        double pollo = getTextFromEditTextDouble(R.id.etPollo);
        double cerdo = getTextFromEditTextDouble(R.id.etCerdo);
        double ternera = getTextFromEditTextDouble(R.id.etTernera);
        double cereales = getTextFromEditTextDouble(R.id.etCereales);
        double dulces = getTextFromEditTextDouble(R.id.etDulces);
        double aceites = getTextFromEditTextDouble(R.id.etAceite);
        double refrescos = getTextFromEditTextDouble(R.id.etRefrescos);
        double cerveza = getTextFromEditTextDouble(R.id.etCerveza);
        double vino = getTextFromEditTextDouble(R.id.etVino);
        double licores = getTextFromEditTextDouble(R.id.etLicores);
        double leche = getTextFromEditTextDouble(R.id.etLeche);

        Food food = new Food(hortalizas, fruta, frutos_secos, pescado, mariscos, pavo, pollo, cerdo, ternera, huevos, cereales, dulces, aceites, refrescos, cerveza, vino, licores, leche);

        sumSecciones = (sumSecciones == null) ? "0.0" : sumSecciones;

        sumCarbonFootprint = Math.round((Double.parseDouble(resultadoHouseDouble) + food.FoodCalculator()) * 10d) / 10d;
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

        int pantalonVaquero = getTextFromEditTextInt(R.id.etPantalon);
        int otroPantalon = getTextFromEditTextInt(R.id.etOtrosPantalones);
        int camisas = getTextFromEditTextInt(R.id.etCamisas);
        int camisetas = getTextFromEditTextInt(R.id.etCamisetas);
        int vestidos = getTextFromEditTextInt(R.id.etVestidos);
        int calcetines = getTextFromEditTextInt(R.id.etCalcetines);
        int chaquetas = getTextFromEditTextInt(R.id.etChaquetas);
        int abrigos = getTextFromEditTextInt(R.id.etAbrigos);
        int jerseys = getTextFromEditTextInt(R.id.etJerseys);
        int zapatos = getTextFromEditTextInt(R.id.etZapatos);
        int zapatillasDeporte = getTextFromEditTextInt(R.id.etZapatillasDeportivas);
        int ropaInterior = getTextFromEditTextInt(R.id.etRopaInterior);

        Clothes clothes = new Clothes(pantalonVaquero, otroPantalon, camisas, camisetas, vestidos, calcetines, chaquetas, abrigos, jerseys, zapatos, zapatillasDeporte, ropaInterior);

        sumSecciones = (sumSecciones == null) ? "0.0" : sumSecciones;

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
        //finish();
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

        double consumoLitrosCar = getTextFromEditTextDouble(R.id.etConsumoLitrosCar);
        double cantidadKmCar = getTextFromEditTextDouble(R.id.etCantidadKmCar);
        double consumoLitrosMoto = getTextFromEditTextDouble(R.id.etConsumoLitrosMoto);
        double cantidadKmMoto = getTextFromEditTextDouble(R.id.etCantidadKmMoto);

        sumSecciones = (sumSecciones == null) ? "0.0" : sumSecciones;

        Intent intentTransport = new Intent(this, CarbonFootprintCalculator.class);
        Transport transporte;
        boolean isCarSelected = getRadioGroupSelectedText(radioGroupCar).equals("Sí");
        boolean isMotoSelected = getRadioGroupSelectedText(radioGroupMoto).equals("Sí");

        if (isCarSelected && !isMotoSelected) {
            radioGroupMoto.clearCheck();
            transporte = new Transport(getRadioGroupSelectedText(radioGroupTipoCar), getRadioGroupSelectedText(radioGroupCombustibleCar), cantidadKmCar, consumoLitrosCar);
        } else if (!isCarSelected && isMotoSelected) {
            radioGroupCar.clearCheck();
            transporte = new Transport(cantidadKmMoto, consumoLitrosMoto, getRadioGroupSelectedText(radioGroupCCMoto), getRadioGroupSelectedText(radioGroupCombustibleMoto));
        } else if (isCarSelected && isMotoSelected) {
            transporte = new Transport(getRadioGroupSelectedText(radioGroupTipoCar), getRadioGroupSelectedText(radioGroupCombustibleCar), cantidadKmCar, consumoLitrosCar, cantidadKmMoto, consumoLitrosMoto, getRadioGroupSelectedText(radioGroupCCMoto), getRadioGroupSelectedText(radioGroupCombustibleMoto));
        } else {
            intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
            intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
            intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
            intentTransport.putExtra("RESULT_TRANSPORT", resultadoTransportDouble);
            intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", sumSecciones);
            intentTransport.putExtra("NEXT_STAGE", 4);
            intentTransport.putExtra("BACK_LINES", backlines);
            startActivity(intentTransport);
            //finish();
            return;
        }

        sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + transporte.TransportCalculatorCar() + transporte.TransportCalculatorMoto()) * 10d) / 10d;

        System.out.println("********Prueba resultado: " + Math.round((transporte.TransportCalculatorCar() + transporte.TransportCalculatorMoto()) * 10d) / 10d);
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

        System.out.println("********Prueba resultado suma secciones Vivienda, alimentacion y ropa: " + sumCarbonFootprint);

        intentTransport.putExtra("RESULT_HOUSE", resultadoHouseDouble);
        intentTransport.putExtra("RESULT_FOOD", resultadoFoodDouble);
        intentTransport.putExtra("RESULT_CLOTHES", resultadoClothesDouble);
        intentTransport.putExtra("RESULT_TRANSPORT", String.valueOf(Math.round((transporte.TransportCalculatorCar() + transporte.TransportCalculatorMoto()) * 10d) / 10d));
        intentTransport.putExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT", String.valueOf(sumCarbonFootprint));
        intentTransport.putExtra("NEXT_STAGE", 4);
        intentTransport.putExtra("BACK_LINES", backlines);
        startActivity(intentTransport);
        finish();

    }

    private void CaptionOtherTransport() {

        sumSecciones = getIntent().getStringExtra("RESULT_HOUSE_FOOD_CLOTHES_TRANSPORT");
        resultadoTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT");
        resultadoHouseDouble = getIntent().getStringExtra("RESULT_HOUSE");
        resultadoFoodDouble = getIntent().getStringExtra("RESULT_FOOD");
        resultadoClothesDouble = getIntent().getStringExtra("RESULT_CLOTHES");

        double AVE = getTextFromEditTextInt(R.id.etAVE);
        double trenL = getTextFromEditTextInt(R.id.etTrenL);
        double cercanias = getTextFromEditTextInt(R.id.etCercanias);
        double busUrbano = getTextFromEditTextInt(R.id.etBusUrbano);
        double autocar = getTextFromEditTextInt(R.id.etAutocar);
        double metro = getTextFromEditTextInt(R.id.etMetro);
        double vuelosCortos = getTextFromEditTextInt(R.id.etVuelosCortos);
        double vuelosMedios = getTextFromEditTextInt(R.id.etVuelosMedios);
        double vuelosLargos = getTextFromEditTextInt(R.id.etVuelosLargos);

        sumSecciones = (sumSecciones == null) ? "0.0" : sumSecciones;

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
        String resultadoOtherTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT_OTHER");
        resultadoClothesDouble = getIntent().getStringExtra("RESULT_CLOTHES");
        resultadoTransportDouble = getIntent().getStringExtra("RESULT_TRANSPORT");

        resultadoHouseDouble = (resultadoHouseDouble == null) ? "0.0" : resultadoHouseDouble;
        resultadoFoodDouble = (resultadoFoodDouble == null) ? "0.0" : resultadoFoodDouble;
        resultadoClothesDouble = (resultadoClothesDouble == null) ? "0.0" : resultadoClothesDouble;
        resultadoTransportDouble = (resultadoTransportDouble == null) ? "0.0" : resultadoTransportDouble;
        resultadoOtherTransportDouble = (resultadoOtherTransportDouble == null) ? "0.0" : resultadoOtherTransportDouble;

        int udsPC = getTextFromEditTextInt(R.id.etUdsPC);
        double yearPC = getTextFromEditTextDouble(R.id.etYearPC);
        int udsLaptop = getTextFromEditTextInt(R.id.etUdsLaptop);
        double yearLaptop = getTextFromEditTextDouble(R.id.etYearLaptop);
        int udsTablet = getTextFromEditTextInt(R.id.etUdsTablets);
        double yearTablet = getTextFromEditTextDouble(R.id.etYearTablets);
        int udsSmartphone = getTextFromEditTextInt(R.id.etUdsMobile);
        double yearSmartphone = getTextFromEditTextDouble(R.id.etUdsMobile);
        int udsRouter = getTextFromEditTextInt(R.id.etUdsRouter);
        double yearRouter = getTextFromEditTextDouble(R.id.etYearRouter);

        Technology technology = new Technology(udsPC, yearPC, udsLaptop, yearLaptop, udsTablet, yearTablet, udsSmartphone, yearSmartphone, udsRouter, yearRouter);

        sumSecciones = (sumSecciones == null) ? "0.0" : sumSecciones;
        resultInput.setText(String.valueOf(sumCarbonFootprint).replace(".", ","));

        sumCarbonFootprint = Math.round((Double.parseDouble(sumSecciones) + technology.TechnologyCalculator()) * 10d) / 10d;

        System.out.println("*********************Prueba resultado: " + technology.TechnologyCalculator());
        System.out.println("*********************Prueba resultado final: " + String.valueOf(sumCarbonFootprint));

        Intent intentTechnology = new Intent(this, ResultsView.class);
        intentTechnology.putExtra("RESULT_TECHNOLOGY", String.valueOf(technology.TechnologyCalculator()));
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

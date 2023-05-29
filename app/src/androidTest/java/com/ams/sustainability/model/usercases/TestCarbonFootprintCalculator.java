package com.ams.sustainability.model.usercases;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.ams.sustainability.R;
import com.ams.sustainability.model.usecases.CarbonFootprintCalculator;

import org.junit.Before;
import org.junit.Test;

public class TestCarbonFootprintCalculator {


    @Before
    public void setUp() {
        ActivityScenario.launch(CarbonFootprintCalculator.class);
    }

    @Test
    public void testCaptionEmissions1() throws InterruptedException {

        // ------------------------------ House ------------------------------

        Espresso.onView(withId(R.id.radioGroupRenovable))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.etNumPersonas))
                .perform(ViewActions.typeText("3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etElectricidad))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasNatural))
                .perform(ViewActions.typeText("50"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasoil))
                .perform(ViewActions.typeText("30"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasButano))
                .perform(ViewActions.typeText("20"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasPropano))
                .perform(ViewActions.typeText("10"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCarbon))
                .perform(ViewActions.typeText("40"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPellets))
                .perform(ViewActions.typeText("15"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,1")));


        // ------------------------------ Food ------------------------------


        Espresso.onView(withId(R.id.etHortalizas))
                .perform(ViewActions.typeText("1.4"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFruta))
                .perform(ViewActions.typeText("0.6"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCereales))
                .perform(ViewActions.typeText("0.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLeche))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etHuevos))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFrutosSecos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etDulces))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPescado))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMarisco))
                .perform(ViewActions.typeText("0.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAceite))
                .perform(ViewActions.typeText("0.1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPavo))
                .perform(ViewActions.typeText("1.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPollo))
                .perform(ViewActions.typeText("0.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerdo))
                .perform(ViewActions.typeText("0.7"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTernera))
                .perform(ViewActions.typeText("0.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerveza))
                .perform(ViewActions.typeText("0.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLicores))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRefrescos))
                .perform(ViewActions.typeText("0.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVino))
                .perform(ViewActions.typeText("0.2"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("2,0")));


        // ------------------------------ Clothes ------------------------------


        Espresso.onView(withId(R.id.etPantalon))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etOtrosPantalones))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisas))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisetas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVestidos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCalcetines))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etChaquetas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAbrigos))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etJerseys))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatos))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatillasDeportivas))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRopaInterior))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("2,1")));


        // ------------------------------ Transport ------------------------------


        Espresso.onView(ViewMatchers.withId(R.id.rbNoCar))
                .perform(ViewActions.scrollTo(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCar))
                .perform(ViewActions.scrollTo());
        Espresso.onView(ViewMatchers.withId(R.id.rbUtilitario))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());

        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaCar))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosCar))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmCar))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.radioGroupPropMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbNoMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.radioGroupCC_Moto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.rbCCmenos125))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosMoto))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmMoto))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("2,1")));


        // ------------------------------ OtherTransport ------------------------------


        Espresso.onView(withId(R.id.etAVE))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTrenL))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCercanias))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etBusUrbano))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAutocar))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMetro))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosCortos))
                .perform(ViewActions.typeText("1200"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosMedios))
                .perform(ViewActions.typeText("12000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosLargos))
                .perform(ViewActions.typeText("101"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("4,3")));


        // ------------------------------ Technology ------------------------------


        Espresso.onView(withId(R.id.etUdsPC))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearPC))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsLaptop))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearLaptop))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsTablets))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearTablets))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsMobile))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearMobile))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsRouter))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearRouter))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());


        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.textTotal))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("4,6 t")));

    }

    @Test
    public void testCaptionEmissions2() throws InterruptedException {

        // ------------------------------ House ------------------------------

        Espresso.onView(withId(R.id.radioGroupRenovable))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.etNumPersonas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etElectricidad))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasNatural))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasoil))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasButano))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasPropano))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCarbon))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPellets))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Food ------------------------------


        Espresso.onView(withId(R.id.etHortalizas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFruta))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCereales))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLeche))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etHuevos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFrutosSecos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etDulces))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPescado))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMarisco))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAceite))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPavo))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPollo))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerdo))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTernera))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerveza))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLicores))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRefrescos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVino))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Clothes ------------------------------


        Espresso.onView(withId(R.id.etPantalon))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etOtrosPantalones))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisetas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVestidos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCalcetines))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etChaquetas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAbrigos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etJerseys))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatillasDeportivas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRopaInterior))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Transport ------------------------------


        Espresso.onView(ViewMatchers.withId(R.id.rbNoCar))
                .perform(ViewActions.scrollTo(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCar))
                .perform(ViewActions.scrollTo());
        Espresso.onView(ViewMatchers.withId(R.id.rbUtilitario))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());

        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaCar))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosCar))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmCar))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.radioGroupPropMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbNoMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.radioGroupCC_Moto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.rbCCmenos125))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosMoto))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmMoto))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ OtherTransport ------------------------------


        Espresso.onView(withId(R.id.etAVE))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTrenL))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCercanias))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etBusUrbano))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAutocar))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMetro))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosCortos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosMedios))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosLargos))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Technology ------------------------------


        Espresso.onView(withId(R.id.etUdsPC))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearPC))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsLaptop))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearLaptop))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsTablets))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearTablets))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsMobile))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearMobile))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsRouter))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearRouter))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());


        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.textTotal))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("0,0 t")));

    }

    @Test
    public void testCaptionEmissions3() throws InterruptedException {

        // ------------------------------ House ------------------------------

        Espresso.onView(withId(R.id.radioGroupRenovable))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.etNumPersonas))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etElectricidad))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etElectricidad))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etElectricidad))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasNatural))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasoil))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etGasButano))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etGasButano))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etGasButano))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etGasButano))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etGasButano))
                .perform(ViewActions.scrollTo());
        Espresso.onView(withId(R.id.etGasPropano))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCarbon))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPellets))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etPellets))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Food ------------------------------


        Espresso.onView(withId(R.id.etHortalizas))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etFruta))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etFruta))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCereales))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCereales))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etLeche))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etLeche))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etHuevos))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etHuevos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFrutosSecos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etDulces))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etDulces))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etDulces))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etDulces))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPescado))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMarisco))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAceite))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPavo))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etPavo))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etPavo))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPollo))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etPollo))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etPollo))
                .perform(ViewActions.scrollTo());
        Espresso.onView(withId(R.id.etPollo))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCerdo))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCerdo))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etTernera))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etTernera))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCerveza))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCerveza))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etLicores))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etLicores))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etRefrescos))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etRefrescos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etVino))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etVino))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Clothes ------------------------------


        Espresso.onView(withId(R.id.etPantalon))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etOtrosPantalones))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etOtrosPantalones))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCamisas))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCamisas))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCamisetas))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCamisetas))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etVestidos))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etVestidos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCalcetines))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCalcetines))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etChaquetas))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etChaquetas))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etAbrigos))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etAbrigos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etJerseys))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etJerseys))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etZapatos))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etZapatos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etRopaInterior))
                .perform(ViewActions.swipeUp());Espresso.onView(withId(R.id.etZapatillasDeportivas))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRopaInterior))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Transport ------------------------------


        Espresso.onView(ViewMatchers.withId(R.id.rbNoCar))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosCar))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmCar))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupPropMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbNoMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupCC_Moto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.rbCCmenos125))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosMoto))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmMoto))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.btnNext))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ OtherTransport ------------------------------


        Espresso.onView(withId(R.id.etAVE))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTrenL))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCercanias))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etCercanias))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etBusUrbano))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etBusUrbano))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAutocar))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etMetro))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etMetro))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosCortos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etVuelosMedios))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.etVuelosMedios))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosLargos))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("0,0")));


        // ------------------------------ Technology ------------------------------


        Espresso.onView(withId(R.id.etUdsPC))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearPC))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsLaptop))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearLaptop))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsTablets))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearTablets))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsMobile))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearMobile))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsRouter))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearRouter))
                .perform(ViewActions.typeText(""), ViewActions.closeSoftKeyboard());


        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.textTotal))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("0,0 t")));

    }

    @Test
    public void testCaptionEmissions4() throws InterruptedException {

        // ------------------------------ House ------------------------------

        Espresso.onView(ViewMatchers.withId(R.id.rbNo))
                .perform(ViewActions.scrollTo(), ViewActions.click());
        Espresso.onView(withId(R.id.etNumPersonas))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etElectricidad))
                .perform(ViewActions.typeText("3000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasNatural))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasoil))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasButano))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etGasPropano))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCarbon))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPellets))
                .perform(ViewActions.typeText("100"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("1,7")));


        // ------------------------------ Food ------------------------------


        Espresso.onView(withId(R.id.etHortalizas))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFruta))
                .perform(ViewActions.typeText("1.6"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCereales))
                .perform(ViewActions.typeText("1.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLeche))
                .perform(ViewActions.typeText("10"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etHuevos))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etFrutosSecos))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etDulces))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPescado))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMarisco))
                .perform(ViewActions.typeText("1.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAceite))
                .perform(ViewActions.typeText("1.1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPavo))
                .perform(ViewActions.typeText("1.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etPollo))
                .perform(ViewActions.typeText("1.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerdo))
                .perform(ViewActions.typeText("1.7"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTernera))
                .perform(ViewActions.typeText("1.5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCerveza))
                .perform(ViewActions.typeText("1.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etLicores))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRefrescos))
                .perform(ViewActions.typeText("1.3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVino))
                .perform(ViewActions.typeText("1.2"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("7,8")));


        // ------------------------------ Clothes ------------------------------


        Espresso.onView(withId(R.id.etPantalon))
                .perform(ViewActions.typeText("10"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etOtrosPantalones))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisas))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCamisetas))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVestidos))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCalcetines))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etChaquetas))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAbrigos))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etJerseys))
                .perform(ViewActions.typeText("8"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatos))
                .perform(ViewActions.typeText("8"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etZapatillasDeportivas))
                .perform(ViewActions.typeText("8"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etRopaInterior))
                .perform(ViewActions.typeText("8"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("9,2")));


        // ------------------------------ Transport ------------------------------


        Espresso.onView(ViewMatchers.withId(R.id.rbYesCar))
                .perform(ViewActions.scrollTo(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCar))
                .perform(ViewActions.scrollTo());
        Espresso.onView(ViewMatchers.withId(R.id.rbFamiliar))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleCar))
                .perform(ViewActions.swipeUp());

        Espresso.onView(ViewMatchers.withId(R.id.rbDieselCar))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosCar))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmCar))
                .perform(ViewActions.typeText("10500"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.radioGroupPropMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbYesMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.radioGroupCC_Moto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(withId(R.id.rbCCmenos125))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.radioGroupTipoCombustibleMoto))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withId(R.id.rbGasolinaMoto))
                .perform(ViewActions.swipeUp(), ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.etConsumoLitrosMoto))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etCantidadKmMoto))
                .perform(ViewActions.typeText("15000"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("12,2")));


        // ------------------------------ OtherTransport ------------------------------


        Espresso.onView(withId(R.id.etAVE))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etTrenL))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etCercanias))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etBusUrbano))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etAutocar))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etMetro))
                .perform(ViewActions.typeText("1000"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosCortos))
                .perform(ViewActions.typeText("1900"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosMedios))
                .perform(ViewActions.typeText("13500"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etVuelosLargos))
                .perform(ViewActions.typeText("7800"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.resultInput))
                .check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("16,0")));


        // ------------------------------ Technology ------------------------------


        Espresso.onView(withId(R.id.etUdsPC))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearPC))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsLaptop))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearLaptop))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsTablets))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearTablets))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsMobile))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearMobile))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etUdsRouter))
                .perform(ViewActions.typeText("1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.etYearRouter))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());


        Espresso.onView(withId(R.id.btnNext))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.textTotal))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("16,6 t")));

    }
}


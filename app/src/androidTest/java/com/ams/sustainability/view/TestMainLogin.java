package com.ams.sustainability.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiSelector;

import com.ams.sustainability.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

public class TestMainLogin {


    @Rule
    public ActivityScenarioRule<MainLogin> activityRule = new ActivityScenarioRule<>(MainLogin.class);

    @Test
    public void testInvalidLogin() {
        // Ingresa el email y la contraseña en los campos de texto
        onView(ViewMatchers.withId(R.id.identityField)).perform(ViewActions.typeText("usuario@example.com"));
        onView(ViewMatchers.withId(R.id.passwordField)).perform(replaceText("contraseña"));

        ViewInteraction viewInteraction = onView(withId(R.id.btnLogin))
                .withFailureHandler((Throwable error, Matcher<View> viewMatcher) -> {
                    if (error instanceof PerformException) {
                        Throwable cause = error.getCause();
                        if (cause instanceof InjectEventSecurityException) {
                            // Ignorar el error relacionado con las animaciones
                            return;
                        }
                    }
                    throw new RuntimeException(error);
                });

        // Verifica que se muestre un Toast con el mensaje de error esperado
        String expectedErrorMessage = "El usuario o contraseña introducidos son erróneos";
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(uiDevice, is(notNullValue()));
        assertThat(uiDevice.findObject(new UiSelector().text(expectedErrorMessage)), is(notNullValue()));
    }

    @Test
    public void testEmptyFieldsLogin() {
        // No ingresa nada en los campos de texto
        onView(withId(R.id.btnLogin)).perform(click());


        // Verifica que se muestre un Toast con el mensaje de error esperado
        String expectedErrorMessage = "Por favor, introduce tu email y contraseña";
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(uiDevice, is(notNullValue()));
        assertThat(uiDevice.findObject(new UiSelector().text(expectedErrorMessage)), is(notNullValue()));
    }

    @Test
    public void testValidLogin() {
        // Ingresa el email y la contraseña en los campos de texto
        onView(withId(R.id.identityField)).perform(replaceText("prueba@gmail.com"));
        onView(withId(R.id.passwordField)).perform(replaceText("1"));

        // Hace clic en el botón de inicio de sesión
        onView(withId(R.id.btnLogin)).perform(click());


        ActivityScenario<GetStartedCalculator> scenario = ActivityScenario.launch(GetStartedCalculator.class);
        scenario.onActivity(activity -> {

        });

    }


}

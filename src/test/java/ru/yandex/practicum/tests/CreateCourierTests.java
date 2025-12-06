package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.practicum.model.Courier;
import ru.practicum.practicum.steps.CouriersSteps;

import static org.hamcrest.CoreMatchers.*;


public class CreateCourierTests extends BaseTest{
    private CouriersSteps couriersSteps = new CouriersSteps();
    private Courier courier;

    @Before
    public void setUp(){

        courier = new Courier();
        courier
                .setLogin(RandomStringUtils.randomAlphabetic(12))
                .setPassword(RandomStringUtils.randomAlphabetic(12))
                .setFirstName(RandomStringUtils.randomAlphabetic(12));

    }


    @Test
    @DisplayName("Создание нового курьера.")
    public void createNewCourier(){


        couriersSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание нового курьера. Курьер без имени курьера.")
    public void createNewCourierWithoutFirstName(){


        courier.setFirstName("");
        couriersSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    //Создание курьеров с одинаковыми логинами
    @Test
    @DisplayName("Создание нового курьера. Логин уже есть в базе.")
    public void shouldCreateCourierSameLoginTest(){


        couriersSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));

        String oldLogin = courier.getLogin();

        couriersSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        courier.setLogin(oldLogin);
    }

    @Test
    @DisplayName("Создание нового курьера. Курьер без логина")
    public void shouldCreateCourierWithoutLoginTest(){


        courier.setLogin("");
        couriersSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("Создание нового курьера. Курьер без пароля.")
    public void shouldCreateCourierWithoutPasswordTest(){


        courier.setPassword("");
        couriersSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }


    @Test
    @DisplayName("Создание нового курьера. Курьер без пароля и логина.")
    public void shouldCreateCourierWithoutLoginAndPasswordTest(){


        courier.setLogin("");
        courier.setPassword("");
        couriersSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @After
    public void tearDown(){
        if(!courier.getLogin().isEmpty() && !courier.getPassword().isEmpty()) {
            Integer id = couriersSteps
                    .login(courier)
                    .extract().body().path("id");

            courier.setId(id);
            couriersSteps.deleteCourier(courier);
        }


    }

}

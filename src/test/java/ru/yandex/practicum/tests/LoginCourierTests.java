package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.practicum.model.Courier;
import ru.practicum.practicum.steps.CouriersSteps;

import static org.hamcrest.CoreMatchers.*;

public class LoginCourierTests  extends BaseTest{
    private CouriersSteps couriersSteps = new CouriersSteps();
    private Courier courier;

    @Before
    public void setUp(){
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        courier
                .setLogin("diamond25")
                .setPassword("BKFMuDaDskcN")
                .setFirstName("Petya");
        couriersSteps.createCourier(courier);
        Integer id = couriersSteps
                .login(courier)
                .extract().body().path("id");
        courier.setId(id);

    }

    @Test
    @DisplayName("Успешная авторизация пользователя.")
    public void shouldLoginCourierTest(){


        couriersSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());


       }

    @Test
    @DisplayName("Успешная авторизация пользователя c обязательными полями login и password")
    public void shouldLoginCourierWithoutFirstNameTest(){

        courier.setFirstName("");
        couriersSteps
                .login(courier)
                .statusCode(200)
                .body("id", notNullValue());

    }

    @Test
    @DisplayName("Авторизация пользователя c пустым логином")
    public void shouldLoginCourierWithoutLoginTest(){

        courier.setLogin("");
        couriersSteps
                .login(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Авторизация пользователя c пустым паролем")
    public void shouldLoginCourierWithoutPasswordTest(){

        courier.setPassword("");
        couriersSteps
                .login(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Авторизация пользователя c пустым логином и паролем")
    public void shouldLoginCourierWithoutLoginAndPasswordTest(){

        courier.setLogin("");
        courier.setPassword("");
        couriersSteps
                .login(courier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Авторизация пользователя c некорректным логином")
    public void shouldLoginCourierWithWrongLoginTest(){

        courier.setLogin(RandomStringUtils.randomAlphabetic(12));
        couriersSteps
                .login(courier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Авторизация пользователя c некорректным паролем")
    public void shouldLoginCourierWithWrongPasswordTest(){

        courier.setPassword(RandomStringUtils.randomAlphabetic(12));
        couriersSteps
                .login(courier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }
    // Залогинивание с неправильным логином и паролем
    @Test
    @DisplayName("Авторизация пользователя c некорректным логином и паролем")
    public void shouldLoginCourierWithWrongLoginAndWrongPasswordTest(){

        courier.setLogin(RandomStringUtils.randomAlphabetic(12));
        courier.setPassword(RandomStringUtils.randomAlphabetic(12));
        couriersSteps
                .login(courier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @After
    public void tearDown(){
        couriersSteps.deleteCourier(courier);


    }

}

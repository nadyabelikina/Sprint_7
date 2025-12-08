package ru.practicum.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.practicum.model.Order;
import static io.restassured.RestAssured.given;

public class OrdersSteps {
    @Step
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    @Step
    public ValidatableResponse orderList() {
        // return given().header("Content-type", "application/json").log().all().get("/api/v1/orders").then().assertThat().statusCode(200);
        return given()
                .when()
                .get("/api/v1/orders")
                .then();
    }

}

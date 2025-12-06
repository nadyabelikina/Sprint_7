package ru.practicum.practicum.steps;

import io.restassured.response.ValidatableResponse;
import ru.practicum.practicum.model.Courier;

import static io.restassured.RestAssured.given;




public class CouriersSteps {

    public ValidatableResponse createCourier(Courier courier){
        return given()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse login(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

   public ValidatableResponse deleteCourier(Courier courier){
       return given()
               .pathParams("id", courier.getId())
               .when()
               .delete("/api/v1/courier/{id}")
               .then();
   }

}

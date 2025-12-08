package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.practicum.model.Order;
import ru.practicum.practicum.steps.OrdersSteps;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
@DisplayName("Создание нового заказа. Проверка поля Цвет самоката.")

public class CreateOrderTests extends BaseTest {
    private OrdersSteps ordersSteps = new OrdersSteps();
    private Order order;
    private List<String> color;

    public CreateOrderTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката - {0}")
    public static Object[][] dataGen() {
        return new Object[][]{
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of()}
        };
    }

    @Before
    public void setUp() {

        order = new Order();
        order
                .setFirstName("Naruto")
                .setLastName("Uchiha")
                .setAddress("Konoha, 142 apt.")
                .setMetroStation(4)
                .setPhone("+7 800 355 35 35")
                .setRentTime(5)
                .setDeliveryDate("2020-06-06")
                .setComment("Saske, come back to Konoha");

    }

    @Test
    //Создание нового заказа color = GREY
    public void createNewOrderGrey() {


        ordersSteps
                .createOrder(order)
                .statusCode(201)
                .body("track", notNullValue());
    }


}

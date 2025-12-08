package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.practicum.model.Order;
import ru.practicum.practicum.steps.OrdersSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTests extends BaseTest {
    private OrdersSteps ordersSteps = new OrdersSteps();
    private Order order;

    @Before
    public void setUp() {
        order = new Order();
    }

    @Test
    @DisplayName("Список всех заказов.")
    public void getListOrdersTest() {

        ordersSteps
                .orderList()
                .statusCode(200)
                .body("orders", notNullValue());


    }
}


package com.example.axon.query;

import com.example.axon.command.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProjector {

    private final List<OrderView> orders = new ArrayList<>();

    @EventHandler
    void on(OrderCreatedEvent event) {
        if(!orders.stream().anyMatch(order -> order.getName().equals(event.getName()))) {
            orders.add(new OrderView(event.getName()));
        }
    }

    @QueryHandler
    List<OrderView> handle(FindAllOrdersQuery query) {
        return orders;
    }
}

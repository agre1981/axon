package com.example.axon.controller;

import com.example.axon.api.CreateOrderDTO;
import com.example.axon.api.OrderAggregate;
import com.example.axon.command.CancelOrderCommand;
import com.example.axon.command.CreateOrderCommand;
import com.example.axon.query.FindAllOrdersQuery;
import com.example.axon.query.OrderView;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping
    CompletableFuture<List<OrderView>> getAllOrders() {
        return queryGateway.query(new FindAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<?> createOrder(@RequestBody CreateOrderDTO request) {
        return commandGateway.send(new CreateOrderCommand(request.getName()));
    }

    @DeleteMapping("/{name}")
    CompletableFuture<?> deleteOrder(@PathVariable String name) {
        return commandGateway.send(new CancelOrderCommand(name));
    }

}

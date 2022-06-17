package com.example.axon.api;

import com.example.axon.command.CancelOrderCommand;
import com.example.axon.command.CreateOrderCommand;
import com.example.axon.event.OrderCancelledEvent;
import com.example.axon.event.OrderCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Slf4j
@Component
@Aggregate
@Getter
@Setter
public class OrderAggregate {

    @AggregateIdentifier
    private String name;

    protected OrderAggregate() { }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        if (command.getName() == null || command.getName().length() == 0) {
            throw new IllegalArgumentException("invalid name");
        }
        apply(new OrderCreatedEvent(command.getName()));
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        apply(new OrderCancelledEvent(command.getName()));
        markDeleted();
    }

    @EventHandler
    void on(OrderCreatedEvent event) {
       name = event.getName();
    }

    @EventHandler
    void on(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent handled");
    }

}

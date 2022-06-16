package com.example.axon.api;

import com.example.axon.command.CreateOrderCommand;
import com.example.axon.command.OrderCreatedEvent;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Aggregate
@Component
public class OrderAggregate {

    @AggregateIdentifier
    private String name;

    @CommandHandler
    OrderAggregate(CreateOrderCommand command) {
        if (command.getName() == null || command.getName().length() == 0) {
            throw new IllegalArgumentException("invalid name");
        }
        name = command.getName();

        apply(new OrderCreatedEvent(command.getName()));
    }
}

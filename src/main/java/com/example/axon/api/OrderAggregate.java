package com.example.axon.api;

import com.example.axon.command.CancelOrderCommand;
import com.example.axon.command.CreateOrderCommand;
import com.example.axon.event.OrderCancelledEvent;
import com.example.axon.event.OrderCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

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
        name = command.getName();

        apply(new OrderCreatedEvent(command.getName()));
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) {
        apply(new OrderCancelledEvent(command.getName()));
        markDeleted();
    }

}

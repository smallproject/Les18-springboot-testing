package com.example.les18.service;

import com.example.les18.dto.OrderDto;
import com.example.les18.model.Order;
import com.example.les18.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepos;

    public OrderService(OrderRepository repos) {
        this.orderRepos = repos;
    }
    public int putOrder(OrderDto newOrderDto) {
        Order o = new Order(newOrderDto.productname, newOrderDto.unitprice, newOrderDto.quantity);

        orderRepos.save(o);

        return o.getOrderid();
    }
    public OrderDto getOrder(int orderid) {
        Optional<Order> oo = orderRepos.findById(orderid);
        if (oo.isPresent()) {
            Order o = oo.get();
            OrderDto odto = new OrderDto();
            odto.productname = o.getProductname();
            odto.unitprice = o.getUnitprice();
            odto.quantity = o.getQuantity();
            return odto;
        }
        return null;
    }

    public double getAmount(int orderid) {
        Optional<Order> oo = orderRepos.findById(orderid);
        if (oo.isPresent()) {
            Order o = oo.get();
            return o.calculateAmount();
        }
        return 0;
    }
}

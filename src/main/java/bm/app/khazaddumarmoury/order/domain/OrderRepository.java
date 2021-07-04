package bm.app.khazaddumarmoury.order.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository {
    Order save (Order order);
    List<Order> findAll();
}

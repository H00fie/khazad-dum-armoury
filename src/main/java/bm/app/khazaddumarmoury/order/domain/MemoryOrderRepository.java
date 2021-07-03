package bm.app.khazaddumarmoury.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> hoard = new ConcurrentHashMap<>();
    private final AtomicLong NEXT_ID = new AtomicLong(0L);

    @Override
    public Order save(Order order) {
        if (order.getId() != null) {
            hoard.put(order.getId(), order);
        } else {
            long nextId = nextId();
            order.setId(nextId);
            order.setCreatedAt(LocalDateTime.now());
            hoard.put(nextId, order);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(hoard.values());
    }

    private long nextId() {
        return NEXT_ID.getAndIncrement();
    }
}
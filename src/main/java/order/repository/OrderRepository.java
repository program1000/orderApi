package order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
        public Page<Order> findAll(Pageable pageable);

}

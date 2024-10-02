package order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import order.model.OrderRegel;

public interface OrderRegelRepository extends JpaRepository<OrderRegel, Long> {
    public Page<OrderRegel> findAll(Pageable pageable);

}

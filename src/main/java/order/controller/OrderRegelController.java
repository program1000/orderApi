package order.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import order.exception.OrderNotFoundException;
import order.model.Order;
import order.model.OrderRegel;
import order.repository.OrderRegelRepository;
import order.repository.OrderRepository;

@RestController
public class OrderRegelController {
    
private final String API_ID = "/api/order-regel/{id}";
    
    private final Logger log = LoggerFactory.getLogger( OrderRegelController.class );
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderRegelRepository orderRegelRepository;
    
    /**get all orderregels */
    @GetMapping( "/api/order-regels" )
    public List<OrderRegel> getAllCurrencies( @RequestParam( defaultValue = "0" ) int page, 
                                            @RequestParam( defaultValue = "10" ) int size ) {
        log.info( "Call to Order Api: GET ALL ORDER REGELS, page [{}], size[{}], sort[{}]", page, size );
        PageRequest req = PageRequest.of( page, size );
        
        Page<OrderRegel> pageCurrencies = orderRegelRepository.findAll( req );
        List<OrderRegel> result = pageCurrencies.getContent();
        log.debug( "GET ALL ORDER REGELS result: {}", result );
        return result;
    }
    
    /**create order regel*/
    @PostMapping( "/api/order-regel/{order_id}" )
    public OrderRegel newOrderRegel( @RequestBody OrderRegel newOrderRegel, @PathVariable Long order_id ) {
        log.info( "Call to Rest Api: ADD NEW ORDER REGEL [{} {}]", newOrderRegel.getName(), order_id );
        Optional<Order> optOrder = orderRepository.findById( order_id );
        if (!optOrder.isPresent()) {
            throw new OrderNotFoundException( order_id );
        }
        Order order = optOrder.get();
        newOrderRegel.setOrg_order( order );
        order.getRegels().add( newOrderRegel );
        orderRepository.save( order );
        log.debug( "NEW ORDER REGEL result: {}", newOrderRegel );
        return newOrderRegel;
    }
    
    /**update order regel*/
    @PutMapping( API_ID )
    public OrderRegel updateOrderRegel( @RequestBody OrderRegel newOrderRegel, @PathVariable Long id) {
        log.info( "Call to Rest Api: UPDATE ORDER REGEL [{}]", id );
        return orderRegelRepository.findById( id ).map( orderRegel -> {
            orderRegel.setName( newOrderRegel.getName() );
            orderRegel.setAmount( newOrderRegel.getAmount() );
            orderRegel.setPrice( newOrderRegel.getPrice() );
            log.debug( "UPDATE EXISTING ORDER REGEL result: {}", orderRegel );
            return orderRegelRepository.save( orderRegel );
        } ).orElseThrow( () -> new OrderNotFoundException( id ) );
    }
    
    /**delete order regel*/
    @DeleteMapping( API_ID )
    public void deleteOrderRegel( @PathVariable Long id) {
        log.info( "Call to Rest Api: DELETE ORDER REGEL [{}]", id );
        if ( orderRegelRepository.existsById( id ) == false ) {
            throw new OrderNotFoundException( id );
        }
        
        orderRegelRepository.deleteById(id);
        log.debug( "DELETE ORDER REGEL [{}]", id );
    }


}

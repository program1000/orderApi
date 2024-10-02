package order.controller;

import java.util.List;

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
import order.repository.OrderRepository;

@RestController
public class OrderController {
    
    private final String API_ID = "/api/order/{id}";
    
    private final Logger log = LoggerFactory.getLogger( OrderController.class );
    
    @Autowired
    private OrderRepository repository;

    /**get all orders */
    @GetMapping( "/api/orders" )
    public List<Order> getAllCurrencies(    @RequestParam( defaultValue = "0" ) int page, 
                                            @RequestParam( defaultValue = "10" ) int size ) {
        log.info( "Call to Order Api: GET ALL ORDERS, page [{}], size[{}], sort[{}]", page, size );
        PageRequest req = PageRequest.of( page, size );
        
        Page<Order> pageCurrencies = repository.findAll( req );
        List<Order> result = pageCurrencies.getContent();
        log.debug( "GET ALL CURRENCIES result: {}", result );
        return result;
    }
    
    /**get order */
    @GetMapping( API_ID )
    public Order getOneOrder( @PathVariable Long id ) {
        log.info( "Call to Rest Api: GET ORDER [{}]", id );
        Order result = repository.findById( id ).orElseThrow( () -> new OrderNotFoundException( id ) );
        log.debug( "GET ORDER [{}]: {}", id, result );
        return result;
    }
    
    /**create order*/
    @PostMapping( "/api/order" )
    public Order newOrder( @RequestBody Order newOrder ) {
        log.info( "Call to Rest Api: ADD NEW ORDER [{}]", newOrder.getName() );
        Order result = repository.save( newOrder );
        log.debug( "NEW ORDER result: {}", result );
        return result;
    }
    
    /**Update a order*/
    @PutMapping( API_ID )
    public Order updateOrder( @RequestBody Order newOrder, @PathVariable Long id ) {
        log.info( "Call to Rest Api: UPDATE ORDER [{}]", id );
        return repository.findById( id ).map( order -> {
            order.setCreationDate( newOrder.getCreationDate() );
            order.setLocation( newOrder.getLocation() );
            order.setName( newOrder.getName() );
            order.setStatus( newOrder.getStatus() );
            order.setRegels( newOrder.getRegels());
            log.debug( "UPDATE EXISTING ORDER result: {}", order );
            return repository.save( order );
        } ).orElseThrow( () -> new OrderNotFoundException( id ) );
    }
    
    /**Remove a order*/
    @DeleteMapping( API_ID )
    public void deleteOrder( @PathVariable Long id ) {
        log.info( "Call to Rest Api: DELETE ORDER [{}]", id );
        if ( repository.existsById( id ) == false ) {
            throw new OrderNotFoundException( id );
        }
        repository.deleteById(id);
        log.debug( "DELETE ORDER [{}]", id );
    }

}

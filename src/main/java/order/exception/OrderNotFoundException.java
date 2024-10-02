package order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderNotFoundException  extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger( OrderNotFoundException.class );

    public OrderNotFoundException( Long id ) {
        super( getMessage( id ) );
        log.error( getMessage() );
    }
    
    private static String getMessage( Long id ) {
        return "Could not find order with id [" + id + "]";
    }
}

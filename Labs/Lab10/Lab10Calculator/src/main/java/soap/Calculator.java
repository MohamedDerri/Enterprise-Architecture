package soap;

import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;


@Component
public class Calculator {

    public int add(int x, int y){
        return  x+y;
    }

    public int subtract(int x, int y){
        return x-y;
    }

}




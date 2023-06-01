package soap;

import generated.AddRequest;
import generated.AddResponse;
import generated.SubtractRequest;
import generated.SubtractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorEndpoint {

    @Autowired
    Calculator calculator;

    private static final String NAMESPACE_URL = "http://springtraining/calculator";

    @PayloadRoot(namespace = NAMESPACE_URL,localPart = "AddRequest")
    @ResponsePayload
    public AddResponse add(@RequestPayload AddRequest request){
        AddResponse response = new AddResponse();
        int calc = calculator.add(request.getNumber1(), request.getNumber2());
        response.setResult(calc);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URL,localPart = "SubtractRequest")
    @ResponsePayload
    public SubtractResponse subtract(@RequestPayload SubtractRequest request){
        SubtractResponse response = new SubtractResponse();
        int calc = calculator.subtract(request.getNumber1(), request.getNumber2());
        response.setResult(calc);
        System.out.println("test");
        return response;
    }


}

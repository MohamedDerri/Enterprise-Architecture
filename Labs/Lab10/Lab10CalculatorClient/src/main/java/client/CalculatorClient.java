package client;

import generated.AddRequest;
import generated.AddResponse;
import generated.SubtractRequest;
import generated.SubtractResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CalculatorClient extends WebServiceGatewaySupport {

	public int getResultOfAdd(Inputs inputs){
		AddRequest request = new AddRequest();
		request.setNumber1(inputs.getX());
		request.setNumber2(inputs.getY());

		AddResponse response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		return  response.getResult();
	}


	public int getResultOfSubtraction(Inputs inputs){
		SubtractRequest request = new SubtractRequest();
		request.setNumber1(inputs.getX());
		request.setNumber2(inputs.getY());

		SubtractResponse response = (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		return  response.getResult();
	}

}



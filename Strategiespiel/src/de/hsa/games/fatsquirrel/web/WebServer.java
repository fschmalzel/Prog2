package de.hsa.games.fatsquirrel.web;

import java.io.*;
import javax.xml.ws.*;
import javax.xml.ws.http.*;

import de.hsa.games.fatsquirrel.core.State;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

@WebServiceProvider
@ServiceMode(value = Service.Mode.PAYLOAD)
public class WebServer implements Provider<Source> {

	State state;
	
    public Source invoke(Source request) {
        return  new StreamSource(new StringReader("<p>" + state.getCurrentScore() + "</p>"));
    }
    
    private WebServer(State state) {
    	this.state = state;
    }
    
    public static void start(State state) throws InterruptedException {

        String address = "http://127.0.0.1:8080/";
        Endpoint.create(HTTPBinding.HTTP_BINDING, new WebServer(state)).publish(address);
    }
}

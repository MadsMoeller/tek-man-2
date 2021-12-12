package kea.tek.tekman2.serverToServerHandling;

import kea.tek.tekman2.requestHandling.IncomingRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveRequest {

    @Autowired
    private IncomingRequestHandler requestHandler;

    @RequestMapping(method = RequestMethod.POST, path = "/friendship")
    public ResponseEntity<String> postGreetingRoot(@RequestBody Object req){
        System.out.println("Root request: " + req);
        String request = req.toString().substring(9, req.toString().length()-1);
        System.out.println(request);
        String response = requestHandler.unpackIncomingRequest(request);
        return ResponseEntity.ok(response);
    }
}

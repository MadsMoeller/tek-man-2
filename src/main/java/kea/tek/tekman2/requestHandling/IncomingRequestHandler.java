package kea.tek.tekman2.requestHandling;

import kea.tek.tekman2.models.ForeignUser;
import kea.tek.tekman2.models.Request;
import kea.tek.tekman2.models.User;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IncomingRequestHandler {

    public String unpackIncomingRequest(String request){
        String response;
        Request incomingRequest = new Request();
        try {
            Scanner requestReader = new Scanner(request);
            incomingRequest.setRequestType(requestReader.next());
            incomingRequest.setSource(new ForeignUser(requestReader.next(), requestReader.next()));
            incomingRequest.setDestination(new ForeignUser(requestReader.next(), requestReader.next()));
            String version = requestReader.next();
        }catch (Exception e){
            response = "1 500 Error, command";
            return response;
        }
        switch (incomingRequest.getRequestType()){
            case "Add":
                response = handleAddRequest(incomingRequest);
                break;
            case "Accept":
                response = handleAcceptRequest(incomingRequest);
                break;
            case "Deny":
                response = handleDenyRequest(incomingRequest);
                break;
            case "Remove":
                response = handleRemoveRequest(incomingRequest);
                break;
            case "Block":
                response = handleBlockRequest(incomingRequest);
                break;
            default:
                System.out.println("Method: " + incomingRequest.getRequestType());
                response = "1 501 Error, parameters Method not known";
        }

        return response;
    }

    public String handleAddRequest(Request request){
        String response = "Handling 'Add' request";

        //First: find destination user
        //else: return "user not found"

        //Second: find source user
        //else: create source user

        //Third: see if friend request already exists
        //if so: return "already requested"
        //else: create pending request - return "200 Success"
        return response;
    }

    public String handleAcceptRequest(Request request){
        String response = "Handling 'Accept' request";

        //First: find request with corresponding source and destination users
        //else: return "'Add'-request not found"

        //Second: create friendship, then delete request
        return response;
    }

    public String handleDenyRequest(Request request){
        String response = "Handling 'Deny' request";

        //First: find request with corresponding source and destination users
        //else: return "'Add'-request not found"

        //Second: delete request
        return response;
    }

    public String handleRemoveRequest(Request request){
        String response = "Handling 'Remove' request";

        //First: find friendship with corresponding source and destination users
        //else: return "friendship not found"

        //Second: delete friendship
        return response;
    }

    public String handleBlockRequest(Request request){
        String response = "Handling 'Block' request";

        //First: find destination user
        //else: user not found

        //Second: find source user
        //else: create source user

        //Third: see if blocked contact already exists
        //if so: return "200 Success"
        //else: create blocked contact - return "200 Success"
        return response;
    }
}

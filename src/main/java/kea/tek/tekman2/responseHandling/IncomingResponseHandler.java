package kea.tek.tekman2.responseHandling;

import kea.tek.tekman2.models.Request;
import kea.tek.tekman2.models.Response;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IncomingResponseHandler {
    public void unpackIncomingResponse(String response, Request requestToHandle){
        Response incomingResponse = new Response();
        try{
            Scanner responseReader = new Scanner(response);
            String version = responseReader.next();
            incomingResponse.setStatusCode(responseReader.next());
            incomingResponse.setPhrase(responseReader.nextLine());
        } catch (Exception e){
            System.out.println("Bad response: " + e.getMessage());
        }
        switch (incomingResponse.getStatusCode()){
            case "200":
                System.out.println("Success from foreign server.");
                System.out.println("This is where the local server would perform the update the foreign server was asked to do.");
                System.out.println("This could be a friendship request, which will only update at the local server,");
                System.out.println("when the foreign server has told us the request is okay.");
                break;
            case "500":
                System.out.println("Error 500 from foreign server.");
                System.out.println("Here would be a method to handle such an error.");
                break;
            case "501":
                System.out.println("Error 501 from foreign server.");
                System.out.println("Here would be a method to handle such an error.");
                break;
            case "530":
                System.out.println("Error 530 from foreign server.");
                System.out.println("Here would be a method to handle such an error.");
                break;
            default:
                System.out.println("Unknown response received.");
        }
    }
}

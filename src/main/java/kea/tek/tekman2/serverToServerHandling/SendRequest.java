package kea.tek.tekman2.serverToServerHandling;

import kea.tek.tekman2.models.ForeignUser;
import kea.tek.tekman2.models.Request;
import kea.tek.tekman2.models.User;
import kea.tek.tekman2.responseHandling.IncomingResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
public class SendRequest {
    final String API_GREETING_POST = "http://localhost:9091/friendship";
    final String SOURCE_HOST = "myHost";

    @Autowired
    IncomingResponseHandler responseHandler;

    @PostMapping("/sendGreetingPost")
    public String sendGreeting(@RequestBody Request requestToSend){
        WebClient webClient = WebClient.builder()
                .baseUrl(API_GREETING_POST)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // method srcEmail srcHost dstEmail dstHost ver
        String request =
                "\"" + requestToSend.getRequestType()
                + " " + requestToSend.getSource().getEmail()
                + " " + SOURCE_HOST
                + " " + requestToSend.getDestination().getEmail()
                + " " + ((ForeignUser) requestToSend.getDestination()).getHost()
                + " " + 1
                + "\"";
        String key = "\"request\"";
        System.out.println(key + ":" + request);
        String response = webClient.post()
                .body(Mono.just("{" + key + ":" + request + "}"), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("\nResponse: " + response + "\n");
        responseHandler.unpackIncomingResponse(response, requestToSend);
        System.out.println("\n\n");
        return response;
    }
}

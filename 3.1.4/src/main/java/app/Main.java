package app;

import app.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        String url = "http://91.241.64.178:7081/";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User[]> response1 =
                restTemplate.exchange(
                        url + "api/users",
                        HttpMethod.GET,
                        new HttpEntity(new HttpHeaders()),
                        User[].class);

        HttpHeaders headers = response1.getHeaders();
        String sessionId = headers.getValuesAsList("Set-Cookie").get(0).split(";")[0];
        System.out.println(sessionId);

        HttpHeaders headersReq = new HttpHeaders();
        headersReq.set("Cookie", sessionId);

        Map<String,Object> map = new HashMap<>();
        map.put("id",3);
        map.put("name","James");
        map.put("lastName","Brown");
        map.put("age",(byte) 2);
        HttpEntity entity = new HttpEntity(map, headersReq);
        HttpEntity<String> response2 = restTemplate.exchange(
                url + "api/users",
                HttpMethod.POST,
                entity,
                String.class);
        System.out.println(response2.getBody());

        map.put("id",3);
        map.put("name","Thomas");
        map.put("lastName","Shelby");
        map.put("age",(byte) 2);
        entity = new HttpEntity(map, headersReq);
        HttpEntity<String> response3 = restTemplate.exchange(
                url + "api/users",
                HttpMethod.PUT,
                entity,
                String.class);
        System.out.println(response3.getBody());

        entity = new HttpEntity(headersReq);
        HttpEntity<String> response4 = restTemplate.exchange(
                url + "api/users/3",
                HttpMethod.DELETE,
                entity,
                String.class);
        System.out.println(response4.getBody());
    }
}
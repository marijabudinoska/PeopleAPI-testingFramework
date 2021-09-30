package code.academy.payloads;

import code.academy.model.requests.PostNewPersonRequest;
import org.json.JSONObject;

public class PostNewPersonPayload {

    public PostNewPersonRequest createPersonPayload () {

        return PostNewPersonRequest.builder()
                .name("Marija")
                .surname("Budinoska")
                .age(37)
                .isEmployed(true)
                .location("Skopje")
                .build();
    }

    public JSONObject createNewPersonPayloadAsString(){

        JSONObject personObject = new JSONObject();
        personObject.put("name", "Ema");
        personObject.put("surname","Budinoska");
        personObject.put("age",20);
        personObject.put("isEmployed", "string");
        personObject.put("location","Monako");

        return personObject;
    }
}


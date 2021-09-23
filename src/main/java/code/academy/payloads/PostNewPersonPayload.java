package code.academy.payloads;

import code.academy.model.requests.PostNewPersonRequest;

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
}


package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.responses.PostNewPersonResponse;
import code.academy.payloads.PostNewPersonPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.*;

import static code.academy.utils.ConversionUtils.jsonStringToObject;
import static code.academy.utils.ConversionUtils.objectToJsonString;

public class DeletePersonFeatureTest {

    public DeletePersonFeatureTest() throws Exception {
    }

    PeopleApiClient peopleApiClient = new PeopleApiClient();

    HttpResponse response;

    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();
    String createdPersonId;

    @BeforeClass
    public void beforeClass() throws Exception {

            HttpResponse postResponse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person",
                    objectToJsonString(postNewPersonPayload.createPersonPayload()));

            String postResponseBodyAsString = EntityUtils.toString(postResponse.getEntity());
            PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString, PostNewPersonResponse.class);

            createdPersonId = postNewPersonResponse.getPersonData().getId();
    }
    @BeforeTest
    public void beforeTest() throws Exception {

    }

    @Test
    public void deletePersonTest() throws Exception {

        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + createdPersonId);

        String body = EntityUtils.toString(response.getEntity());
    }

    @AfterTest
    public void afterTest(){

    }
    @AfterClass
    public void afterClass(){

    }
}

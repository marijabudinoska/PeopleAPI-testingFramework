package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import code.academy.model.requests.PostNewPersonRequest;
import code.academy.model.responses.PostNewPersonResponse;
import code.academy.payloads.PostNewPersonPayload;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static code.academy.utils.ConversionUtils.*;
import static org.apache.http.HttpStatus.SC_CREATED;


public class InitialTestFile {

    PeopleApiClient peopleApiClient = new PeopleApiClient();

    HttpResponse response;

    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();

    public InitialTestFile() throws Exception {
    }

    @Test
    public void WelcomeMessageTest() throws Exception {

      response = peopleApiClient.getWelcomeRequest();
      String body = EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(body);

        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals(messageAsString,"Welcome to People API");

          }

    @Test
    public void getSinglePersonTest()throws Exception{

        String expectedMessage = "Person succesfully fetched";

        response = peopleApiClient.getOnePerson();
        String bodyGetOnePerson = EntityUtils.toString(response.getEntity());

        JSONObject bodyAsObject = new JSONObject(bodyGetOnePerson);

        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals(messageAsString, expectedMessage);

    }

    @Test
    public void getPersonNameTest() throws Exception{

        response = peopleApiClient.getOnePerson();

//        cel response sme go pretvorile vo String
        String bodyGetOnePerson = EntityUtils.toString(response.getEntity());
//        cel JSON string sme go pretvorile vo objekt
        JSONObject bodyAsObject = new JSONObject(bodyGetOnePerson);
//        person sme go zemale kako String
        String personAsString = bodyAsObject.get("person").toString();
//        person string go pretvoram vo objekt
        JSONObject personData = new JSONObject(personAsString);
//        get name
        String name = personData.get("name").toString();
        Assert.assertEquals( name, "Marija");

    }

    @Test
    public void getTest() throws Exception{

       response = peopleApiClient.httpGet("https://people-api1.herokuapp.com");
        String body= EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals( messageAsString, "Welcome to People API");

    }

    @Test
    public void deleteTest() throws Exception{
        response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/61475a11734f160004e51285");
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals( messageAsString, "Person with id=61475a11734f160004e51285 has been succesfully deleted");

    }
    @Test
    public void postTest() throws Exception {

        postNewPersonRequest = postNewPersonPayload.createPersonPayload();
        String newPersonPayloadAsString = objectToJsonString(postNewPersonRequest);

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person/", newPersonPayloadAsString);
        String body = EntityUtils.toString(response.getEntity());
        PostNewPersonResponse postNewPersonResponse;
        postNewPersonResponse =jsonStringToObject(body, PostNewPersonResponse.class);

        Assert.assertEquals(postNewPersonResponse.getMessage(), "Person succesfully inserted");
        Assert.assertEquals(postNewPersonResponse.getCode(),"P201");
        Assert.assertEquals(postNewPersonResponse.getPersonData().getName(),"Marija");

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_CREATED);

    }

    @Test
    public void putTest() throws Exception {
        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("location", "Skopje");

        response = peopleApiClient.httpPut("https://people-api1.herokuapp.com/api/person/614b54f7e76e820004c36874", payloadAsObject);
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals( messageAsString, "Person's location succesfully updated !");

    }
    @Test
    public void deletePersonTest() throws Exception {
    HttpResponse postResponse = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person",
            objectToJsonString(postNewPersonPayload.createPersonPayload()));

    String postResponseBodyAsString = EntityUtils.toString(postResponse.getEntity());
    PostNewPersonResponse postNewPersonResponse = jsonStringToObject(postResponseBodyAsString, PostNewPersonResponse.class);

    String createdPersonId = postNewPersonResponse.getPersonData().getId();

    response = peopleApiClient.httpDelete("https://people-api1.herokuapp.com/api/person/" + createdPersonId);

    String body = EntityUtils.toString(response.getEntity());
}
}




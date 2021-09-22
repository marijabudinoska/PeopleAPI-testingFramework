package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;



public class InitialTestFile {

    PeopleApiClient peopleApiClient = new PeopleApiClient();

    HttpResponse response;


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
        JSONObject payloadAsObject = new JSONObject();
        payloadAsObject.put("name", "Marija");
        payloadAsObject.put("surname", "Budinoska");
        payloadAsObject.put("age", 37);
        payloadAsObject.put("isEmployed", true);
        payloadAsObject.put("location", "Skopje");

        response = peopleApiClient.httpPost("https://people-api1.herokuapp.com/api/person/", payloadAsObject);
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals( messageAsString, "Person succesfully inserted");
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
}




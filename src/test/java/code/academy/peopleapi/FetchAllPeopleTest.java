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

public class FetchAllPeopleTest {

    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse response;
    PostNewPersonPayload postNewPersonPayload = new PostNewPersonPayload();
    PostNewPersonRequest postNewPersonRequest = new PostNewPersonRequest();

    public FetchAllPeopleTest() throws Exception {

    }

    @Test
    public void getAllPersonTest()throws Exception {

        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com");
        String body = EntityUtils.toString(response.getEntity());
        JSONObject bodyAsObject = new JSONObject(body);
        String messageAsString = bodyAsObject.get("message").toString();
        Assert.assertEquals(messageAsString, "Welcome to People API");

    }

    }

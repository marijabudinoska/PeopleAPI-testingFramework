package code.academy.peopleapi;
import code.academy.client.PeopleApiClient;
import code.academy.model.responses.GetAllPeopleResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static code.academy.utils.ConversionUtils.jsonStringToObject;
import static org.apache.http.HttpStatus.SC_OK;


public class GetAllPeopleTest {

    HttpResponse response;
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    GetAllPeopleResponse getAllPeopleResponse;


    public GetAllPeopleTest() throws Exception {

    }

    @Test
    public void getAllPeopleTest()throws Exception {
     response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
     String body = EntityUtils.toString(response.getEntity());
     getAllPeopleResponse = jsonStringToObject(body, GetAllPeopleResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(getAllPeopleResponse.getCode(),"P200");
        Assert.assertEquals(getAllPeopleResponse.getMessage(), "List of people successfully fetched");
        Assert.assertNotNull(getAllPeopleResponse.getNumberOfPeople());
        Assert.assertNotNull(getAllPeopleResponse.getPeopleData().size());

    }
    @Test
    public void getNumberOfPeopleTest()throws Exception {
        response = peopleApiClient.httpGet("https://people-api1.herokuapp.com/api/people");
        String body = EntityUtils.toString(response.getEntity());
        getAllPeopleResponse = jsonStringToObject(body, GetAllPeopleResponse.class);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), SC_OK);
        Assert.assertEquals(getAllPeopleResponse.getPeopleData().size(),getAllPeopleResponse.getNumberOfPeople());

    }

}

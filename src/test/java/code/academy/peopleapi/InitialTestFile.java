package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;



public class InitialTestFile {
    PeopleApiClient peopleApiClient = new PeopleApiClient();
    HttpResponse getAllPeople;
    HttpResponse getOnePerson;

    @Test
    public void InitialTest() throws Exception {

      getAllPeople = peopleApiClient.getAllPeople();
      String bodyAllPeople = EntityUtils.toString(getAllPeople.getEntity());

      getOnePerson = peopleApiClient.getOnePerson();
      String bodyOnePerson = EntityUtils.toString(getOnePerson.getEntity());
    }

}

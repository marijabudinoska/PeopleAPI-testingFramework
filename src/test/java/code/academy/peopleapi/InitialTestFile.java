package code.academy.peopleapi;

import code.academy.client.PeopleApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;



public class InitialTestFile {

    PeopleApiClient peopleApiClient = new PeopleApiClient();

    HttpResponse deleteOnePerson;
    HttpResponse postOnePerson;
    HttpResponse putLocation;


    @Test
    public void InitialTest() throws Exception {

      deleteOnePerson = peopleApiClient.deleteOnePerson();
      String bodyDeleteOnePerson = EntityUtils.toString(deleteOnePerson.getEntity());

      postOnePerson = peopleApiClient.postOnePerson();
      String bodyPostOnePerson = EntityUtils.toString(postOnePerson.getEntity());

      putLocation = peopleApiClient.putLocation();
      String bodyPutLocation = EntityUtils.toString(putLocation.getEntity());
          }
}



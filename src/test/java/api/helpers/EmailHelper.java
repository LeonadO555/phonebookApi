package api.helpers;

import api.email.EmailsApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class EmailHelper extends EmailsApi {

    public void deleteEmail(Integer emailId) {
        deleteExistingEmail(200, emailId);
        Response errorMessage = getEmail(500, emailId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");
    }

}

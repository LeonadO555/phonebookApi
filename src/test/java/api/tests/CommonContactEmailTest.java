package api.tests;

import api.email.EmailsApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactEmailTest extends EmailsApi {

    @Test
    public void createEditDeleteContactEmailTest() {
        createEmail(201);
        Response response = getAllEmails(200);
        int emailId = response.jsonPath().getInt("[0].id");
        String email = response.jsonPath().getString("[0].email");
        Assert.assertEquals(email, randomDataBodyForCreateEmail().getEmail(), "Created emails is not equals");

        editExistingEmail(200, emailId);
        Response editedEmails = getAllEmails(200);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, randomDataBodyForEditEmail(emailId).getEmail(), "Edited emails is not equals");

        deleteExistingEmail(200, emailId);
        Response errorMessage = getEmail(500, emailId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

    }
}

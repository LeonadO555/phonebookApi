package api.tests;

import api.email.EmailsApi;
import api.helpers.EmailHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactEmailTest {
    EmailsApi emailsApi = new EmailsApi();
    EmailHelper emailHelper = new EmailHelper();
    int contactId = 4909;

    @Test
    public void createEditDeleteContactEmailTest() {
        emailsApi.createEmail(201, contactId);
        Response response = emailsApi.getAllEmails(200, contactId);
        int emailId = response.jsonPath().getInt("[0].id");
        String email = response.jsonPath().getString("[0].email");
        Assert.assertEquals(email, emailsApi.randomDataBodyForCreateEmail(contactId).getEmail(), "Created emails is not equals");

        emailsApi.editExistingEmail(200, emailId, contactId);
        Response editedEmails = emailsApi.getAllEmails(200, contactId);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, emailsApi.randomDataBodyForEditEmail(emailId, contactId).getEmail(), "Edited emails is not equals");

        emailHelper.deleteEmail(emailId);
    }
}

package api.tests;

import api.contact.ContactApi;
import api.email.EmailsApi;
import api.helpers.EmailHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {
    ContactApi contactApi = new ContactApi();
    EmailsApi emailsApi = new EmailsApi();
    EmailHelper emailHelper = new EmailHelper();

    @Test
    public void createEmailForNewContact() {
        Response newContactResponse = contactApi.createContact(201);
        Integer contactId = newContactResponse.jsonPath().getInt("id");
        emailsApi.createEmail(201, contactId);
        Response newEmailResponse = emailsApi.getAllEmails(200, contactId);
        int emailId = newEmailResponse.jsonPath().getInt("[0].id");
        emailsApi.editExistingEmail(200, emailId, contactId);

        emailHelper.deleteEmail(emailId);

        contactApi.deleteExistingContact(200, contactId);
        Response actualDeletedResponse = contactApi.getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}

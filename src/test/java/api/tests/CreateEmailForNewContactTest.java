package api.tests;

import api.helpers.ContactHelper;
import api.helpers.EmailHelper;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {
    EmailHelper emailHelper = new EmailHelper();
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEmailForNewContact() {
        Integer contactId = contactHelper.createContact();
        // Work with Email
        Integer emailId = emailHelper.createEmail(contactId);
        emailHelper.editEmail(emailId, contactId);
        emailHelper.deleteEmail(emailId);

        contactHelper.deleteContact(contactId);
    }
}

package api.tests;

import api.helpers.EmailHelper;
import org.testng.annotations.Test;

public class CommonContactEmailTest {
    EmailHelper emailHelper = new EmailHelper();
    int contactId = 4909;

    @Test
    public void createEditDeleteContactEmailTest() {
        Integer emailId = emailHelper.createEmail(contactId);
        emailHelper.editEmail(emailId, contactId);
        emailHelper.deleteEmail(emailId);
    }
}

package api.email;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.email.EmailDto;

public class EmailsApi extends ApiBase {

    Response response;
    EmailDto dto;

    public EmailDto randomDataBodyForCreateEmail(Integer contactId) {
        dto = new EmailDto();
        dto.setEmail("leo@gmail.com");
        dto.setContactId(contactId);
        return dto;
    }

    public EmailDto randomDataBodyForEditEmail(Integer emailId, Integer contactId) {
        dto = new EmailDto();
        dto.setEmail("leo123@gmail.com");
        dto.setContactId(contactId);
        dto.setId(emailId);
        return dto;
    }

    public void createEmail(Integer code, Integer contactId) {
        String endpoint = "/api/email";
        postRequest(endpoint, code, randomDataBodyForCreateEmail(contactId));
    }

    public void editExistingEmail(Integer code, Integer emailId, Integer contactId) {
        String endpoint = "/api/email";
        putRequest(endpoint, code, randomDataBodyForEditEmail(emailId, contactId));
    }

    public void deleteExistingEmail(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        deleteRequest(endpoint, code, emailId);
    }

    public Response getAllEmails(Integer code, Integer contactId) {
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "contactId", contactId);
        return response;
    }

    public Response getEmail(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint, code, "id", emailId);
        return response;
    }
}

package api.email;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.email.EmailDto;

public class EmailsApi extends ApiBase {

    Response response;
    EmailDto dto;

    public EmailDto randomDataBodyForCreateEmail() {
        dto = new EmailDto();
        dto.setEmail("leo@gmail.com");
        dto.setContactId(4909);
        return dto;
    }

    public EmailDto randomDataBodyForEditEmail(Integer emailId) {
        dto = new EmailDto();
        dto.setEmail("leo123@gmail.com");
        dto.setContactId(4909);
        dto.setId(emailId);
        return dto;
    }

    public void createEmail(Integer code) {
        String endpoint = "/api/email";
        postRequest(endpoint, code, randomDataBodyForCreateEmail());
    }

    public void editExistingEmail(Integer code, Integer emailId) {
        String endpoint = "/api/email";
        putRequest(endpoint, code, randomDataBodyForEditEmail(emailId));
    }

    public void deleteExistingEmail(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        deleteRequest(endpoint, code, emailId);
    }

    public Response getAllEmails(Integer code) {
        String endpoint = "/api/email/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "contactId", 4909);
        return response;
    }

    public Response getEmail(Integer code, int emailId) {
        String endpoint = "/api/email/{id}";
        response = getRequestWithParam(endpoint, code, "id", emailId);
        return response;
    }

//    public Response getAllEmails(Integer code) {
//        String endpoint = "/api/email/4909/all";
//        response = getRequest(endpoint, code);
//        return response;
//    }

}

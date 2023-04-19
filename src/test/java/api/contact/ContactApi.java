package api.contact;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.contact.ContactDto;

public class ContactApi extends ApiBase {
    Response response;
    ContactDto dto;
    Faker faker = new Faker();


    public ContactDto randomDataBodyForCreateContact() {
        dto = new ContactDto();
        dto.setFirstName(faker.internet().uuid());
        dto.setLastName(faker.internet().uuid());
        dto.setDescription(faker.internet().uuid());
        return dto;
    }

    public ContactDto randomDataBodyForEditContact(Integer contactId) {
        dto = new ContactDto();
        dto.setId(contactId);
        dto.setFirstName("leo");
        dto.setLastName("Mikhailov");
        dto.setDescription("I am");
        return dto;
    }

    public Response createContact(Integer code) {
        String endpoint = "/api/contact";
        response = postRequest(endpoint, code, randomDataBodyForCreateContact());
        return response;
    }

    public void editExistingContact(Integer code, Integer contactId) {
        String endpoint = "/api/contact";
        putRequest(endpoint, code, randomDataBodyForEditContact(contactId));
    }

    public void deleteExistingContact(Integer code, int contactId) {
        String endpoint = "/api/contact/{id}";
        deleteRequest(endpoint, code, contactId);
    }

    public Response getContact(Integer code, int contactId) {
        String endpoint = "/api/contact/{id}";
        response = getRequestWithParam(endpoint, code, contactId);
        return response;
    }
}

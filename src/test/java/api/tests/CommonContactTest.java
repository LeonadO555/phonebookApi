package api.tests;

import api.contact.ContactApi;
import api.helpers.ContactHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEditDeleteNewContact() {
        // создаем контакт , записываем ответ после создания контакта
        Integer contactId = contactHelper.createContact();
        // Изменяем данные контакта , но данный эндпоинт не имеет ответа (см. сваггер)
        editExistingContact(200, contactId);
        //Получаем измененный(отредактированный) контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (это те данные которые мы вытащили с помощью запрос GET), с данными которые у нас находятся в коде и мы вытскиваем его геттером пример (randomDataBodyForEditContact(contactId).getFirstName())
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

        contactHelper.deleteContact(contactId);
    }
}

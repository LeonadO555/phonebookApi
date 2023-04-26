package api.tests;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {

    @Test
    public void createEditDeleteNewContact() {
        // создаем контакт , записываем ответ после создания контакта
        Response actualResponse = createContact(201);
        // Из ответа вытаскиваем id для того что бы использовать данное id для получения данных(get запрос)
        int contactId = actualResponse.jsonPath().getInt("id");
        // Получаем данные по созданному контакту
        Response expectedResponse = getContact(200, contactId);
        //Проверка!! Сравниваем ответ эндпоинта по созданию контакта с ответом эндпоинта который получает контакт
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description contact not equal");

        // Изменяем данные контакта , но данный эндпоинт не имеет ответа (см. сваггер)
        editExistingContact(200, contactId);
        //Получаем измененный(отредактированный) контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (это те данные которые мы вытащили с помощью запрос GET), с данными которые у нас находятся в коде и мы вытскиваем его геттером пример (randomDataBodyForEditContact(contactId).getFirstName())
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

        deleteExistingContact(200, contactId);
        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Category;
import models.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;



public class ExampleGETRequest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINT = "/pet";
    Pet pet;
    int id = 249194;
    String name = "Mocha";
    String status = "available";
    int categoryId = 1;
    String categoryName = "dog";


    @BeforeClass()
    public static void SetUp() {
        RestAssured.baseURI = BASE_URL;    }

    @Test(description = "Create Pet")
    public void PostPetTest() {
        String url = PET_ENDPOINT;

        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");

        String contentType = ContentType.JSON.toString();

        // Given
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        // Given Pet
        Pet newPet = Pet.builder()
                .id(id)
                .name(name)
                .status(status)
                .category(category)
                .build();

        // When
        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(header)
                .body(newPet)
                .when().log().all()
                .post(url);

        response.then().log().all()
                .statusCode(200)
                .body("name", equalTo("Mocha"));

    }
    }
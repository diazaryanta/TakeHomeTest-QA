package api.tests;

import api.base.BaseAPITest;
import api.services.UserService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Listeners(utils.TestListenerAPI.class)
public class UserTest extends BaseAPITest {

    private int createdUserId;

    @Test(description = "1. GET Users", priority = 1)
    public void testGetUsers() {
        Response response = UserService.getUsers(baseRequestSpec, 2);

        Assert.assertEquals(response.statusCode(), 200, "Status code harus 200");
        response.then().assertThat().body("page", equalTo(2));
    }

    @Test(description = "2. GET Users by ID", priority = 2)
    public void testGetUserById() {
        int targetId = 2;
        Response response = UserService.getUserById(baseRequestSpec, targetId);

        Assert.assertEquals(response.statusCode(), 200, "Status code harus 200");
        response.then().assertThat().body("data.id", equalTo(targetId));
    }

    @Test(description = "3. POST Users", priority = 3)
    public void testPostUser() {
        String payload = "{\n" +
                "    \"email\": \"diaz\",\n" +
                "    \"password\": \"Test123\"\n" +
                "}";

        Response response = UserService.createUser(baseRequestSpec, payload);
        Assert.assertEquals(response.statusCode(), 201, "Status code untuk Create harus 201");
        createdUserId = Integer.parseInt(response.jsonPath().getString("id"));
        response.then().assertThat().body("email", equalTo("diaz"));
    }

    @Test(description = "4. PUT Users", priority = 4, dependsOnMethods = "testPostUser")
    public void testPutUser() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = UserService.updateUser(baseRequestSpec, createdUserId, payload);

        Assert.assertEquals(response.statusCode(), 200, "Status code untuk Update harus 200");
        response.then().assertThat().body("job", equalTo("zion resident"));
    }

    @Test(description = "5. DELETE Users", priority = 5, dependsOnMethods = "testPutUser")
    public void testDeleteUser() {
        Response response = UserService.deleteUser(baseRequestSpec, createdUserId);
        Assert.assertEquals(response.statusCode(), 204, "Status code untuk Delete harus 204");
    }
}
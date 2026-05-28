package api.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UserService {
    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_BY_ID_ENDPOINT = "/users/{id}";

    // 1. GET Users (List)
    public static Response getUsers(RequestSpecification spec, int page) {
        return given().spec(spec).queryParam("page", page).when().get(USERS_ENDPOINT);
    }

    // 2. GET Users by ID
    public static Response getUserById(RequestSpecification spec, int id) {
        return given().spec(spec).pathParam("id", id).when().get(USER_BY_ID_ENDPOINT);
    }

    // 3. POST Users (Create)
    public static Response createUser(RequestSpecification spec, String payload) {
        return given().spec(spec).body(payload).when().post(USERS_ENDPOINT);
    }

    // 4. PUT Users (Update)
    public static Response updateUser(RequestSpecification spec, int id, String payload) {
        return given().spec(spec).pathParam("id", id).body(payload).when().put(USER_BY_ID_ENDPOINT);
    }

    // 5. DELETE Users
    public static Response deleteUser(RequestSpecification spec, int id) {
        return given().spec(spec).pathParam("id", id).when().delete(USER_BY_ID_ENDPOINT);
    }
}
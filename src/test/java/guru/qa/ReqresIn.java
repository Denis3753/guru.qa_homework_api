package guru.qa;

import guru.qa.entities.User;
import guru.qa.entities.Registration;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresIn extends Config {

    @Test
    public void registrationSuccessful() {

        Registration registration = new Registration("eve.holt@reqres.in", "pistol");

        given()
                .log().all()
                .body(registration)
                .when().post("/api/register")
                .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void registrationUnSuccessful() {

        Registration registration = new Registration("eve.holt@reqres.in", null);

        given()
                .log().all()
                .body(registration)
                .when().post("/api/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    public void getUser() {

        given()
                .log().all()
                .when().get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void createUser() {

        User user = new User("Иван", "Программист");

        given()
                .log().all()
                .body(user)
                .when().post("/api/users")
                .then()
                .log().all()
                .body("name", equalTo("Иван"))
                .body("job", equalTo("Программист"));
    }


    @Test
    public void changeUser() {

        User user = new User("Иван", "SQL-разработчик");

        given()
                .log().all()
                .body(user)
                .when().put("/api/users/525")
                .then()
                .statusCode(200)
                .body("name", equalTo("Иван"))
                .body("job", equalTo("SQL-разработчик"));
    }

}

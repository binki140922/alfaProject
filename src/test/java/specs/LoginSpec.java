package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static basetest.BaseTestAPI.API_CONFIG;
import static io.restassured.RestAssured.with;
import static helpers.CustomAllureListener.withCustomTemplates;

public class LoginSpec {

    public static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(ContentType.JSON)
            .baseUri(API_CONFIG.getBaseUrl())
            .basePath(API_CONFIG.getPatch());

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();
}

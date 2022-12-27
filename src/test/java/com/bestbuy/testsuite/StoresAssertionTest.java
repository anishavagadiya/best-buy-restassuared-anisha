package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;

public class StoresAssertionTest {
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1. Verify the if the total is equal to 1561
    @Test
    public void verifyTheTotal() {
        response.body("total", equalTo(1561));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void verifyLimitOfStore() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void singleNameInArrayList() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void checkMultipleNames() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void verifyStoreId() {
        response.body("data[2].services[2].storeservices.storeId", equalTo(7));
    }

    //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void checkCreatedAt() {
        response.body("data[2].services.storeservices", hasItem(hasKey("createdAt")));
    }

    //7. Verify the state = MN of forth store
    @Test
    public void verifyTheState() {

    }

    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreName() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreIdForSixthStore() {
        response.body("data[5].services[1].storeservices.storeId", equalTo(11));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceId() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }

}

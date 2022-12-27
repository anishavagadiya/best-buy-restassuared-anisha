package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
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

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of storeName is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listOfStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of storeName is : " + listOfStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> storeId = response.extract().path("data.id");
        storeId.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of size is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud.
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Value of store: " + values);
        System.out.println("------------------End of Test---------------------------");

    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of store: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String, ?>> services = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("services of store: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<String> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All storeservices: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> storeId = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All storeId is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Id is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> ND = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the store names Where state = ND : " + ND);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<List<Integer>> totalServices = response.extract().path("data.findAll{it.name =='Rochester'}.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services are: " + totalServices.get(0).size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015() {
        List<?> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the createdAt for all services whose name = 'Windows Store' : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> name = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of services are: " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test0017() {
        List<String> zipStore = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the zip of all the store : " + zipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test0018() {
        List<String> zipStore = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville : " + zipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test0019() {
        List<HashMap<String, ?>> serviceName = response.extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services details of the service name is Magnolia Home Theater : " + serviceName);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test0020() {
        List<Integer> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the lat of all the stores : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
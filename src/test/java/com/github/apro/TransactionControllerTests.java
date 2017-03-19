package com.github.apro;

import com.github.apro.transactions.TransactService;
import com.github.apro.transactions.Transaction;
import com.github.apro.transactions.TransactionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

/**
 * Created by achoudh on 19/03/2017.
 */

@RunWith(SpringRunner.class)
public class TransactionControllerTests {

    @MockBean
    private TransactService service;

    @Test
    public void test_post_transaction() {
        given()
                .standaloneSetup(new TransactionController(service))
                .body(new Transaction(12.3, Instant.now().toEpochMilli())).
                when()
                .post("/transactions").
                then()
                .statusCode(201);
    }
}

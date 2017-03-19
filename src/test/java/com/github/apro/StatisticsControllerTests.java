package com.github.apro;

import com.github.apro.statistics.StatisticService;
import com.github.apro.statistics.StatisticsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by achoudh on 19/03/2017.
 */

@RunWith(SpringRunner.class)
public class StatisticsControllerTests {

    @MockBean
    private StatisticService service;

    @Test
    public void test_statistics() {
        given().
                standaloneSetup(new StatisticsController(service)).
                when().
                async().
                get("/statistics").
                then().
                statusCode(200).
                content("count", equalTo(0));
    }
}

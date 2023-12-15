package com.github.whatasame.gitsubmodule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
class ProductionConfigurationTest {

    @Autowired Environment environment;

    @Test
    @DisplayName("서브 모듈의 운영용 프로퍼티를 가져온다.")
    void importProductionProperty() {
        /* given */
        final String prodProblem = "Why couldn't the bicycle stand up by itself?";
        final String prodAnswer = "It was two tired.";
        final Double prodRate = 3.7;

        /* when */
        final String problem = environment.getProperty("secret.problem");
        final String answer = environment.getProperty("secret.answer");
        final Double rate = environment.getProperty("secret.rate", Double.class);

        /* then */
        assertAll(
                () -> assertThat(problem).isEqualTo(prodProblem),
                () -> assertThat(answer).isEqualTo(prodAnswer),
                () -> assertThat(rate).isEqualTo(prodRate));
    }
}

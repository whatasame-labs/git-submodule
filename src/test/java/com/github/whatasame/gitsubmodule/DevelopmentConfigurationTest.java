package com.github.whatasame.gitsubmodule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class DevelopmentConfigurationTest {

    @Autowired Environment environment;

    @Test
    @DisplayName("서브 모듈의 개발용 프로퍼티를 가져온다.")
    void importDevelopmentProperty() {
        /* given */
        final String devProblem = "What did Baby Corn say to Mama Corn?";
        final String devAnswer = "Where's Pop Corn?";
        final Double devRate = 4.3;

        /* when */
        final String problem = environment.getProperty("secret.problem");
        final String answer = environment.getProperty("secret.answer");
        final Double rate = environment.getProperty("secret.rate", Double.class);

        /* then */
        assertAll(
                () -> assertThat(problem).isEqualTo(devProblem),
                () -> assertThat(answer).isEqualTo(devAnswer),
                () -> assertThat(rate).isEqualTo(devRate));
    }
}

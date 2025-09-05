package com.proelectricos.mdserp;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Connection;

@SpringBootTest
class MdserpApplicationTests {

        @Autowired
        private DataSource dataSource;

        @Test
        public void testDatabaseConnection() throws Exception {
            try (Connection connection = dataSource.getConnection()) {
                assertThat(connection).isNotNull();
                assertThat(connection.isValid(1)).isTrue(); // Check if the connection is valid
            }
        }
    }



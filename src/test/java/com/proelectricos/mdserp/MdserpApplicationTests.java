package com.proelectricos.mdserp;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.proelectricos.mdserp.config.PersistenceSqlFactoryAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
class MdserpApplicationTests {

    @Qualifier("sqlFactoryDataSource")
    @Autowired
    private DataSource sqlFactoryDataSource;

    @Qualifier("pdmDataSource")
    @Autowired
    private DataSource pdmDataSource;

        @Test
        public void testSqlFactoryConnection() throws Exception {
            try (Connection connection = sqlFactoryDataSource.getConnection()) {
                assertThat(connection).isNotNull();
                assertThat(connection.isValid(1)).isTrue(); // Check if the connection is valid
            }
        }


    @Test
    public void tesPdmConnection() throws Exception {
        try (Connection connection = pdmDataSource.getConnection()) {
            assertThat(connection).isNotNull();
            assertThat(connection.isValid(1)).isTrue(); // Check if the connection is valid
        }
    }
    }



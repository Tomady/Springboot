package com.travelmaker.persistence;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
public class DataSourceTests {

    @Setter(onMethod_ = @Autowired)
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try(Connection con = dataSource.getConnection()) {
            log.info(String.valueOf(con));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}

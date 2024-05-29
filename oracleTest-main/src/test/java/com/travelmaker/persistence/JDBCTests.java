package com.travelmaker.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class JDBCTests {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try(Connection con =
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@travelmaker_high?TNS_ADMIN=E:/yellowKim/Wallet_travelmaker",
                        "ADMIN",
                        "Travelmaker1"
                )
        ) {
            log.info(String.valueOf(con));
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
}

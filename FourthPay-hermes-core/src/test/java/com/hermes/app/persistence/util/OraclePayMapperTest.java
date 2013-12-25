package com.hermes.app.persistence.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 *
 * @author of644
 */
public abstract class OraclePayMapperTest extends BaseMapperTest{

    @Autowired
    private DataSource oraclePayDataSource;

    public DataSource getDataSource() {
        return oraclePayDataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.oraclePayDataSource = dataSource;
    }

}

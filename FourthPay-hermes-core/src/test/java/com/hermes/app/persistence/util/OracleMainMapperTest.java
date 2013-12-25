package com.hermes.app.persistence.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 *
 * @author of644
 */
public abstract class OracleMainMapperTest extends BaseMapperTest{

    @Autowired
    private DataSource oracleMainDataSource;

    public DataSource getDataSource() {
        return oracleMainDataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.oracleMainDataSource = dataSource;
    }

}

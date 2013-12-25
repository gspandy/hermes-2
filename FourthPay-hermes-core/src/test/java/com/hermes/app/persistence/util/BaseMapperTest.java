package com.hermes.app.persistence.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;

@ContextConfiguration(
        locations={
                "classpath:/applicationContext-unit-resources.xml",
                "classpath:/applicationContext-unit-dao.xml",
                "classpath*:/applicationContext-unit.xml"
        })
@TestExecutionListeners(listeners = { DcExecutionListener.class })
@TransactionConfiguration(transactionManager = "oracleMainTransactionManager", defaultRollback = true)
public abstract class BaseMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
    public abstract DataSource getDataSource();
    public abstract String getDataSetFileUrl();
}

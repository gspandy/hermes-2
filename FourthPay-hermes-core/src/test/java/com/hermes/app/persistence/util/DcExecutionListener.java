package com.hermes.app.persistence.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;


public class DcExecutionListener extends TransactionalTestExecutionListener {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    private DataSource dataSource;

    BaseMapperTest baseMapperTest;
    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);
        baseMapperTest = (BaseMapperTest) testContext.getTestInstance();
        dataSource = baseMapperTest.getDataSource();
        DbUnitUtils.loadData(dataSource, baseMapperTest.getDataSetFileUrl());
        logger.info("将要执行测试方法{}", testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        super.afterTestMethod(testContext);
        logger.info("执行了测试方法{}", testContext.getTestMethod().getName());
        DataSourceUtils.releaseConnection(dataSource.getConnection(), dataSource);
        logger.info("已经执行完测试方法");
    }
}

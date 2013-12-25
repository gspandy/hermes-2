package com.hermes.app.createdata;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.core.io.ClassPathResource;
import org.unitils.core.Unitils;
import org.unitils.dbunit.DbUnitModule;

import java.io.*;

public class ExportData {

    private static final String DATABASE_SCHEMA_NAMES = "database.schemaNames";

    protected IDatabaseConnection fetchConnection() {
        DbUnitModule dm = Unitils.getInstance().getModulesRepository().getModuleOfType(DbUnitModule.class);
        String temp = Unitils.getInstance().getConfiguration().getProperty(DATABASE_SCHEMA_NAMES);
        String[] schema = temp.split(",");

        return dm.getDbUnitDatabaseConnection(schema[0]);
    }

    public boolean export(String tableName) {
        try {
            File file = createDestFile(tableName);
            IDatabaseConnection conn = fetchConnection();
            IDataSet databaseDataSet = conn.createDataSet(new String[] { tableName });
            FlatXmlDataSet.write(databaseDataSet, new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private File createDestFile(String tableName) throws IOException {
        String uri = new ClassPathResource("").getURI() + tableName + ".xml";
        if (uri.startsWith("file:"));
        {
            // 去掉file前缀
            uri = uri.substring(6);
            // 修改类路径到src的源代码上
            uri = uri.replace("/target/test-classes", "/src/test/resources");
        }
        System.out.println(uri);
        File file = new File(uri);
        return file;
    }

    public static void main(String[] args) {

        String tableName = "";
        BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            stdout.write("请输入表名(多表名以逗号分割)：\n");
            stdout.flush();
            tableName = stdin.readLine();
            if ("".equals(tableName)) {
                System.out.println("表名不能为空");
            } else {
                ExportData exportData = new ExportData();
                String[] table = tableName.split(",");

                for (int i = 0; i < table.length; i++) {

                    if (exportData.export(table[i].trim().toUpperCase())) {
                        System.out.println(table[i].toUpperCase().trim() + ".xml文件创建成功！");
                    }
                }
            }
        } catch (IOException ie) {

        } finally {
            try {
                stdin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stdout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

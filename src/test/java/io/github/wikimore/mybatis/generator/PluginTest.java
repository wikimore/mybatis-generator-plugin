package io.github.wikimore.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ted Wang
 */
public class PluginTest {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("generator-config.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(inputStream);
        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        ProgressCallback progressCallback = new VerboseProgressCallback();
        myBatisGenerator.generate(progressCallback);
    }
}

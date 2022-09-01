# mybatis代码生成插件

目前支持分页和分表代码生成。

### 使用说明

先使用mybatis-generator的maven插件，具体[见](http://www.mybatis.org/generator/running/runningWithMaven.html)。

#### Maven增加插件依赖

```xml
<plugin>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.4.1</version>
	<executions>
	<execution>
	  <id>Generate MyBatis Artifacts</id>
	  <goals>
	    <goal>generate</goal>
	  </goals>
	</execution>
	</executions>
	<configuration>
	<configurationFile>src/main/mybatis/config.xml</configurationFile>
	<overwrite>true</overwrite>
	</configuration>
	<dependencies>
	<dependency>
	  <groupId>com.wikimore</groupId>
	  <artifactId>mybatis-generator-plugin</artifactId>
	  <version>0.0.1</version>
	</dependency>
	</dependencies>
</plugin>
```

#### 增加分页插件

配置如下即可

```xml
<plugin type="com.wikimore.mybatis.plugin.MysqlPagingPlugin"></plugin>
```
#### 增加分表插件

配置如下即可

```xml
<plugin type="com.wikimore.mybatis.plugin.MysqlTableShardPlugin"></plugin>
```

### 具体示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>

  <classPathEntry
    location="${jdbc driver path}" />

  <context id="mgp-example" targetRuntime="MyBatis3">

    <plugin type="com.wikimore.mybatis.plugin.MysqlTableShardPlugin"></plugin>
    <plugin type="com.wikimore.mybatis.plugin.MysqlPagingPlugin"></plugin>
    <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin"></plugin>
    <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"></plugin>
    <plugin type="org.mybatis.generator.plugins.FluentBuilderMethodsPlugin"></plugin>
    <plugin type="org.mybatis.generator.plugins.MapperConfigPlugin">
      <property name="targetPackage" value="mybatis"></property>
      <property name="targetProject" value="src/main/resources"></property>
      <property name="fileName" value="mybatis-config.xml"></property>
    </plugin>
    <plugin type="org.mybatis.generator.plugins.ToStringPlugin"></plugin>

    <jdbcConnection connectionURL="jdbc:mysql://127.0.0.1:3306"
      driverClass="com.mysql.cj.jdbc.Driver" password="password" userId="" />

    <javaTypeResolver>
      <property name="forceBigDecimals" value="false" />
    </javaTypeResolver>

    <javaModelGenerator targetPackage="com.example.model"
      targetProject="src/main/java">
      <property name="trimStrings" value="true" />
    </javaModelGenerator>

    <sqlMapGenerator targetPackage="mybatis"
      targetProject="src/main/resources" />

    <javaClientGenerator targetPackage="com.example.dao"
      targetProject="src/main/java" type="XMLMAPPER">
    </javaClientGenerator>

    <table schema="mgp" tableName="tb_gift" domainObjectName="Gift">
      <property name="runtimeSchema" value="gift" />
    </table>
  </context>
</generatorConfiguration>
```


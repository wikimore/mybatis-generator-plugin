package com.wikimore.mybatis.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 增加分表逻辑代码的插件
 *
 * @author Ted Wang
 */
public class MysqlTableShardPlugin extends PluginAdapter {

  public boolean validate(List<String> paramList) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    topLevelClass.setSuperClass(new FullyQualifiedJavaType("BaseModel"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.wikimore.mybatis.model.BaseModel"));
    return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
  }

  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    FullyQualifiedJavaType shardType = new FullyQualifiedJavaType("Shard");

    // shard field
    Field shardField = new Field("shard", shardType);
    shardField.setVisibility(JavaVisibility.PROTECTED);
    introspectedTable.getContext().getCommentGenerator().addFieldComment(shardField, introspectedTable);
    topLevelClass.addField(shardField);

    // get shard method
    Method getShardMethod = new Method("getShard");
    getShardMethod.setReturnType(shardType);
    getShardMethod.addBodyLine("return this.shard;");
    getShardMethod.setVisibility(JavaVisibility.PUBLIC);
    introspectedTable.getContext().getCommentGenerator().addGeneralMethodComment(getShardMethod, introspectedTable);
    topLevelClass.addMethod(getShardMethod);

    // set shard method
    Method setShardMethod = new Method("setShard");
    setShardMethod.addParameter(new Parameter(shardType, "shard"));
    setShardMethod.addBodyLine("this.shard = shard;");
    setShardMethod.setVisibility(JavaVisibility.PUBLIC);
    introspectedTable.getContext().getCommentGenerator().addGeneralMethodComment(setShardMethod, introspectedTable);
    topLevelClass.addMethod(setShardMethod);

    topLevelClass.addImportedType(new FullyQualifiedJavaType("com.wikimore.mybatis.shard.Shard"));
    return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
  }

  @Override
  public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
    return super.sqlMapDocumentGenerated(document, introspectedTable);
  }

  @Override
  public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    return super.sqlMapExampleWhereClauseElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
    return super.sqlMapGenerated(sqlMap, introspectedTable);
  }

  @Override
  public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapInsertElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapSelectAllElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addUpdateShardElement(element, introspectedTable);
    return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addUpdateShardElement(element, introspectedTable);
    return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addUpdateShardElement(element, introspectedTable);
    return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addShardElement(element, introspectedTable);
    return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
  }

  private void addShardElement(XmlElement element, IntrospectedTable introspectedTable) {
    List<VisitableElement> currentElements = new ArrayList<VisitableElement>(element.getElements());
    element.getElements().clear();
    for (VisitableElement e : currentElements) {
      if (e instanceof TextElement) {
        TextElement textElement = (TextElement) e;
        String content = textElement.getContent()
                .replace(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(),
                         introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + "${shard.tableSuffix}");
        element.getElements().add(new TextElement(content));
      } else {
        element.getElements().add(e);
      }
    }
  }

  private void addUpdateShardElement(XmlElement element, IntrospectedTable introspectedTable) {
    List<VisitableElement> currentElements = new ArrayList<VisitableElement>(element.getElements());
    element.getElements().clear();
    for (VisitableElement e : currentElements) {
      if (e instanceof TextElement) {
        TextElement textElement = (TextElement) e;
        String content = textElement.getContent()
                .replace(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(),
                         introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + "${example.shard.tableSuffix}");
        element.getElements().add(new TextElement(content));
      } else {
        element.getElements().add(e);
      }
    }
  }
}

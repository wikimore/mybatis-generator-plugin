package io.github.wikimore.mybatis.generator.plugin;

import io.github.wikimore.mybatis.generator.util.Const;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * MySQL分页代码生成插件
 *
 * @author Ted Wang
 */
public class MysqlPagingPlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    FullyQualifiedJavaType type = new FullyQualifiedJavaType("Limit");

    Field limit = new Field("limit", type);
    limit.setVisibility(JavaVisibility.PROTECTED);
    introspectedTable.getContext().getCommentGenerator().addFieldComment(limit, introspectedTable);
    topLevelClass.addField(limit);

    Method setLimit = new Method("setLimit");
    setLimit.setVisibility(JavaVisibility.PUBLIC);
    setLimit.addParameter(new Parameter(type, "limit"));
    setLimit.addBodyLine("this.limit = limit;");
    introspectedTable.getContext().getCommentGenerator().addGeneralMethodComment(setLimit, introspectedTable);
    topLevelClass.addMethod(setLimit);

    Method getLimit = new Method("getLimit");
    getLimit.setVisibility(JavaVisibility.PUBLIC);
    getLimit.setReturnType(type);
    getLimit.addBodyLine("return limit;");
    introspectedTable.getContext().getCommentGenerator().addGeneralMethodComment(getLimit, introspectedTable);
    topLevelClass.addMethod(getLimit);

    topLevelClass.addImportedType(new FullyQualifiedJavaType(Const.LIMIT));

    return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addLimitElement(element, introspectedTable);
    return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    addLimitElement(element, introspectedTable);
    return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
  }

  private void addLimitElement(XmlElement element, IntrospectedTable introspectedTable) {
    XmlElement ifLimitNotNullElement = new XmlElement("if");
    ifLimitNotNullElement.addAttribute(new Attribute("test", "limit != null"));
    ifLimitNotNullElement.addElement(new TextElement("limit ${limit.offset},${limit.maxRows}"));

    element.addElement(ifLimitNotNullElement);
  }

}

package io.github.wikimore.mybatis.generator.resolver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.Date;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * @author Ted Wang
 */
public class JavaTypeResolver extends JavaTypeResolverDefaultImpl {
  /**
   * 计算字段的Java类型
   *
   * @param introspectedColumn 需要计算Java类型的字段
   * @return 正确的java类型
   */
  public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
    FullyQualifiedJavaType answer;
    switch (introspectedColumn.getJdbcType()) {
      case Types.BIT:
      case Types.TINYINT:
      case Types.BOOLEAN:
        answer = new FullyQualifiedJavaType(Boolean.class.getName());
        break;
      case Types.SMALLINT:
        if (introspectedColumn.getJdbcTypeName().contains("UNSIGNED")) {
          answer = new FullyQualifiedJavaType(Integer.class.getName());
        } else {
          answer = new FullyQualifiedJavaType(Short.class.getName());
        }
        break;
      case Types.INTEGER:
        if (introspectedColumn.getJdbcTypeName().contains("UNSIGNED")) {
          answer = new FullyQualifiedJavaType(Long.class.getName());
        } else {
          answer = new FullyQualifiedJavaType(Integer.class.getName());
        }
        break;
      case Types.BIGINT:
        if (introspectedColumn.getJdbcTypeName().contains("UNSIGNED")) {
          answer = new FullyQualifiedJavaType(BigInteger.class.getName());
        } else {
          answer = new FullyQualifiedJavaType(Long.class.getName());
        }
        break;
      case Types.DECIMAL:
      case Types.NUMERIC:
        if ((introspectedColumn.getScale() > 0) || (introspectedColumn.getLength() > 18) || forceBigDecimals) {
          answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
        } else {
          if (introspectedColumn.getLength() > 9) {
            answer = new FullyQualifiedJavaType(Long.class.getName());
          } else {
            if (introspectedColumn.getLength() > 4) {
              answer = new FullyQualifiedJavaType(Integer.class.getName());
            } else {
              answer = new FullyQualifiedJavaType(Short.class.getName());
            }
          }
        }
        break;
      case Types.REAL:
        answer = new FullyQualifiedJavaType(Double.class.getName());
        break;
      case Types.FLOAT:
      case Types.DOUBLE:
        answer = new FullyQualifiedJavaType(Double.class.getName());
        break;
      case Types.CHAR:
      case Types.CLOB:
      case Types.LONGVARCHAR:
      case Types.VARCHAR:
        answer = new FullyQualifiedJavaType(String.class.getName());
        break;
      case Types.NULL:
      case Types.OTHER:
      case Types.STRUCT:
      case Types.DISTINCT:
      case Types.DATALINK:
      case Types.ARRAY:
      case Types.JAVA_OBJECT:
      case Types.REF:
        answer = new FullyQualifiedJavaType(Object.class.getName());
        break;
      case Types.BINARY:
      case Types.BLOB:
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
        answer = new FullyQualifiedJavaType("byte[]");
        break;
      case Types.DATE:
      case Types.TIME:
      case Types.TIMESTAMP:
        answer = new FullyQualifiedJavaType(Date.class.getName());
        break;
      default:
        answer = null;
    }
    return answer;
  }
}

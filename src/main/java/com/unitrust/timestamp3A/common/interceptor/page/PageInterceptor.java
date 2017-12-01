package com.unitrust.timestamp3A.common.interceptor.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			BoundSql boundSql = statementHandler.getBoundSql();
			Object obj = boundSql.getParameterObject();
			if (obj instanceof Page<?>) {
				Page<?> page = (Page<?>) obj;
				BaseStatementHandler delegate = (BaseStatementHandler) getFieldValue(statementHandler, "delegate");
				MappedStatement mappedStatement = (MappedStatement) getFieldValue(delegate, "mappedStatement");
				Connection connection = (Connection) invocation.getArgs()[0];
				this.setTotalRecord(page, mappedStatement, connection);
				String sql = boundSql.getSql();
				String pageSql = this.getPageSql(page, sql);
				setFieldValue(boundSql, "sql", pageSql);
			}
		}
		return invocation.proceed();
	}

	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		String sql = boundSql.getSql();
		String countSql = this.getCountSql(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				page.setTotalRecords(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void setFieldValue(Object obj, String fieldName, Object fieldValue) throws Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(obj, fieldValue);
	}

	private Object getFieldValue(Object obj, String fieldName) throws Exception {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(obj);
			} catch (Exception e) {
			}
		}
		return null;
	}

	private String getPageSql(Page<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		int offset = (page.getPageNum() - 1) * page.getPageSize();
		int end = page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",").append(end);
		return sqlBuffer.toString();
	}

	private String getCountSql(String sql) {
		// int index = sql.indexOf("FROM");
		// return "SELECT COUNT(*) " + sql.substring(index);
		return "SELECT COUNT(*) FROM(" + sql + " ) ref ";
	}

	public Object plugin(Object arg0) {
		if (arg0 instanceof StatementHandler) {
			return Plugin.wrap(arg0, this);
		} else {
			return arg0;
		}
	}

	public void setProperties(Properties p) {

	}

}

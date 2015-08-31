package com.unitever.platform.core.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import com.unitever.platform.util.UUID;

public class JdbcHelper {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcHelper() {

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * 查询返回集合
	 * 
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 * @throws DataAccessException
	 */
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Map<String, ?> valueMap) throws DataAccessException {
		MapSqlParameterSource paramSource = new MapSqlParameterSource(valueMap);
		return namedParameterJdbcTemplate.getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rowMapper);
	}

	public void execute(final String sql) throws DataAccessException {
		jdbcTemplate.execute(sql);
	}

	public int[] batchExecute(List<String> sqls) throws DataAccessException {
		return jdbcTemplate.batchUpdate(sqls.toArray(new String[] {}));
	}

	@SuppressWarnings("unchecked")
	public int[] batchExecute(String sql, List<Map<String, ?>> batchValues) {
		return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new HashMap[] {}));
	}

	private PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource) {
		ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
		Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
		List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
		return pscf.newPreparedStatementCreator(params);
	}

	public String createMemoryTable(String sql) throws DataAccessException {
		String tableName = "mem_" + UUID.getUUID();
		String createSql = "create table " + tableName + " ENGINE=MEMORY(" + sql + ")";
		try {
			DataSource dataSource = jdbcTemplate.getDataSource();
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(createSql);
			JdbcUtils.closeStatement(stmt);
			DataSourceUtils.releaseConnection(conn, dataSource);
		} catch (SQLException e) {
			e.printStackTrace();
			throw jdbcTemplate.getExceptionTranslator().translate("createMemoryTable", createSql, e);
		}
		return tableName;
	}

	public void dropMemoryTable(String tableName) throws DataAccessException {
		String dropSql = "drop table " + tableName;
		try {
			DataSource dataSource = jdbcTemplate.getDataSource();
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(dropSql);
			JdbcUtils.closeStatement(stmt);
			DataSourceUtils.releaseConnection(conn, dataSource);
		} catch (SQLException e) {
			e.printStackTrace();
			throw jdbcTemplate.getExceptionTranslator().translate("dropMemoryTable", dropSql, e);
		}
	}
}

package br.com.javajdbcdao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.javajdbcdao.model.dao.CanalDao;
import br.com.javajdbcdao.model.entities.Canal;
import db.DB;
import db.DbException;

public class CanalDaoJDBC implements CanalDao {

	private Connection conn;

	public CanalDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void inserir(Canal novoCanal) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO canal (nome,regiao,horarioInicial,horarioFinal) VALUES (?,?,?,?)");
			preparedStatement.setString(1, novoCanal.getNome());
			preparedStatement.setString(2, novoCanal.getRegiao());
			preparedStatement.setString(3, novoCanal.getHorarioInicial());
			preparedStatement.setString(4, novoCanal.getHorarioFinal());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}

	}

	@Override
	public Canal encontrarPorRegiaoHorario(String regiao, String horarioInicial) {

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = conn.prepareStatement("SELECT * from canais where regiao = ? AND horarioInicial = ?");
			statement.setString(1, regiao);
			statement.setString(2, horarioInicial);
			resultSet = statement.executeQuery();
			Canal canal = null;
			while (resultSet.next()) {
				String nomeCanal = resultSet.getString("nome");
				String regiaoEncontrada = resultSet.getString("regiao");
				String horarioInicialEncontrado = resultSet.getString("horarioInicial");

				canal = new Canal(nomeCanal, regiaoEncontrada, horarioInicialEncontrado);

			}
			return canal;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
		}
	}

}

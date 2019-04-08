package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.BebidaVO;

public class BebidaDAO {

	public boolean existeRegistroPorNome(String nome) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM BEBIDA WHERE NOME LIKE '" +nome+ "'";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que verifica existência da bebida por nome!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int cadastrarBebidaDAO(BebidaVO bebidaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO BEBIDA (NOME, PRECO) VALUES ('" +bebidaVO.getNome()+ "', " +bebidaVO.getPreco()+ ")";
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que cadastra bebida!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public boolean existeRegistroPorIdBebida(int idBebida) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT idbebida FROM bebida WHERE idbebida = " + idBebida;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que verifica a existência de Bebida por ID!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int excluirBebidaDAO(BebidaVO bebidaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "DELETE FROM bebida WHERE idbebida = " + bebidaVO.getIdBebida();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de exclusão de Bebida!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public int atualizarBebidaDAO(BebidaVO bebidaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query  = "UPDATE bebida SET nome = '" + bebidaVO.getNome() + "', preco = " + bebidaVO.getPreco() + " WHERE idbebida = " + bebidaVO.getIdBebida();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de atualização de Bebida!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public ArrayList<BebidaVO> consultarTodasBebidasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<BebidaVO> bebidasVO = new ArrayList<BebidaVO>();
		String query = "SELECT idbebida, nome, preco FROM bebida";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				BebidaVO bebidaVO = new BebidaVO();
				bebidaVO.setIdBebida(Integer.parseInt(resultado.getString(1)));
				bebidaVO.setNome(resultado.getString(2));
				bebidaVO.setPreco(Double.parseDouble(resultado.getString(3)));
				bebidasVO.add(bebidaVO);
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a consulta de Bebida!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return bebidasVO;
	}

	public BebidaVO consultarBebidaDAO(BebidaVO bebidaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		BebidaVO bebida = new BebidaVO();
		String query = "SELECT idbebida, nome, preco FROM bebida WHERE idbebida = " + bebidaVO.getIdBebida();
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				bebida.setIdBebida(Integer.parseInt(resultado.getString(1)));
				bebida.setNome(resultado.getString(2));
				bebida.setPreco(Double.parseDouble(resultado.getString(3)));
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a consulta de Bebida!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return bebida;
	}

}

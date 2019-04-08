package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import model.vo.VendaVO;

public class VendaDAO {
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

	public boolean existeRegistroVenda(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT IDVENDA FROM VENDA WHERE SENHAPEDIDO = " +vendaVO.getSenhaPedido()+ " AND DATAVENDA = '" + vendaVO.getDataVenda() + "'";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de existencia de Venda!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
	
	public boolean existeRegistroPorIdVenda(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT IDVENDA FROM VENDA WHERE IDVENDA = " + idVenda;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que verifica a existencia de Venda por ID!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int cadastrarVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		int retorno = 0;
		String query = "INSERT INTO VENDA (dataVenda, senhaPedido, flagVendaCancelada) VALUES ('" + df.format(vendaVO.getDataVenda()) + "', " + vendaVO.getSenhaPedido() + ", " + vendaVO.isFlagVendaCancelada() + ")";
		try {
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				retorno = resultado.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de Cadastro de Venda!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean cadastrarItemPratoDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		int cont = 0;
		boolean retorno = false;
		String query = "";
		try {
			for(int i = 0; i < vendaVO.getItensPratos().size(); i++) {
				query = "INSERT INTO itemprato (idprato, idvenda, quantidade) "
						+ "VALUES ("+ vendaVO.getItensPratos().get(i).getIdPrato() +", "+ vendaVO.getIdVenda() +", "+ vendaVO.getItensPratos().get(i).getQuantidade() +")";
				resultado = stmt.executeUpdate(query);
				if(resultado == 1) {
					cont++;
				}
		    }
			if(cont == vendaVO.getItensPratos().size()) {
			retorno =  true;
			}else {
			System.out.println("\nNem todos os pratos foram cadastrados com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de cadastro item prato!");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean cadastrarItemBebidaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		int cont = 0;
		boolean retorno = false;
		String query = "";
		try {
			for(int i = 0; i < vendaVO.getItensBebidas().size(); i++) {
				query = "INSERT INTO ITEMBEBIDA (idbebida, idvenda, quantidade) "
						+ "VALUES ("+ vendaVO.getItensBebidas().get(i).getIdBebida() +", "+ vendaVO.getIdVenda() +", "+ vendaVO.getItensBebidas().get(i).getQuantidade() +")";
				resultado = stmt.executeUpdate(query);
				if(resultado == 1) {
					cont++;
				}
		    }
			if(cont == vendaVO.getItensBebidas().size()) {
			retorno =  true;
			}else {
			System.out.println("\nNem todas as bebidas foram cadastrados com sucesso!");
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de cadastro item bebida!");
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}


	public int cancelarVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "UPDATE VENDA SET FLAGVENDACANCELADA = TRUE WHERE IDVENDA = " + vendaVO.getIdVenda();
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query de cancelamento de Venda!");
			e.printStackTrace();
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

	public boolean vendaEstaCancelada(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT IDVENDA FROM VENDA WHERE IDVENDA = " + vendaVO.getIdVenda() + " AND FLAGVENDACANCELADA IS TRUE";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar query que confere se Venda já está cancelada!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
	
}

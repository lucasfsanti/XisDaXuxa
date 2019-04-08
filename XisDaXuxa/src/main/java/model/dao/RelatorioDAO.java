package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.ProdutoDTO;
import model.vo.BebidaVO;
import model.vo.ItemBebidaVO;
import model.vo.ItemPratoVO;
import model.vo.PratoVO;
import model.vo.VendaVO;

public class RelatorioDAO {

	public ArrayList<ProdutoDTO> consultarTodosProdutosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ProdutoDTO> listaProdutosDTO = new ArrayList<ProdutoDTO>();
		try {
			boolean aux = stmt.execute("SET @CONT = 0");
		} catch (SQLException e1) {
			System.out.println("\nErro ao executar a query que define uma variável!");
			e1.printStackTrace();
		}
		String query = "SELECT(@CONT := @CONT + 1) AS IDPRODUTO, PRODUTO.NOME, PRODUTO.PRECO "
				+ "FROM ((SELECT NOME, PRECO FROM PRATO) UNION (SELECT NOME, PRECO FROM BEBIDA)) AS PRODUTO ORDER BY NOME";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ProdutoDTO produtoDTO = new ProdutoDTO();
				produtoDTO.setIdProduto(Integer.parseInt(resultado.getString(1)));
				produtoDTO.setNome(resultado.getString(2));
				produtoDTO.setPreco(Double.parseDouble(resultado.getString(3)));
				listaProdutosDTO.add(produtoDTO);
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a consulta do Produto!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaProdutosDTO;
	}

	public ArrayList<VendaVO> consultarTodasVendasCanceladasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<VendaVO> listaVendasVO = new ArrayList<VendaVO>();
		String query = "SELECT * FROM ((SELECT VENDA.IDVENDA, VENDA.DATAVENDA, ITEMPRATO.IDPRATO, ITEMPRATO.QUANTIDADE AS PRATOQUANTIDADE, "
				+ "0 AS IDBEBIDA, 0 AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMPRATO ON VENDA.IDVENDA = ITEMPRATO.IDVENDA "
				+ "WHERE VENDA.FLAGVENDACANCELADA IS TRUE) UNION (SELECT VENDA.IDVENDA, VENDA.DATAVENDA, 0 AS IDPRATO, 0 AS PRATOQUANTIDADE, "
				+ "ITEMBEBIDA.IDBEBIDA, ITEMBEBIDA.QUANTIDADE AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMBEBIDA ON VENDA.IDVENDA = ITEMBEBIDA.IDVENDA "
				+ "WHERE VENDA.FLAGVENDACANCELADA IS TRUE)) AS VENDA ORDER BY IDVENDA";
		try {
			resultado = stmt.executeQuery(query);
			int cont = -1;
			while(resultado.next()) {
				VendaVO vendaVO = new VendaVO();
				vendaVO.setIdVenda(Integer.parseInt(resultado.getString(1)));
				vendaVO.setDataVenda(Date.valueOf(resultado.getString(2)));
				ArrayList<ItemPratoVO> itensPratos = new ArrayList<ItemPratoVO>();
				ArrayList<ItemBebidaVO> itensBebidas = new ArrayList<ItemBebidaVO>();
				ItemPratoVO itemPratoVO = new ItemPratoVO();
				itemPratoVO.setIdPrato(Integer.parseInt(resultado.getString(3)));
				itemPratoVO.setQuantidade(Integer.parseInt(resultado.getString(4)));
				ItemBebidaVO itemBebidaVO = new ItemBebidaVO();
				itemBebidaVO.setIdBebida(Integer.parseInt(resultado.getString(5)));
				itemBebidaVO.setQuantidade(Integer.parseInt(resultado.getString(6)));
				if(cont < 0) {
					itensPratos.add(itemPratoVO);
					itensBebidas.add(itemBebidaVO);
					vendaVO.setItensPratos(itensPratos);
					vendaVO.setItensBebidas(itensBebidas);
					listaVendasVO.add(vendaVO);
					cont++;
				} else if(vendaVO.getIdVenda() == listaVendasVO.get(cont).getIdVenda()) {
					listaVendasVO.get(cont).getItensPratos().add(itemPratoVO);
					listaVendasVO.get(cont).getItensBebidas().add(itemBebidaVO);
				} else {
					itensPratos.add(itemPratoVO);
					itensBebidas.add(itemBebidaVO);
					vendaVO.setItensPratos(itensPratos);
					vendaVO.setItensBebidas(itensBebidas);
					listaVendasVO.add(vendaVO);
					cont++;
				}
			}
			System.out.println(cont);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que consulta Vendas Canceladas!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaVendasVO;
	}

	public ArrayList<VendaVO> consultarTodasVendasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<VendaVO> listaVendasVO = new ArrayList<VendaVO>();
		String query = "SELECT * FROM ((SELECT VENDA.IDVENDA, VENDA.DATAVENDA, ITEMPRATO.IDPRATO, ITEMPRATO.QUANTIDADE AS PRATOQUANTIDADE, "
				+ "0 AS IDBEBIDA, 0 AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMPRATO ON VENDA.IDVENDA = ITEMPRATO.IDVENDA) UNION "
				+ "(SELECT VENDA.IDVENDA, VENDA.DATAVENDA, 0 AS IDPRATO, 0 AS PRATOQUANTIDADE, "
				+ "ITEMBEBIDA.IDBEBIDA, ITEMBEBIDA.QUANTIDADE AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMBEBIDA ON "
				+ "VENDA.IDVENDA = ITEMBEBIDA.IDVENDA)) AS VENDA ORDER BY IDVENDA";
		try {
			resultado = stmt.executeQuery(query);
			int cont = -1;
			while(resultado.next()) {
				VendaVO vendaVO = new VendaVO();
				vendaVO.setIdVenda(Integer.parseInt(resultado.getString(1)));
				vendaVO.setDataVenda(Date.valueOf(resultado.getString(2)));
				ArrayList<ItemPratoVO> itensPratos = new ArrayList<ItemPratoVO>();
				ArrayList<ItemBebidaVO> itensBebidas = new ArrayList<ItemBebidaVO>();
				ItemPratoVO itemPratoVO = new ItemPratoVO();
				itemPratoVO.setIdPrato(Integer.parseInt(resultado.getString(3)));
				itemPratoVO.setQuantidade(Integer.parseInt(resultado.getString(4)));
				ItemBebidaVO itemBebidaVO = new ItemBebidaVO();
				itemBebidaVO.setIdBebida(Integer.parseInt(resultado.getString(5)));
				itemBebidaVO.setQuantidade(Integer.parseInt(resultado.getString(6)));
				if(cont < 0) {
					itensPratos.add(itemPratoVO);
					itensBebidas.add(itemBebidaVO);
					vendaVO.setItensPratos(itensPratos);
					vendaVO.setItensBebidas(itensBebidas);
					listaVendasVO.add(vendaVO);
					cont++;
				} else if(vendaVO.getIdVenda() == listaVendasVO.get(cont).getIdVenda()) {
					listaVendasVO.get(cont).getItensPratos().add(itemPratoVO);
					listaVendasVO.get(cont).getItensBebidas().add(itemBebidaVO);
				} else {
					itensPratos.add(itemPratoVO);
					itensBebidas.add(itemBebidaVO);
					vendaVO.setItensPratos(itensPratos);
					vendaVO.setItensBebidas(itensBebidas);
					listaVendasVO.add(vendaVO);
					cont++;
				}
			}
			System.out.println(cont);
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que consulta Vendas Canceladas!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaVendasVO;
	}

	public VendaVO consultarVendaPorIdVendaDAO(VendaVO vendaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		VendaVO venda = new VendaVO();
		String query = "SELECT * FROM ((SELECT VENDA.IDVENDA, VENDA.DATAVENDA, ITEMPRATO.IDPRATO, ITEMPRATO.QUANTIDADE AS PRATOQUANTIDADE, "
				+ "0 AS IDBEBIDA, 0 AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMPRATO ON VENDA.IDVENDA = ITEMPRATO.IDVENDA) UNION "
				+ "(SELECT VENDA.IDVENDA, VENDA.DATAVENDA, 0 AS IDPRATO, 0 AS PRATOQUANTIDADE, "
				+ "ITEMBEBIDA.IDBEBIDA, ITEMBEBIDA.QUANTIDADE AS BEBIDAQUANTIDADE FROM VENDA INNER JOIN ITEMBEBIDA ON "
				+ "VENDA.IDVENDA = ITEMBEBIDA.IDVENDA)) AS VENDA WHERE IDVENDA = " + vendaVO.getIdVenda() + " ORDER BY IDVENDA";
		try {
			resultado = stmt.executeQuery(query);
			int cont = 0;
			ArrayList<ItemPratoVO> itensPratos = new ArrayList<ItemPratoVO>();
			ArrayList<ItemBebidaVO> itensBebidas = new ArrayList<ItemBebidaVO>();
			while(resultado.next()) {
				if(cont == 0) {
					venda.setIdVenda(Integer.parseInt(resultado.getString(1)));
					venda.setDataVenda(Date.valueOf(resultado.getString(2)));
					ItemPratoVO itemPratoVO = new ItemPratoVO();
					itemPratoVO.setIdPrato(Integer.parseInt(resultado.getString(3)));
					itemPratoVO.setQuantidade(Integer.parseInt(resultado.getString(4)));
					ItemBebidaVO itemBebidaVO = new ItemBebidaVO();
					itemBebidaVO.setIdBebida(Integer.parseInt(resultado.getString(5)));
					itemBebidaVO.setQuantidade(Integer.parseInt(resultado.getString(6)));
					itensPratos.add(itemPratoVO);
					itensBebidas.add(itemBebidaVO);	
				} else {
					ItemPratoVO itemPratoVO = new ItemPratoVO();
					ItemBebidaVO itemBebidaVO = new ItemBebidaVO();
					itemPratoVO.setIdPrato(Integer.parseInt(resultado.getString(3)));
					itemPratoVO.setQuantidade(Integer.parseInt(resultado.getString(4)));
					itemBebidaVO.setIdBebida(Integer.parseInt(resultado.getString(5)));
					itemBebidaVO.setQuantidade(Integer.parseInt(resultado.getString(6)));
					venda.getItensPratos().add(itemPratoVO);
					venda.getItensBebidas().add(itemBebidaVO);
				}
				cont++;
				venda.setItensPratos(itensPratos);
				venda.setItensBebidas(itensBebidas);
			}
		} catch (SQLException e) {
			System.out.println("\nErro ao executar a query que consulta Venda por Código!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return venda;
	}

}

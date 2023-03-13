package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;

public class PessoaDao implements ICRUDDao<Pessoa> {
	
	private GenericDao gDao;

	public PessoaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	private String callProcedurePessoa(String opcao, Pessoa pessoa) throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, opcao);
		cs.setInt(2, pessoa.getId());
		cs.setString(3, pessoa.getNome());
		cs.setString(4, pessoa.getTelefoneFixo());
		cs.setString(5, pessoa.getTelefoneCelular());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		c.close();
		
		return saida;
	
	}
	
	@Override
	public String insert(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		String saida = callProcedurePessoa("I", pessoa);
		return saida;
	}

	@Override
	public String update(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		String saida = callProcedurePessoa("U", pessoa);
		return saida;
	}

	@Override
	public String delete(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_pessoa(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setInt(2, pessoa.getId());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		c.close();
		
		return saida;
	}
	
	@Override
	public Pessoa select(Pessoa pessoa) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, tel_fixo, tel_cel FROM pessoa WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, pessoa.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			pessoa.setNome(rs.getString("nome"));
			pessoa.setTelefoneFixo(rs.getString("tel_fixo"));
			pessoa.setTelefoneCelular(rs.getString("tel_cel"));
		}
		rs.close();
		ps.close();
		c.close();		
		return pessoa;
	}

	@Override
	public List<Pessoa> list() throws SQLException, ClassNotFoundException {
		List<Pessoa> pessoas = new ArrayList<>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, tel_fixo, tel_cel FROM pessoa";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setTelefoneFixo(rs.getString("tel_fixo"));
			pessoa.setTelefoneCelular(rs.getString("tel_cel"));
			pessoas.add(pessoa);
		}
		rs.close();
		ps.close();
		c.close();		
		return pessoas;
	}


}

package br.com.cv.iface;

import java.util.List;

public interface CRUD<Entidade>{
	
	boolean cadastrar(Entidade entidade);
	boolean editar(long codigo, Entidade entidade);
	List<Entidade> listar(String filter);
	boolean excluir(long codigo);
	
}

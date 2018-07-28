package fbe_aula4.dao;

import fbe_aula4.model.Produto;
import java.util.List;

/**
 *
 * @author FANNY ROSANA PASTOR HUMPIRI
 */

public interface ProdutoDAO {

    public List<Produto> getProdutos();

    public Produto getProduto(String codigo);
    
    public boolean delProduto(String codigo);
    
    public boolean setProduto(Produto produto);

    public boolean addProduto(Produto newproduto);
    



    
}
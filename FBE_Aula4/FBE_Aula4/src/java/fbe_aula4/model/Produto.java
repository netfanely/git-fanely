package fbe_aula4.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 *
 * @author FANNY ROSANA PASTOR HUMPIRI
 */
@Entity
//@Table(name = "produto")
@Table(name = "produtos") //nombre de la tabela es "produtos"
//@SequenceGenerator(name = "SEQUENCE", sequenceName = "produto_produto_id_seq")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "produtos_produtos_id_seq")
//Sequence no banco de dados "postgres" es "produtos_produtos_id_seq"
public class Produto implements Serializable { //NOME DA CLASE MODEL E Produto

    /**
     * Codigo do objeto
     */
    @Id
    @Column(name = "produto_id") //coluna = "produto_id"
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
   // protected Long produtoId; //variable que almacena no projeto "produtoID"
    //private long produtoId;
    private long produtoID;

    @Column
        //(name = "nome")
    private String nome;

    @Column
        //(name = "codigo")
    private String codigo;

    @Column
        //(name = "preco")
    private float preco;

    public Produto() {
        produtoID = new Long(-1);
    }

    public Produto(String nome, String codigo, float preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    //public Long getProdutoId() {
    public long getProdutoID() {
        //return produtoId;
        return produtoID;
    }
    
  
    //public void setProdutoID(String produtoId) {
    //    this.produtoID = Long.valueOf(produtoId);
    public void setProdutoID(long produtoID) {
       this.produtoID = produtoID;
    }

  /*
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }*/
    

    public String getNome() {
        return nome;
    }

     public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

     public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

}

package fbe_aula4.dao.impl;

import fbe_aula4.model.Produto;
import fbe_aula4.dao.HibernateUtil;
import fbe_aula4.dao.ProdutoDAO;
import java.util.List;
//import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
/**
 *
 * @author FANNY ROSANA PASTOR HUMPIRI
 */
public class ProdutoDAOImpl implements ProdutoDAO{
    
    SessionFactory sessionFactory = null;
    
    public ProdutoDAOImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    public List<Produto> getProdutos(){ //viene de RecursoProdutos.java
        List<Produto> produtos = null;
        Session session = null;
        try {            
            session = sessionFactory.openSession();
            session.beginTransaction();
            produtos = session.createQuery("from Produto").list(); 
            // Produto é o nome da classe model
            /*  Exemplo de uso de Criteria
                Criteria criteria = session.createCriteria(Produto.class);
                criteria.add(Restrictions.like("nome", "%ar%"));
                produtos = criteria.list();
                Fim do exemplo de Criteria  */
            session.getTransaction().commit();
        } catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
        } finally{
            if(session != null){
                session.close();
            }
        }
        return produtos;
    }

    public Produto getProduto(String codigo){
        Produto produto = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            produto = (Produto)session
                    //.createQuery("from Produto p where p.codigo = :cod").
                    .createQuery("from Produto p where p.codigo = :codigo"). 
                    //setString("cod",codigo).uniqueResult();
                    setString("codigo", codigo).uniqueResult(); 
            session.getTransaction().commit();
        } catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
        } finally{
            if(session != null){
                session.close();
            }
        }
        return produto;
    }


    //metodo para adicionar produto
     @Override
    public boolean addProduto(Produto newproduto) { 
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            //session.persist(newproduto);
            session.save(newproduto);
            session.getTransaction().commit();
            
        }catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
            return false;
        }finally{
            if(session != null){
                session.close();
            } 
        }
        return true;
    }  
    
    @Override
    public boolean setProduto(Produto produto){
    //  public boolean putProduto(Produto produto) { //put
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            session.saveOrUpdate(produto);
            session.getTransaction().commit();

        }catch(Exception e){
            if(session != null){
               session.getTransaction().rollback();
            }
            return false;
        }finally{
            if(session != null){
               session.close();
            } 
        }
        return true;
    }

    @Override
    public boolean delProduto(String codigo){
            Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            Produto produto = (Produto)session
                   // load(Produto.class, codigo);
                    .createQuery("from Produto p where p.codigo = :codigo")
                    .setString("codigo", codigo)
                    .uniqueResult();
            session.delete(produto);
            session.getTransaction().commit();

        }catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
            return false;
        }finally{
            if(session != null){
                session.close();
            } 
        }
        return true;
    }
    
}

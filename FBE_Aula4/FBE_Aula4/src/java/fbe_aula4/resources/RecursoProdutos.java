package fbe_aula4.resources;

import com.google.gson.Gson;
import fbe_aula4.dao.ProdutoDAO;
import fbe_aula4.dao.impl.ProdutoDAOImpl;
import fbe_aula4.model.Produto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("produtos")
public class RecursoProdutos {

    private final ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    Gson gson = new Gson();

    public RecursoProdutos() { //construtor
    }

    @GET //METODO GET DE LA LISTA DE PRODUCTOS
    //@Produces("application/json")
     @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        String produtos = null;
        //List<Produto> prodLista = new ProdutoDAOImpl().getProdutos(); //plurar (RecursoProdutos.java es getProdutos()
        List<Produto> prodLista = new ArrayList<>();
        String strProdutos = "";
        try{
            //Gson gson = new Gson();
            prodLista = produtoDAO.getProdutos();
            strProdutos = gson.toJson(prodLista);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(produtos.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.ok(strProdutos).build();
        }
    }

    @GET //UN PRODUCTO
    @Path("{codigo}")
    //@Produces("application/json")
    //@Consumes("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos(@PathParam("codigo") String codigo){
        String prod = null;
        //Produto produto = new ProdutoDAO().getProduto(codigo);
        Produto produto = new ProdutoDAOImpl().getProduto(codigo);
        try{
            //Gson gson = new Gson();
            prod = gson.toJson(produto);
        }catch(Exception e){
            e.printStackTrace();
        }
        //if(produto.isEmpty()){
        if(produto.getCodigo().isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.ok(prod).build(); //-produto
        }
    }
    
    @PUT
    @Path("{put/codigo}") //put//codigo
    @Consumes("application/json")
    public Response putProduto(String codigo) {
        boolean result = false;
        Produto produto_temp = new Produto();
        
        try {
            produto_temp = gson.fromJson(codigo, Produto.class);
            result = produtoDAO.setProduto(produto_temp);
             //result = produtoDAO.putProduto(produto_temp);
        } catch (Exception e) {
        }
        
        if (result) {
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduto(String produto, @Context UriInfo uriInfo){
        boolean result = false;
        URI uri;
        Produto novoProduto = new Produto();
        
        try {
            novoProduto = gson.fromJson(produto, Produto.class);
            result = produtoDAO.addProduto(novoProduto);
        } catch (Exception e) {
        }
        
        if (result) {
            uri = uriInfo.getAbsolutePathBuilder().path(novoProduto.getCodigo()).build();
            return Response.created(uri).build();
        }
        else{
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
}
    
    @DELETE
    @Path("{delete/codigo}")
  //  @Produces("application/json")
   // @Consumes("application/json")
     @Consumes(MediaType.APPLICATION_JSON)
    //public Response delProduto(String codigo) {
    public Response delProduto(@PathParam("codigo") String codigo) {
         boolean result = false;
        
        try {
            result = produtoDAO.delProduto(codigo);
        } catch (Exception e) {
        }
        
        if (result) {
            return Response.ok().build();
        } 
        else {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

    }
}

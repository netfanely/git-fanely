package fbe_aula4.resources;

import java.util.Set;
import javax.ws.rs.core.Application;
/**
 *
 * @author FANNY ROSANA PASTOR HUMPIRI
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fbe_aula4.resources.RecursoProdutos.class);
    }
    
}


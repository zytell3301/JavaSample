package com.example.sample.Startup;

import com.example.sample.DataAccess.Abstract.Repositories.Products.ProductsDataAccess;
import com.example.sample.DataAccess.Repositories.Products.ProductsRepository;
import com.example.sample.Services.Abstract.Products.ProductsService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Configuration
@EnableJdbcRepositories("com.example.sample.DataAccess.Repositories.Products")
@EnableCaching
public class App {
    private Map<String, String> Configs = null;

    public App() {
        LoadYaml();
    }

    private void LoadYaml() {
        if (Configs != null) {
            return;
        }
        try {
            Yaml yaml = new Yaml();
            var configString = Files.readString(Paths.get("conf/config.yaml"));
            Configs = yaml.load(configString);
        } catch (Exception e) {
            System.out.println("An error occurred while attempting to load config file. Error message: " + e.getMessage());
            System.exit(0);
        }
    }

    @Bean
    DataSource dataSource() {
        var ds = new DriverManagerDataSource(Configs.get("ConnectionString"));
        ds.setUsername(Configs.get("Username"));
        ds.setPassword(Configs.get("Password"));
        return ds;
    }

    @Bean
    ProductsDataAccess productsDataAccess(ProductsRepository productsRepository) {
        return new com.example.sample.DataAccess.Repositories.Products.ProductsDataAccess(productsRepository);
    }

    @Bean
    ProductsService productsService(ProductsDataAccess productsDataAccess) {
        return new com.example.sample.Services.Impl.Products.ProductsService(productsDataAccess);
    }

}

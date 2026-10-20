package org.sekhsoukhi.ormfirstproject;

import org.sekhsoukhi.ormfirstproject.entities.Product;
import org.sekhsoukhi.ormfirstproject.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OrmFirstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmFirstProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            Product p = Product.builder()
                    .name("Computer")
                    .price(5400)
                    .quantity(43)
                    .build();
            productRepository.save(p);

            productRepository.save(Product.builder().name("Printer").price(640).quantity(4).build());
            productRepository.save(Product.builder().name("Smart Phone").price(1200).quantity(34).build());
            List<Product> products = productRepository.findAll();
            products.forEach(prod->{
                System.out.println(prod.toString());
            });

            List<Product> productsByKW = productRepository.findByNameContainsIgnoreCase("m");
            productsByKW.forEach(prod->{
                System.out.println(prod.toString());
            });
        };
    }

}

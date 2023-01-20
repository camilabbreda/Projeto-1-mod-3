package br.com.clamed.pharmacymanagement;

import br.com.clamed.pharmacymanagement.client.FeignClient;
import br.com.clamed.pharmacymanagement.client.FeignClient;
import br.com.clamed.pharmacymanagement.model.entity.EnderecoEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 *
 * Spring Boot application starter class
 */

@EnableFeignClients
@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//    @Bean
//    public CommandLineRunner run(FeignClient client){
//        return args -> {
//            if (args.length > 0) {
//                String cep = args[0];
//
//                EnderecoEntity endereco = client.buscaCep(cep);
//            }
//        };
//    }


}

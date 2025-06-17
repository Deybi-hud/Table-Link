package Prueba.TableLink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        
        return new OpenAPI()
        .info(new Info()
        .title("API de convertidor")
        .version("1.0")
        .license(new License().name("GNU General Public License v3.0").url("https://github.com/Deybi-hud/Table-Link/blob/main/LICENSE")));
        
    }
}

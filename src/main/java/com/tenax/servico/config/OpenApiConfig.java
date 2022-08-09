package com.tenax.servico.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Serviço api Servidores e Setores",
                description = "" +
                        "Serviço api com consulta ao banco de dados , cadastro de servidores e setores .",
                contact = @Contact(
                        name = "Serviço Api",
                        url = "https://github.com/gabrielffguimaraes/servico-webapi",
                        email = "gabrielffguimaraes@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/thombergs/code-examples/blob/master/LICENSE"))
)
class OpenApiConfig {}
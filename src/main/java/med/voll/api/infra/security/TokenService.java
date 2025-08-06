package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}") //Toma el valor desde el application.properties y lo pone en la variable secret
    private String secret; //secret es una clave para encriptar el token
    private static final String ISSUER = "API Med.voll";
    public String generarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret); //se selecciona el algoritmo de encriptación para el token usando la variable secret
            return JWT.create() //
                    .withIssuer(ISSUER) //se agrega la información del emisor, debe ser igual en el Metodo getSubject
                    .withSubject(usuario.getUsername()) //se agrega el username
                    .withExpiresAt(fechaExpiracion()) //se agrega la fecha de expiración
                    .sign(algoritmo); //se encripta el token usando el algoritmo seleccionado
        } catch (JWTCreationException exception){ //en caso de no poder crear el token
            throw new RuntimeException("error al generar el token JWT",exception);
        }
    }

    public String getSubject(String tokenJWT){ //se obtiene el subject del token
        try {
            System.out.println("Verificando token...\n");
            var algoritmo = Algorithm.HMAC256(secret); //se asigna el algoritmo de encriptación a una variable
            return JWT.require(algoritmo) //se selecciona el algoritmo de encriptación para poder desencriptar el token
                    .withIssuer(ISSUER) //se agrega la información del emisor, debe ser igual en el MetodogenerarToken
                    .build() //se construye el token
                    .verify(tokenJWT) //se verifica el token
                    .getSubject(); //se obtiene el subject
        } catch (JWTVerificationException exception){ //en caso de no poder verificar el token
            throw new RuntimeException("Token JWT inválido o expirado: " + tokenJWT);
        }
    }
    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}



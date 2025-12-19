package es.softtek.jwtDemo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import es.softtek.jwtDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import es.softtek.jwtDemo.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/ejemplojwt")
public class UserController {
    /*
    El método login(...) interceptará las peticiones POST al endpoint /user
    y recibirá como parámetros el usuario y contraseña.
    Como se puede observar, para este ejemplo no se realiza ninguna validación de usuario y contraseña,
    por lo que para cualquier valor de dichos parámetros dejaremos paso. Obviamente, para un proyecto real,
    en este punto deberíamos autenticar el usuario contra nuestra base de datos
    o contra cualquier proveedor externo.
     */


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")

    // public User login(  @PathVariable String username,@PathVariable String pwd)
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {


        User userBD = userRepository.findByCredentials(username,pwd);

        if (userBD != null) {
            String token = getJWTToken(username);

            userBD.setToken(token);

            return userBD;
        } else {
            return null;
        }

        /*
        if ((username.equals("juan")) && (pwd.equals("juan"))) {
            System.out.println("Me crea el token");
            String token = getJWTToken(username);
            User user = new User();
            user.setUser(username);
            user.setPwd(pwd);
            user.setToken(token);

            return user;
        } else
            return null;*/

    }
    //Utilizamos el método getJWTToken(...) para construir el token,
    // delegando en la clase de utilidad Jwts que incluye información sobre su expiración
    // y un objeto de GrantedAuthority de Spring que, como veremos más adelante,
    // usaremos para autorizar las peticiones a los recursos protegidos.

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}

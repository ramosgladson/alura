package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.entities.User;
import med.voll.api.domain.records.UserDTO;
import med.voll.api.infra.security.TokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UserDTO userDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.senha());
        var auth = authenticationManager.authenticate(authToken);
        var token = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(token));
    }

}

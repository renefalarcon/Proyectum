package cl.prueba.proyectum.controller;

import cl.prueba.proyectum.entity.UsuarioEntity;
import cl.prueba.proyectum.repository.UsuarioRepository;
import cl.prueba.proyectum.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuario) {
        return usuarioRepository.findByUsername(usuario.getUsername())
                .filter(u -> passwordEncoder.matches(usuario.getPassword(), u.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(u.getUsername()))))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas")));
    }
}

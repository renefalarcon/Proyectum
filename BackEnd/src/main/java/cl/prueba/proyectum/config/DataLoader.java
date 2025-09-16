package cl.prueba.proyectum.config;

import cl.prueba.proyectum.entity.UsuarioEntity;
import cl.prueba.proyectum.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                UsuarioEntity admin = new UsuarioEntity();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("1234"));
                usuarioRepository.save(admin);
            }

            /*if (usuarioRepository.findByUsername("user").isEmpty()) {
                UsuarioEntity user = new UsuarioEntity();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                usuarioRepository.save(user);
            }*/
        };
    }

}

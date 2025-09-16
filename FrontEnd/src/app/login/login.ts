import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  //selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  //styleUrl: './login.css'
})
export class Login {
  username = '';
  password = '';

  constructor(private authService: AuthService) {}

  login() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => window.location.href = '/productos',
      error: () => alert('Usuario o contraseña incorrectos')
    });
  }
}

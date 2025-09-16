import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService,
    private router: Router
  ) {}

  /*canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      window.location.href = '/login';
      return false;
    }
    return true;
  }*/

    canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);  // 🔹 redirección sin recargar la app
      return false;
    }
    return true;
  }
    
}

import { Routes } from '@angular/router';
import { Login } from './login/login';
import { AuthGuard } from './services/auth.guard';
import { Producto } from './producto/producto';


export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'productos', component: Producto, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login' }
];

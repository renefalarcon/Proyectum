import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductoDTO } from '../models/productoDTO';
import { ProductoService } from '../services/producto.services';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  //selector: 'app-producto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './producto.html',
  styleUrl: './producto.css'
})
export class Producto {

  productos = signal<ProductoDTO[]>([]);
  nuevoProducto = signal<ProductoDTO>({ nombre: '', precio: 0 });
  editando = signal(false);
  productoSeleccionado = signal<ProductoDTO | null>(null);

  constructor(private productoService: ProductoService, private router: Router) {
    this.listar();
  }

  listar() {
    this.productoService.listar().subscribe(data => this.productos.set(data));
  }

  crear() {
    this.productoService.crear(this.nuevoProducto()).subscribe(() => {
      this.listar();
      this.nuevoProducto.set({ nombre: '', precio: 0 });
    });
  }

  seleccionar(producto: ProductoDTO) {
    this.editando.set(true);
    this.productoSeleccionado.set({ ...producto });
  }

  actualizar() {
    const p = this.productoSeleccionado();
    if (p && p.id) {
      this.productoService.actualizar(p.id, p).subscribe(() => {
        this.listar();
        this.cancelarEdicion();
      });
    }
  }

  eliminar(id: number) {
    this.productoService.eliminar(id).subscribe(() => this.listar());
  }

  cancelarEdicion() {
    this.editando.set(false);
    this.productoSeleccionado.set(null);
  }

   logout() {
    localStorage.removeItem('token');   // Elimina el token
    this.router.navigate(['/login']);   // Redirige al login
  }
}

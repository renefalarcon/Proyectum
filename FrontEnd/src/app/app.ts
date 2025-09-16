import { Component, signal } from '@angular/core';
import { Producto } from './producto/producto';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
/*
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Producto],
  template: `<app-producto></app-producto>`
})
export class App {
  protected readonly title = signal('productos-app');
}*/



@Component({
  standalone: true,
  selector: 'app-root',       // 🔹 importante
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`
})
export class App {}


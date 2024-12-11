import { Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';
import { AuthenticationModule } from './features/authentication/authentication.module';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    AuthenticationModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'event-management-app';
}

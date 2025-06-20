import { Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';
import { AuthenticationModule } from './features/authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { EventsModule } from './features/events/events.module';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    BrowserModule,
    SharedModule,
    AuthenticationModule,
    EventsModule,
    HttpClientModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'event-management-app';
}

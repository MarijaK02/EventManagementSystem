import { Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';
import { AuthenticationModule } from './features/authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { EventsModule } from './features/events/events.module';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    SharedModule,
    AuthenticationModule,
    EventsModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'event-management-app';
}

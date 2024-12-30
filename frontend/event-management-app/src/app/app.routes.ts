import { Routes } from '@angular/router';
import { RegisterComponent } from './features/authentication/register/register.component';
import { EventsViewComponent } from './features/events/events-view/events-view.component';
import { LoginComponent } from './features/authentication/login/login.component';
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'events',
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {

    path: 'events',
    component: EventsViewComponent

  }
];

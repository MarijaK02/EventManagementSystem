import { Routes } from '@angular/router';
import { RegisterComponent } from './features/authentication/register/register.component';
import { EventsViewComponent } from './features/events/events-view/events-view.component';
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
    path: 'events',
    component: EventsViewComponent
  }
];

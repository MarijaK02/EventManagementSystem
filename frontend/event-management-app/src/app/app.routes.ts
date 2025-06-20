import { Routes } from '@angular/router';
import { RegisterComponent } from './features/authentication/register/register.component';
import { EventsViewComponent } from './features/events/events-view/events-view.component';
import { LoginComponent } from './features/authentication/login/login.component';
import { EventCreateComponent } from './features/events/event-create/event-create.component';
import { EventEditComponent } from './features/events/event-edit/event-edit.component';
import { EventDetailsComponent } from './features/events/event-details/event-details.component';

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
    path: 'events/:id',
    component: EventDetailsComponent
  },
  {
    path: 'events',
    component: EventsViewComponent
  },
  {
    path: 'events/create-event',
    component: EventCreateComponent
  },
  {
    path: 'edit-event/:id',
    component: EventEditComponent
  }
];

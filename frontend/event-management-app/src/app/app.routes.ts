import { Routes } from '@angular/router';
import { RegisterComponent } from './features/authentication/register/register.component';
export const routes: Routes = [
  {
    path: '',
    redirectTo: 'register',
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

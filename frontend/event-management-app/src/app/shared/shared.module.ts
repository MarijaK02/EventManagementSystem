import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedPasswordInputComponent } from './components/shared-password-input/shared-password-input.component';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    SharedPasswordInputComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  exports: [
    SharedPasswordInputComponent
  ]
})
export class SharedModule { }

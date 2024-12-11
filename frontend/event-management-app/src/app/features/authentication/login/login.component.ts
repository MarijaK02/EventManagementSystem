import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { passwordValidator } from '../../../shared/validators/password-validator';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
  ]
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup | undefined;
  showPassword: boolean = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: [
        '', 
        [
          Validators.required, 
          Validators.minLength(6), 
          passwordValidator // Add the custom passwordValidator
        ]
      ]
    });
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  public getPasswordErrorMessage(): string {
    const passwordControl = this.loginForm?.get('password');
    
    if (passwordControl?.hasError('required')) {
      return 'Ве молиме внесете лозинка';
    }
    if (passwordControl?.hasError('minlength')) {
      return 'Лозинката мора да има најмалку 6 карактери';
    }
    if (passwordControl?.hasError('passwordUppercase')) {
      return 'Лозинката мора да содржи барем една голема буква';
    }
    if (passwordControl?.hasError('passwordSpecialChar')) {
      return 'Лозинката мора да содржи барем еден специјален карактер';
    }
    return '';
  }

  public getEmailErrorMessage(): string {
    if (this.loginForm?.get('email')?.hasError('required')) {
      return 'Ве молиме внесете e-mail адреса';
    }
    if (this.loginForm?.get('email')?.hasError('email')) {
      return 'Ве молиме внесете валидна e-mail адреса';
    }
    return '';
  }

  onSubmit(): void {
    if (this.loginForm?.valid) {
      console.log('Login Form Submitted:', this.loginForm.value);
    } else {
      console.log('Login Form is not valid!');
    }
  }
}

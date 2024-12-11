import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup | undefined;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  public getPasswordErrorMessage(): string {
    return 'Ве молиме внесете лозинка со повеќе од 6 карактери';
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

  public getPasswordControl(): FormControl | null {
    return this.loginForm?.get('password') as FormControl;
  }

  public getEmailControl(): FormControl | null {
    return this.loginForm?.get('email') as FormControl;
  }

  onSubmit(): void {
    if (this.loginForm?.valid) {
      console.log('Login Form Submitted:', this.loginForm.value);
    } else {
      console.log('Login Form is not valid!');
    }
  }
}

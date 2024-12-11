import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, FormControl } from '@angular/forms';
import { passwordMatchValidator } from '../../../shared/validators/password-match.validator';

@Component({
  selector: 'app-register',
  standalone: false,

  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {
  public registerForm: FormGroup | undefined;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    },
    {
      validator: passwordMatchValidator('password', 'confirmPassword')
    }
  );

    this.registerForm.get('confirmPassword')?.updateValueAndValidity();
  }

  public getPasswordErrorMessage() : string {
    return "Ве молиме внесете лозинка со повеќе од 6 карактери";
  }

  public getConfirmPasswordErrorMessage(){
    if(this.registerForm?.get('confirmPassword')?.hasError('required')){
      return "Ве молиме повторно внесете ја лозинката";
    }
    if(!this.registerForm?.get('confirmPassword')?.hasError('required') && this.registerForm?.get('confirmPassword')?.hasError('mismatch')){
      return "Лозинките не се совпаѓаат";
    }

    return "";
  }

  public getPasswordControl() : FormControl | null {
    return this.registerForm?.get('password') as FormControl;
  }

  public getConfirmPasswordControl() : FormControl | null {
    return this.registerForm?.get('confirmPassword') as FormControl;
  }

  onSubmit(): void {
    if (this.registerForm?.valid) {
      console.log('Form Submitted:', this.registerForm.value);
    } else {
      console.log('Form is not valid!');
    }
  }


}

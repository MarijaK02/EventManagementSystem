import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, FormControl } from '@angular/forms';

@Component({
  selector: 'app-shared-password-input',
  standalone: false,

  templateUrl: './shared-password-input.component.html',
  styleUrl: './shared-password-input.component.scss'
})
export class SharedPasswordInputComponent {

  @Input() control: FormControl | null = null;
  @Input() placeholder: string = '';
  @Input() label: string = '';
  @Input() type: string = 'password';
  @Input() errorMassage: string = '';
  @Output() passwordChange = new EventEmitter<string>();
  showPassword: boolean = false;

  constructor() {}

  ngOnInit(): void {
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
    this.type = this.showPassword ? 'text' : 'password';
  }

  onInputChange(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    this.passwordChange.emit(inputElement.value);
  }
}

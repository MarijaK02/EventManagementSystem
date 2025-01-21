import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventStatus, EventType } from '../../../core/models/event';

@Component({
  selector: 'app-event-create',
  standalone: false,
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.scss']
})
export class EventCreateComponent implements OnInit {
  @Output() eventCreated = new EventEmitter<any>();
  createEventForm!: FormGroup;
  eventTypes = Object.values(EventType);
  eventStatuses = Object.values(EventStatus);

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.createEventForm = this.fb.group({
      name: ['', Validators.required],
      date: ['', Validators.required],
      capacity: [0, [Validators.required, Validators.min(1)]],
      type: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  createEvent(): void {
    if (this.createEventForm.valid) {
      this.eventCreated.emit(this.createEventForm.value);
      this.createEventForm.reset();
    }
  }
}

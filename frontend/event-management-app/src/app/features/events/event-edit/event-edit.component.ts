import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventStatus, EventType } from '../../../core/models/event';

@Component({
  selector: 'app-event-edit',
  standalone: false,
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {
  @Input() event: any;
  @Output() eventUpdated = new EventEmitter<any>();
  editEventForm!: FormGroup;
  eventTypes = Object.values(EventType);
  eventStatuses = Object.values(EventStatus);

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.editEventForm = this.fb.group({
      id: [this.event?.id || null],
      name: [this.event?.name || '', Validators.required],
      date: [this.event?.date || '', Validators.required],
      capacity: [this.event?.capacity || 0, [Validators.required, Validators.min(1)]],
      type: [this.event?.type || '', Validators.required],
      status: [this.event?.status || '', Validators.required]
    });
  }

  updateEvent(): void {
    if (this.editEventForm.valid) {
      this.eventUpdated.emit(this.editEventForm.value);
    }
  }
}

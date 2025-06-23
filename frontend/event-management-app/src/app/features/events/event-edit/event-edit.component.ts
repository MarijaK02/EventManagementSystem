import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { EventStatus, EventType } from '../../../core/models/event';
import { EventService } from '../../../core/services/events/event.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  standalone: false,
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss'],
})
export class EventEditComponent implements OnInit {
  editEventForm!: FormGroup;
  eventTypes = Object.values(EventType);
  eventStatuses = Object.values(EventStatus);
  selectedImageFile: File | null = null;
  errorMessage: string | null = null;
  eventId!: number;
  eventImageUrl: string | null = null;

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.initForm();
    this.loadEvent();
  }

  initForm(): void {
    this.editEventForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      location: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      capacity: [0, [Validators.required, Validators.min(10)]],
      type: ['', Validators.required],
      status: ['', Validators.required],
      organizer: [''],
      startDate: [null, Validators.required],
      startTime: [null, Validators.required],
      endDate: [null, Validators.required],
      endTime: [null, Validators.required],
      // no creator field here
    });
  }

  loadEvent(): void {
    this.eventService.getEventById(this.eventId).subscribe({
      next: (event) => {
        // Parse startTime and endTime into date + time parts for form
        const start = new Date(event.startTime);
        const end = new Date(event.endTime);

         this.eventImageUrl = event.imageUrl;

        this.editEventForm.patchValue({
          name: event.name,
          description: event.description,
          location: event.location,
          price: event.price,
          capacity: event.capacity,
          type: event.type,
          status: event.status,
          organizer: event.organizer,
          startDate: start,
          startTime: start,
          endDate: end,
          endTime: end,
        });
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = 'Failed to load event data';
      }
    });
  }

  onImageSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedImageFile = input.files[0];
    }
  }

  combineDateTime(date: any, time: any): string {
    const combinedDate = new Date(date);
    combinedDate.setHours(time.getHours(), time.getMinutes());
    return combinedDate.toISOString();
  }

  updateEvent(): void {
    if (this.editEventForm.invalid) return;

    const formValue = this.editEventForm.value;
    const formData = new FormData();

    formData.append('name', formValue.name);
    formData.append('description', formValue.description || '');
    formData.append('organizer', formValue.organizer || '');
    formData.append('startTime', this.combineDateTime(formValue.startDate, formValue.startTime));
    formData.append('endTime', this.combineDateTime(formValue.endDate, formValue.endTime));
    formData.append('location', formValue.location);
    formData.append('price', formValue.price.toString());
    formData.append('capacity', formValue.capacity.toString());
    formData.append('type', formValue.type);
    formData.append('status', formValue.status);

    if (this.selectedImageFile) {
      formData.append('image', this.selectedImageFile, this.selectedImageFile.name);
    }

    this.eventService.updateEvent(this.eventId, formData).subscribe({
      next: (updatedEvent) => {
        this.errorMessage = null;
        this.router.navigate(['/events']);
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = err.error || 'Failed to update event';
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/events']);
  }
}

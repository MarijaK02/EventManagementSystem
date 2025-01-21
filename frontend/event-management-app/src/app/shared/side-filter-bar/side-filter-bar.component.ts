import { Component, EventEmitter, Output, Input, OnInit } from '@angular/core';
import { EventStatus, EventType } from '../../core/models/event';

@Component({
  selector: 'app-side-filter-bar',
  standalone: false,
  templateUrl: './side-filter-bar.component.html',
  styleUrls: ['./side-filter-bar.component.scss']
})
export class SideFilterBarComponent implements OnInit {
  @Input() eventTypes: EventType[] = []; // The available event types
  @Input() selectedStatus: EventStatus | null = null;  // Selected status filter
  @Input() selectedEventTypes: { [key in EventType]: boolean } = {} as any;  // Selected event types filter
  @Input() selectedDate: Date | null = null; // Selected date filter
  @Input() selectedCapacity: number | null = null; // Selected capacity filter
  @Input() selectedTime: string | null = null; // Selected time filter (newly added)
  @Input() dateDisabled: boolean = false;

  @Output() filtersChanged = new EventEmitter<any>(); // Emit when filters change

  constructor() {}

  ngOnInit(): void {
    // Ensure the selectedEventTypes are initialized
    this.eventTypes.forEach(type => {
      if (!this.selectedEventTypes[type]) {
        this.selectedEventTypes[type] = false;
      }
    });
  }

  get allEventTypesSelected(): boolean {
    return this.eventTypes.every((type) => this.selectedEventTypes[type]);
  }

  // Check if some event types are selected (for indeterminate state)
  get someEventTypesSelected(): boolean {
    return (
      this.eventTypes.some((type) => this.selectedEventTypes[type]) &&
      !this.allEventTypesSelected
    );
  }


  // Method to emit filter changes
  applyFilters(): void {
    this.filtersChanged.emit({
      selectedStatus: this.selectedStatus,
      selectedEventTypes: this.selectedEventTypes,
      selectedDate: this.selectedDate,
      selectedCapacity: this.selectedCapacity
    });
  }

  // Method to toggle the selection of all event types
  toggleAllEventTypes(checked: boolean): void {
    this.eventTypes.forEach((type) => {
      this.selectedEventTypes[type] = checked;
    });
    this.applyFilters();
  }

  // Method to toggle an individual event type
  toggleEventType(type: EventType, checked: boolean): void {
    this.selectedEventTypes[type] = checked;
    this.applyFilters();
  }
}

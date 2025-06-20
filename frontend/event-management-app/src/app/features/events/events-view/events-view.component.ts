import { Component, OnInit } from '@angular/core';
import { EventData } from '../../../core/data_holder/events-data';
import { Event, EventStatus, EventType } from '../../../core/models/event';
import { Router } from '@angular/router';

@Component({
  selector: 'app-events-view',
  standalone: false,
  templateUrl: './events-view.component.html',
  styleUrls: ['./events-view.component.scss']
})
export class EventsViewComponent implements OnInit {
  todayEvents = EventData.todayEvents;
  thisWeekEvents = EventData.thisWeekEvents;
  thisMonthEvents = EventData.thisMonthEvents;
  filteredEvents = [...this.todayEvents];

  eventTypes = Object.values(EventType); // All available event types

  selectedStatus: EventStatus | null = null;
  selectedDate: Date | null = null;
  selectedTime: string | null = null;
  selectedCapacity: number | null = null;
  selectedEventTypes: { [key in EventType]: boolean } = {} as any;

  showCreateForm = false;
  showEditForm = false;
  selectedEvent: any = null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Initialize selected event types as unchecked
    this.eventTypes.forEach((type) => {
      this.selectedEventTypes[type] = false;
    });
  }

  // Apply filters after the filter component emits changes
  applyFilters(filters: any): void {
    this.selectedStatus = filters.selectedStatus;
    this.selectedEventTypes = filters.selectedEventTypes;
    this.selectedDate = filters.selectedDate;
    this.selectedCapacity = filters.selectedCapacity;

    this.filteredEvents = this.todayEvents.filter((event) => {
      // Matching the event status using EventStatus enum
      const matchesStatus =
        !this.selectedStatus || this.selectedStatus === event.status; // Directly comparing the status field

      // Matching the selected date
      const matchesDate =
        !this.selectedDate || new Date(event.date).toDateString() === this.selectedDate.toDateString();

      // Matching the selected capacity
      const matchesCapacity =
        !this.selectedCapacity || event.capacity >= this.selectedCapacity;

      // Matching the selected event types
      const matchesEventTypes =
        !Object.values(this.selectedEventTypes).some((selected) => selected) || // No type selected
        this.selectedEventTypes[event.type]; // Matches selected type

      return matchesStatus && matchesDate && matchesCapacity && matchesEventTypes;
    });
  }

  // Reset filters
  resetFilters(): void {
    this.selectedStatus = null;
    this.selectedDate = null;
    this.selectedCapacity = null;
    this.eventTypes.forEach((type) => {
      this.selectedEventTypes[type] = false;
    });
    this.filteredEvents = [...this.todayEvents];
  }

  handleEditEvent(event: any): void {
    this.selectedEvent = event;
    this.showCreateForm = false;
    this.showEditForm = true;
  }

  openCreateEvent(): void {
    this.showCreateForm = true;
    this.showEditForm = false;
  }

  openEditEvent(event: any): void {
    this.selectedEvent = event;
    this.showEditForm = true;
    this.showCreateForm = false;
  }

  handleEventCreated(newEvent: any): void {
    this.todayEvents.push(newEvent);
    this.showCreateForm = false;
  }

  handleEventUpdated(updatedEvent: any): void {
    const index = this.todayEvents.findIndex(event => event === updatedEvent.id);
    if (index !== -1) {
      this.todayEvents[index] = updatedEvent;
    }
    this.showEditForm = false;
  }

  openEventDetails(event: Event) : void {
    console.log("from viewss")
    console.log(event.id)
    this.router.navigate(['/events', event.id]);
  }
}

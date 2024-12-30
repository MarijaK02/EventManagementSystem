import { Component, Input, signal } from '@angular/core';
import { Event, EventStatus } from '../../../core/models/event';

@Component({
  selector: 'app-event-card',
  standalone: false,
  
  templateUrl: './event-card.component.html',
  styleUrl: './event-card.component.scss'
})
export class EventCardComponent {
  @Input() event: Event | null = null; // Event data
  eventStatus = EventStatus;
}

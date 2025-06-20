import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Event } from '../../../core/models/event';
import { EventData } from '../../../core/data_holder/events-data';

@Component({
  selector: 'app-event-details',
  standalone: false,
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {
  event: Event | undefined;
  userResponse: 'going' | 'interested' | 'declined' | null = null;
  newComment: string = '';
  comments: { user: string, text: string }[] = [];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadEvent(eventId);
  }

  loadEvent(id: number): void {
    // Replace this logic with a real data fetch if needed
    const allEvents = [
      ...EventData.todayEvents,
      ...EventData.thisWeekEvents,
      ...EventData.thisMonthEvents
    ];
    this.event = allEvents.find(e => e.id === id);
  }

  respond(response: 'going' | 'interested' | 'declined') {
  this.userResponse = response;
  // Optionally send to backend
}

addComment() {
  if (this.newComment.trim()) {
    this.comments.push({
      user: 'You',
      text: this.newComment.trim()
    });
    this.newComment = '';
  }
}
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../../models/event';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private baseUrl = 'http://localhost:8080/api/events'; // update if needed

  constructor(private http: HttpClient) {}

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.baseUrl);
  }

  getEventById(id: number): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}/${id}`);
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.baseUrl, event);
  }

  updateEvent(id: number, event: Event): Observable<Event> {
    return this.http.put<Event>(`${this.baseUrl}/${id}`, event);
  }

  deleteEvent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  markGoing(id: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${id}/going`, {});
  }

  markInterested(id: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${id}/interested`, {});
  }

  markDeclined(id: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${id}/declined`, {});
  }
}

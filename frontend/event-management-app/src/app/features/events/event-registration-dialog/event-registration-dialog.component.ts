import { Component } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import { EventService } from '../../../core/services/events/event.service';
import { UserService } from '../../../core/services/users/user.service';
import { User, UserRole } from '../../../core/models/user';

@Component({
  selector: 'app-event-registration-dialog',
  standalone: false,

  templateUrl: './event-registration-dialog.component.html',
  styleUrl: './event-registration-dialog.component.scss'
})
export class EventRegistrationDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { eventId: number },
    private userService: UserService,
    private eventService: EventService,
    public dialogRef: MatDialogRef<EventRegistrationDialogComponent>
  ) {}

  selectedUsers: User[] = [];
  allUsers: User[] = [];
  searchText = '';
  currentUserEmail: string = localStorage.getItem('email') ?? "Default";

  ngOnInit() {
    this.userService.getAllUsers().subscribe(users => {
      this.allUsers = users.filter(u => u.role === UserRole.STUDENT);
    });
  }

  filterUsers() {
    return this.allUsers.filter(user => user.username.includes(this.searchText));
  }

  toggleUserSelection(user: User) {
    const idx = this.selectedUsers.indexOf(user);
    if (idx > -1) {
      this.selectedUsers.splice(idx, 1);
    } else {
      this.selectedUsers.push(user);
    }
  }

  register() {
    const userIds = this.selectedUsers.map(u => u.id);
    console.log(this.data.eventId, userIds)
    this.eventService.register(this.data.eventId, userIds).subscribe({
      next: () => this.dialogRef.close(userIds),
      error: err => console.error('Registration failed', err),
    });
  }
}

export enum EventType {
  Sports = 'Sports',
  Party = 'Party',
  Conference = 'Conference',
  Workshop = 'Workshop',
  Cultural = 'Cultural',
  Academic = 'Academic',
}

export enum EventStatus {
  Available = 'Available',
  Booked = 'Booked',
  Cancelled = 'Cancelled'
}

export interface Event {
  id: number,
  name: string,
  description: string,
  picture: string,
  start: string,
  end: string,
  room: string,
  capacity: number,
  going: number,
  interested: number,
  declined: number,
  status: EventStatus,
  price: number,
  type: EventType
}

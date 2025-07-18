export enum UserRole{
  ADMIN = 'ADMIN',
  STUDENT = 'STUDENT',
  CREATOR = 'CREATOR'
}

export interface User{
  id: number,
  name: string,
  surname: string,
  username: string,
  email: string,
  password: string,
  role: UserRole
}

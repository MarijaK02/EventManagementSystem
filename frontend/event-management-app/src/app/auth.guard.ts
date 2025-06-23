import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
 import { jwtDecode } from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    // Check if the token is stored in localStorage
    const token = localStorage.getItem('token');

    if (token) {
      try {
        // Decode the token to check if it is expired
        const decodedToken: any = jwtDecode(token);

        // Check if the token is expired
        if (decodedToken.exp * 1000 < Date.now()) {
          // Token is expired, so log the user out
          this.router.navigate(['/login']);
          return false;
        }

        // Token is valid, allow access to the route
        return true;
      } catch (error) {
        console.error('Error decoding token:', error);
        // If token decoding fails, redirect to login
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      // If no token is found, redirect to login
      this.router.navigate(['/login']);
      return false;
    }
  }
}

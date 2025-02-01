import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminGuardGuard implements CanActivate {
  constructor (private readonly router : Router, private authService: AuthService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const userRole = this.authService.getUserRole();
      if (userRole === 'ADMIN') {
        return true;
      } else {
        if (userRole === 'USER') return this.router.navigate(['/']);
        if (userRole !== "USER") return this.router.navigate(['/login']);
        return false;
      }
  }
  
}

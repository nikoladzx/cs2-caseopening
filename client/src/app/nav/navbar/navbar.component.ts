import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AuthService } from 'src/app/auth/auth-service/auth.service';
import { UserBasic } from 'src/app/models/UserBasic';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user$: Observable<UserBasic | null> = of(null);

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
    console.log(this.user$ + "ovo je prvo")
    this.user$ = this.authService.user$;
    console.log(this.user$ + "ovo je drugo");
  }

  logout()
  {
    this.authService.logout();
  }



}

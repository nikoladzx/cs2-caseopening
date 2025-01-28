import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth-service/auth.service';
import { UserBasic } from './models/UserBasic';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private authService: AuthService){}

  ngOnInit(): void {
    const user: UserBasic = JSON.parse(localStorage.getItem('user')!);
    this.authService.setCurrentUser(user);
  }
  title = 'client';
}

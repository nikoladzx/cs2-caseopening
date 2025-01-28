import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { LoginRequest } from 'src/app/models/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user :LoginRequest = {
      email: '',
      password: '',
    };

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }
  login(loginRequest: LoginRequest) {
    this.authService.login(loginRequest).subscribe(
    {
      next: (res) => console.log(res),
      error: err => console.log(err)
    })
    }
  

}

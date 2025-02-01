import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { LoginRequest } from 'src/app/models/DTOs/LoginRequest';
import { UserService } from 'src/app/services/user/user.service';
import { Router } from '@angular/router';

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
  
  constructor(private authService:AuthService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }
  login(loginRequest: LoginRequest) {
    this.authService.login(loginRequest).subscribe(
    {
      next: (res) => {console.log(res)
        this.userService.triggerProfileUpdate();
        if (res.userRole === 'ADMIN') {
          this.router.navigate(['/admin']);
        }
        if (res.userRole === 'USER') {
        this.router.navigate(['/']);
      }},
      error: err => console.log(err)
    })
    }
  

}

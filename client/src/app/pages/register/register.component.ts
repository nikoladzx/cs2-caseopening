import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { RegisterRequest } from 'src/app/models/DTOs/RegisterRequest';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user :RegisterRequest = {
    name: '',
    email: '',
    password: '',
  };
  constructor(private authService : AuthService) { }

  ngOnInit(): void {
  }

  register(registerRequest: RegisterRequest) {
    this.authService.register(registerRequest).subscribe(
    {
      next: (res) => console.log(res),
      error: err => console.log(err)
    })
  }

}

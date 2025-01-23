import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterRequest } from 'src/app/models/registerRequest';
import { LoginRequest } from 'src/app/models/LoginRequest';


const BASE_URL = "http://localhost:8080/api/"


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private http: HttpClient) { }

  register(registerRequest: RegisterRequest) : Observable<any> {
    return this.http.post(BASE_URL + "auth/signup", registerRequest);
  }

  login(loginRequest: LoginRequest) : Observable<any>
  {
    return this.http.post(BASE_URL + "auth/login", loginRequest);
  }
}

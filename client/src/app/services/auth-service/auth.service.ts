import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { RegisterRequest } from 'src/app/models/DTOs/RegisterRequest';
import { LoginRequest } from 'src/app/models/DTOs/LoginRequest';
import { UserBasic } from 'src/app/models/UserBasic';


const BASE_URL = "http://localhost:8080/api/"


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private user: BehaviorSubject<UserBasic | null> =
    new BehaviorSubject<UserBasic | null>(null);

  user$=this.user.asObservable();


  constructor(private http: HttpClient) { }

  register(registerRequest: RegisterRequest) : Observable<any> {
    return this.http.post(BASE_URL + "auth/signup", registerRequest);
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<any>(`${BASE_URL}auth/login`, loginRequest)
      .pipe(
        tap((response: any) => {
          if (response) {
            localStorage.setItem('user', JSON.stringify(response));
            this.user.next(response);
          }
        })
      );
  }
  logout() {
    localStorage.removeItem('user');
    this.user.next(null);

  }

  setCurrentUser(user: UserBasic){
    this.user.next(user);
  }
  
  getUserId()
  {
    let userId;
    const user = localStorage.getItem('user');
    if (user)
    {
      userId = JSON.parse(user).userId;
    }

    if(userId)
      return userId;
    return null;
  }

  getUserRole()
  {
    let userRole;
    const user = localStorage.getItem('user');
    if (user)
    {
      userRole = JSON.parse(user).userRole;
    }

    if(userRole)
      return userRole;
    return null;
  }

  getUserToken()
  {
    let token;
    const user = localStorage.getItem('user');
    if (user)
    {
      token = JSON.parse(user).jwt;
    }

    if(token)
      return token;
    return null;
  }
}

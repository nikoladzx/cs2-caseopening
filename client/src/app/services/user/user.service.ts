import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { AuthService } from '../auth-service/auth.service';
import { CoinflipRequest } from 'src/app/models/CoinflipRequest';
import { CrashRequest } from 'src/app/models/CrashRequest';
import { RouletteRequest } from 'src/app/models/RouletteRequest';
import { SlotRequest } from 'src/app/models/SlotRequest';

const BASE_URL = "http://localhost:8080/api/user/";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private profileUpdatedSubject = new Subject<void>();

  // Observable for profile updates
  get profileUpdated$(): Observable<void> {
    return this.profileUpdatedSubject.asObservable();
  }

constructor(private http: HttpClient, private authService : AuthService) {
    
   }

 
   // Method to trigger profile update
   triggerProfileUpdate(): void {
     this.profileUpdatedSubject.next();
   }

   unbox(caseId:number, userId: number) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({ 'Authorization' : `Bearer ${token}`});
    return this.http.get(BASE_URL + `unbox/${userId}/${caseId}`, {headers});
   } 

   getSkins(userId:number) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({ 'Authorization' : `Bearer ${token}`});
    return this.http.get(BASE_URL+`getSkins/${userId}`, {headers});
   }

   sellSkin(userId:number, skinId:number) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({ 'Authorization' : `Bearer ${token}`});
    return this.http.delete(BASE_URL+`sellSkin/${userId}/${skinId}`, {headers});
   }

   addBalance(userId:number, balance:number) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({ 'Authorization' : `Bearer ${token}`});
    return this.http.post(BASE_URL+`addBalance/${userId}/${balance}`, {}, {headers});
   }

   getProfile(userId: number) :Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({ 'Authorization' : `Bearer ${token}`});
    return this.http.get(BASE_URL+`getProfile/${userId}`, {headers});
   }
}

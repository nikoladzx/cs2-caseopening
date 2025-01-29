import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth-service/auth.service';
import { CoinflipRequest } from 'src/app/models/CoinflipRequest';
import { CrashRequest } from 'src/app/models/CrashRequest';
import { RouletteRequest } from 'src/app/models/RouletteRequest';

const BASE_URL = "http://localhost:8080/api/user/";


@Injectable({
  providedIn: 'root'
})
export class UserService {

constructor(private http: HttpClient, private authService : AuthService) {
    
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
   
   coinflip(coinflipRequest : CoinflipRequest) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
    return this.http.post(BASE_URL+ "coinflip", coinflipRequest, {headers});
   }

   crash(crashRequest : CrashRequest) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
    return this.http.post(BASE_URL+ "crash", crashRequest, {headers});
   }
  
   roulette(rouletteRequest : RouletteRequest) : Observable<any>
   {
    const token = this.authService.getUserToken();
    const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
    return this.http.post(BASE_URL+ "roulette", rouletteRequest, {headers});
   }
  
  
}

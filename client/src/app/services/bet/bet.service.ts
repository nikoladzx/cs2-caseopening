import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SlotRequest } from 'src/app/models/DTOs/SlotRequest';
import { AuthService } from '../auth-service/auth.service';
import { RouletteRequest } from 'src/app/models/DTOs/RouletteRequest';
import { CrashRequest } from 'src/app/models/DTOs/CrashRequest';
import { CoinflipRequest } from 'src/app/models/DTOs/CoinflipRequest';

const BASE_URL = "http://localhost:8080/api/bet/";

@Injectable({
  providedIn: 'root'
})

export class BetService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  slot(slotRequest: SlotRequest) : Observable<any>
  {
   const token = this.authService.getUserToken();
   const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
   return this.http.post(BASE_URL + "slot", slotRequest, {headers})
  }

  getSlot(slotId:number) : Observable<any>
  {
   const token = this.authService.getUserToken();
   const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
   return this.http.get(BASE_URL + `getSlot/${slotId}`, {headers})
  }

  
  getSlots() : Observable<any>
  {
   const token = this.authService.getUserToken();
   const headers = new HttpHeaders({'Authorization' : `Bearer ${token}`});
   return this.http.get(BASE_URL + `getSlots`, {headers})
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

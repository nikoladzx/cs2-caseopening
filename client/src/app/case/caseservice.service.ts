import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = "http://localhost:8080/api/case/";

@Injectable({
  providedIn: 'root'
})


export class CaseserviceService {

  constructor(private http: HttpClient) {
    
   }

   getCaseById(caseId: number) : Observable<any>
   {
    return this.http.get(BASE_URL+"getCase/" + caseId);
   }

   getCases() : Observable<any>{
    return this.http.get(BASE_URL + "getCases");
   }

   unbox(caseId:number, userId: number) : Observable<any>
   {
    return this.http.get(BASE_URL + `unbox/${userId}/${caseId}`);
   }
}

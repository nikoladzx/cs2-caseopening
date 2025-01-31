import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = "http://localhost:8080/images/";

@Injectable({
  providedIn: 'root'
})
export class ImgService {

  constructor(private http: HttpClient) { }

  getImage(imagePath: string): Observable<any> {
    return this.http.get(BASE_URL + imagePath, { responseType: 'blob' });
  }

  uploadImage(file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');

    return this.http.post<string>(BASE_URL + "upload", formData, { headers, responseType: 'text' as 'json' });
  }
}

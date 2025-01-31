import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth-service/auth.service';
import { Item } from 'src/app/models/Item';
import { Case } from 'src/app/models/Case';
import SlotItem from 'src/app/models/SlotItem';
import Slot from 'src/app/models/Slot';

const BASE_URL = "http://localhost:8080/api/admin/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  addSlot(slot: Slot) {
    const token = this.authService.getUserToken();
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(BASE_URL + "addSlot", slot, { headers });
  }

  addSlotItem(slotItem: SlotItem, slotId: number) {
    const token = this.authService.getUserToken();
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(BASE_URL + `addSlotItem/${slotId}`, slotItem, { headers });
  }

  addCase(newCase: Case) {
    const token = this.authService.getUserToken();
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(BASE_URL + `addCase`, newCase, { headers });
  }

  addCaseItem(caseItem: Item) {
    const token = this.authService.getUserToken();
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(BASE_URL + "addItem", caseItem, { headers });}
}

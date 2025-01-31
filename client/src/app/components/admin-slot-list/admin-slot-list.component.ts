import { Component, OnInit } from '@angular/core';
import Slot from 'src/app/models/Slot';
import { BetService } from 'src/app/services/bet/bet.service';

@Component({
  selector: 'app-admin-slot-list',
  templateUrl: './admin-slot-list.component.html',
  styleUrls: ['./admin-slot-list.component.css']
})
export class AdminSlotListComponent implements OnInit {

 slotList : Slot[] = [];
 
   constructor(private betService: BetService) { }
 
   ngOnInit(): void {
     this.getSlots();
 
   }
 
   getSlots(): void {
     this.betService.getSlots().subscribe({
       next: response => {
         console.log(response);
         this.slotList = response;
       }
     });
   }
}

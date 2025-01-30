import { Component, OnInit } from '@angular/core';
import Slot from 'src/app/models/Slot';
import { BetService } from 'src/app/services/bet/bet.service';

@Component({
  selector: 'app-slotpage',
  templateUrl: './slotpage.component.html',
  styleUrls: ['./slotpage.component.css']
})
export class SlotpageComponent implements OnInit {
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

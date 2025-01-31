import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import SlotItem from 'src/app/models/SlotItem';
import { SlotRequest } from 'src/app/models/SlotRequest';
import { SlotResponse } from 'src/app/models/SlotResponse';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { BetService } from 'src/app/services/bet/bet.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-slot',
  templateUrl: './slot.component.html',
  styleUrls: ['./slot.component.css']
})
export class SlotComponent implements OnInit {
  slotId: string | null = null;
  slotItems: SlotItem[] = [];
  inputBet: number = 0;
  slotResponse: SlotResponse | null = null;
  displayItems: SlotItem[] = [];

  constructor(private betService: BetService, private route: ActivatedRoute, private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.slotId = this.route.snapshot.paramMap.get('id');
    this.getSlot();
    this.displayItems = this.getRandomItems();
  }

  getSlot(): void {
    this.betService.getSlot(parseInt(this.slotId!)).subscribe({
      next: response => {
        console.log(response);
        this.slotItems = response;
        this.displayItems = this.slotResponse ? this.slotResponse.items : this.getRandomItems();
      }
    });
  }

  playSlot() {
    const userId = this.authService.getUserId();
    const slotRequest: SlotRequest = {
      userId: userId,
      slotId: parseInt(this.slotId!),
      bet: this.inputBet
    }
    this.betService.slot(slotRequest).subscribe({
      next: response => {
        console.log(response);
        this.slotResponse = response;
        this.displayItems = this.slotResponse!.items;
        this.userService.triggerProfileUpdate();
      }
    });
  }

  getRandomItems(): SlotItem[] {
    const randomItems: SlotItem[] = [];
    for (let i = 0; i < 3; i++) {
      randomItems.push({
        id: Math.floor(Math.random() * 1000),
        imagepath: '',
        multiplier: Math.floor(Math.random() * 10),
      });
    }
    return randomItems;
  }
}

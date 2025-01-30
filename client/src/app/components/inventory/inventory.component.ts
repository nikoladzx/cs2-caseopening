import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Skin } from 'src/app/models/Skin';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  
  skins: Skin[] | [] = [];

  constructor(private userService: UserService, private authService : AuthService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getSkins();

  }
  getWear(condition: number)
  {
    if (condition< 0.07)
      return "(Factory-New)";
    if (condition<0.15)
      return "(Minimal-Wear)";
    if (condition<0.38)
      return "(Field-Tested)";
    if (condition<0.45)
      return "(Well-Worn)"
    return "(Battle-Scarred)"
  }

  getSkins()
  {
    const userId = this.authService.getUserId();
    if (userId === null)
      return;
    this.userService.getSkins(userId).subscribe({
      next: response => this.skins=response,
      error: error => console.log(error)
    })
  }

  sellSkin(skinId : any)
  {
    console.log("ovo je skinid" + skinId)
    const userId = this.authService.getUserId();
    if (userId === null)
      return;
    this.userService.sellSkin(userId, skinId).subscribe({
      next: () => this.userService.triggerProfileUpdate(),
      error: error => console.log(error),
      complete: () => this.getSkins()

    })
  }

}

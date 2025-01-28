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

}

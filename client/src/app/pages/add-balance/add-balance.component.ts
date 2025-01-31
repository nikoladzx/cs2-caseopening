import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-add-balance',
  templateUrl: './add-balance.component.html',
  styleUrls: ['./add-balance.component.css']
})
export class AddBalanceComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  addBalance(amount: number): void {
    this.userService.addBalance(amount).subscribe({
      next: () => {
        this.userService.triggerProfileUpdate();
      }
    })
  }

}

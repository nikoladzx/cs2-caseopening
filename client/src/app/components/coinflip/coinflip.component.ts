import { Component, OnInit } from '@angular/core';
import { CoinflipRequest } from 'src/app/models/DTOs/CoinflipRequest';
import { CoinflipResponse } from 'src/app/models/DTOs/CoinflipResponse';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { BetService } from 'src/app/services/bet/bet.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-coinflip',
  templateUrl: './coinflip.component.html',
  styleUrls: ['./coinflip.component.css']
})
export class CoinflipComponent implements OnInit {

  coinflipResponse : CoinflipResponse | null = null;
  
  bet: number = 0;
  heads: boolean = false;

  constructor(private userService: UserService, private betService: BetService, private authService: AuthService) { }

  ngOnInit(): void {}

  placeBet(isHeads: boolean): void {
    const userId = this.authService.getUserId();
    this.heads = isHeads;
    const coinflipRequest: CoinflipRequest = {
      userId: userId,
      bet: this.bet,
      heads: this.heads
    };
    console.log(coinflipRequest.userId)
    this.betService.coinflip(coinflipRequest).subscribe({
      next: response => {
        this.coinflipResponse=response;
        this.userService.triggerProfileUpdate();
      },
      error : error => console.log(error)
    });
  }
}

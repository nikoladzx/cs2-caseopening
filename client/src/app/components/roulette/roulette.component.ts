import { Component, OnInit } from '@angular/core';
import { RouletteRequest } from 'src/app/models/RouletteRequest';
import { RouletteResponse } from 'src/app/models/RouletteResponse';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { BetService } from 'src/app/services/bet/bet.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-roulette',
  templateUrl: './roulette.component.html',
  styleUrls: ['./roulette.component.css']
})
export class RouletteComponent implements OnInit {
  betAmount: number = 0;
  betOption: string = "";
  rouletteResponse: RouletteResponse | null = null;

  constructor(private betService: BetService, private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.betAmount = 0;
    this.betOption = 'red';
  }

  getColor(): { [key: string]: string } {
    return { 'color': this.rouletteResponse ? this.rouletteResponse.drawnColor : 'gray' };
  }

  placeBet(): void {
    const userId = this.authService.getUserId();
    if (userId !==null)
    {

    this.betService.roulette({
      bet: this.betAmount,
      userId:userId,
      betOption: this.betOption
    }).subscribe(response => {
      this.rouletteResponse = response;
      this.userService.triggerProfileUpdate();
    }, error => {
      console.error(error);
    });}
  }
}

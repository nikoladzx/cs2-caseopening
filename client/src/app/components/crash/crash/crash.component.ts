import { Component, OnInit } from '@angular/core';
import { CrashRequest } from 'src/app/models/CrashRequest';
import { CrashResponse } from 'src/app/models/CrashResponse';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { BetService } from 'src/app/services/bet/bet.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-crash',
  templateUrl: './crash.component.html',
  styleUrls: ['./crash.component.css']
})
export class CrashComponent implements OnInit {

  crashRequest: CrashRequest | null = null;
  multiplier: number = 1;
  bet: number = 0;
  crashResponse: CrashResponse | null = null;
  

  constructor(private userService: UserService, private betService: BetService, private authService: AuthService) { }

  ngOnInit(): void {
  
  }

  crash(){
    const userId = this.authService.getUserId();
    this.crashRequest = {
      bet: this.bet,
      userId: userId,
      multiplier: this.multiplier

    }

    this.betService.crash(this.crashRequest).subscribe({
      next: response => {this.crashResponse = response;
        this.userService.triggerProfileUpdate();

      },
      error: error => console.log(error)
    })
  }

}

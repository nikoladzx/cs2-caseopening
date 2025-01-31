import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { UserBasic } from 'src/app/models/UserBasic';
import { UserService } from 'src/app/services/user/user.service';
import { Profile } from 'src/app/models/Profile';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user$: Observable<UserBasic | null> = of(null);
  profile: Profile | null = null;
  user: UserBasic | null = null;

  constructor(public authService: AuthService, private userService: UserService,  private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    console.log(this.user$ + "ovo je prvo")
    this.user$ = this.authService.user$;
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.getProfile();
    console.log(this.user$ + "ovo je drugo");
    this.userService.profileUpdated$.subscribe(()=> 
    this.getProfile());
  }

  logout()
  {
    this.authService.logout();
  }

  getProfile()
  {
    const userId = this.authService.getUserId();
    this.userService.getProfile(userId).subscribe({
      next: response => {this.profile=response;
        this.cdr.detectChanges();
      },
      error: error => console.log(error)
    })
  }


}

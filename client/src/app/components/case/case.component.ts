import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Case } from 'src/app/models/Case';
import { CaseserviceService } from '../../services/case/caseservice.service';
import { UserBasic } from 'src/app/models/UserBasic';
import { Item } from 'src/app/models/Item';
import { Skin } from 'src/app/models/Skin';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-case',
  templateUrl: './case.component.html',
  styleUrls: ['./case.component.css']
})
export class CaseComponent implements OnInit {

  caseId: string | null = null;
  case: Case | null = null;
  skin: Skin | null = null;
  constructor(private route: ActivatedRoute, private caseService: CaseserviceService, private userService: UserService ) { }

  ngOnInit(): void {
    this.caseId= this.route.snapshot.paramMap.get('id');
    if (this.caseId)
      this.getCase(this.caseId);
    if (localStorage.getItem('user'))
      console.log(JSON.parse(localStorage.getItem('user')!).userId);

  }
  roundToTwoDecimals(num: number): string {
    return num.toFixed(2);
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
  okay()
  {
    this.skin=null;
  }


  getCase(caseId : string)
  {

    this.caseService.getCaseById(parseInt(caseId)).subscribe({
      next: response => this.case=response,
      error: error => console.log(error)
    })

  }
  unbox(caseId : number) 
  {
    const userId = JSON.parse(localStorage.getItem('user')!).userId;
    if (userId)
    {
      this.userService.unbox(caseId, userId).subscribe(
        {
          next: response => {this.skin = response
            console.log(response);
            console.log(this.skin);
          },
          error: error => console.log(error)

        }
      )
    }
  }

}

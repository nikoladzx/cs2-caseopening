import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs';
import { Case } from 'src/app/models/Case';
import { CaseserviceService } from 'src/app/services/case/caseservice.service';

@Component({
  selector: 'app-admin-case-list',
  templateUrl: './admin-case-list.component.html',
  styleUrls: ['./admin-case-list.component.css']
})
export class AdminCaseListComponent implements OnInit {

  caseList : Case[] | [] = [];
    constructor(private caseService: CaseserviceService) { }
  
    ngOnInit(): void {
      this.getCases();
      timeout(500);
      console.log(this.caseList);
    }
  
    getCases()
    {
      this.caseService.getCases().subscribe({
        next: response => {this.caseList=response
          console.log(this.caseList)
        },
        error: error => console.log(error)
      })
    }
}

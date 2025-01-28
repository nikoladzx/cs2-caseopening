import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs';
import { CaseserviceService } from 'src/app/services/case/caseservice.service';
import { Case } from 'src/app/models/Case';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

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

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCaseListComponent } from './admin-case-list.component';

describe('AdminCaseListComponent', () => {
  let component: AdminCaseListComponent;
  let fixture: ComponentFixture<AdminCaseListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCaseListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCaseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

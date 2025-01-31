import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCaseComponent } from './admin-case.component';

describe('AdminCaseComponent', () => {
  let component: AdminCaseComponent;
  let fixture: ComponentFixture<AdminCaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCaseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

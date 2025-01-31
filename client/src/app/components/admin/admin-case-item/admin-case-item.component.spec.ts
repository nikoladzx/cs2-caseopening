import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCaseItemComponent } from './admin-case-item.component';

describe('AdminCaseItemComponent', () => {
  let component: AdminCaseItemComponent;
  let fixture: ComponentFixture<AdminCaseItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCaseItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCaseItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

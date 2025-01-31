import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSlotComponent } from './admin-slot.component';

describe('AdminSlotComponent', () => {
  let component: AdminSlotComponent;
  let fixture: ComponentFixture<AdminSlotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminSlotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
